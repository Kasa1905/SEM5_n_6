# Pass 1 Assembler

This folder contains the implementation of a Two-Pass Assembler's first pass, which processes assembly language source code and generates intermediate representation along with symbol and literal tables.

## 📋 Program Overview

### **pass1_.java** - Pass 1 Assembler Implementation
- **Purpose**: First pass of two-pass assembler
- **Function**: Symbol table generation, literal table creation, location counter management
- **Input**: Assembly language source code (`sample.txt`)
- **Output**: Intermediate code, symbol table, literal table, pool table
- **Status**: ✅ Complete implementation with full functionality

## 🎯 Assembler Concept

### Two-Pass Assembler
A two-pass assembler processes the source code twice:
1. **Pass 1**: Symbol definition, forward reference resolution, intermediate code generation
2. **Pass 2**: Machine code generation using symbol and literal tables

### Pass 1 Responsibilities:
- Process declarative statements (DS, DC, START, END, ORIGIN, EQU, LTORG)
- Build Symbol Table (SYMTAB) for labels and variables
- Build Literal Table (LITTAB) for literal constants
- Generate Pool Table (POOLTAB) for literal management
- Create intermediate code with proper addressing
- Manage Location Counter (LC) throughout processing

## 📁 Files Structure

### Input Files:
- **`sample.txt`** - Assembly source code (auto-generated if not present)

### Output Files Generated:
- **`Intermediate.txt`** - Intermediate code representation
- **`Symtab.txt`** - Symbol table with addresses
- **`Littab.txt`** - Literal table with addresses
- **`Pooltab.txt`** - Pool table for literal management

### Main Class:
- **`pass1_.java`** - Complete Pass 1 assembler implementation

## 🔧 Data Structures

### Symbol Table Entry:
```java
class TableEntry {
    String name;           // Symbol/Label name
    int address;           // Memory address (-1 if unresolved)
}
```

### Literal Table Entry:
```java
class TableEntry {
    String name;           // Literal value (e.g., "=5")
    int address;           // Memory address (-1 if unresolved)
}
```

### Pool Table Entry:
```java
class PoolEntry {
    int literalIndex;      // Starting index in literal table
}
```

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Assembly source file (auto-created as `sample.txt` if not present)

### Quick Start (Cross-Platform)
```bash
# Using build scripts (recommended)
./build.sh          # On macOS/Linux
build.bat           # On Windows

# Manual compilation and execution
javac pass1_.java
java pass1_
```

## 📝 Assembly Language Format

### Supported Instructions:
- **Imperative Statements**: STOP, ADD, SUB, MULT, MOVER, MOVEM, COMP, BC, DIV, READ, PRINT
- **Declarative Statements**: DS (Declare Storage), DC (Declare Constant)
- **Assembler Directives**: START, END, ORIGIN, EQU, LTORG

### Registers Supported:
- AREG, BREG, CREG, DREG (mapped to 1, 2, 3, 4)

### Sample Assembly Code (`sample.txt`):
```assembly
; Sample Assembly Program for Two Pass Assembler
START 100
READ N1
READ N2
MOVER AREG N1
ADD AREG N2
MOVEM AREG SUM
LOOP: MOVER BREG =5
COMP BREG N1
BC GT NEXT
ADD AREG =1
BC UN LOOP
NEXT: PRINT SUM
STOP
N1: DS 1
N2: DS 1
SUM: DS 1
LTORG
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
## 📊 Output Tables

### Symbol Table (`Symtab.txt`):
```
Index   Symbol          Address
0       N1              116
1       N2              117
2       SUM             118
3       LOOP            106
4       NEXT            111
```

### Literal Table (`Littab.txt`):
```
Index   Literal         Address
0       =5              119
1       =1              120
```

### Pool Table (`Pooltab.txt`):
```
Index   Literal_Index
0       2
```

### Intermediate Code (`Intermediate.txt`):
```
(100) (AD,01) (C,100)
(100) (IS,09) (S,00)
(101) (IS,09) (S,01)
(102) (IS,04) (1) (S,00)
(103) (IS,01) (1) (S,01)
(104) (IS,05) (1) (S,02)
(105) (IS,04) (2) (L,00)
(106) (IS,06) (2) (S,00)
(107) (IS,07) GT (S,04)
(108) (IS,01) (1) (L,01)
(109) (IS,07) UN (S,03)
(110) (IS,10) (S,02)
(111) (IS,00)
(112) (DL,01) (C,1)
(113) (DL,01) (C,1)
(114) (DL,01) (C,1)
(115) (AD,05)
(116) (AD,02)
```

## 🔄 Algorithm Steps

### Pass 1 Processing:
1. **Initialize**: LC = START address, empty tables
2. **Read Line**: Get next assembly instruction
3. **Parse**: Extract label, opcode, operands
4. **Process Label**: Add to symbol table with current LC
5. **Process Instruction**:
   - **Imperative**: Generate IC with opcode and operands
   - **Declarative**: Update LC, process data allocation
   - **Directive**: Handle START, END, LTORG, ORIGIN
6. **Update LC**: Based on instruction type
7. **Write Intermediate**: Output intermediate code
8. **Repeat**: Until END directive

### Symbol Resolution:
- **Forward Reference**: Symbol used before definition (address = -1)
- **Backward Reference**: Symbol defined before use (address known)
- **Label Definition**: Symbol followed by ":" gets current LC value

### Literal Management:
- **Detection**: Operands starting with "=" are literals
- **Storage**: Added to literal table with unresolved address
- **Resolution**: LTORG or END assigns actual memory addresses

## 🎯 Features

- ✅ **Complete Two-Pass Architecture**
- ✅ **Symbol Table Management**
- ✅ **Literal Table with Pool Management**
- ✅ **Forward Reference Resolution**
- ✅ **Intermediate Code Generation**
- ✅ **Cross-Platform Build Scripts**
- ✅ **Comprehensive Error Handling**
- ✅ **Auto Sample Program Generation**

## 📈 Technical Specifications

- **Time Complexity**: O(n) single pass through source
- **Space Complexity**: O(s + l + p) for symbols, literals, pools
- **File Format**: Plain text with structured output
- **Memory Model**: Sequential allocation with LC management

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