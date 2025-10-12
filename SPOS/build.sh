#!/bin/bash

# SPOS Build Script for Unix/Linux/macOS
# This script compiles all Java programs in the SPOS repository

echo "🚀 SPOS Build Script - Compiling all Java programs..."
echo "=================================================="

# Function to compile Java files in a directory
compile_directory() {
    local dir="$1"
    local name="$2"
    
    if [ -d "$dir" ]; then
        echo "📂 Compiling $name..."
        cd "$dir"
        
        # Check if there are any Java files
        if ls *.java >/dev/null 2>&1; then
            # Compile all Java files together to handle dependencies
            echo "   ⚡ Compiling all Java files together..."
            javac *.java
            if [ $? -eq 0 ]; then
                echo "   ✅ All Java files compiled successfully"
                # List compiled files for verification
                for java_file in *.java; do
                    if [ -f "$java_file" ]; then
                        echo "   ✅ $java_file compiled successfully"
                    fi
                done
            else
                echo "   ❌ Error compiling Java files"
            fi
        else
            echo "   ℹ️  No Java files found in $dir"
        fi
        
        cd - > /dev/null
        echo ""
    else
        echo "❌ Directory $dir not found"
        echo ""
    fi
}

# Navigate to PRACTICAL/CODE directory
if [ -d "PRACTICAL/CODE" ]; then
    cd PRACTICAL/CODE
else
    echo "❌ PRACTICAL/CODE directory not found!"
    echo "Please run this script from the SPOS root directory."
    exit 1
fi

# Compile all program categories
compile_directory "CPU-Scheduling" "CPU Scheduling Algorithms"
compile_directory "Memory-Management" "Memory Management Algorithms"
compile_directory "Page-Replacement" "Page Replacement Algorithms"
compile_directory "Deadlock-Bankers" "Deadlock-Bankers Algorithm"
compile_directory "Pass1-Assembler" "Pass 1 Assembler"
compile_directory "Pass2-Assembler" "Pass 2 Assembler"
compile_directory "Macro-Pass1" "Macro Processor Pass 1"
compile_directory "Macro-Pass2" "Macro Processor Pass 2"
compile_directory "Reader-Writer-Problem" "Reader-Writer Problem"

# Special handling for JNI-DLL (requires native compilation)
if [ -d "JNI-DLL" ]; then
    echo "📂 Compiling JNI-DLL (Java Native Interface)..."
    cd JNI-DLL
    
    # Compile Java files first
    for java_file in *.java; do
        if [ -f "$java_file" ]; then
            echo "   ⚡ Compiling $java_file"
            javac "$java_file"
            if [ $? -eq 0 ]; then
                echo "   ✅ $java_file compiled successfully"
            else
                echo "   ❌ Error compiling $java_file"
            fi
        fi
    done
    
    # Check if Makefile exists and compile native library
    if [ -f "Makefile" ]; then
        echo "   🔨 Building native library..."
        make clean > /dev/null 2>&1
        make
        if [ $? -eq 0 ]; then
            echo "   ✅ Native library built successfully"
        else
            echo "   ❌ Error building native library"
        fi
    else
        echo "   ⚠️  Makefile not found - skipping native library build"
    fi
    
    cd - > /dev/null
    echo ""
fi

echo "🎉 Build complete!"
echo ""
echo "📋 Usage Instructions:"
echo "===================="
echo "1. Navigate to any program directory"
echo "2. Run: java <ClassName>"
echo "3. Follow the program prompts"
echo ""
echo "Example:"
echo "cd CPU-Scheduling && java FCFS"
echo ""
echo "For JNI programs:"
echo "cd JNI-DLL && java InteractiveDMASCalculator"