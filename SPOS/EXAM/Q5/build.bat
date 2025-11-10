@echo off
echo === Building JNI Calculator DLL ===

REM Step 1: Compile Java file
echo Step 1: Compiling Java file...
javac MathCalculator.java
if errorlevel 1 (
    echo Error: Java compilation failed
    exit /b 1
)

REM Step 2: Generate header file
echo Step 2: Generating header file...
javac -h . MathCalculator.java
if errorlevel 1 (
    echo Error: Header generation failed
    exit /b 1
)

REM Step 3: Compile C library
echo Step 3: Compiling native library...
gcc -shared -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" mathcalc.c -o mathcalc.dll
if errorlevel 1 (
    echo Error: Native library compilation failed
    echo Make sure JAVA_HOME is set correctly
    echo Current JAVA_HOME: %JAVA_HOME%
    exit /b 1
)

echo === Build Complete! ===
echo Running the program...

REM Step 4: Run the program
java -Djava.library.path=. MathCalculator