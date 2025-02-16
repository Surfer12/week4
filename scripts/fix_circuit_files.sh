#!/bin/bash

# Fix common circuit file issues according to professor's standards

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

fix_asc() {
    local file=$1
    local temp_file=$(mktemp)
    local content
    
    echo -e "${YELLOW}Fixing $file...${NC}"
    
    # Read entire file
    content=$(cat "$file")
    
    # Fix version header
    content=$(echo "$content" | sed '1s/^Version.*/Version 4/')
    if ! echo "$content" | grep -q "^Version 4"; then
        content="Version 4\n$content"
    fi
    
    # Fix SHEET definition
    content=$(echo "$content" | sed 's/^SHEET.*/SHEET 1 880 680/')
    if ! echo "$content" | grep -q "^SHEET"; then
        content="$content\nSHEET 1 880 680"
    fi
    
    # Match FLAGS with IOPINs
    while IFS= read -r line; do
        if [[ "$line" =~ ^FLAG[[:space:]]+([^[:space:]]+)[[:space:]]+([^[:space:]]+)[[:space:]]+(.*) ]]; then
            pin_name="${BASH_REMATCH[3]}"
            pin_x="${BASH_REMATCH[1]}"
            pin_y="${BASH_REMATCH[2]}"
            if ! echo "$content" | grep -q "IOPIN $pin_x $pin_y"; then
                # Add IOPIN for this FLAG
                content="$content\nIOPIN $pin_x $pin_y In"
            fi
        fi
    done <<< "$content"
    
    # Fix instance naming
    content=$(echo "$content" | sed 's/InstName [^X][0-9]/InstName X\1/')
    
    echo "$content" > "$temp_file"
    mv "$temp_file" "$file"
    echo -e "${GREEN}Fixed $file${NC}"
}

fix_asy() {
    local file=$1
    local temp_file=$(mktemp)
    local content
    
    echo -e "${YELLOW}Fixing $file...${NC}"
    
    # Read entire file
    content=$(cat "$file")
    
    # Fix version header
    content=$(echo "$content" | sed '1s/^Version.*/Version 4/')
    if ! echo "$content" | grep -q "^Version 4"; then
        content="Version 4\n$content"
    fi
    
    # Add SymbolType if missing
    if ! echo "$content" | grep -q "^SymbolType BLOCK"; then
        content="$content\nSymbolType BLOCK"
    fi
    
    # Fix PIN attributes
    while IFS= read -r line; do
        if [[ "$line" =~ ^PIN[[:space:]]+([^[:space:]]+)[[:space:]]+([^[:space:]]+)[[:space:]]+(LEFT|RIGHT)[[:space:]]+([0-9]+) ]]; then
            pin_x="${BASH_REMATCH[1]}"
            pin_y="${BASH_REMATCH[2]}"
            pin_dir="${BASH_REMATCH[3]}"
            # Set standard spacing to 8
            line=$(echo "$line" | sed 's/[0-9]*$/8/')
            # Check if this PIN has proper attributes
            if ! echo "$content" | grep -q "PINATTR.*$pin_x.*$pin_y"; then
                # Add pin attributes if missing
                content="$content\n$line\nPINATTR PinName P${pin_x}_${pin_y}\nPINATTR SpiceOrder 1"
            fi
        fi
    done <<< "$content"
    
    echo "$content" > "$temp_file"
    mv "$temp_file" "$file"
    echo -e "${GREEN}Fixed $file${NC}"
}

# Find and fix all circuit files
echo "Fixing circuit files..."
find . -type f -name "*.asc" ! -path "*/professor-reference-library/*" -exec bash -c 'fix_asc "$0"' {} \;
find . -type f -name "*.asy" ! -path "*/professor-reference-library/*" -exec bash -c 'fix_asy "$0"' {} \;

echo -e "\n${GREEN}File fixes complete. Run validation to check results.${NC}"