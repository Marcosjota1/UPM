#!/bin/bash

echo "Introduzca un numero"
read  num

if (( $num % 2 == 0 ));then
    echo "El numero es par"
else
    echo "El numero no es par"
fi


