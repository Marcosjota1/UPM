
# Grandes Objetos

## Introducci�n 

Se pretende trabajar el concepto de referencia y composici�n de 
objetos en el contexto de un problema donde se desea trabajar
con objetos que contienen mucha informaci�n. 

El problema est� inspirado en el patr�n "Flyweight" (traducido
ser�a algo as� como peso pluma), pero no es exactamente este
patr�n. 

## Descripci�n

Como resultado del an�lisis de los datos de un problema se
ha determinado que el m�delo de clases debe incluir
una clase `Matriz` con las siguientes caracter�sticas:

1. Sus datos deben ser inmutables.
1. Se debe proporcionar un m�todo para consultar las
componentes de la matriz. 
1. Se debe poder crear nuevas matrices a partir de las
ya existentes mediante la modificaci�n de alguna de 
sus componentes. 
1. Se sabe que el tama�o de las matrices van a ser
muy grandes, pero con tama�o fijo, para desarrollar el prototipo se
admite un tama�o de 10x10. 
1. En la misma l�nea para desarrollar las pruebas
necesarias se supone que es suficiente utilizar
matrices aleatorias. 
1. ES imprescindible utilizar un dise�o que sea 
capaz de usar eficientemente la memoria. 

## Se pide:

* El dise�o necesario para resolver el problema.
* Implementar las interfaces y clases necesarias.
* Comprobar las clases. 