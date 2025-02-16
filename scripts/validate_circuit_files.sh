#!/bin/bash

# File Structure Validator for Circuit Files
# Checks .asc and .asy files for compliance with professor's standards

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Error counter
total_files=0
errors=0
warnings=0

# Check .asc file
check_asc() {
    local file=$1
    local has_error=0
    local file_errors=""
    ((total_files++))
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        file_errors+="${RED}ERROR: Invalid or missing Version 4 header${NC}\n"
        ((errors++))
        has_error=1
    fi
    
    # Check SHEET
    if ! grep -q "^SHEET 1 880 680$" "$file"; then
        file_errors+="${YELLOW}WARNING: Non-standard sheet size${NC}\n"
        ((warnings++))
    fi
    
    # Check SYMBOL naming
    grep "^SYMBOL" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "InstName X[0-9]"; then
            file_errors+="${YELLOW}WARNING: Non-standard instance naming found in: $line${NC}\n"
            ((warnings++))
        fi
    done
    
    # Check FLAG/IOPIN pairs
    flags=$(grep "^FLAG" "$file" | wc -l)
    iopins=$(grep "^IOPIN" "$file" | wc -l)
    if [ "$flags" != "$iopins" ]; then
        file_errors+="${RED}ERROR: Mismatch between FLAGS ($flags) and IOPINs ($iopins)${NC}\n"
        ((errors++))
        has_error=1
    fi
    
    # Only print if there are errors
    if [ $has_error -eq 1 ]; then
        echo -e "\n${RED}Issues in $file:${NC}"
        echo -e "$file_errors"
    fi
}

# Check .asy file
check_asy() {
    local file=$1
    local has_error=0
    local file_errors=""
    ((total_files++))
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        file_errors+="${RED}ERROR: Invalid or missing Version 4 header${NC}\n"
        ((errors++))
        has_error=1
    fi
    
    # Check SymbolType
    if ! grep -q "^SymbolType BLOCK$" "$file"; then
        file_errors+="${RED}ERROR: Missing or invalid SymbolType BLOCK${NC}\n"
        ((errors++))
        has_error=1
    fi
    
    # Check PIN attributes
    pins=$(grep "^PIN" "$file" | wc -l)
    pinattrs=$(grep "^PINATTR" "$file" | wc -l)
    if [ "$((pinattrs/2))" != "$pins" ]; then
        file_errors+="${RED}ERROR: Missing PIN attributes${NC}\n"
        ((errors++))
        has_error=1
    fi
    
    # Check pin directions
    grep "^PIN" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "LEFT\|RIGHT"; then
            file_errors+="${YELLOW}WARNING: Non-standard pin direction: $line${NC}\n"
            ((warnings++))
        fi
    done
    
    # Only print if there are errors
    if [ $has_error -eq 1 ]; then
        echo -e "\n${RED}Issues in $file:${NC}"
        echo -e "$file_errors"
    fi
}

# Main execution
echo "Circuit File Validator"
echo "===================="
echo "Checking for compliance with professor's standards..."
echo

# Find and check all circuit files
find . -type f -name "*.asc" ! -path "*/professor-reference-library/*" | while read -r file; do
    check_asc "$file"
done

find . -type f -name "*.asy" ! -path "*/professor-reference-library/*" | while read -r file; do
    check_asy "$file"
done

# Print summary
echo
echo "Validation Summary"
echo "================="
echo -e "Total files checked: ${GREEN}$total_files${NC}"
echo -e "Errors found: ${RED}$errors${NC}"
echo -e "Warnings found: ${YELLOW}$warnings${NC}"

# Color coding explanation
echo
echo "Color Coding:"
echo -e "${RED}Red: Critical errors that must be fixed${NC}"
echo -e "${YELLOW}Yellow: Warnings that should be reviewed${NC}"
echo -e "${GREEN}Green: Compliant items${NC}"

# Exit with error if any errors found
if [ $errors -gt 0 ]; then
    exit 1
fi