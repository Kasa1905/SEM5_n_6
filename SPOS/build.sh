#!/bin/bash

# Universal SPOS Build Script
# Builds all programs with cross-platform compatibility

echo "🚀 Building SPOS Laboratory Programs..."
echo "========================================"

# Color codes for better output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Track build results
success_count=0
total_count=0

# Function to build a program
build_program() {
    local dir="$1"
    local name="$2"
    
    if [[ -d "$dir" ]]; then
        echo -e "${BLUE}Building: $name${NC}"
        cd "$dir"
        
        total_count=$((total_count + 1))
        
        # Try different build methods
        if [[ -f "build.sh" ]]; then
            chmod +x build.sh
            if ./build.sh > /dev/null 2>&1; then
                echo -e "  ${GREEN}✅ Success${NC}"
                success_count=$((success_count + 1))
            else
                echo -e "  ${RED}❌ Failed (build script)${NC}"
            fi
        elif [[ -f "simple_build.sh" ]]; then
            chmod +x simple_build.sh
            if ./simple_build.sh > /dev/null 2>&1; then
                echo -e "  ${GREEN}✅ Success${NC}"
                success_count=$((success_count + 1))
            else
                echo -e "  ${RED}❌ Failed (simple build)${NC}"
            fi
        else
            # Try to compile all Java files
            if find . -name "*.java" -exec javac {} + 2>/dev/null; then
                echo -e "  ${GREEN}✅ Success${NC}"
                success_count=$((success_count + 1))
            else
                echo -e "  ${YELLOW}⚠️  Manual compilation may be needed${NC}"
            fi
        fi
        
        cd - > /dev/null
    else
        echo -e "${YELLOW}⚠️  Directory not found: $dir${NC}"
    fi
}

# Build all programs
echo -e "\n${BLUE}🔄 CPU Scheduling Programs${NC}"
build_program "PRACTICAL/CODE/CPU scheduling" "CPU Scheduling"

echo -e "\n${BLUE}🧠 Memory Management Programs${NC}"
build_program "PRACTICAL/CODE/Memory Management" "Memory Management"

echo -e "\n${BLUE}📄 Page Replacement Programs${NC}"
build_program "PRACTICAL/CODE/Page replacement" "Page Replacement"

echo -e "\n${BLUE}⚙️ Two-Pass Assembler${NC}"
build_program "PRACTICAL/CODE/Pass 1" "Pass 1 Assembler"
build_program "PRACTICAL/CODE/Pass 2" "Pass 2 Assembler"

echo -e "\n${BLUE}🔧 Macro Processor${NC}"
build_program "PRACTICAL/CODE/Macro Pass1" "Macro Pass 1"
build_program "PRACTICAL/CODE/Macro Pass 2" "Macro Pass 2"

echo -e "\n${BLUE}🔒 Deadlock Prevention${NC}"
build_program "PRACTICAL/CODE/Deadlock (Bankers)" "Banker's Algorithm"

echo -e "\n${BLUE}👥 Synchronization Problems${NC}"
build_program "PRACTICAL/CODE/Reader Writer Problem" "Reader-Writer Problem"

echo -e "\n${BLUE}🔗 Java Native Interface${NC}"
build_program "PRACTICAL/CODE/JNI DLL" "JNI Calculator (Simplified)"
build_program "PRACTICAL/CODE/DLL" "JNI Calculator (Advanced)"

# Summary
echo -e "\n========================================"
echo -e "${BLUE}📊 Build Summary${NC}"
echo -e "========================================"
echo -e "Total Programs: $total_count"
echo -e "Successful: ${GREEN}$success_count${NC}"
echo -e "Failed/Manual: ${RED}$((total_count - success_count))${NC}"

if [[ $success_count -eq $total_count ]]; then
    echo -e "\n${GREEN}🎉 All programs built successfully!${NC}"
else
    echo -e "\n${YELLOW}⚠️  Some programs may need manual compilation${NC}"
    echo -e "   Check individual program directories for specific instructions"
fi

echo -e "\n${BLUE}🚀 Ready to run! Check individual README files for usage instructions.${NC}"
