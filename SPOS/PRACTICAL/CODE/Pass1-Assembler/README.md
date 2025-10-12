# Pass 1 Assembler

This folder contains the implementation of a Two-Pass Assembler's first pass, which processes assembly language source code and generates intermediate representation.

## 📋 Program Overview

### **pass1_.java** - Pass 1 Assembler Implementation (In Development)
- **Purpose**: First pass of two-pass assembler
- **Function**: Symbol table generation, literal table creation, location counter management
- **Input**: Assembly language source code (`sample.txt`)
- **Output**: Intermediate code, symbol table, literal table, pool table
- **Status**: ✅ Folder organization corrected, basic test file created

## 🎯 Assembler Concept

### Two-Pass Assembler
A two-pass assembler processes the source code twice:
1. **Pass 1**: Symbol definition, forward reference resolution, intermediate code generation
2. **Pass 2**: Machine code generation using symbol and literal tables

### Pass 1 Responsibilities:
- Process declarative statements (DS, DC)
- Build Symbol Table (SYMTAB)
- Build Literal Table (LITTAB)
- Generate Pool Table (POOLTAB)
- Create intermediate code
- Manage Location Counter (LC)

## 📁 Files Structure

### Input Files:
- **`input.txt`** - Assembly source with macro definitions

### Output Files:
- **`output.txt`** - Processed output with macro information
- **Macro tables** - Generated in memory/console

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

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Assembly source file (`sample.txt`)

### Compilation & Execution
```bash
# Navigate to Pass 1 directory
cd "Pass 1"

# Compile the program
javac pass1_.java Obj.java Pool_table.java

# Run the assembler
java pass1_
```

## 📝 Assembly Language Format

### Supported Instructions:
- **Imperative Statements**: MOVER, MOVEM, ADD, SUB, MUL, DIV, etc.
- **Declarative Statements**: DS (Declare Storage), DC (Declare Constant)
- **Assembler Directives**: START, END, LTORG

### Sample Assembly Code (`sample.txt`):
```assembly
START   100
        MOVER   R1, A
        ADD     R1, ='5'
        MOVER   R2, B
        ADD     R2, ='10'
A       DS      1
B       DS      1
        LTORG
        PRINT   A
        STOP
        END
```

## 📊 Output Format

### Intermediate Code Format:
```
(AD,01) (C,100)         ; START 100
(IS,04) (RG,1) (S,01)   ; MOVER R1, A
(IS,01) (RG,1) (L,01)   ; ADD R1, ='5'
(IS,04) (RG,2) (S,02)   ; MOVER R2, B
(IS,01) (RG,2) (L,02)   ; ADD R2, ='10'
(DL,01) (C,1)           ; A DS 1
(DL,01) (C,1)           ; B DS 1
(AD,05)                 ; LTORG
(IS,09) (S,01)          ; PRINT A
(IS,00)                 ; STOP
(AD,02)                 ; END
```

### Symbol Table (`Symtab.txt`):
```
Symbol  Address
A       104
B       105
```

### Literal Table (`Littab.txt`):
```
Literal Address
='5'    106
='10'   107
```

### Pool Table (`Pooltab.txt`):
```
Pool    Start_Index
1       1
```

## 🔄 Algorithm Steps

### Pass 1 Processing:
1. **Initialize**: LC = 0, Symbol table, Literal table
2. **Read Line**: Get next assembly instruction
3. **Parse**: Extract label, opcode, operands
4. **Process Label**: Add to symbol table if present
5. **Process Opcode**:
   - **Imperative**: Generate intermediate code
   - **Declarative**: Update LC, process data
   - **Directive**: Handle START, END, LTORG
6. **Update LC**: Increment location counter
7. **Repeat**: Until END directive

### Symbol Processing:
```java
if (label != null) {
    if (symbolTable.contains(label)) {
        // Forward reference resolution
        symbolTable.updateAddress(label, LC);
    } else {
        // New symbol definition
        symbolTable.add(label, LC);
    }
}
```

### Literal Processing:
```java
if (operand.startsWith("='")) {
    // Add to literal table
    literalTable.add(operand, -1); // Address assigned later
    // Generate intermediate code with literal reference
    generateIC("L", literalTable.getIndex(operand));
}
```

## 📈 Complexity Analysis

- **Time Complexity**: O(n) where n = number of source lines
- **Space Complexity**: O(s + l) where s = symbols, l = literals
- **Symbol Lookup**: O(1) average with hash table
- **File I/O**: O(n) for reading/writing files

## 🛠️ Instruction Set Architecture

### Instruction Classes:
| Class | Code | Examples |
|-------|------|----------|
| IS (Imperative) | 01-12 | MOVER, ADD, SUB |
| DL (Declarative) | 01-02 | DS, DC |
| AD (Assembler Directive) | 01-05 | START, END, LTORG |

### Register Codes:
| Register | Code |
|----------|------|
| R1       | 1    |
| R2       | 2    |
| R3       | 3    |
| R4       | 4    |

## 🔍 Error Handling

### Common Errors:
1. **Undefined Symbols**: Symbol used but not defined
2. **Duplicate Labels**: Same label defined multiple times
3. **Invalid Syntax**: Malformed assembly instructions
4. **Missing START**: Program without START directive

### Error Detection:
```java
// Check for undefined symbols
for (Symbol sym : symbolTable) {
    if (!sym.isDefined()) {
        System.err.println("Error: Undefined symbol - " + sym.getName());
    }
}
```

## 🧪 Test Cases

### Test Case 1: Basic Program
```assembly
START 100
MOVER R1, A
STOP
A DS 1
END
```

### Test Case 2: With Literals
```assembly
START 200
ADD R1, ='5'
MUL R2, ='10'
LTORG
STOP
END
```

### Test Case 3: Forward References
```assembly
START 300
JUMP LABEL
MOVER R1, A
LABEL STOP
A DS 1
END
```

## 🎓 Learning Objectives

- Understand two-pass assembly process
- Learn symbol table management
- Practice file I/O operations in Java
- Implement literal and pool table handling
- Study intermediate code generation

## 🔗 Related Components

- **Pass 2**: Machine code generation
- **Loader**: Program loading and relocation
- **Linker**: Multiple object file linking
- **Preprocessor**: Macro expansion

## 📚 References

- System Programming (Beck)
- Assembly Language Programming
- Compiler Design Principles