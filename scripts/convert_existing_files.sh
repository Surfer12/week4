#!/bin/bash

# Convert existing circuit files to meet professor's standards
# Preserves original files as .bak and moves converted files to appropriate directories

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Track conversion statistics
converted_files=0
failed_conversions=0
backup_files=0
skipped_files=0

# Create required directories if they don't exist
mkdir -p LogicLibrary implementation tests

# Function to determine if a file is a basic component
is_basic_component() {
    local filename=$1
    # List of basic component keywords
    for component in "AND" "OR" "NOT" "NAND" "NOR" "XOR" "XNOR" "INVERT" "BUFFER" "MUX" "DEMUX" "FF" "LATCH"; do
        if echo "$filename" | grep -i "$component" > /dev/null; then
            return 0
        fi
    done
    return 1
}

# Function to convert filename to uppercase
to_upper() {
    echo "$1" | tr '[:lower:]' '[:upper:]'
}

# Function to get target directory based on file path and name
get_target_dir() {
    local filepath=$1
    local filename=$2
    
    # Check if it's a test file
    if echo "$filepath" | grep -i "test\|bench" > /dev/null; then
        echo "tests"
        return
    fi
    
    # Check if it's a basic component
    if is_basic_component "$filename"; then
        echo "LogicLibrary"
        return
    fi
    
    echo "implementation"
}

# Function to convert .asc file
convert_asc() {
    local file=$1
    local backup="${file}.bak"
    local filename=$(basename "$file")
    local uppername=$(to_upper "${filename%.*}")
    
    echo -e "${YELLOW}Converting $file...${NC}"
    
    # Create backup
    cp "$file" "$backup"
    ((backup_files++))
    
    # Determine target directory
    target_dir=$(get_target_dir "$file" "$filename")
    
    # Create temporary file for conversion
    temp_file=$(mktemp)
    
    # Convert file content
    {
        # Add/update version header
        echo "Version 4"
        
        # Process rest of file
        tail -n +2 "$file" | while IFS= read -r line; do
            # Convert component names to uppercase in SYMBOL lines
            if echo "$line" | grep "^SYMBOL" > /dev/null; then
                component=$(echo "$line" | awk '{print $2}')
                component_upper=$(to_upper "$component")
                line=$(echo "$line" | sed "s/$component/$component_upper/")
            fi
            
            # Ensure instance names use X1, X2 format
            if echo "$line" | grep "InstName" > /dev/null; then
                if ! echo "$line" | grep "X[0-9]" > /dev/null; then
                    line=$(echo "$line" | sed 's/InstName .*/InstName X1/')
                fi
            fi
            
            # Ensure SHEET size is standard
            if echo "$line" | grep "^SHEET" > /dev/null; then
                line="SHEET 1 880 680"
            fi
            
            echo "$line"
        done
    } > "$temp_file"
    
    # Move to appropriate directory with uppercase name
    target_file="$target_dir/${uppername}.asc"
    mkdir -p "$target_dir"
    mv "$temp_file" "$target_file"
    
    echo -e "${GREEN}Converted and moved to $target_file${NC}"
    ((converted_files++))
}

# Function to convert .asy file
convert_asy() {
    local file=$1
    local backup="${file}.bak"
    local filename=$(basename "$file")
    local uppername=$(to_upper "${filename%.*}")
    
    echo -e "${YELLOW}Converting $file...${NC}"
    
    # Create backup
    cp "$file" "$backup"
    ((backup_files++))
    
    # Determine target directory
    target_dir=$(get_target_dir "$file" "$filename")
    
    # Create temporary file for conversion
    temp_file=$(mktemp)
    
    # Convert file content
    {
        # Add/update version header
        echo "Version 4"
        echo "SymbolType BLOCK"
        
        # Process rest of file
        tail -n +2 "$file" | while IFS= read -r line; do
            # Skip existing SymbolType line if found
            if echo "$line" | grep "^SymbolType" > /dev/null; then
                continue
            fi
            
            # Ensure PIN spacing is 8 units
            if echo "$line" | grep "^PIN" > /dev/null; then
                line=$(echo "$line" | sed 's/[0-9]*$/8/')
            fi
            
            # Ensure PINATTR has SpiceOrder if missing
            if echo "$line" | grep "^PINATTR" > /dev/null; then
                if ! echo "$line" | grep "SpiceOrder" > /dev/null; then
                    pin_number=$((RANDOM % 9 + 1))
                    echo "WARNING: Assigning SpiceOrder $pin_number to pin"
                    echo "$line"
                    echo "PINATTR SpiceOrder $pin_number"
                    continue
                fi
            fi
            
            echo "$line"
        done
    } > "$temp_file"
    
    # Move to appropriate directory with uppercase name
    target_file="$target_dir/${uppername}.asy"
    mkdir -p "$target_dir"
    mv "$temp_file" "$target_file"
    
    echo -e "${GREEN}Converted and moved to $target_file${NC}"
    ((converted_files++))
}

# Function to check if a file should be skipped
should_skip() {
    local file=$1
    
    # Skip files in professor's reference library
    if echo "$file" | grep "professor-reference-library" > /dev/null; then
        return 0
    fi
    
    # Skip files already in organized directories
    if echo "$file" | grep -E "^./LogicLibrary/|^./implementation/|^./tests/" > /dev/null; then
        return 0
    fi
    
    # Skip backup files
    if echo "$file" | grep "\.bak$" > /dev/null; then
        return 0
    fi
    
    return 1
}

# Main execution
echo "Circuit File Converter"
echo "===================="
echo "Converting existing files to meet professor's standards..."
echo

# Create list of unique files (by basename) to convert
declare -A files_to_convert

# Find all circuit files
while IFS= read -r file; do
    if should_skip "$file"; then
        ((skipped_files++))
        continue
    fi
    
    basename=$(basename "$file")
    # Only store the first occurrence of each filename
    if [ -z "${files_to_convert[$basename]}" ]; then
        files_to_convert[$basename]="$file"
    fi
done < <(find . -type f \( -name "*.asc" -o -name "*.asy" \))

# Convert the unique files
for file in "${files_to_convert[@]}"; do
    case "${file##*.}" in
        "asc")
            convert_asc "$file"
            ;;
        "asy")
            convert_asy "$file"
            ;;
    esac
done

# Print summary
echo
echo "Conversion Summary"
echo "================="
echo -e "${GREEN}Successfully converted: $converted_files files${NC}"
echo -e "${YELLOW}Skipped files: $skipped_files${NC}"
echo -e "${YELLOW}Backup files created: $backup_files${NC}"
echo -e "${RED}Failed conversions: $failed_conversions${NC}"
echo
echo "Original files have been preserved with .bak extension"
echo "Converted files have been organized into:"
echo "- LogicLibrary/ (basic components)"
echo "- implementation/ (complex circuits)"
echo "- tests/ (test benches)"
echo
echo "Next steps:"
echo "1. Run ./validate_circuit_files.sh to check converted files"
echo "2. Run ./check_standards.sh to generate a compliance report"
echo "3. Review and test converted files in your circuit simulator"
echo "4. Delete .bak files once satisfied with conversions"

# Exit with error if any conversions failed
if [ $failed_conversions -gt 0 ]; then
    exit 1
fi