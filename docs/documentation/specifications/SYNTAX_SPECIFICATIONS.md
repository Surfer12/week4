## LTSpice Syntax Specifications

### 1. Syntax Requirements

1. **File Structure**
   - Version declaration must be first line
   - SHEET declaration follows version
   - No LTSpice-specific directives (.include, .backanno, .end)

2. **Supported Elements**
   ```
   - WIRE definitions
   - FLAG declarations
   - IOPIN specifications
   - SYMBOL declarations
   - SYMATTR settings
   - TEXT annotations
   ```

3. **Standard Format**
   ```
   Version 4
   SHEET 1 1920 1080
   WIRE [connections]
   FLAG [signal names]
   IOPIN [pin definitions]
   SYMBOL [component placements]
   SYMATTR [component attributes]
   TEXT [annotations]
   ```

### 2. Incompatible Directives

The following LTSpice directives should be avoided:
```
.include     ; Do not use for model inclusion
.backanno    ; Remove from file
.end         ; Remove from file
```

### 3. Model Integration

1. **External Models**
   - Models should be managed separately through tool configuration
   - Do not use .include directives for model files

2. **Component Definitions**
   ```
   SYMBOL [component] [position] [orientation]
   SYMATTR InstName [instance_name]
   SYMATTR Value [component_value]
   ```

### 4. Example Implementation

**Correct Syntax:**
```
Version 4
SHEET 1 1920 1080
WIRE 176 -160 144 -160
FLAG 144 -160 Clk
IOPIN 144 -160 In
SYMBOL InstructionMemory 216 -80 R0
SYMATTR InstName X2
TEXT 144 80 Left 2 ;Instruction Set:
```

**Incorrect Syntax:**
```
Version 4
.include "models.txt"    ; Wrong - avoid .include
SHEET 1 1920 1080
...
.backanno               ; Wrong - remove directive
.end                    ; Wrong - remove directive
```

### 5. Verification Checklist

- [ ] Version declaration present
- [ ] SHEET declaration follows version
- [ ] No .include directives
- [ ] No .backanno directive
- [ ] No .end directive
- [ ] All components properly defined with SYMBOL
- [ ] All pins defined with IOPIN
- [ ] All connections defined with WIRE
- [ ] Text annotations properly formatted

### 6. Implementation Benefits

1. **Compatibility**
   ```
   - Improved tool interoperability
   - Reduced parsing errors
   - Consistent behavior across platforms
   ```

2. **Maintainability**
   ```
   - Clear component definitions
   - Explicit wire connections
   - Self-documented through TEXT annotations
   ```

3. **Reliability**
   ```
   - Reduced syntax-related failures
   - Predictable parsing behavior
   - Stable across tool versions
   ```

### 7. Future Considerations

1. **Potential Enhancements**
   ```
   - Standardized model management
   - Improved component libraries
   - Enhanced documentation integration
   ```

2. **Tool Integration**
   ```
   - Version control compatibility
   - Automated verification
   - Cross-platform validation
   ```

### 8. Symbol File Requirements

1. **File Co-location**
   ```
   - .asc (schematic) files require corresponding .asy (symbol) files in same directory
   - Symbol files must match component names exactly
   - Example:
     /directory
     ├── Computer.asc           # Main schematic
     ├── InstructionMemory.asy  # Required symbol
     ├── DataMem.asy           # Required symbol
     ├── RegisterFile.asy      # Required symbol
     └── ALU.asy               # Required symbol
   ```

2. **Symbol Dependencies**
   - Each SYMBOL directive in .asc must have matching .asy file
   - Missing .asy files result in "Couldn't find symbol(s):" error
   - Symbol search is performed in:
     1. Current schematic directory
     2. LTSpice library paths (if configured)

3. **Example Error Resolution**
   ```
   Error: "Couldn't find symbol(s): InstructionMemory, DataMem, RegisterFile, ALU"
   Fix: Ensure these .asy files exist in same directory as schematic:
   - InstructionMemory.asy
   - DataMem.asy
   - RegisterFile.asy
   - ALU.asy
   ```

4. **Best Practices**
   ```
   - Keep all related .asc and .asy files in same directory
   - Use consistent naming between SYMBOL directives and .asy files
   - Version control both .asc and .asy files together
   - Document symbol dependencies in project README
   ``` 