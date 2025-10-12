# Makefile for MathOperations JNI Library
# Supports macOS, Linux, and provides Windows command reference

# Detect the operating system
UNAME_S := $(shell uname -s)

# Java home detection
JAVA_HOME ?= $(shell /usr/libexec/java_home 2>/dev/null || echo "/usr/lib/jvm/default-java")

# Compiler and flags
CC = gcc
CFLAGS = -fPIC -shared -O2 -Wall

# Include directories for JNI headers
INCLUDES = -I"$(JAVA_HOME)/include"

# Platform-specific settings
ifeq ($(UNAME_S),Darwin)
    # macOS
    INCLUDES += -I"$(JAVA_HOME)/include/darwin"
    TARGET = libmathops.dylib
    LDFLAGS = -dynamiclib -lm
else ifeq ($(UNAME_S),Linux)
    # Linux
    INCLUDES += -I"$(JAVA_HOME)/include/linux"
    TARGET = libmathops.so
    LDFLAGS = -shared -lm
else
    # Windows (requires MinGW or MSYS2)
    INCLUDES += -I"$(JAVA_HOME)/include/win32"
    TARGET = mathops.dll
    LDFLAGS = -shared -lm
endif

# Source files
SOURCES = mathops.c
OBJECTS = $(SOURCES:.c=.o)

# Default target
all: $(TARGET)

# Build the shared library
$(TARGET): $(OBJECTS)
	@echo "Building $(TARGET) for $(UNAME_S)..."
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $^ $(INCLUDES)
	@echo "Successfully built $(TARGET)"

# Compile source files
%.o: %.c MathOperations.h
	@echo "Compiling $<..."
	$(CC) $(CFLAGS) $(INCLUDES) -c $< -o $@

# Clean build artifacts
clean:
	@echo "Cleaning build artifacts..."
	rm -f $(OBJECTS) $(TARGET) libmathops.so libmathops.dylib mathops.dll
	@echo "Clean complete"

# Install library to system path (optional)
install: $(TARGET)
ifeq ($(UNAME_S),Darwin)
	cp $(TARGET) /usr/local/lib/
else ifeq ($(UNAME_S),Linux)
	cp $(TARGET) /usr/local/lib/
	ldconfig
endif

# Test compilation only
test-compile: $(OBJECTS)
	@echo "Test compilation successful"

# Help target
help:
	@echo "Available targets:"
	@echo "  all          - Build the shared library"
	@echo "  clean        - Remove build artifacts"
	@echo "  install      - Install library to system path"
	@echo "  test-compile - Test compilation without linking"
	@echo "  help         - Show this help message"
	@echo ""
	@echo "Environment variables:"
	@echo "  JAVA_HOME    - Path to Java installation"
	@echo "  CC           - C compiler (default: gcc)"
	@echo ""
	@echo "Windows users: Use MinGW or MSYS2 with 'make' command"
	@echo "Alternative Windows build:"
	@echo "  gcc -shared -fPIC -O2 -I\"%JAVA_HOME%\\include\" -I\"%JAVA_HOME%\\include\\win32\" -o mathops.dll mathops.c -lm"

# Phony targets
.PHONY: all clean install test-compile help

# Windows batch file equivalent (saved as separate file)
windows-build:
	@echo "For Windows, create build.bat with:"
	@echo "gcc -shared -fPIC -O2 -I\"%JAVA_HOME%\\include\" -I\"%JAVA_HOME%\\include\\win32\" -o mathops.dll mathops.c -lm"