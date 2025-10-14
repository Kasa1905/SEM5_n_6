@echo off
REM Cross-platform JNI build script for Windows
echo 🔧 Windows JNI Build Script
echo ==============================

REM Find Java installation
if defined JAVA_HOME (
    set JDK_HOME=%JAVA_HOME%
) else (
    echo ❌ JAVA_HOME not set. Please set JAVA_HOME to your JDK installation
    exit /b 1
)

echo Using JDK: %JDK_HOME%

REM Compile Java source
echo 📦 Compiling Java source...
javac ArithmeticJNI.java
if %ERRORLEVEL% neq 0 (
    echo ❌ Java compilation failed
    exit /b 1
)

REM Generate JNI header if needed
if not exist ArithmeticJNI.h (
    echo 📋 Generating JNI header...
    javac -h . ArithmeticJNI.java
)

REM Build native library for Windows
echo 🔨 Building native library for Windows...
gcc -shared -fPIC ^
    -I"%JDK_HOME%\include" ^
    -I"%JDK_HOME%\include\win32" ^
    ArithmeticJNI.c ^
    -o ArithmeticLib.dll

if %ERRORLEVEL% neq 0 (
    echo ❌ Native library compilation failed
    echo Make sure you have MinGW or Visual Studio Build Tools installed
    exit /b 1
)

echo ✅ Created: ArithmeticLib.dll

echo.
echo 🎉 Build completed successfully!
echo 📁 Generated files:
dir ArithmeticJNI.class ArithmeticLib.dll 2>nul

echo.
echo 🚀 To run:
echo java -Djava.library.path=. ArithmeticJNI