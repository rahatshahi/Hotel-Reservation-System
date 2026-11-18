@echo off
setlocal
cd /d "%~dp0"

if not exist out mkdir out

echo Compiling Hotel Reservation System...
javac -encoding UTF-8 -d out *.java
if errorlevel 1 (
    echo.
    echo Compilation failed. Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Starting Hotel Reservation System...
java -cp out MainFrame

if errorlevel 1 (
    echo.
    echo The application exited with an error.
    pause
)
endlocal
