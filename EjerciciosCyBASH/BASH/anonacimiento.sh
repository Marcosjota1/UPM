#!/bin/bash

read -p "Año" year
read -p "mes" mes
read -p "dia" dia

edad=$(($(date +%y)-$year))

if [ $(date+%m) -lt $mes ];then
    edad=$($edad-1)
fi
echo Tienes $edad años



#MAL HECHO, NO TERMINADO