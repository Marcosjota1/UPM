bsvc#!/bin/bash


if [ $# -ne 1 ]; then
    echo "ERROR: No hay Argumento de entrada"
    exit 1
fi

case "$1" in
    compilar)
    #bin/68kasm -l wuolah-free-es_int.s
    bin/68kasm -l es_prog.s  #Compilar (Importante -l genera el .lis para depurar)
    ;;
    listar)  #listar ejecutables(debe haber 3)
    ls -lrt es_prog.*
    ;;
    limpiar || l)
    rm *.lis
    rm *.h68
    ;;
    ejecutar)
    #cp /home/marcos/ArquitecturaComputadores/usr/local/bsvc-2.1/samples/m68000/practica.setup . (La 1 vez para copiar entorno a tu directorio de trabajo)
    bin/bsvc practica.setup &  #Abre entorno trabajo
    ;;
    
    *)
    echo "Solo se permiten los argumentos:'COMPILAR/LISTAR/LIMPIAR/EJECUTAR'"
esac


# 1º conectarse a triqui
#2º #cp /home/marcos/ArquitecturaComputadores/usr/local/bsvc-2.1/samples/m68000/practica.setup .
# Load program -> .h68
#Das a reset (PC apuntara a direccion de inicio)
#Ejecutas(run / single step)
#Window (Abres ventana de memoria y del programa)
