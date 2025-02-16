# Quick Reference Guide: Circuit File Creation and Validation

## Quick Start Templates

### Basic .asc Template
```
Version 4
SHEET 1 880 680
WIRE <x1> <y1> <x2> <y2>
FLAG <x> <y> <signal_name>
IOPIN <x> <y> <direction>
SYMBOL <component> <x> <y> <rotation>
SYMATTR InstName X1
```

### Basic .asy Template
```
Version 4
SymbolType BLOCK
LINE Normal <x1> <y1> <x2> <y2>
PIN <x> <y> <direction> <size>
PINATTR PinName <name>
PINATTR SpiceOrder <number>
```

## File Creation Checklist

### Creating .asc Files
1. [ ] Version 4 header
2. [ ] SHEET definition (880x680 standard)
3. [ ] All inputs on left side
4. [ ] All outputs on right side
5. [ ] Instance names in X1, X2 format
6. [ ] Proper WIRE connections
7. [ ] IOPINs for all external connections
8. [ ] Appropriate FLAGS
9. [ ] UPPERCASE component names

### Creating .asy Files
1. [ ] Version 4 header
2. [ ] SymbolType BLOCK
3. [ ] All pins have PinName
4. [ ] All pins have SpiceOrder
5. [ ] LEFT/RIGHT pin orientation
6. [ ] 8-unit pin spacing
7. [ ] Proper symbol dimensions

## Validation Scripts

### File Structure Validator
Save as `validate_circuit_files.sh`:
```bash
#!/bin/bash

# Check .asc file
check_asc() {
    local file=$1
    echo "Checking $file..."
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        echo "ERROR: Invalid or missing Version 4 header"
    fi
    
    # Check SHEET
    if ! grep -q "^SHEET 1 880 680$" "$file"; then
        echo "WARNING: Non-standard sheet size"
    fi
    
    # Check SYMBOL naming
    grep "^SYMBOL" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "InstName X[0-9]"; then
            echo "WARNING: Non-standard instance naming found"
        fi
    done
    
    # Check FLAG/IOPIN pairs
    flags=$(grep "^FLAG" "$file" | wc -l)
    iopins=$(grep "^IOPIN" "$file" | wc -l)
    if [ "$flags" != "$iopins" ]; then
        echo "ERROR: Mismatch between FLAGS and IOPINs"
    fi
}

# Check .asy file
check_asy() {
    local file=$1
    echo "Checking $file..."
    
    # Check Version
    if ! grep -q "^Version 4$" "$file"; then
        echo "ERROR: Invalid or missing Version 4 header"
    fi
    
    # Check SymbolType
    if ! grep -q "^SymbolType BLOCK$" "$file"; then
        echo "ERROR: Missing or invalid SymbolType BLOCK"
    fi
    
    # Check PIN attributes
    pins=$(grep "^PIN" "$file" | wc -l)
    pinattrs=$(grep "^PINATTR" "$file" | wc -l)
    if [ "$((pinattrs/2))" != "$pins" ]; then
        echo "ERROR: Missing PIN attributes"
    fi
    
    # Check pin directions
    grep "^PIN" "$file" | while read -r line; do
        if ! echo "$line" | grep -q "LEFT\|RIGHT"; then
            echo "WARNING: Non-standard pin direction"
        fi
    done
}

# Main validation loop
find . -name "*.asc" -type f | while read -r file; do
    check_asc "$file"
done

find . -name "*.asy" -type f | while read -r file; do
    check_asy "$file"
done
```

### Component Creation Helper
Save as `create_component.sh`:
```bash
#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 COMPONENT_NAME"
    exit 1
fi

COMPONENT=$1
COMPONENT_UPPER=$(echo "$COMPONENT" | tr '[:lower:]' '[:upper:]')

# Create .asc file
cat > "${COMPONENT_UPPER}.asc" << EOL
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
EOL

# Create .asy file
cat > "${COMPONENT_UPPER}.asy" << EOL
Version 4
SymbolType BLOCK
RECTANGLE Normal -64 -32 64 32
PIN -64 0 LEFT 8
PINATTR PinName A
PINATTR SpiceOrder 1
PIN 64 0 RIGHT 8
PINATTR PinName Out
PINATTR SpiceOrder 2
EOL

echo "Created ${COMPONENT_UPPER}.asc and ${COMPONENT_UPPER}.asy"
```

## Standard Compliance Report Generator
Save as `check_standards.sh`:
```bash
#!/bin/bash

report_file="circuit_standards_report.txt"

{
    echo "Circuit Standards Compliance Report"
    echo "================================="
    echo
    echo "Generated: $(date)"
    echo
    
    # Check directory structure
    echo "Directory Structure:"
    echo "------------------"
    if [ -d "LogicLibrary" ]; then
        echo "[✓] LogicLibrary directory exists"
    else
        echo "[✗] Missing LogicLibrary directory"
    fi
    
    # Check file naming
    echo
    echo "File Naming Conventions:"
    echo "----------------------"
    find . -name "*.asc" -o -name "*.asy" | while read -r file; do
        basename=$(basename "$file")
        if [[ "$basename" =~ ^[A-Z] ]]; then
            echo "[✓] $basename follows naming convention"
        else
            echo "[✗] $basename should be uppercase"
        fi
    done
    
    # Check version headers
    echo
    echo "Version Headers:"
    echo "---------------"
    find . -name "*.asc" -o -name "*.asy" | while read -r file; do
        if grep -q "^Version 4$" "$file"; then
            echo "[✓] $file has correct version header"
        else
            echo "[✗] $file missing or incorrect version header"
        fi
    done
    
} > "$report_file"

echo "Report generated: $report_file"
```

## Usage Instructions

1. Install the Validation Scripts:
```bash
# Copy scripts to project root
cp validate_circuit_files.sh /Users/ryanoates/week4/
cp create_component.sh /Users/ryanoates/week4/
cp check_standards.sh /Users/ryanoates/week4/

# Make executable
chmod +x /Users/ryanoates/week4/*.sh
```

2. Create New Component:
```bash
./create_component.sh AND
```

3. Validate Files:
```bash
./validate_circuit_files.sh
```

4. Generate Compliance Report:
```bash
./check_standards.sh
```

## Common Issues and Solutions

1. Version Header Issues:
   - Error: Missing Version 4
   - Fix: Add "Version 4" as first line

2. Instance Naming:
   - Error: Wrong InstName format
   - Fix: Use X1, X2, etc.

3. Pin Attributes:
   - Error: Missing SpiceOrder
   - Fix: Add PINATTR SpiceOrder <number>

4. Directory Structure:
   - Error: Files in wrong location
   - Fix: Move to LogicLibrary/ or implementation/

## Best Practices

1. Always use the creation script for new components
2. Run validation before committing changes
3. Generate compliance report weekly
4. Keep reference library synchronized
5. Document any deviations from standards