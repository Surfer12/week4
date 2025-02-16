#!/bin/bash

# File Structure Validator for Circuit Files
# Checks .asc and .asy files for compliance with professor's standards

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Error counter
errors=0
warnings=0

# Check .asc file
check_asc() {
    local file=$1
    echo -e "\n${YELLOW}Checking $file...${NC}"
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        echo -e "${RED}ERROR: Invalid or missing Version 4 header${NC}"
        ((errors++))
    fi
    
    # Check SHEET
    if ! grep -q "^SHEET 1 880 680$" "$file"; then
        echo -e "${YELLOW}WARNING: Non-standard sheet size${NC}"
        ((warnings++))
    fi
    
    # Check SYMBOL naming
    grep "^SYMBOL" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "InstName X[0-9]"; then
            echo -e "${YELLOW}WARNING: Non-standard instance naming found in: $line${NC}"
            ((warnings++))
        fi
    done
    
    # Check FLAG/IOPIN pairs
    flags=$(grep "^FLAG" "$file" | wc -l)
    iopins=$(grep "^IOPIN" "$file" | wc -l)
    if [ "$flags" != "$iopins" ]; then
        echo -e "${RED}ERROR: Mismatch between FLAGS ($flags) and IOPINs ($iopins)${NC}"
        ((errors++))
    fi
    
    # Check component naming case
    grep "^SYMBOL" "$file" | while read -r line; do
        component=$(echo "$line" | awk '{print $2}')
        if [[ ! "$component" =~ ^[A-Z] ]]; then
            echo -e "${RED}ERROR: Component $component should be uppercase${NC}"
            ((errors++))
        fi
    done
}

# Check .asy file
check_asy() {
    local file=$1
    echo -e "\n${YELLOW}Checking $file...${NC}"
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        echo -e "${RED}ERROR: Invalid or missing Version 4 header${NC}"
        ((errors++))
    fi
    
    # Check SymbolType
    if ! grep -q "^SymbolType BLOCK$" "$file"; then
        echo -e "${RED}ERROR: Missing or invalid SymbolType BLOCK${NC}"
        ((errors++))
    fi
    
    # Check PIN attributes
    pins=$(grep "^PIN" "$file" | wc -l)
    pinattrs=$(grep "^PINATTR" "$file" | wc -l)
    if [ "$((pinattrs/2))" != "$pins" ]; then
        echo -e "${RED}ERROR: Missing PIN attributes${NC}"
        ((errors++))
    fi
    
    # Check pin directions
    grep "^PIN" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "LEFT\|RIGHT"; then
            echo -e "${YELLOW}WARNING: Non-standard pin direction: $line${NC}"
            ((warnings++))
        fi
    done
    
    # Check pin spacing
    grep "^PIN" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "8$"; then
            echo -e "${YELLOW}WARNING: Non-standard pin spacing: $line${NC}"
            ((warnings++))
        fi
    done
}

# Main execution
echo "Circuit File Validator"
echo "===================="
echo "Checking for compliance with professor's standards..."

# Find and check all circuit files
find . -name "*.asc" -type f | while read -r file; do
    check_asc "$file"
done

find . -name "*.asy" -type f | while read -r file; do
    check_asy "$file"
done

# Print summary
echo -e "\n${YELLOW}Validation Complete${NC}"
echo -e "Found ${RED}$errors errors${NC} and ${YELLOW}$warnings warnings${NC}"

# Exit with error if there were any errors
if [ $errors -gt 0 ]; then
    exit 1
fi