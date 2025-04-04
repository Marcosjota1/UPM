
# Enunciado

## Escribir `Euclides`

Se pide escribir una clase que implemente el MCD seg�n el algoritmo de Euclides. 

## Escribir `Fraccion`

Se pide escribir una clase `Fraccion` que cumpla con las siguientes especificaciones:

1. Que sirva para representar un n�mero racional (una fracci�n)
1. Que sea inmutable
1. Que si la fracci�n es negativa, el numerador sea negativo
1. Que su valor se pueda obtener en una cadena alfanum�rica en la forma `numerador/denominador`
1. Que el estado de cualquier objeto de la clase siempre sea la fracci�n simplificada
1. Escriba un peque�o programa (main) en la propia clase para probar lo que se ha escrito.

## Otras funcionalidades

Como complemento a las funcionalidades anteriores se desea dotar a las fracciones de 
un conjunto de operaciones aritmeticas. Dichas operaciones se debe poner una clase `Aritmetica`
y/o en la propia clase `Fraccion`. Escriba alguna de esta operaciones en ambos sitios y 
compare la forma en que tienen que realizar. En concreto:

1. Implemente la suma de fracciones
1. La resta de fracciones
1. La multiplicaci�n de un n�mero por una fracci�n
1. La multiplicaci�n de dos fracciones

Probar cada una de ellas en un `main` escrito sobre la propia clase. Lo mejor es ir haciendo
cada una de las operaciones y probarla inmediatamente. 

## Usando varias fracciones

Una vez que podemos sumar dos fracciones, vamos a sumar cualquier n�mero de ellas...

### Sumando fracciones

Se pide: 

1. Escribir una clase `MuchasFracciones` con un m�todo que permita sumar varias 
fracciones dadas a trav�s de un `Array` de fracciones
1. Probar el m�todo en un `main` dentro de la propia clase.

O tal vez haciendo una sucesi�n de n�meros racionales...

### Sucesi�n de fracciones

Se pide:

* A�adir un m�todo que a�ada un elemento a una lista (`IList`) de fracciones dado por la 
siguiente expresi�n:

    An = 2*An-1 - (5/3)*An-2

Indique con documentaci�n de Javadoc las precondiciones y postcondiciones aplicadas.

* Completar el `main` con las sentencias necesarias para generar una lista con 10 elementos
de la sucesi�n indicada. 

## Una secuencia de implementaci�n propia

Se pide:

A partir de la definici�n de la clase `Node` (en tads), construir una secuencia de 
fracciones que tenga las siguientes caracter�sticas:

* Un m�todo `insert` que permita insertar una `Fraccion` manteniendo la secuencia ordenada
(en orden creciente de valor de las fracciones). 

* Escribir el m�todo `toString` para dicha secuencia.




