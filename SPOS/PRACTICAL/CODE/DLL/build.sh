#!/usr/bin/env bash
# Cross-platform JNI build script for ArithmeticJNI

set -euo pipefail

echo "🔧 Cross-Platform JNI Build Script"
echo "=================================="

# Detect operating system
OS="$(uname -s)"
ARCH="$(uname -m)"
echo "Detected: $OS ($ARCH)"

# Find Java installation
if [[ -n "${JAVA_HOME:-}" ]]; then
    JDK_HOME="$JAVA_HOME"
elif command -v java >/dev/null 2>&1; then
    if [[ "$OS" == "Darwin" ]]; then
        JDK_HOME="$(/usr/libexec/java_home)"
    else
        JAVA_PATH="$(which java)"
        JDK_HOME="$(dirname "$(dirname "$JAVA_PATH")")"
    fi
else
    echo "❌ Java not found. Please install JDK or set JAVA_HOME"
    exit 1
fi

echo "Using JDK: $JDK_HOME"

# Compile Java source
echo "📦 Compiling Java source..."
javac ArithmeticJNI.java

# Generate JNI header (if needed)
if [[ ! -f "ArithmeticJNI.h" ]] || [[ "ArithmeticJNI.java" -nt "ArithmeticJNI.h" ]]; then
    echo "📋 Generating JNI header..."
    javah -jni ArithmeticJNI 2>/dev/null || javac -h . ArithmeticJNI.java
fi

# Build native library based on platform
echo "🔨 Building native library for $OS..."

case "$OS" in
    "Darwin")  # macOS
        echo "Building for macOS..."
        gcc -shared -fPIC \
            -I"$JDK_HOME/include" \
            -I"$JDK_HOME/include/darwin" \
            ArithmeticJNI.c \
            -o libArithmeticLib.dylib
        echo "✅ Created: libArithmeticLib.dylib"
        ;;
        
    "Linux")   # Linux
        echo "Building for Linux..."
        gcc -shared -fPIC \
            -I"$JDK_HOME/include" \
            -I"$JDK_HOME/include/linux" \
            ArithmeticJNI.c \
            -o libArithmeticLib.so
        echo "✅ Created: libArithmeticLib.so"
        ;;
        
    "CYGWIN"*|"MINGW"*|"MSYS"*)  # Windows
        echo "Building for Windows..."
        gcc -shared -fPIC \
            -I"$JDK_HOME/include" \
            -I"$JDK_HOME/include/win32" \
            ArithmeticJNI.c \
            -o ArithmeticLib.dll
        echo "✅ Created: ArithmeticLib.dll"
        ;;
        
    *)
        echo "❌ Unsupported operating system: $OS"
        exit 1
        ;;
esac

echo ""
echo "🎉 Build completed successfully!"
echo "📁 Generated files:"
ls -la ArithmeticJNI.class lib*.dylib lib*.so *.dll 2>/dev/null || true

echo ""
echo "🚀 To run:"
echo "java -Djava.library.path=. ArithmeticJNI"