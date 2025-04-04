#!/bin/bash

# Verificar si se proporciona al menos un argumento
if [ $# -lt 1 ]; then
    echo "Uso: $0 <comando>"
    exit 1
fi

# Crear un directorio temporal para almacenar los archivos temporales
temp_dir=$(mktemp -d)

# Convertir archivos .txt a formato Unix
dos2unix *.txt

# Obtener una lista de archivos que comienzan con "win"
files_to_process=(win*.txt)

# Verificar si hay archivos para procesar
if [ ${#files_to_process[@]} -eq 0 ]; then
    echo "No hay archivos que coincidan con el patrón win*.txt"
    exit 1
fi

# Iterar sobre los archivos y ejecutar el comando proporcionado
for file in "${files_to_process[@]}"; do
    echo "Ejecutando '$1' en $file"
    $1 "$file"
done

# Limpiar archivos temporales y directorio
rm -rf "$temp_dir"

echo "Proceso completado."
