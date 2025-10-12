# Macro Pass 1 - Macro Definition Processing

This folder contains the implementation of the first pass of a macro processor, which handles macro definitions and builds macro tables.

## 📋 Program Overview

### **MacroPass1.java** - Macro Processor Pass 1
- **Purpose**: First pass of two-pass macro processor
- **Function**: Macro definition processing, table building
- **Input**: Assembly source with macro definitions
- **Output**: Macro Name Table (MNT), Macro Definition Table (MDT), Argument List Table (ALA)

## 🎯 Macro Processing Concept

### Two-Pass Macro Processor
A macro processor expands macro calls with their definitions:
1. **Pass 1**: Build macro tables, process definitions
2. **Pass 2**: Expand macro calls, generate expanded code

### Pass 1 Responsibilities:
- Identify macro definitions (MACRO...MEND)
- Build Macro Name Table (MNT)
- Build Macro Definition Table (MDT)
- Create Argument List Table (ALA)
- Process nested macro definitions
- Handle parameter substitution markers

## 📁 Files Structure

### Input Files:
- **`input.txt`** - Assembly source with macro definitions

### Output Files:
- **`MNT.txt`** - Macro Name Table
- **`MDT.txt`** - Macro Definition Table  
- **`ALA.txt`** - Argument List Table

### Class Files:
- **`MacroNameTable.java`** - MNT implementation
- **`MacroDefTable.java`** - MDT implementation
- **`TestMacroProcessor.java`** - Test driver

## 🔧 Data Structures

### Macro Name Table (MNT):
```java
class MNTEntry {
    String macroName;      // Name of the macro
    int mdtIndex;          // Starting index in MDT
    int paramCount;        // Number of parameters
    int alaIndex;          // Starting index in ALA
}
```

### Macro Definition Table (MDT):
```java
class MDTEntry {
    String line;           // Macro body line
    int nextIndex;         // Next MDT index (-1 for end)
}
```

### Argument List Table (ALA):
```java
class ALAEntry {
    String parameter;      // Parameter name
    int index;            // Parameter index
}
```

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Source file with macro definitions

### Compilation & Execution
```bash
# Navigate to Macro Pass1 directory
cd "Macro Pass1"

# Compile all Java files together (required for class dependencies)
javac *.java

# Run the macro processor
java MacroPass1

# Or run the test driver
java TestMacroProcessor

# Alternative: Use build script
./build.sh    # For Unix/Linux/macOS
build.bat     # For Windows
```

## 📝 Macro Definition Format

### Basic Macro Syntax:
```assembly
MACRO_NAME  MACRO   PARAM1, PARAM2, ...
            ; Macro body with parameter references
            ; &PARAM1, &PARAM2 are parameter placeholders
            MEND
```

### Sample Input (`input.txt`):
```assembly
START
INCR    MACRO   &A, &B
        MOVER   R1, &A
        ADD     R1, &B
        MOVEM   R1, &A
        MEND

DECR    MACRO   &X
        MOVER   R1, &X
        SUB     R1, ='1'
        MOVEM   R1, &X
        MEND

; Main program
        INCR    A, B
        DECR    C
        STOP
A       DS      1
B       DS      1  
C       DS      1
        END
```

## 📊 Output Tables

### Macro Name Table (`MNT.txt`):
```
Index  Macro_Name  MDT_Index  Param_Count  ALA_Index
1      INCR        1          2            1
2      DECR        5          1            3
```

### Macro Definition Table (`MDT.txt`):
```
Index  Definition
1      MOVER   R1, &A
2      ADD     R1, &B  
3      MOVEM   R1, &A
4      MEND
5      MOVER   R1, &X
6      SUB     R1, ='1'
7      MOVEM   R1, &X
8      MEND
```

### Argument List Table (`ALA.txt`):
```
Index  Parameter
1      &A
2      &B
3      &X
```

## 🔄 Algorithm Steps

### Pass 1 Processing:
1. **Initialize**: Empty MNT, MDT, ALA tables
2. **Read Source**: Process line by line
3. **Detect Macro**:
   ```java
   if (line.contains("MACRO")) {
       String macroName = extractMacroName(line);
       String[] params = extractParameters(line);
       addToMNT(macroName, currentMDTIndex, params.length, currentALAIndex);
   }
   ```
4. **Process Definition**: Store macro body in MDT
5. **Handle Parameters**: Add to ALA with substitution markers
6. **End Detection**: Process MEND directive
7. **Continue**: Until all macros processed

