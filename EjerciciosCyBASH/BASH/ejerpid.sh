#!/bin/bash

echo "Introduce un numero"
read num
cont=1;

while [ $num -ne $$ ]; do
    if [ $num -gt $$ ];then
        echo "$num es mayor que pid"
    else    
        echo "$num es menor que pid"
    fi
    echo "Introduce otro num : "
    read num
    cont=$(($cont+1))
done
echo "Has necesitado $cont intentos para adivinar el PID $$"

