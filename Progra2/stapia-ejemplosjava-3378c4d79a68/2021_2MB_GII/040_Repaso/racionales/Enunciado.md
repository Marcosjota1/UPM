
# Enunciado

## Escribir `Euclides`

Se pide escribir una clase que implemente el MCD según el algoritmo de Euclides. 

## Escribir `Fraccion`

Se pide escribir una clase `Fraccion` que cumpla con las siguientes especificaciones:

1. Que sirva para representar un número racional (una fracción)
1. Que sea inmutable
1. Que si la fracción es negativa, el numerador sea negativo
1. Que su valor se pueda obtener en una cadena alfanumérica en la forma `numerador/denominador`
1. Que el estado de cualquier objeto de la clase siempre sea la fracción simplificada
1. Escriba un pequeño programa (main) en la propia clase para probar lo que se ha escrito.

## Otras funcionalidades

Como complemento a las funcionalidades anteriores se desea dotar a las fracciones de 
un conjunto de operaciones aritmeticas. Dichas operaciones se debe poner una clase `Aritmetica`
y/o en la propia clase `Fraccion`. Escriba alguna de esta operaciones en ambos sitios y 
compare la forma en que tienen que realizar. En concreto:

1. Implemente la suma de fracciones
1. La resta de fracciones
1. La multiplicación de un número por una fracción
1. La multiplicación de dos fracciones

Probar cada una de ellas en un `main` escrito sobre la propia clase. Lo mejor es ir haciendo
cada una de las operaciones y probarla inmediatamente. 

## Usando varias fracciones

Una vez que podemos sumar dos fracciones, vamos a sumar cualquier número de ellas...

### Sumando fracciones

Se pide: 

1. Escribir una clase `MuchasFracciones` con un método que permita sumar varias 
fracciones dadas a través de un `Array` de fracciones
1. Probar el método en un `main` dentro de la propia clase.

O tal vez haciendo una sucesión de números racionales...

### Sucesión de fracciones

Se pide:

* Añadir un método que añada un elemento a una lista (`IList`) de fracciones dado por la 
siguiente expresión:

    An = 2*An-1 - (5/3)*An-2

Indique con documentación de Javadoc las precondiciones y postcondiciones aplicadas.

* Completar el `main` con las sentencias necesarias para generar una lista con 10 elementos
de la sucesión indicada. 

## Una secuencia de implementación propia

Se pide:

A partir de la definición de la clase `Node` (en tads), construir una secuencia de 
fracciones que tenga las siguientes características:

* Un método `insert` que permita insertar una `Fraccion` manteniendo la secuencia ordenada
(en orden creciente de valor de las fracciones). 

* Escribir el método `toString` para dicha secuencia.




