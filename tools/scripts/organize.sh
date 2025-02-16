#!/bin/bash

# Create necessary directories if they don't exist
mkdir -p docs/{specifications,guides,analysis} \
        src/{components/{basic/{gates,arithmetic,memory},complex/{alu,control,datapath}},integration/{cpu,memory,io},verification/{testbenches,simulations}} \
        labs/{lab1-basic-gates,lab2-arithmetic,lab3-memory} \
        tools/{scripts/{simulation,analysis},ltspice/{models,symbols}} \
        resources/{datasheets,references,examples} \
        build/{outputs,reports}

# Additional source directory
LOGIC_LIB_DIR="/Users/ryanoates/week4/src/logic-library"

# Function to safely move files
safe_move() {
    local src="$1"
    local dest="$2"
    if [ -f "$src" ]; then
        cp "$src" "$dest"
        echo "Moved $src to $dest"
    else
        echo "Warning: $src does not exist."
    fi
}

# Basic Gates
for ext in asc asy; do
    # From logic-library
    safe_move "$LOGIC_LIB_DIR/ANDx.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/ORx.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/INVERT.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/NAND.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/NAND3.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/NOR.${ext}" "src/components/basic/gates/"
    safe_move "$LOGIC_LIB_DIR/NOR3.${ext}" "src/components/basic/gates/"
done

# Arithmetic Components
for ext in asc asy; do
    # From logic-library
    safe_move "$LOGIC_LIB_DIR/ALU.${ext}" "src/components/complex/alu/"
    safe_move "$LOGIC_LIB_DIR/half_adder.${ext}" "src/components/basic/arithmetic/"
    safe_move "$LOGIC_LIB_DIR/full_adder.${ext}" "src/components/basic/arithmetic/"
    safe_move "$LOGIC_LIB_DIR/carry_save_adder.${ext}" "src/components/basic/arithmetic/"
    safe_move "$LOGIC_LIB_DIR/cla_4bit.${ext}" "src/components/basic/arithmetic/"
    safe_move "$LOGIC_LIB_DIR/cla_gp_unit.${ext}" "src/components/basic/arithmetic/"
    safe_move "$LOGIC_LIB_DIR/cla_logic_unit.${ext}" "src/components/basic/arithmetic/"
done

# Memory Components
for ext in asc asy; do
    # From logic-library
    safe_move "$LOGIC_LIB_DIR/dff.${ext}" "src/components/basic/memory/"
    safe_move "$LOGIC_LIB_DIR/RegisterFile.${ext}" "src/components/basic/memory/"
    safe_move "$LOGIC_LIB_DIR/DataMem.${ext}" "src/integration/memory/"
    safe_move "$LOGIC_LIB_DIR/InstructionMemory.${ext}" "src/integration/memory/"
done

# Control Components
for ext in asc asy; do
    # From logic-library
    safe_move "$LOGIC_LIB_DIR/MUX.${ext}" "src/components/complex/control/"
done

# Integration Components
safe_move "$LOGIC_LIB_DIR/Computer.asc" "src/integration/cpu/"
safe_move "$LOGIC_LIB_DIR/Master.asc" "src/integration/cpu/"

# Test Files
for test_file in "$LOGIC_LIB_DIR"/*_test.asc "$LOGIC_LIB_DIR"/Test*.asc; do
    if [ -f "$test_file" ]; then
        cp "$test_file" "src/verification/testbenches/"
        echo "Copied $test_file to src/verification/testbenches/"
    else
        echo "Warning: $test_file does not exist."
    fi
done

# Lab Files
safe_move "$LOGIC_LIB_DIR/lab2-update-feb8.asc" "labs/lab2-arithmetic/"
safe_move "$LOGIC_LIB_DIR/lab2-update-feb8-retry.asc" "labs/lab2-arithmetic/"

# Model Files
safe_move "$LOGIC_LIB_DIR/ModelSp2025.txt" "tools/ltspice/models/"

# Simulation Results
if [ -f "$LOGIC_LIB_DIR/half_adder.log" ]; then
    cp "$LOGIC_LIB_DIR/half_adder.log" "build/outputs/"
    echo "Copied $LOGIC_LIB_DIR/half_adder.log to build/outputs/"
fi
if [ -f "$LOGIC_LIB_DIR/half_adder.raw" ]; then
    cp "$LOGIC_LIB_DIR/half_adder.raw" "build/outputs/"
    echo "Copied $LOGIC_LIB_DIR/half_adder.raw to build/outputs/"
fi

# Create documentation files
cat > docs/guides/simulation.md << 'EOL'
# LTSpice Simulation Guide

This guide explains how to run simulations for the digital logic components in this project.

## Prerequisites
- LTSpice XVII or later
- Basic understanding of digital logic

## Running Simulations
1. Open the desired test bench file from src/verification/testbenches/
2. Run the simulation using the "Run" button or Ctrl+R
3. View the results in the waveform viewer

## Common Test Benches
- Basic Gates: GateTest.asc
- Arithmetic:
  - half_adder_test.asc
  - full_adder_test.asc
  - carry_save_adder_test.asc
  - cla_4bit_test.asc
- Memory: RegisterFile_test.asc

## Analyzing Results
- Use markers to measure timing
- Export data for further analysis
- Compare with expected truth tables
EOL

echo "Organization complete. Please verify the results before removing any original files."
exit 0
