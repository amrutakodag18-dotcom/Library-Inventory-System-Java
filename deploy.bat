@echo off
echo ===================================================
echo     BUILDING & DEPLOYING LIBRARY INVENTORY SYSTEM
echo ===================================================

echo [1/3] Cleaning old compilation files...
del *.class 2>nul

echo [2/3] Compiling Java source files...
javac Book.java LibraryService.java Main.java

if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed! Please check your code.
    pause
    exit /b %errorlevel%
)

echo [3/3] Compilation successful! Packaging into executable JAR...
jar cfe LibrarySystem.jar Main Book.class LibraryService.class Main.class

echo ===================================================
echo   DEPLOYS FINISHED! Run app using: java -jar LibrarySystem.jar
echo ===================================================
pause