### Parameter Processing:
```java
private void processParameters(String[] params) {
    for (String param : params) {
        if (!param.trim().isEmpty()) {
            ala.add(param.trim());
            // Replace in macro body with &parameter format
            currentMacroBody = currentMacroBody.replace(
                param, "&" + param);
        }
    }
}
```

### Macro Body Storage:
```java
private void storeMacroLine(String line) {
    if (!line.trim().equals("MEND")) {
        // Process parameter substitution
        String processedLine = substituteParameters(line);
        mdt.add(processedLine);
    } else {
        mdt.add("MEND");
        insideMacro = false;
    }
}
```

## 📈 Complexity Analysis

- **Time Complexity**: O(n) where n = number of source lines
- **Space Complexity**: O(m + d + p) where:
  - m = number of macros
  - d = total macro definition lines
  - p = total parameters
- **Lookup Time**: O(1) average for hash-based MNT

## 🛠️ Advanced Features

### Nested Macro Support:
```java
private int macroNestingLevel = 0;

if (line.contains("MACRO")) {
    macroNestingLevel++;
    if (macroNestingLevel == 1) {
        // Process top-level macro
        processMacroDefinition(line);
    }
} else if (line.contains("MEND")) {
    macroNestingLevel--;
}
```

### Conditional Assembly:
```assembly
MACRO_NAME  MACRO   &PARAM
            AIF     (&PARAM EQ 0).SKIP
            MOVER   R1, &PARAM
.SKIP       MEND
```

### Local Labels:
```assembly
INCREMENT   MACRO   &VAR
.LOOP       MOVER   R1, &VAR
            ADD     R1, ='1'
            MOVEM   R1, &VAR
            MEND
```

## 🔍 Error Handling

### Common Errors:
1. **Missing MEND**: Macro definition without termination
2. **Nested MACRO**: Improper macro nesting
3. **Invalid Parameters**: Malformed parameter list
4. **Duplicate Macros**: Same macro name defined twice

### Error Detection:
```java
// Check for unclosed macros
if (insideMacro && isEndOfFile()) {
    System.err.println("Error: Unclosed macro definition");
}

// Check for duplicate macro names
if (mnt.contains(macroName)) {
    System.err.println("Error: Duplicate macro definition - " + macroName);
}

// Validate parameter syntax
if (!isValidParameter(param)) {
    System.err.println("Error: Invalid parameter - " + param);
}
```

## 🧪 Test Cases

### Test Case 1: Simple Macro
```assembly
SIMPLE  MACRO   &A
        MOVER   R1, &A
        MEND
```

### Test Case 2: Multiple Parameters
```assembly
SWAP    MACRO   &X, &Y, &TEMP
        MOVER   R1, &X
        MOVEM   R1, &TEMP
        MOVER   R1, &Y
        MOVEM   R1, &X
        MOVER   R1, &TEMP
        MOVEM   R1, &Y
        MEND
```

### Test Case 3: No Parameters
```assembly
INIT    MACRO
        CLEAR   R1
        CLEAR   R2
        CLEAR   R3
        MEND
```

## 📋 Validation Rules

### Macro Name Rules:
- Must start with letter
- Can contain letters, digits, underscore
- Maximum length limit
- Case sensitivity

### Parameter Rules:
- Must start with &
- Unique within macro
- No reserved words
- Proper substitution syntax

## 🎓 Learning Objectives

- Understand macro processing fundamentals
- Learn table-driven programming
- Practice string manipulation in Java
- Implement parser for assembly language
- Study preprocessor design patterns

## 🔗 Related Components

### Pass 2 Integration:
- Uses MNT for macro call identification
- Uses MDT for expansion
- Uses ALA for parameter substitution

### Symbol Table Interaction:
- Macro expansion may generate symbols
- Local label handling
- Global symbol resolution

## 📚 Advanced Topics

### Macro Libraries:
- Standard macro collections
- Library search paths
- Conditional inclusion

### Performance Optimization:
- Hash tables for fast lookup
- Efficient string operations
- Memory management

### Language Extensions:
- Arithmetic expressions in macros
- String manipulation functions
- Conditional macro expansion

## 🔧 Debugging Tips

### Debug Output:
```java
if (DEBUG_MODE) {
    System.out.println("MNT: " + mnt.toString());
    System.out.println("MDT: " + mdt.toString());
    System.out.println("ALA: " + ala.toString());
}
```

### Common Issues:
1. **Parameter Mismatch**: Wrong number of arguments
2. **Symbol Conflicts**: Macro symbols vs program symbols
3. **Infinite Recursion**: Self-referencing macros
4. **Memory Limits**: Large macro definitions