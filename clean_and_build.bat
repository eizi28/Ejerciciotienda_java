
@echo off
setlocal
cd /d "%~dp0"

echo =====================================
echo    COMPILANDO SISTEMA DE TRANSPORTE
echo =====================================

where javac >nul 2>&1
if errorlevel 1 (
    echo ERROR: No se encontro javac.
    echo Instala el JDK y configura el PATH.
    exit /b 1
)

where java >nul 2>&1
if errorlevel 1 (
    echo ERROR: No se encontro Java.
    exit /b 1
)

if exist "bin" rmdir /s /q "bin"
mkdir "bin"

javac -encoding UTF-8 -d "bin" "src\Main\Main.java" "src\Controlador\ControladorGeneral.java" "src\Modelo\Api.java" "src\Modelo\Carro.java" "src\Modelo\Motor.java" "src\Modelo\Chofer.java" "src\Modelo\Pasajero.java" "src\Modelo\RegistroViaje.java" "src\Vista\VistaGeneral.java"

if errorlevel 1 (
    echo.
    echo ERROR: No se pudo compilar el proyecto.
    exit /b 1
)

echo.
echo Compilacion finalizada correctamente.
echo Iniciando programa...
echo.

java -cp "bin" Main.Main
