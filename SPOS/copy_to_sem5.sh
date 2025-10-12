#!/bin/bash

# SPOS to SEM5 Clean Copy Script
# This script copies essential files from SPOS to SEM5 while excluding unnecessary files

echo "🚀 Starting clean copy from SPOS to SEM5..."

# Set source and destination paths
SOURCE_DIR="/Users/kaushiksambe/Documents/pro/sample/LABS/SPOS"
DEST_DIR="/Users/kaushiksambe/Documents/GitHub/SEM5/SPOS"

# Check if source exists
if [ ! -d "$SOURCE_DIR" ]; then
    echo "❌ Source directory not found: $SOURCE_DIR"
    exit 1
fi

# Create destination directory
echo "📁 Creating destination directory..."
mkdir -p "$DEST_DIR"

# Copy files using rsync with exclusions
echo "📂 Copying essential files (excluding compiled and generated files)..."
rsync -av \
  --exclude='*.class' \
  --exclude='*.o' \
  --exclude='*.dylib' \
  --exclude='output.txt' \
  --exclude='Output.txt' \
  --exclude='Intermediate.txt' \
  --exclude='*.zip' \
  --exclude='.DS_Store' \
  --exclude='Symtab.txt' \
  --exclude='Littab.txt' \
  --exclude='Pooltab.txt' \
  --exclude='MDT.txt' \
  --exclude='MNT.txt' \
  --exclude='ARGLIST.txt' \
  "$SOURCE_DIR/" "$DEST_DIR/"

# Clean up any remaining unwanted files
echo "🧹 Cleaning up any remaining generated files..."
find "$DEST_DIR" -name "*.class" -delete
find "$DEST_DIR" -name "*.o" -delete
find "$DEST_DIR" -name "*.dylib" -delete
find "$DEST_DIR" -name "output.txt" -delete
find "$DEST_DIR" -name "Output.txt" -delete

# Show summary
echo ""
echo "✅ Copy completed successfully!"
echo "📊 Summary:"
echo "   Source: $SOURCE_DIR"
echo "   Destination: $DEST_DIR"

# Count files
JAVA_FILES=$(find "$DEST_DIR" -name "*.java" | wc -l)
README_FILES=$(find "$DEST_DIR" -name "README.md" | wc -l)
BUILD_SCRIPTS=$(find "$DEST_DIR" -name "build.*" | wc -l)

echo "   📁 Java files: $JAVA_FILES"
echo "   📄 README files: $README_FILES"
echo "   🔨 Build scripts: $BUILD_SCRIPTS"

echo ""
echo "🎯 Repository structure ready for GitHub!"
echo "Next steps:"
echo "1. cd /Users/kaushiksambe/Documents/GitHub/SEM5"
echo "2. git add ."
echo "3. git commit -m 'Add SPOS lab implementations'"
echo "4. git push origin main"