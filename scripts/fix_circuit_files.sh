#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Create required directories
echo "Setting up directories..."
mkdir -p LogicLibrary implementation tests

# Process a single file
fix_file() {
    local file=$1
    local temp_file=$(mktemp)
    local filename=$(basename "$file")
    local is_asy=false
    
    if [[ "$file" == *.asy ]]; then
        is_asy=true
    fi
    
    echo -e "${YELLOW}Fixing $file...${NC}"
    
    # Read file and make modifications
    {
        echo "Version 4"
        if $is_asy; then
            echo "SymbolType BLOCK"
        else
            echo "SHEET 1 880 680"
        fi
        
        # Skip existing header and copy rest of file
        tail -n +2 "$file" | while IFS= read -r line; do
            # Skip existing SymbolType or SHEET line
            if [[ "$line" =~ ^(SymbolType|SHEET) ]]; then
                continue
            fi
            echo "$line"
        done
    } > "$temp_file"
    
    # Determine target directory
    local target_dir="implementation"
    if [[ "$filename" =~ ^(AND|OR|NOT|NAND|NOR|XOR|XNOR|INVERT|BUFFER|MUX|DEMUX|FF|LATCH) ]]; then
        target_dir="LogicLibrary"
    elif [[ "$filename" =~ [Tt]est ]]; then
        target_dir="tests"
    fi
    
    # Convert filename to uppercase
    local uppercase_name=$(echo "${filename%.*}" | tr '[:lower:]' '[:upper:]')
    local target_file="$target_dir/${uppercase_name}.${filename##*.}"
    
    # Move file to target directory
    mv "$temp_file" "$target_file"
    echo -e "${GREEN}Fixed and moved to $target_file${NC}"
}

# Process all circuit files
echo "Fixing circuit files..."
find . -type f \( -name "*.asc" -o -name "*.asy" \) ! -path "*/professor-reference-library/*" | while read -r file; do
    fix_file "$file"
done

echo -e "\n${GREEN}File fixes complete. Run validation to check results.${NC}"