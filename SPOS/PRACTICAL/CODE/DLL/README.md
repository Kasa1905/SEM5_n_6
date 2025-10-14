# ArithmeticJNI - Cross-Platform Java Native Interface Example

This project demonstrates Java Native Interface (JNI) with C functions for arithmetic operations, designed to work seamlessly across Windows, macOS, and Linux.

## 🎯 Features

- **Cross-Platform**: Automatically detects OS and loads appropriate native library
- **Auto-Build**: Platform-specific build scripts included
- **Error Handling**: Clear error messages for missing libraries or compilation issues
- **Simple Interface**: Easy-to-use arithmetic operations (add, subtract, multiply, divide)

## 📁 Project Structure

```
DLL/
├── ArithmeticJNI.java      # Main Java class with native methods
├── ArithmeticJNI.c         # C implementation of native methods
├── ArithmeticJNI.h         # JNI header file (auto-generated)
├── build.sh               # Build script for macOS/Linux
├── build.bat              # Build script for Windows
├── README.md              # This file
│
├── libArithmeticLib.dylib  # macOS native library (generated)
├── ArithmeticLib.dll       # Windows native library (generated)
└── libArithmeticLib.so     # Linux native library (generated)
```

## 🚀 Quick Start

### Prerequisites
- **Java JDK 8+** (with JAVA_HOME set)
- **GCC compiler** or equivalent:
  - **macOS**: Xcode Command Line Tools (`xcode-select --install`)
  - **Linux**: `sudo apt install gcc` or `sudo yum install gcc`
  - **Windows**: MinGW-w64 or Visual Studio Build Tools

### Option A: Use Build Scripts (Recommended)

#### macOS/Linux:
```bash
./build.sh
java -Djava.library.path=. ArithmeticJNI
```

#### Windows:
```cmd
build.bat
java -Djava.library.path=. ArithmeticJNI
```

### Option B: Manual Build

#### macOS:
```bash
javac ArithmeticJNI.java
gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" \
    ArithmeticJNI.c -o libArithmeticLib.dylib
java -Djava.library.path=. ArithmeticJNI
```

#### Linux:
```bash
javac ArithmeticJNI.java
gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" \
    ArithmeticJNI.c -o libArithmeticLib.so
java -Djava.library.path=. ArithmeticJNI
```

#### Windows:
```cmd
javac ArithmeticJNI.java
gcc -shared -fPIC -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" ^
    ArithmeticJNI.c -o ArithmeticLib.dll
java -Djava.library.path=. ArithmeticJNI
```

## 🔧 How It Works

1. **Java Side**: `ArithmeticJNI.java` declares native methods and loads the appropriate library based on OS detection
2. **C Side**: `ArithmeticJNI.c` implements the actual arithmetic functions
3. **JNI Bridge**: `ArithmeticJNI.h` provides the interface between Java and C
4. **Runtime**: Java automatically selects the correct native library file:
   - Windows: `ArithmeticLib.dll`
   - macOS: `libArithmeticLib.dylib`
   - Linux: `libArithmeticLib.so`

## 📱 Sample Output

```
Detected OS: mac os x (aarch64)
Loading macOS dylib...
✅ Native library loaded successfully!
Enter Any two no.:
10
5
Addition: 15
Subtraction: 5
Multiplication: 50
Division: 2.0
```

## 🛠️ Troubleshooting

### Common Issues:

1. **`UnsatisfiedLinkError`**:
   - Ensure the native library exists for your OS
   - Run with `-Djava.library.path=.` flag
   - Check that JAVA_HOME is set correctly

2. **Compilation Errors**:
   - Verify GCC is installed and accessible
   - Check JAVA_HOME points to JDK (not JRE)
   - Ensure JNI headers exist in `$JAVA_HOME/include`

3. **Missing JNI Headers**:
   - Make sure you have the full JDK installed
   - On some Linux distributions: `sudo apt install default-jdk`

### Debug Commands:
```bash
# Check Java installation
java -version
echo $JAVA_HOME

# Check if native library exists
ls -la *.dll *.so *.dylib

# Verbose JNI loading
java -verbose:jni -Djava.library.path=. ArithmeticJNI
```

## 🔄 Cross-Platform Deployment

To distribute this application:

1. **Build for all platforms** using the respective build scripts
2. **Package all native libraries** (.dll, .so, .dylib) with your JAR
3. **Java automatically selects** the correct library at runtime
4. **No code changes needed** when moving between platforms

## 📚 Learning Points

- **JNI Basics**: How to call C functions from Java
- **Cross-Platform Development**: Handling OS differences in native code
- **Build Automation**: Platform-specific compilation and linking
- **Library Loading**: Dynamic library loading strategies
- **Error Handling**: Robust error reporting for native code issues

## 🎓 Educational Value

This project is perfect for understanding:
- Java Native Interface (JNI) fundamentals
- Cross-platform native development
- Build system automation
- Operating system differences in shared libraries
- Integration between high-level and low-level programming languages