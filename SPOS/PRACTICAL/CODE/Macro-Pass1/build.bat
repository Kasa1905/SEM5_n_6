@echo off
echo Building Macro Pass1...
javac *.java
if %errorlevel% equ 0 (
    echo Build successful!
    echo To run: java MacroPass1
    echo To test: java TestMacroProcessor
) else (
    echo Build failed!
)
pause