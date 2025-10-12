#!/bin/bash
echo "Building Macro Pass1..."
javac *.java
if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "To run: java MacroPass1"
    echo "To test: java TestMacroProcessor"
else
    echo "Build failed!"
fi