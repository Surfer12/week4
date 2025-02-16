#!/bin/bash

# Component Creation Helper Script
# Creates template .asc and .asy files according to professor's standards

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

if [ "$#" -ne 1 ]; then
    echo -e "${RED}Usage: $0 COMPONENT_NAME${NC}"
    exit 1
fi

COMPONENT=$1
COMPONENT_UPPER=$(echo "$COMPONENT" | tr '[:lower:]' '[:upper:]')

# Ensure we're using uppercase
if [ "$COMPONENT" != "$COMPONENT_UPPER" ]; then
    echo -e "${RED}WARNING: Converting component name to uppercase: $COMPONENT_UPPER${NC}"
fi

# Create LogicLibrary directory if it doesn't exist
mkdir -p LogicLibrary

# Create .asc file
cat > "LogicLibrary/${COMPONENT_UPPER}.asc" << EOL
Version 4
SHEET 1 880 680
WIRE 80 112 48 112
WIRE 208 128 176 128
FLAG 48 112 A
IOPIN 48 112 In
FLAG 208 128 Out
IOPIN 208 128 Out
SYMBOL NAND 128 128 R0
SYMATTR InstName X1
TEXT -384 288 Left 2 ;${COMPONENT_UPPER}
EOL

# Create .asy file
cat > "LogicLibrary/${COMPONENT_UPPER}.asy" << EOL
Version 4
SymbolType BLOCK
RECTANGLE Normal -64 -32 64 32
PIN -64 0 LEFT 8
PINATTR PinName A
PINATTR SpiceOrder 1
PIN 64 0 RIGHT 8
PINATTR PinName Out
PINATTR SpiceOrder 2
TEXT 0 -40 Center 2 ${COMPONENT_UPPER}
EOL

echo -e "${GREEN}Created ${COMPONENT_UPPER}.asc and ${COMPONENT_UPPER}.asy in LogicLibrary/${NC}"

# Run validation on newly created files
if [ -f "./validate_circuit_files.sh" ]; then
    echo -e "\n${GREEN}Validating new files...${NC}"
    ./validate_circuit_files.sh "LogicLibrary/${COMPONENT_UPPER}.*"
fi