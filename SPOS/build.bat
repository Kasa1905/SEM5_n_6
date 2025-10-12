@echo off
REM SPOS Build Script for Windows
REM This script compiles all Java programs in the SPOS repository

echo 🚀 SPOS Build Script - Compiling all Java programs...
echo ==================================================

REM Function to compile Java files in a directory
goto :main

:compile_directory
set "dir=%~1"
set "name=%~2"

if exist "%dir%" (
    echo 📂 Compiling %name%...
    cd /d "%dir%"
    
    REM Check if Java files exist and compile all together
    dir /b *.java >nul 2>&1
    if !errorlevel! equ 0 (
        echo    ⚡ Compiling all Java files together...
        javac *.java
        if !errorlevel! equ 0 (
            echo    ✅ All Java files compiled successfully
            REM List compiled files for verification
            for %%f in (*.java) do (
                echo    ✅ %%f compiled successfully
            )
        ) else (
            echo    ❌ Error compiling Java files
        )
    ) else (
        echo    ℹ️  No Java files found in %dir%
    )
    
    cd /d "%~dp0PRACTICAL\CODE"
    echo.
) else (
    echo ❌ Directory %dir% not found
    echo.
)
goto :eof

:main
setlocal enabledelayedexpansion

REM Navigate to PRACTICAL\CODE directory
if exist "PRACTICAL\CODE" (
    cd /d "PRACTICAL\CODE"
) else (
    echo ❌ PRACTICAL\CODE directory not found!
    echo Please run this script from the SPOS root directory.
    pause
    exit /b 1
)

REM Compile all program categories
call :compile_directory "CPU-Scheduling" "CPU Scheduling Algorithms"
call :compile_directory "Memory-Management" "Memory Management Algorithms"
call :compile_directory "Page-Replacement" "Page Replacement Algorithms"
call :compile_directory "Bankers-Algorithm" "Banker's Algorithm"
call :compile_directory "Pass1-Assembler" "Pass 1 Assembler"
call :compile_directory "Pass2-Assembler" "Pass 2 Assembler"
call :compile_directory "Macro-Pass1" "Macro Processor Pass 1"
call :compile_directory "Macro-Pass2" "Macro Processor Pass 2"
call :compile_directory "Reader-Writer-Problem" "Reader-Writer Problem"

REM Special handling for JNI-DLL
if exist "JNI-DLL" (
    echo 📂 Compiling JNI-DLL ^(Java Native Interface^)...
    cd /d "JNI-DLL"
    
    REM Compile Java files first
    for %%f in (*.java) do (
        echo    ⚡ Compiling %%f
        javac "%%f"
        if !errorlevel! equ 0 (
            echo    ✅ %%f compiled successfully
        ) else (
            echo    ❌ Error compiling %%f
        )
    )
    
    REM Note about native compilation
    echo    ⚠️  For native library compilation on Windows:
    echo    1. Install Microsoft Visual Studio or MinGW
    echo    2. Run: gcc -shared -o mathops.dll mathops.c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32"
    
    cd /d "%~dp0PRACTICAL\CODE"
    echo.
)

echo 🎉 Build complete!
echo.
echo 📋 Usage Instructions:
echo ====================
echo 1. Navigate to any program directory
echo 2. Run: java ^<ClassName^>
echo 3. Follow the program prompts
echo.
echo Example:
echo cd CPU-Scheduling ^&^& java FCFS
echo.
echo For JNI programs:
echo cd JNI-DLL ^&^& java InteractiveDMASCalculator

pause