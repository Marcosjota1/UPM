
# Grandes Objetos

## Introducción 

Se pretende trabajar el concepto de referencia y composición de 
objetos en el contexto de un problema donde se desea trabajar
con objetos que contienen mucha información. 

El problema está inspirado en el patrón "Flyweight" (traducido
sería algo así como peso pluma), pero no es exactamente este
patrón. 

## Descripción

Como resultado del análisis de los datos de un problema se
ha determinado que el módelo de clases debe incluir
una clase `Matriz` con las siguientes características:

1. Sus datos deben ser inmutables.
1. Se debe proporcionar un método para consultar las
componentes de la matriz. 
1. Se debe poder crear nuevas matrices a partir de las
ya existentes mediante la modificación de alguna de 
sus componentes. 
1. Se sabe que el tamaño de las matrices van a ser
muy grandes, pero con tamaño fijo, para desarrollar el prototipo se
admite un tamaño de 10x10. 
1. En la misma línea para desarrollar las pruebas
necesarias se supone que es suficiente utilizar
matrices aleatorias. 
1. ES imprescindible utilizar un diseño que sea 
capaz de usar eficientemente la memoria. 

## Se pide:

* El diseño necesario para resolver el problema.
* Implementar las interfaces y clases necesarias.
* Comprobar las clases. 