#!/bin/bash
echo "Building Pass1 Assembler..."
javac -d . pass1_.java
if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "To run: java pass1_"
else
    echo "Build failed!"
fi