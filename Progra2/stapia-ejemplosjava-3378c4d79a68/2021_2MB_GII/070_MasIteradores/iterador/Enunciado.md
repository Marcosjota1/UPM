
# Enunciado

## Introduci�n

Un iterador es una abstracci�n que permite recorrer una estructura de datos 
sin conocer los detalles internos de su representaci�n. 

## Se pide:

Escribir una clase *IteradorArray* que sirva para recorrer un Array de objectos.

El iterador debe tener las operaciones:

* hasNext
* next

## Alternativa

Se pide:

Hacer una nueva clase que sirva para recorrer un Array de objectos. Pero, la 
interfaz de la clase en esta ocasi�n es:

* hasNext
* advance
* get

Este iterador deber�a poder funcionar correctamente en un bucle como este:

``` 
IteradorArray ite = IteratorArray(array);
for ( ; ite.isExhausted(); ite.advance() ) {
	Object obj = ite.get();
	// Do something
}
``` 


