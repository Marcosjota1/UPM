#! /bin/bash

#Recordar antes de ejecutar el programa exportar la variable de entorno 
#export LD_LIBRARY_PATH=./lib

FLAGS="-Wall -Wextra -Werror -ansi -pedantic"
FLAGS2="-Wall -Wextra -Werror -pedantic" # quitar esta linea y ejecutar el ultimo gcc con FLAGS en triqui
INCLUDE="-I lib/include"

case $1 in
	construir|CONSTRUIR)
		make
			gcc $FLAGS $INCLUDE -c src/pps_iniciar.c
			gcc $FLAGS $INCLUDE -c src/pps_dicc.c
			gcc $FLAGS $INCLUDE -c src/test_dicc.c
			gcc $FLAGS $INCLUDE -c src/wordle.c
			gcc $FLAGS2 $INCLUDE -o wordle *.o -L ./lib -lwordle 	
			#gcc $FLAGS $INCLUDE -o wordle pps_iniciar.o pps_dicc.o test_dicc.o wordle.o -L ./lib -lwordle 	
		exit $?
	;;

	jugar)
		if [ -e wordle ] && [ -x wordle ]; then
			./wordle data/dicES5.dat data/log_jugar.txt j
		else
			exit 1
		fi
	;;

	probar)
		if test -e wordle && test -x wordle; then
			./wordle data/dicTest.dat data/log_probar.txt t
			exit $?
		fi
		exit 1
		
	;;

	limpiar)
		if test -e wordle; then
			rm wordle
		fi
		rm -f *.o
		exit 0
	;;

	*)
		echo "ERROR: parámetro incorrecto"
		echo "USO: ./wordle.sh construir|jugar|probar|limpiar"
	;;
esac
