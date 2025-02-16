#!/bin/bash

# Standard Compliance Report Generator
# Generates a detailed report of circuit file compliance with professor's standards

report_file="circuit_standards_report.txt"
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

{
    echo "Circuit Standards Compliance Report"
    echo "================================="
    echo
    echo "Generated: $(date)"
    echo
    
    # Check directory structure
    echo "1. Directory Structure"
    echo "-------------------"
    if [ -d "LogicLibrary" ]; then
        echo "[✓] LogicLibrary directory exists"
    else
        echo "[✗] Missing LogicLibrary directory"
    fi
    
    if [ -d "implementation" ]; then
        echo "[✓] Implementation directory exists"
    else
        echo "[✗] Missing implementation directory"
    fi
    
    # Check file naming
    echo
    echo "2. File Naming Conventions"
    echo "------------------------"
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
    echo "3. Version Headers"
    echo "-----------------"
    find . -name "*.asc" -o -name "*.asy" | while read -r file; do
        if grep -q "^Version 4$" "$file"; then
            echo "[✓] $file has correct version header"
        else
            echo "[✗] $file missing or incorrect version header"
        fi
    done
    
    # Check SPICE models
    echo
    echo "4. SPICE Models"
    echo "--------------"
    if grep -r "Sp2025N\|Sp2025P" .; then
        echo "[✓] SPICE models found in use"
    else
        echo "[✗] No SPICE models found"
    fi
    
    # Check symbol attributes
    echo
    echo "5. Symbol Attributes"
    echo "------------------"
    find . -name "*.asy" | while read -r file; do
        echo "Checking $file:"
        if grep -q "SymbolType BLOCK" "$file"; then
            echo "  [✓] Has SymbolType BLOCK"
        else
            echo "  [✗] Missing SymbolType BLOCK"
        fi
        
        if grep -q "PINATTR SpiceOrder" "$file"; then
            echo "  [✓] Has SpiceOrder attributes"
        else
            echo "  [✗] Missing SpiceOrder attributes"
        fi
    done
    
    # Check test files
    echo
    echo "6. Test Files"
    echo "------------"
    if ls ./*.txt 2>/dev/null >/dev/null; then
        echo "[✓] Test files present"
        for file in *.txt; do
            if grep -q "PULSE(" "$file"; then
                echo "  [✓] $file contains proper voltage definitions"
            else
                echo "  [✗] $file missing voltage definitions"
            fi
        done
    else
        echo "[✗] No test files found"
    fi
    
    # Summary section
    echo
    echo "Summary"
    echo "-------"
    total_files=$(find . -name "*.asc" -o -name "*.asy" | wc -l)
    compliant_files=$(find . -name "*.asc" -o -name "*.asy" | xargs grep -l "^Version 4$" | wc -l)
    
    echo "Total circuit files: $total_files"
    echo "Compliant files: $compliant_files"
    echo "Compliance rate: $((compliant_files * 100 / total_files))%"
    
} > "$report_file"

echo -e "${GREEN}Report generated: $report_file${NC}"