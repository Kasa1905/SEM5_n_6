#!/bin/bash

echo "=== Building JNI Calculator DLL ==="

# Step 1: Compile Java file
echo "Step 1: Compiling Java file..."
javac MathCalculator.java
if [ $? -ne 0 ]; then
    echo "Error: Java compilation failed"
    exit 1
fi

# Step 2: Generate header file
echo "Step 2: Generating header file..."
javac -h . MathCalculator.java
if [ $? -ne 0 ]; then
    echo "Error: Header generation failed"
    exit 1
fi

# Step 3: Compile C library
echo "Step 3: Compiling native library..."
if [[ "$OSTYPE" == "darwin"* ]]; then
    # macOS
    gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" mathcalc.c -o libmathcalc.dylib
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Linux
    gcc -shared -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" mathcalc.c -o libmathcalc.so
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
    # Windows
    gcc -shared -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" mathcalc.c -o mathcalc.dll
else
    echo "Unsupported OS: $OSTYPE"
    exit 1
fi

if [ $? -ne 0 ]; then
    echo "Error: Native library compilation failed"
    echo "Make sure JAVA_HOME is set correctly"
    echo "Current JAVA_HOME: $JAVA_HOME"
    exit 1
fi

echo "=== Build Complete! ==="
echo "Running the program..."

# Step 4: Run the program
java -Djava.library.path=. MathCalculator