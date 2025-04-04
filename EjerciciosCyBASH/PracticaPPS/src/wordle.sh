#!/bin/bash

export LD_LIBRARY_PATH=./lib:$LD_LIBRARY_PATH

# Función para manejar errores
function manejar_error() {
    echo "Error: $1"
    exit 1
}

# Manejar los argumentos
case "$1" in 
    construir)
        # Compilar
        gcc -Wall -Wextra -Werror -ansi -pedantic -Ilib/include -o bin/wordle.o -c src/wordle.c || manejar_error "Error al compilar wordle.c"
        gcc -Wall -Wextra -Werror -ansi -pedantic -Ilib/include -o bin/test_dicc.o -c src/test_dicc.c || manejar_error "Error al compilar test_dicc.c"
        gcc -Wall -Wextra -Werror -ansi -pedantic -Ilib/include -o bin/pps_dicc.o -c src/pps_dicc.c || manejar_error "Error al compilar pps_dicc.c"
        gcc -Wall -Wextra -Werror -ansi -pedantic -Ilib/include -o bin/pps_iniciar.o -c src/pps_iniciar.c || manejar_error "Error al compilar pps_iniciar.c"
        gcc -Ilib/Include  -o wordle bin/*.o -Llib -lwordle || manejar_error "Error al enlazar los archivos o"
        exit 0
        ;;
    jugar)
        if [ -e "wordle" && -x "wordle" ]  ; then # e compruba existe
            ./wordle data/dicES5.dat data/jugar.txt j
        else
            exit 1
        fi
        ;;
    probar)
        if [ -e "wordle" ] ; then
            ./wordle data/dicTest.dat data/probar.txt t
            exit $?  # El valor de salida se mantiene automáticamente, sigues en el anterior, creo que con 0 funciona
        fi
        exit 1
        ;;
    limpiar)
        if [ -e "wordle" ]; then
            rm wordle  # Elimina wordle si existe el ejecutable
        fi
        rm -f bin/*.o  # Borra los .o existentes
        exit 0
        ;;
    *)
        #caso estandar
        echo "Error: Argumento no válido. Las opciones son: construir, jugar, probar, limpiar." 
        exit 1
        ;;
esac



#!/bin/bash

export LD_LIBRARY_PATH="./lib:$LD_LIBRARY_PATH"
case "$1" in 
construir) 
gcc -Ilib/include -c -o bin/pps_dicc.o src/pps_dicc.c
gcc -Ilib/include -c -o bin/pps_iniciar.o src/pps_iniciar.c
gcc -Ilib/include -c -o bin/test_dicc.o src/test_dicc.c
gcc -Ilib/include -c -o bin/wordle.o src/wordle.c
gcc -Llib -lwordle -o wordle bin/*.o
if [ $? -ne 0 ]; then
	exit 1
fi
exit 0
;;
jugar)
if [ -e "wordle" ];then
	./wordle data/dicES5.dat logDic.txt j
	exit 0
else
	exit 1
fi
;;
probar)
if [ -e wordle ];then
	./wordle data/dicTest.dat logTest.txt t
	exit 0
else
	exit 1
fi
;;
limpiar)
rm -f "wordle" bin/*.o
exit 0
;;
*)
echo "Instrucción incorrecta"
exit 1
;;
esac

exit 0;