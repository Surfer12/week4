#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Create required directories
mkdir -p LogicLibrary implementation tests

# Fix version and basic structure
fix_file() {
    local file=$1
    local temp_file=$(mktemp)
    local filename=$(basename "$file")
    local is_asy=false
    
    if [[ "$file" == *.asy ]]; then
        is_asy=true
    fi
    
    echo -e "${YELLOW}Fixing $file...${NC}"
    
    # Read file and fix header
    {
        echo "Version 4"
        if $is_asy; then
            echo "SymbolType BLOCK"
        else
            echo "SHEET 1 880 680"
        fi
        
        # Skip existing header
        tail -n +2 "$file" | while IFS= read -r line; do
            # Skip existing SymbolType or SHEET line
            if [[ "$line" =~ ^(SymbolType|SHEET) ]]; then
                continue
            fi
            
            # Fix pin attributes for .asy files
            if $is_asy; then
                if [[ "$line" =~ ^PIN[[:space:]]+(.*) ]]; then
                    # Fix pin spacing to 8
                    line=$(echo "$line" | sed 's/[0-9]*$/8/')
                    echo "$line"
                    # Add pin attributes if missing
                    if ! grep -A2 "$line" "$file" | grep -q "PINATTR"; then
                        echo "PINATTR PinName Pin_${RANDOM}"
                        echo "PINATTR SpiceOrder 1"
                    fi
                    continue
                fi
            fi
            
            # Fix instance names for .asc files
            if ! $is_asy; then
                if [[ "$line" =~ InstName && ! "$line" =~ "X[0-9]" ]]; then
                    line=$(echo "$line" | sed 's/InstName .*/InstName X1/')
                fi
            fi
            
            echo "$line"
        done
    } > "$temp_file"
    
    # Move to appropriate directory
    local target_dir="implementation"
    if [[ "$filename" =~ ^(AND|OR|NOT|NAND|NOR|XOR|XNOR|INVERT|BUFFER|MUX|DEMUX|FF|LATCH) ]]; then
        target_dir="LogicLibrary"
    elif [[ "$filename" =~ [Tt]est ]]; then
        target_dir="tests"
    fi
    
    local uppercase_name=$(echo "${filename%.*}" | tr '[:lower:]' '[:upper:]')
    local target_file="$target_dir/${uppercase_name}.${filename##*.}"
    
    mv "$temp_file" "$target_file"
    echo -e "${GREEN}Fixed and moved to $target_file${NC}"
}

# Process all circuit files
echo "Fixing circuit files..."
find . -type f \( -name "*.asc" -o -name "*.asy" \) ! -path "*/professor-reference-library/*" | while read -r file; do
    fix_file "$file"
done

echo -e "\n${GREEN}File fixes complete. Run validation to check results.${NC}"