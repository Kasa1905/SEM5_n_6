#!/usr/bin/env bash
# Simple build script for JNI Math Calculator
# Works on macOS, Linux, and Windows (with MinGW)

set -e

echo "🔧 Simple JNI Build Script"
echo "=========================="

# Step 1: Compile Java source
echo "📦 Step 1: Compiling Java..."
javac SimpleMathCalculator.java

# Step 2: Generate JNI header
echo "📋 Step 2: Generating JNI header..."
javac -h . SimpleMathCalculator.java

# Step 3: Detect OS and build native library
OS="$(uname -s)"
echo "🔍 Detected OS: $OS"

# Find Java installation
if [[ -z "${JAVA_HOME:-}" ]]; then
    if [[ "$OS" == "Darwin" ]]; then
        JAVA_HOME="$(/usr/libexec/java_home)"
    elif [[ "$OS" == "Linux" ]]; then
        JAVA_HOME="$(dirname $(dirname $(readlink -f $(which java))))"
    fi
fi

echo "☕ Using JAVA_HOME: $JAVA_HOME"

# Step 4: Build native library
echo "🔨 Step 3: Building native library..."

case "$OS" in
    "Darwin")
        # macOS
        gcc -shared -fPIC \
            -I"$JAVA_HOME/include" \
            -I"$JAVA_HOME/include/darwin" \
            simple_math.c \
            -o libmathops.dylib
        echo "✅ Built: libmathops.dylib"
        ;;
    "Linux")
        # Linux
        gcc -shared -fPIC \
            -I"$JAVA_HOME/include" \
            -I"$JAVA_HOME/include/linux" \
            simple_math.c \
            -o libmathops.so
        echo "✅ Built: libmathops.so"
        ;;
    *)
        echo "❌ Unsupported OS: $OS"
        echo "For Windows, run: build.bat"
        exit 1
        ;;
esac

echo ""
echo "🎉 Build completed successfully!"
echo ""
echo "🚀 To run the calculator:"
echo "java -Djava.library.path=. SimpleMathCalculator"
echo ""
echo "📁 Generated files:"
ls -la SimpleMathCalculator.class SimpleMathCalculator.h libmathops.* 2>/dev/null || true