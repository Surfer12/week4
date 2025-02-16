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
