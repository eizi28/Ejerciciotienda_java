
@echo off
setlocal
cd /d "%~dp0"

echo Limpiando archivos compilados...

if exist "bin" (
    rmdir /s /q "bin"
    echo Carpeta bin eliminada.
) else (
    echo No hay archivos compilados.
)

echo Limpieza terminada.
