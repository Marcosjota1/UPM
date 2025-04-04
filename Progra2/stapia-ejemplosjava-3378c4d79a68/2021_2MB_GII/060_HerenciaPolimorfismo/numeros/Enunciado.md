
# Enunciado

## Introducción

Una relación de herencia entre dos clases A y B, siendo A la base y B la
derivada se puede leer como B es A. 

Si se añade una clase derivada más, por ejemplo, C se tiene que B y C son
A, pero, obviamente no es cierto que:

* Todos los A son B (respectivamente C)
* Todos los B son C

Si en este esquema se añade una colección genérica de objetos, por ejemplo:
`LinkedList<A> lista` se tiene que:

* En `lista` se pueden guardar objetos de B (respectivamente C).
* Una vez guardados no es posible saber si eran B y C.
* Dicho de otra manera, se puede ver la parte *común* que es A, pero 
la parte particular (B ó C)
* Salvo que utilicemos *introspección*. 

> Introspección es la capacidad
del lenguaje de programación de proporcionar información del tipo de 
un objeto posteriormente al momento de su creación y cuando, por algún
motivo esa información se desconozca. Por ejemplo porque lo usemos
a través de una referencia a su clase base. 

En Java, el mecanismo de introspección más sencilla consiste en preguntar 
si un objeto es instancia de una clase. 

En el siguiente bloque de código se pregunta si un objeto apuntado 
por una referencia a Number es un Double (Double hereda de Number,
como tambi�n Integer y otros).

``` java
void comprobarDouble(Number number) {
	if ( number instanceof Double ) {
		// Se puede convertir de forma segura:
		Double d = (Double) number;
		System.out.println("Es un Double");
	}
}
```

# Se pide:

* Escribir una clase que implemente un método `sumar` de manera que sume los elementos
de una lista que contiene números de tipo `Double` e `Integer`. Cada uno por si lado. 

* Escribir un programa principal en la misma clase de manera que: 


1. guarde en una lista números de tipo `Double` e `Integer` generados aleatoriamente,
1. muestre los números generados,
1. llame a `sumar` y muestre el resultado obtenido.



