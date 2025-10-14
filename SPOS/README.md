# 🖥️ SPOS (System Programming and Operating System) Laboratory

[![Cross-Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-brightgreen)](https://github.com/Kasa1905/SEM5)
[![Java](https://img.shields.io/badge/Java-8%2B-orange)](https://www.oracle.com/java/)
[![Build System](https://img.shields.io/badge/Build-Cross--Platform%20Scripts-blue)](https://github.com/Kasa1905/SEM5)

Complete implementation of SPOS laboratory programs with **cross-platform compatibility**, **easy-to-use build systems**, and **comprehensive documentation**. Ready to run on Windows, macOS, and Linux with minimal setup.

## 🎯 Features

- ✅ **28 Complete Programs** covering all SPOS concepts
- ✅ **Cross-Platform Build Scripts** for seamless development
- ✅ **Input Files Included** for immediate testing
- ✅ **Compiled Classes** for quick execution
- ✅ **Comprehensive Documentation** with usage examples
- ✅ **OS-Independent** design for universal compatibility

## 📁 Project Structure

```
SPOS/
├── �� PRACTICAL/CODE/           # All laboratory implementations
│   ├── 🔄 CPU-Scheduling/       # FCFS, SJF, Priority, Round Robin
│   ├── 🧠 Memory-Management/    # First Fit, Best Fit, Worst Fit
│   ├── 📄 Page-Replacement/     # FIFO, LRU, Optimal
│   ├── ⚙️  Pass1-Assembler/      # Two-pass assembler (Pass 1)
│   ├── ⚙️  Pass2-Assembler/      # Two-pass assembler (Pass 2)
│   ├── 🔧 Macro-Pass1/          # Macro processor (Pass 1)
│   ├── 🔧 Macro-Pass2/          # Macro processor (Pass 2)
│   ├── 🔒 Deadlock-Bankers/     # Banker's algorithm
│   ├── 👥 Reader-Writer-Problem/ # Synchronization problem
│   ├── 🔗 JNI-DLL/              # Java Native Interface (Simplified)
│   └── 🔗 DLL/                  # Java Native Interface (Advanced)
├── 📂 Documentation/            # Academic documentation and writeups
├── 🛠️ build.sh / build.bat      # Universal build scripts
└── 📖 README.md                 # This file
```

## 🚀 Quick Start

### Prerequisites
- **Java JDK 8+** (with `javac` and `java` in PATH)
- **GCC compiler** (for JNI programs only):
  - *macOS*: `xcode-select --install`
  - *Linux*: `sudo apt install gcc`
  - *Windows*: MinGW-w64 or Visual Studio Build Tools

### Option A: Build Everything (Recommended)
```bash
# macOS/Linux
./build.sh

# Windows
build.bat
```

### Option B: Run Individual Programs
Each folder has its own build script and README:

```bash
# Example: CPU Scheduling
cd PRACTICAL/CODE/CPU-Scheduling
javac FCFS.java && java FCFS

# Example: JNI Calculator (Simplified)
cd PRACTICAL/CODE/JNI-DLL
./simple_build.sh
java -Djava.library.path=. SimpleMathCalculator

# Example: Pass 1 Assembler
cd PRACTICAL/CODE/Pass1-Assembler
./build.sh
java pass1
```

## 📋 Program Categories

### 🔄 CPU Scheduling Algorithms
- **FCFS.java** - First Come First Served
- **SJF.java** - Shortest Job First (Non-Preemptive)
- **SJFNP.java** - Shortest Job First (Preemptive)
- **PriorityNP.java** - Priority Scheduling (Non-Preemptive)
- **PriorityP.java** - Priority Scheduling (Preemptive)
- **RoundRobin.java** - Round Robin with time quantum

### 🧠 Memory Management
- **First_fit.java** - First Fit allocation algorithm
- **Best_fit.java** - Best Fit allocation algorithm
- **Worst_fit.java** - Worst Fit allocation algorithm

### 📄 Page Replacement
- **FIFO.java** - First In First Out
- **LRU.java** - Least Recently Used
- **Optimal.java** - Optimal Page Replacement

### ⚙️ Assemblers & Macro Processors
- **Pass1-Assembler** - Symbol table generation, intermediate code
- **Pass2-Assembler** - Machine code generation, address resolution
- **Macro-Pass1** - Macro definition processing, MNT/MDT creation
- **Macro-Pass2** - Macro expansion and parameter substitution

### 🔒 Synchronization & Deadlock
- **bankers.java** - Banker's algorithm for deadlock prevention
- **ReaderWriterProblem.java** - Classic synchronization problem

### 🔗 Java Native Interface (JNI)
- **JNI-DLL** - Simplified version for beginners (recommended)
- **DLL** - Advanced version with cross-platform features

## 🛠️ Build System Features

### Cross-Platform Compatibility
- **Automatic OS detection** and appropriate compilation
- **Universal build scripts** that work everywhere
- **Library path management** for native code
- **Error handling** with clear troubleshooting messages

### Easy Testing
- **Input files included** - no need to create test data
- **Compiled classes provided** - run immediately if needed
- **Sample outputs** in documentation
- **Step-by-step instructions** in each README

## 📚 Learning Path

### Beginners
1. Start with **CPU-Scheduling** (simple algorithms)
2. Try **Memory-Management** (basic concepts)
3. Explore **Page-Replacement** (OS fundamentals)

### Intermediate
4. **Pass1-Assembler** & **Pass2-Assembler** (system programming)
5. **Macro-Pass1** & **Macro-Pass2** (language processors)
6. **Deadlock-Bankers** (resource management)

### Advanced
7. **Reader-Writer-Problem** (synchronization)
8. **JNI-DLL** (native integration)

## 🔧 Individual Program Usage

Each program directory contains:
- **Source code** (`.java` files)
- **Input files** (sample test data)
- **README.md** (specific instructions)
- **Build scripts** (platform-specific)
- **Compiled classes** (for quick testing)

### Example: Running CPU Scheduling
```bash
cd PRACTICAL/CODE/CPU-Scheduling
java FCFS
# Enter process data when prompted
```

### Example: Running JNI Calculator
```bash
cd PRACTICAL/CODE/JNI-DLL
./simple_build.sh        # Build native library
java -Djava.library.path=. SimpleMathCalculator
```

### Example: Running Pass 2 Assembler
```bash
cd PRACTICAL/CODE/Pass2-Assembler
./prepare_output.sh      # Prepare input files from Pass 1
java Pass2
# Enter symbol and literal table data
```

## 🐛 Troubleshooting

### Common Issues
- **"javac not found"**: Install JDK and ensure it's in PATH
- **"Permission denied"**: Run `chmod +x *.sh` on Unix systems
- **"UnsatisfiedLinkError"**: For JNI programs, ensure native library exists and use `-Djava.library.path=.`
- **"Input file not found"**: Check that input files exist in program directory

### Platform-Specific Notes
- **Windows**: Use `.bat` scripts or Git Bash for `.sh` scripts
- **macOS**: May need to install Xcode Command Line Tools for native compilation
- **Linux**: Ensure GCC and JDK development packages are installed

## �� Educational Value

This repository demonstrates:
- **Operating System Concepts**: Process scheduling, memory management, synchronization
- **System Programming**: Assemblers, macro processors, native interfaces
- **Algorithm Implementation**: Various OS algorithms with practical examples
- **Cross-Platform Development**: Writing code that runs everywhere
- **Build Automation**: Streamlined compilation and execution processes

## 📈 Academic Features

- **Complete Coverage** of SPOS syllabus
- **Working Examples** with real input/output
- **Documentation** for each concept
- **Testing Data** included for immediate verification
- **Professional Structure** suitable for submission

## 🤝 Contributing

To add new programs or improvements:
1. Follow the existing directory structure
2. Include comprehensive README.md
3. Add cross-platform build scripts
4. Provide sample input files
5. Test on multiple operating systems

## 📄 License

This project is created for educational purposes. Feel free to use and modify for academic learning.

---

### 🎯 Ready to Start?

```bash
# Clone and run everything
git clone https://github.com/Kasa1905/SEM5.git
cd SEM5/SPOS
./build.sh        # macOS/Linux
# OR
build.bat         # Windows
```

**Happy Learning! 🚀**
