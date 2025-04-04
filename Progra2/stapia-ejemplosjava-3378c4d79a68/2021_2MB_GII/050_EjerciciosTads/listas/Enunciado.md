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

que implemente los siguientes m�todos:

1. `pushBack`: a�ade un elemento al final de la lista
1. `pushFront`: a�ade un elemento al principio de la lista
1. `popBack`: elimina el �ltimo elemento y devuelve su valor
1. `popFront`: elimina el primer elemento y devuelve su valor

Una vez programada la lista, es necesario probarla. 

* Prueba la implementaci�n en un main en la propia clase. Para ello:


1. Es muy conveniente implementar toString
1. Hay que probar los m�todos anteriores en distintas circunstancias: lista vac�a, con alg�n
elemento, etc. 

# Mejorando la lista

Vamos a a�adir una clase que permita recorrer la lista con una *API* que 
permita ocultar los detalles de implementaci�n de la lista. 

> Nota: Una clase tal y como la vamos a hacer recibe el nombre 
> de iterador (*iterator*) y suelen existir en todas las librer�as
> de clases que contienen colecciones de objetos.

Para hacer la implementaci�n esta clase debemos considerar que su *estado*
es una *posici�n* sobre la lista. Una vez que hemos resuelto esto deber�amos
estar en condiciones de implementar los m�todos que se piden. 

Se pide:

* Escribir una clase `IteratorList<E>` que implemente el constructor y
dos m�todos:


1. Un m�todo: `next` para devolver siguiente elemento a la posici�n *actual*
1. Un m�todo: `hasNext` que devuelve verdadero (`true`) si hay un elemento
*siguiente* al *actual* y falso (`false`) en caso contrario
1. El constructor debe fijar la posici�n de tal manera que una llamada posterior
a `next` devuelva el primer elemento de la lista (si es que 
existe).

Una vez programado el iterador, es necesario probarlo. 

* Escribir un programa que:


1. Genere una lista de enteros
1. Recorra la lista sumando todos los n�meros usando el iterador
