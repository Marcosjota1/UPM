#!/bin/bash

# Corrección del código para imprimir los números de la colección

# Se define la colección de números
coleccion=(1 2 3 4 5)

# Se utiliza un bucle for para iterar sobre la colección
for x in "${coleccion[@]}";
do
    # Se imprime el número actual
    echo "$x"

    # Se añade un retardo de 0,2 segundos para que los números se impriman con un intervalo de tiempo
    sleep 0.2
done