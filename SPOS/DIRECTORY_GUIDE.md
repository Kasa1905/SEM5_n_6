# 📁 Directory Structure Guide

This file explains the current directory structure and helps you find the programs mentioned in the main README.

## 📋 Directory Mapping

The repository contains the following directories with their corresponding README documentation:

### ✅ Documented Programs (with README files):

| Main README Reference | Actual Directory | README Location | Status |
|----------------------|------------------|-----------------|---------|
| CPU-Scheduling | `CPU-Scheduling/` | ✅ Available | Complete |
| Deadlock-Bankers | `Deadlock-Bankers/` | ✅ Available | Complete |
| Pass1-Assembler | `Pass1-Assembler/` | ✅ Available | Complete |
| Pass2-Assembler | `Pass2-Assembler/` | ✅ Available | Complete |
| Macro-Pass1 | `Macro-Pass1/` | ✅ Available | Complete |
| Macro-Pass2 | `Macro-Pass2/` | ✅ Available | Complete |

### 📂 Additional Programs (no individual README yet):

| Directory | Contents | Description |
|-----------|----------|-------------|
| `Memory-Management/` | Best_fit.java, First_fit.java, Worst_fit.java | Memory allocation algorithms |
| `Page-Replacement/` | FIFO.java, LRU.java, Optimal.java | Page replacement strategies |
| `Bankers-Algorithm/` | bankers.java | Alternative Banker's algorithm implementation |
| `Reader-Writer-Problem/` | ReaderWriterProblem.java | Process synchronization solution |
| `JNI-DLL/` | InteractiveDMASCalculator.java, MathOperations.java | Java Native Interface example |

## 🎯 Quick Navigation

### For Documented Programs:
```bash
cd "PRACTICAL/CODE/CPU-Scheduling"     # See README for CPU scheduling
cd "PRACTICAL/CODE/Deadlock-Bankers"  # See README for Banker's algorithm
cd "PRACTICAL/CODE/Pass1-Assembler"   # See README for assembler pass 1
cd "PRACTICAL/CODE/Pass2-Assembler"   # See README for assembler pass 2
cd "PRACTICAL/CODE/Macro-Pass1"       # See README for macro pass 1
cd "PRACTICAL/CODE/Macro-Pass2"       # See README for macro pass 2
```

### For Additional Programs:
```bash
cd "PRACTICAL/CODE/Memory-Management"  # Memory allocation algorithms
cd "PRACTICAL/CODE/Page-Replacement"   # Page replacement algorithms
cd "PRACTICAL/CODE/Reader-Writer-Problem" # Synchronization example
cd "PRACTICAL/CODE/JNI-DLL"           # Native interface example
```

## 🔧 Running Programs

### All programs can be run using:
```bash
# Navigate to the desired directory
cd "PRACTICAL/CODE/<directory-name>"

# Compile (if needed)
javac *.java

# Run the main class
java <ClassName>
```

### Examples:
```bash
# Memory Management
cd "PRACTICAL/CODE/Memory-Management"
java First_fit

# Page Replacement
cd "PRACTICAL/CODE/Page-Replacement"
java FIFO

# Reader-Writer Problem
cd "PRACTICAL/CODE/Reader-Writer-Problem"
java ReaderWriterProblem

# JNI Calculator
cd "PRACTICAL/CODE/JNI-DLL"
java InteractiveDMASCalculator
```

## 📋 Build Script Coverage

The build scripts (`build.sh` and `build.bat`) automatically compile **ALL** programs in the repository, including:

✅ CPU Scheduling algorithms  
✅ Memory Management algorithms  
✅ Page Replacement algorithms  
✅ Deadlock/Banker's algorithms  
✅ Pass1 & Pass2 Assemblers  
✅ Macro Pass1 & Pass2 processors  
✅ Reader-Writer Problem  
✅ JNI-DLL native interface  

## 🚀 Getting Started

1. **Use the build scripts** to compile everything at once:
   ```bash
   ./build.sh        # Unix/Linux/macOS
   build.bat         # Windows
   ```

2. **Read individual README files** for detailed documentation of the main algorithms

3. **Explore additional programs** by navigating to their directories and running them directly

## 🎓 Learning Path Recommendation

### Beginner Level:
1. Start with `CPU-Scheduling/` programs
2. Try `Memory-Management/` algorithms
3. Explore `Page-Replacement/` strategies

### Intermediate Level:
4. Study `Deadlock-Bankers/` algorithm
5. Learn `Reader-Writer-Problem/` synchronization

### Advanced Level:
6. Master `Pass1-Assembler/` and `Pass2-Assembler/`
7. Understand `Macro-Pass1/` and `Macro-Pass2/`
8. Experiment with `JNI-DLL/` native interfaces

## 📝 Notes

- All Java programs are cross-platform compatible
- Input files are provided where needed
- Build scripts handle compilation automatically
- Individual README files provide detailed documentation for core algorithms
- Additional programs can be run directly without extensive setup

Happy Learning! 🎉