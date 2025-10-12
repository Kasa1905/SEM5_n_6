# Macro Pass 2 - Macro Expansion Processing

This folder contains the implementation of the second pass of a macro processor, which expands macro calls using the tables built in Pass 1.

## 📋 Program Overview

### **MPass2.java** - Macro Processor Pass 2
- **Purpose**: Second pass of two-pass macro processor
- **Function**: Macro call expansion, parameter substitution
- **Input**: Source code with macro calls, MNT, MDT, ALA from Pass 1
- **Output**: Expanded assembly code without macro calls

## 🎯 Macro Expansion Concept

### Two-Pass Macro Processor - Second Pass
The second pass processes the source code and:
- Identifies macro calls
- Retrieves macro definitions from MDT
- Substitutes actual parameters for formal parameters
- Generates expanded code
- Handles nested macro calls

### Pass 2 Responsibilities:
- Scan source for macro calls
- Parameter matching and substitution
- Macro body expansion
- Nested call handling
- Generate final expanded code

## 📁 Files Structure

### Input Files:
- **`input.txt`** - Source code with macro calls
- **`MNT.txt`** - Macro Name Table (from Pass 1)
- **`MDT.txt`** - Macro Definition Table (from Pass 1)
- **`ALA.txt`** - Argument List Table (from Pass 1)

### Output Files:
- **`expanded_output.txt`** - Fully expanded assembly code
- **`expansion_log.txt`** - Expansion trace (optional)

### Class Files:
- **`MPass2.java`** - Main expansion engine
- **`arglist.java`** - Argument processing utilities

## 🔧 Data Structures

### Actual Parameter Table (APT):
```java
class APTEntry {
    String actualParam;    // Actual parameter value
    String formalParam;    // Corresponding formal parameter
    int callIndex;         // Which macro call this belongs to
}
```

### Expansion Stack:
```java
class ExpansionContext {
    String macroName;      // Currently expanding macro
    int mdtIndex;         // Current position in MDT
    String[] actualParams; // Actual parameters for this call
    int nestLevel;        // Nesting level
}
```

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Output tables from Macro Pass 1
- Source file with macro calls

### Compilation & Execution
```bash
# Navigate to Macro Pass 2 directory
cd "Macro Pass 2"

# Compile the Java files
javac *.java

# Run the macro processor
java MPass2

# View expanded output
cat expanded_output.txt
```

## 📝 Macro Call Format

### Basic Macro Call:
```assembly
MACRO_NAME  ACTUAL_PARAM1, ACTUAL_PARAM2, ...
```

### Sample Input with Macro Calls:
```assembly
START   100
        INCR    A, B        ; Call INCR macro
        DECR    C           ; Call DECR macro
        PRINT   A
        STOP
A       DS      1
B       DS      1
C       DS      1
        END
```

### Corresponding Macro Definitions (from Pass 1):
```assembly
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
```

## 📊 Expansion Process

### Step-by-Step Expansion:

1. **Input Processing**:
```assembly
INCR    A, B
```

2. **Macro Lookup**: Find INCR in MNT
3. **Parameter Binding**:
   - &A → A
   - &B → B

4. **MDT Retrieval**: Get macro body from MDT
5. **Parameter Substitution**:
```assembly
MOVER   R1, A      ; &A replaced with A
ADD     R1, B      ; &B replaced with B  
MOVEM   R1, A      ; &A replaced with A
```

6. **Output Generation**: Write expanded lines

### Final Expanded Output:
```assembly
START   100
        MOVER   R1, A      ; Expanded from INCR A, B
        ADD     R1, B
        MOVEM   R1, A
        MOVER   R1, C      ; Expanded from DECR C
        SUB     R1, ='1'
        MOVEM   R1, C
        PRINT   A
        STOP
A       DS      1
B       DS      1
C       DS      1
        END
```

## 🔄 Algorithm Steps

### Pass 2 Processing:
1. **Initialize**: Load MNT, MDT, ALA tables
2. **Read Source Line**: Get next source line
3. **Check Macro Call**:
   ```java
   String macroName = extractMacroName(line);
   if (mnt.contains(macroName)) {
       expandMacro(macroName, extractActualParams(line));
   } else {
       outputLine(line); // Copy as-is
   }
   ```
4. **Expand Macro**: Substitute parameters and generate code
5. **Handle Nesting**: Process nested macro calls
6. **Continue**: Until end of source

### Parameter Substitution:
```java
private String substituteParameters(String macroLine, String[] actualParams, String[] formalParams) {
    String result = macroLine;
    for (int i = 0; i < formalParams.length; i++) {
        if (i < actualParams.length) {
            result = result.replace(formalParams[i], actualParams[i]);
        }
    }
    return result;
}
```

### Macro Expansion Engine:
```java
private void expandMacro(String macroName, String[] actualParams) {
    MNTEntry mntEntry = mnt.get(macroName);
    int mdtIndex = mntEntry.mdtIndex;
    
    // Build parameter substitution table
    String[] formalParams = getFormalParams(mntEntry.alaIndex, mntEntry.paramCount);
    
    // Expand macro body
    while (!mdt.get(mdtIndex).equals("MEND")) {
        String expandedLine = substituteParameters(mdt.get(mdtIndex), actualParams, formalParams);
        
        // Check for nested macro calls
        if (isNestedMacroCall(expandedLine)) {
            handleNestedCall(expandedLine);
        } else {
            outputExpandedLine(expandedLine);
        }
        mdtIndex++;
    }
}
```

