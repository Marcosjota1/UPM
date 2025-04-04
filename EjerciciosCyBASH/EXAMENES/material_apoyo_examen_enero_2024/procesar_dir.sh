#!/bin/bash

# Aplica un comando a todos los archivos cuyo nombre empiece por un prefijo y 
# termine por un postfijo. Todos ellos dados como argumentos.
#
# Por ejemplo, si el programa de C desarrollado anteriormente se ha 
# compilado a un ejecutable de nombre convertir, la llamada: 
#
# ./procesar_dir.sh ./convertir win .txt 
#
# debe convertir los archivos win-a.txt y win-b.txt a UTF-8 con saltos
# de línea LF.

# /*IniciarRespuesta*/
listado=$(ls "$2"*"$3")  #Creas lista con los archivos con sufijo
                           # $2 = win y $3 = .txt

>&2 echo $listado # Opcional, envia $listado al canal de error para depurar

for f in $listado
do
    >&2 echo "Procesando: $f con el comando $1" # Opcional, para depurar, &2 echo, 
                                                                        #manda por echo los errores
    $1 $f #Itera sobre cada elemento aplicandole el $1 = ./convertir
done

# /*FinalDeRespuesta*/

