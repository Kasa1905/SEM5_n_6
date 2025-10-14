# Simple JNI Math Calculator

A simplified version of the JNI Math Calculator that anyone can compile and run with basic Java commands - no complex Makefiles needed!

## 🎯 What This Does

Simple calculator that performs basic math operations (add, subtract, multiply, divide) using:
- **Java**: User interface and main program
- **C**: Fast native calculations via JNI (Java Native Interface)

## 🚀 Super Easy Setup

### Option A: One-Command Build (Recommended)

**macOS/Linux:**
```bash
chmod +x simple_build.sh && ./simple_build.sh && java -Djava.library.path=. SimpleMathCalculator
```

**Windows:**
```cmd
simple_build.bat && java -Djava.library.path=. SimpleMathCalculator
```

### Option B: Manual Steps (If you want to understand each step)

**Step 1: Compile Java**
```bash
javac SimpleMathCalculator.java
```

**Step 2: Generate JNI Header**
```bash
javac -h . SimpleMathCalculator.java
```

**Step 3: Build Native Library**

*macOS:*
```bash
gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" \
    simple_math.c -o libmathops.dylib
```

*Linux:*
```bash
gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" \
    simple_math.c -o libmathops.so
```

*Windows:*
```cmd
gcc -shared -fPIC -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" ^
    simple_math.c -o mathops.dll
```

**Step 4: Run**
```bash
java -Djava.library.path=. SimpleMathCalculator
```

## 📋 Prerequisites

- **Java JDK** (any version 8+)
- **GCC compiler**:
  - *macOS*: `xcode-select --install`
  - *Linux*: `sudo apt install gcc` or `sudo yum install gcc`
  - *Windows*: Install MinGW-w64 or Visual Studio Build Tools

## 🖥️ Sample Usage

```
🧮 Simple Math Calculator (JNI Demo)
=====================================

Choose an operation:
1. Addition (+)
2. Subtraction (-)
3. Multiplication (×)
4. Division (÷)
5. Exit
Enter your choice (1-5): 1

Enter first number: 15.5
Enter second number: 4.2
────────────────────────────────────────
🔢 Addition Result:
15.50 + 4.20 = 19.700000
────────────────────────────────────────
```

## 📁 Project Files

```
JNI-DLL/
├── SimpleMathCalculator.java    # Main Java program (simple & clean)
├── simple_math.c               # C implementation (basic math functions)
├── simple_build.sh            # Build script for macOS/Linux
├── simple_build.bat           # Build script for Windows
└── README_SIMPLE.md           # This file
```

## 🔧 What Makes This Simple

1. **No Complex Makefiles**: Just basic shell scripts
2. **Single Java File**: All functionality in one clean file
3. **Basic C Code**: Simple math functions, easy to understand
4. **Cross-Platform**: Same commands work everywhere
5. **Clear Error Messages**: Tells you exactly what's wrong
6. **Auto-Detection**: Automatically finds Java and detects your OS

## 🐛 Troubleshooting

**Problem: "Cannot find javac"**
- Install JDK (not just JRE)
- Make sure `java` and `javac` are in your PATH

**Problem: "Cannot find gcc"**
- Install GCC compiler for your platform (see prerequisites above)

**Problem: "UnsatisfiedLinkError"**
- Make sure you run with `-Djava.library.path=.`
- Check that the native library file exists (.dll/.so/.dylib)

**Problem: "JAVA_HOME not set"** (Windows)
- Set JAVA_HOME: `set JAVA_HOME=C:\Program Files\Java\jdk-11.0.1`

## 🎓 Learning Value

This simplified version helps you understand:
- ✅ Basic JNI concepts without complexity
- ✅ How Java calls C functions
- ✅ Cross-platform native library building
- ✅ Simple build automation

Perfect for students and beginners who want to learn JNI without getting lost in complex build systems!

## 🔄 Comparison with Complex Version

| Feature | This Simple Version | Complex Version |
|---------|-------------------|-----------------|
| **Build** | `./simple_build.sh` | Complex Makefile |
| **Files** | 4 files | 10+ files |
| **Setup** | 30 seconds | Several minutes |
| **Learning** | Focus on JNI concepts | Focus on build systems |
| **Debugging** | Clear error messages | Complex error traces |