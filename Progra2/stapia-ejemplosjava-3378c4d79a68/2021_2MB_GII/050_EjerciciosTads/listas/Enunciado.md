# Enunciado

# Escribir una lista

Se pide:

* Escribir clase `SimpleList<E>` una lista enlazada (linked list) basada 
en el `ListNode` simplificado:

``` 
clase ListNode<E> {
	E data;
	ListNode<E> next;
}
```

que implemente los siguientes métodos:

1. `pushBack`: añade un elemento al final de la lista
1. `pushFront`: añade un elemento al principio de la lista
1. `popBack`: elimina el último elemento y devuelve su valor
1. `popFront`: elimina el primer elemento y devuelve su valor

Una vez programada la lista, es necesario probarla. 

* Prueba la implementación en un main en la propia clase. Para ello:


1. Es muy conveniente implementar toString
1. Hay que probar los métodos anteriores en distintas circunstancias: lista vacía, con algún
elemento, etc. 

# Mejorando la lista

Vamos a añadir una clase que permita recorrer la lista con una *API* que 
permita ocultar los detalles de implementación de la lista. 

> Nota: Una clase tal y como la vamos a hacer recibe el nombre 
> de iterador (*iterator*) y suelen existir en todas las librerías
> de clases que contienen colecciones de objetos.

Para hacer la implementación esta clase debemos considerar que su *estado*
es una *posición* sobre la lista. Una vez que hemos resuelto esto deberíamos
estar en condiciones de implementar los métodos que se piden. 

Se pide:

* Escribir una clase `IteratorList<E>` que implemente el constructor y
dos métodos:


1. Un método: `next` para devolver siguiente elemento a la posición *actual*
1. Un método: `hasNext` que devuelve verdadero (`true`) si hay un elemento
*siguiente* al *actual* y falso (`false`) en caso contrario
1. El constructor debe fijar la posición de tal manera que una llamada posterior
a `next` devuelva el primer elemento de la lista (si es que 
existe).

Una vez programado el iterador, es necesario probarlo. 

* Escribir un programa que:


1. Genere una lista de enteros
1. Recorra la lista sumando todos los números usando el iterador
