# 🎓 Semester 5 - Computer Science Lab Implementations

Welcome to my **Semester 5** repository containing comprehensive implementations of core Computer Science laboratory programs and assignments.

## 📚 Subjects Covered

### 🖥️ **SPOS (System Programming and Operating System)**
Complete implementation of system programming concepts including:

- **CPU Scheduling Algorithms** (FCFS, SJF, Priority, Round Robin)
- **Memory Management** (First Fit, Best Fit, Worst Fit)
- **Page Replacement Algorithms** (FIFO, LRU, Optimal)
- **Deadlock Prevention** (Banker's Algorithm)
- **Assembler Implementation** (Two-Pass Assembler)
- **Macro Processor** (Two-Pass Macro Processing)
- **Synchronization** (Reader-Writer Problem)
- **DLL Implementation** (Java Native Interface with C for Dynamic Link Libraries)

## 🗂️ Repository Structure

```
SEM5/
├── SPOS/                          # System Programming & OS
│   ├── PRACTICAL/CODE/            # All lab implementations
│   │   ├── CPU-Scheduling/        # Scheduling algorithms
│   │   ├── Memory-Management/     # Memory allocation algorithms
│   │   ├── Page-Replacement/      # Paging algorithms
│   │   ├── Deadlock-Bankers/      # Deadlock prevention
│   │   ├── Pass1-Assembler/       # Assembler Pass 1
│   │   ├── Pass2-Assembler/       # Assembler Pass 2
│   │   ├── Macro-Pass1/           # Macro Processor Pass 1
│   │   ├── Macro-Pass2/           # Macro Processor Pass 2
│   │   └── Reader-Writer-Problem/ # Synchronization
│   ├── EXAM/                      # Organized exam questions
│   │   ├── Q1/                    # Pass1 Assembler
│   │   ├── Q2/                    # Pass2 Assembler
│   │   ├── Q3/                    # Macro Pass1
│   │   ├── Q4/                    # Macro Pass2
│   │   ├── Q5/                    # DLL Implementation (JNI)
│   │   ├── Q6/                    # Best Fit Memory Management
│   │   ├── Q7/                    # FCFS Scheduling
│   │   ├── Q8/                    # SJF Scheduling
│   │   ├── Q9/                    # Round Robin Scheduling
│   │   ├── Q10/                   # Priority Non-Preemptive
│   │   ├── Q11/                   # Priority Preemptive
│   │   ├── Q12/                   # Reader-Writer Problem
│   │   ├── Q13/                   # JNI Calculator
│   │   ├── Q14/                   # Worst Fit Memory Management
│   │   ├── Q15/                   # FIFO Page Replacement
│   │   ├── Q16/                   # Optimal Page Replacement
│   │   ├── Q17/                   # LRU Page Replacement
│   │   └── Q18/                   # Banker's Algorithm
│   ├── Documentation/             # Lab manuals and guides
│   ├── build.sh / build.bat       # Cross-platform build scripts
│   └── README.md                  # Detailed SPOS guide
├── LICENSE                        # MIT License
└── README.md                      # This file
```

## 🚀 Quick Start

### Prerequisites
- **Java JDK 8+** (for Java programs)
- **GCC/Clang** (for C programs and JNI)
- **Git** (for version control)

### Building and Running

#### 🔨 **Build All Programs (Recommended)**
```bash
# Clone the repository
git clone <your-repo-url>
cd SEM5/SPOS

# Build all programs (Unix/Linux/macOS)
./build.sh

# Build all programs (Windows)
build.bat
```

#### 🎯 **Run Individual Programs**
```bash
# Navigate to specific program directory
cd SPOS/PRACTICAL/CODE/CPU-Scheduling

# Compile and run
javac FCFS.java
java FCFS

# Or use individual build scripts where available
./build.sh    # Unix/Linux/macOS
build.bat     # Windows
```

## 📋 Program Categories

### 1. **CPU Scheduling Algorithms**
- **FCFS** - First Come First Served
- **SJF** - Shortest Job First  
- **Priority** - Priority-based scheduling (Preemptive & Non-preemptive)
- **Round Robin** - Time quantum-based scheduling

### 2. **Memory Management**
- **First Fit** - First available block allocation
- **Best Fit** - Smallest suitable block allocation
- **Worst Fit** - Largest available block allocation

### 3. **Page Replacement**
- **FIFO** - First In First Out
- **LRU** - Least Recently Used
- **Optimal** - Optimal page replacement

### 4. **System Programming**
- **Two-Pass Assembler** - Symbol table generation and machine code
- **Two-Pass Macro Processor** - Macro definition and expansion
- **Banker's Algorithm** - Deadlock avoidance
- **Reader-Writer Problem** - Process synchronization

### 5. **Advanced Integration**
- **JNI (Java Native Interface)** - Java-C integration for performance-critical operations

## 🛠️ Features

### ✅ **Cross-Platform Compatibility**
- Works on **Windows**, **macOS**, and **Linux**
- Automated build scripts for all platforms
- Relative file paths for portability

### ✅ **Professional Code Quality**
- Clean, well-documented source code
- Comprehensive error handling
- User-friendly interfaces
- Detailed README files for each program

### ✅ **Educational Focus**
- Clear algorithm implementations
- Step-by-step execution traces
- Input/output examples
- Learning objectives documented

### ✅ **Complete Documentation**
- Individual README files for each program
- Algorithm explanations and complexity analysis
- Usage instructions and examples
- Build and deployment guides

## 📖 Documentation

Each program directory contains:
- **README.md** - Program-specific documentation
- **Source Code** - Well-commented implementations
- **Input Files** - Sample test cases
- **Build Scripts** - Compilation automation

## 🤝 Contributing

This repository is primarily for educational purposes. Feel free to:
- Report bugs or issues
- Suggest improvements
- Fork for your own educational use
- Submit pull requests for enhancements

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Kaushik Sambe**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Course instructors and lab coordinators
- Reference textbooks and online resources
- Open-source community for tools and libraries

---

### 📈 Repository Stats
- **Languages**: Java, C, Shell Scripts
- **Programs**: 28+ implementations
- **Categories**: 8 major areas
- **Documentation**: Comprehensive guides and examples

**⭐ Star this repository if you find it helpful!**
