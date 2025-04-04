#!/bin/bash

x=1

while [[ $x -le 20 ]]
do
    echo "Hey ,I just did $x pushups"
    (( x ++ ))
    sleep 0.2
done