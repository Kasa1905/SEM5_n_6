@echo off
REM Universal SPOS Build Script for Windows
REM Builds all programs with cross-platform compatibility

echo 🚀 Building SPOS Laboratory Programs...
echo ========================================

set success_count=0
set total_count=0

REM Function to build a program (Windows batch version)
call :build_program "PRACTICAL\CODE\CPU scheduling" "CPU Scheduling"
call :build_program "PRACTICAL\CODE\Memory Management" "Memory Management"  
call :build_program "PRACTICAL\CODE\Page replacement" "Page Replacement"
call :build_program "PRACTICAL\CODE\Pass 1" "Pass 1 Assembler"
call :build_program "PRACTICAL\CODE\Pass 2" "Pass 2 Assembler"
call :build_program "PRACTICAL\CODE\Macro Pass1" "Macro Pass 1"
call :build_program "PRACTICAL\CODE\Macro Pass 2" "Macro Pass 2"
call :build_program "PRACTICAL\CODE\Deadlock (Bankers)" "Banker's Algorithm"
call :build_program "PRACTICAL\CODE\Reader Writer Problem" "Reader-Writer Problem"
call :build_program "PRACTICAL\CODE\JNI DLL" "JNI Calculator (Simplified)"
call :build_program "PRACTICAL\CODE\DLL" "JNI Calculator (Advanced)"

echo ========================================
echo 📊 Build Summary
echo ========================================
echo Total Programs: %total_count%
echo Successful: %success_count%

echo.
echo 🚀 Ready to run! Check individual README files for usage instructions.
pause
goto :eof

:build_program
set "dir=%~1"
set "name=%~2"
set /a total_count+=1

if exist "%dir%" (
    echo Building: %name%
    pushd "%dir%"
    
    if exist "build.bat" (
        call build.bat >nul 2>&1
        if %errorlevel%==0 (
            echo   ✅ Success
            set /a success_count+=1
        ) else (
            echo   ❌ Failed
        )
    ) else (
        for %%f in (*.java) do javac "%%f" >nul 2>&1
        if %errorlevel%==0 (
            echo   ✅ Success  
            set /a success_count+=1
        ) else (
            echo   ⚠️ Manual compilation may be needed
        )
    )
    
    popd
) else (
    echo ⚠️ Directory not found: %dir%
)
goto :eof
