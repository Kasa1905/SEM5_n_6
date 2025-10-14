@echo off
REM Simple build script for Windows JNI Math Calculator

echo 🔧 Simple JNI Build Script (Windows)
echo ====================================

REM Step 1: Compile Java source
echo 📦 Step 1: Compiling Java...
javac SimpleMathCalculator.java
if %ERRORLEVEL% neq 0 (
    echo ❌ Java compilation failed
    exit /b 1
)

REM Step 2: Generate JNI header
echo 📋 Step 2: Generating JNI header...
javac -h . SimpleMathCalculator.java

REM Step 3: Check for JAVA_HOME
if not defined JAVA_HOME (
    echo ❌ JAVA_HOME not set. Please set it to your JDK installation
    echo Example: set JAVA_HOME=C:\Program Files\Java\jdk-11.0.1
    exit /b 1
)

echo ☕ Using JAVA_HOME: %JAVA_HOME%

REM Step 4: Build native library
echo 🔨 Step 3: Building native library...
gcc -shared -fPIC ^
    -I"%JAVA_HOME%\include" ^
    -I"%JAVA_HOME%\include\win32" ^
    simple_math.c ^
    -o mathops.dll

if %ERRORLEVEL% neq 0 (
    echo ❌ Native library compilation failed
    echo Make sure you have MinGW-w64 or Visual Studio Build Tools installed
    exit /b 1
)

echo ✅ Built: mathops.dll
echo.
echo 🎉 Build completed successfully!
echo.
echo 🚀 To run the calculator:
echo java -Djava.library.path=. SimpleMathCalculator
echo.
echo 📁 Generated files:
dir SimpleMathCalculator.class SimpleMathCalculator.h mathops.dll 2>nul