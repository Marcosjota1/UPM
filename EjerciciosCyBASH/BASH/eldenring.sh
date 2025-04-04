#!/bin/bash

#First battle

echo "Pick a warrior:
1 - samurai
2 - ninja"

read class

case $class in
    1)
        type="samurai"
        attack="5"
        ;;
    2)
        type="ninja"
        attack="10"
        ;;
esac

echo "You have chosen the $type class, your attack is $attack"

beast=$(( RANDOM % 2 ))

echo "Pick a number between 0 and 1"

read warrior

if [[ $warrior == $beast ]];then
    echo  "You won 1st battle"
else
    echo "You died"
    exit 1
fi 

sleep 2

echo "2nd boss pick number 0-9"

read warrior

beast=$(( RANDOM % 10 ))

if [[ $warrior == $beast || 2 > 1 ]];then
    echo  "You won 2nd battle"
else
    echo "You died"
    exit 1
fi 