## 📈 Complexity Analysis

- **Time Complexity**: O(n × m) where:
  - n = number of source lines
  - m = average macro expansion size
- **Space Complexity**: O(d + s) where:
  - d = total macro definition size
  - s = expansion stack depth
- **Parameter Substitution**: O(p × l) where p = parameters, l = line length

## 🛠️ Advanced Features

### Nested Macro Calls:
```java
private void handleNestedCall(String line) {
    // Push current context to stack
    expansionStack.push(currentContext);
    
    // Process nested call
    expandMacro(extractMacroName(line), extractActualParams(line));
    
    // Pop context and continue
    currentContext = expansionStack.pop();
}
```

### Conditional Expansion:
```assembly
; AIF directive support
AIF     (&PARAM EQ 0).SKIP
MOVER   R1, &PARAM
.SKIP   ANOP
```

### Label Generation:
```java
private String generateUniqueLabel(String baseLabel) {
    return baseLabel + "_" + macroCallCounter + "_" + expansionLevel;
}
```

## 🔍 Error Handling

### Common Errors:
1. **Parameter Mismatch**: Wrong number of actual parameters
2. **Undefined Macro**: Macro call without definition
3. **Infinite Recursion**: Circular macro calls
4. **Invalid Parameters**: Malformed parameter list

### Error Detection & Recovery:
```java
// Parameter count validation
if (actualParams.length != formalParams.length) {
    System.err.println("Error: Parameter count mismatch in macro " + macroName);
    System.err.println("Expected: " + formalParams.length + ", Got: " + actualParams.length);
    return false;
}

// Recursion detection
if (expansionStack.contains(macroName)) {
    System.err.println("Error: Recursive macro call detected - " + macroName);
    return false;
}

// Undefined macro handling
if (!mnt.containsKey(macroName)) {
    System.err.println("Warning: Undefined macro - " + macroName);
    outputLine(originalLine); // Copy as-is
    return true;
}
```

## 🧪 Test Cases

### Test Case 1: Simple Expansion
```assembly
Input:  SIMPLE  X
Macro:  SIMPLE MACRO &A
            MOVER R1, &A
            MEND
Output: MOVER R1, X
```

### Test Case 2: Multiple Parameters
```assembly
Input:  SWAP    P, Q, TEMP
Output: MOVER   R1, P
        MOVEM   R1, TEMP
        MOVER   R1, Q
        MOVEM   R1, P
        MOVER   R1, TEMP
        MOVEM   R1, Q
```

### Test Case 3: Nested Calls
```assembly
OUTER   MACRO   &A
        INNER   &A
        MEND

INNER   MACRO   &B
        MOVER   R1, &B
        MEND

Call:   OUTER   X
Output: MOVER   R1, X
```

## 📋 Optimization Techniques

### Efficient Parameter Substitution:
```java
// Use StringBuilder for large substitutions
StringBuilder result = new StringBuilder(macroLine);
for (int i = 0; i < formalParams.length; i++) {
    String formal = formalParams[i];
    String actual = actualParams[i];
    replaceAll(result, formal, actual);
}
```

### Caching Mechanisms:
```java
// Cache frequently used expansions
private Map<String, String[]> expansionCache = new HashMap<>();

private String[] getCachedExpansion(String macroName, String[] params) {
    String key = macroName + Arrays.toString(params);
    return expansionCache.get(key);
}
```

## 🎓 Learning Objectives

- Understand macro expansion algorithms
- Learn parameter substitution techniques
- Practice recursive processing
- Implement stack-based expansion
- Study text processing patterns

## 🔗 Integration Points

### With Pass 1:
- Uses all tables generated by Pass 1
- Maintains consistency with macro definitions
- Preserves parameter semantics

### With Assembler:
- Generates standard assembly code
- Maintains label scope and addressing
- Produces assembler-ready output

### With Symbol Table:
- Handles macro-generated symbols
- Manages label scope expansion
- Resolves local vs global symbols

## 📚 Advanced Topics

### Macro Libraries:
```java
// Support for include files
if (line.startsWith("INCLUDE")) {
    String libraryFile = extractFileName(line);
    processMacroLibrary(libraryFile);
}
```

### Macro Arithmetic:
```assembly
; Arithmetic expressions in macros
SET     &RESULT = &A + &B * 2
```

### String Operations:
```assembly
; String manipulation in macros
&LABEL  SETC    '&PREFIX'.'&SUFFIX'
```

## 🔧 Performance Tuning

### Memory Management:
- Efficient string operations
- Minimize object creation
- Reuse expansion contexts

### Processing Speed:
- Optimize parameter lookup
- Cache macro definitions
- Parallel expansion where possible

## 🎯 Quality Assurance

### Testing Strategy:
1. **Unit Tests**: Individual macro expansions
2. **Integration Tests**: Multiple macro interactions
3. **Stress Tests**: Deep nesting, large macros
4. **Edge Cases**: Empty macros, no parameters

### Validation Checks:
```java
// Validate expansion correctness
private boolean validateExpansion(String original, String expanded) {
    // Check line count consistency
    // Verify parameter substitution
    // Ensure no undefined references
    return true;
}
```