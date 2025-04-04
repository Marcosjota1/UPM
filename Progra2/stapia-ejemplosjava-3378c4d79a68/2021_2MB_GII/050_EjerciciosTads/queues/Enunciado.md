
# Enunciado

## Introducci�n

Una fila, *queue*, b�sicamente es una estructura lineal donde 
se a�aden y eliminan elementos siguiendo un esquema FIFO, el
primero que se a�ade es el primero que se elimina. 

Vamos a hacer una implementaci�n de una Queue que se 
corresponda con esa definici�n y que se base en el empleo 
de una array (y por tanto sea un fila acotada). 

Vease `IQueue` y las clases `ArrayQueue` y `Queue` en el 
paquete `tads`. 

Pero vamos a hacer una variante, tanto de la interfaz 
como de la forma en que vamos a especificar los m�todos.

## Se pide

Implementar una clase `VectorFila` que cumpla que:

1. Sea gen�rica
1. Tenga un m�todo, `push`, para a�adir elementos que
devuelva true si se ha a�adido el elemento y false
en caso contrario.
1. Tengo un m�todo `pull` que devuelva el primer
elemento de la fila y `null` si la fila est� vac�a.
1. Escribir un `main` para comprobar la clase. 

## Alternativa

Vamos a hacer ahora un dise�o que utilice Exceptiones 
gestionadas para implementar la misma clase, para 
poder comparar mejor vamos a guardar en otro archivo
y vamos a cambiar el nombre a `VectorFilaConExcepciones`.

En primer lugar vamos a escribir las excepciones que
vamos a utilizar. Ser�n:

* `VectorFilaVacioExcepcion`
* `VectorFilaLlenoExcepcion`

Ambas derivadas directamente de `Exception`.

Luego:

* Vamos a reescribir ambos m�todos, pero haciendo
que los casos especiales lancen una excepci�n
* Vamos a escribir un c�digo cliente que use
estos m�todos.
