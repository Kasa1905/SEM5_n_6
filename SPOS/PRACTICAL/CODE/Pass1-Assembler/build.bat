@echo off
echo Building Pass1 Assembler...
javac -d . pass1_.java
if %errorlevel% equ 0 (
    echo Build successful!
    echo To run: java pass1_
) else (
    echo Build failed!
)
pause