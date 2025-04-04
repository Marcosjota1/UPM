
# Enunciado

## Introdución

Un iterador es una abstracción que permite recorrer una estructura de datos 
sin conocer los detalles internos de su representación. 

## Se pide:

Escribir una clase *IteradorArray* que sirva para recorrer un Array de objectos.

El iterador debe tener las operaciones:

* hasNext
* next

## Alternativa

Se pide:

Hacer una nueva clase que sirva para recorrer un Array de objectos. Pero, la 
interfaz de la clase en esta ocasión es:

* hasNext
* advance
* get

Este iterador debería poder funcionar correctamente en un bucle como este:

``` 
IteradorArray ite = IteratorArray(array);
for ( ; ite.isExhausted(); ite.advance() ) {
	Object obj = ite.get();
	// Do something
}
``` 


