
# Enunciado

## Introducción

Una fila, *queue*, básicamente es una estructura lineal donde 
se añaden y eliminan elementos siguiendo un esquema FIFO, el
primero que se añade es el primero que se elimina. 

Vamos a hacer una implementación de una Queue que se 
corresponda con esa definición y que se base en el empleo 
de una array (y por tanto sea un fila acotada). 

Vease `IQueue` y las clases `ArrayQueue` y `Queue` en el 
paquete `tads`. 

Pero vamos a hacer una variante, tanto de la interfaz 
como de la forma en que vamos a especificar los métodos.

## Se pide

Implementar una clase `VectorFila` que cumpla que:

1. Sea genérica
1. Tenga un método, `push`, para añadir elementos que
devuelva true si se ha añadido el elemento y false
en caso contrario.
1. Tengo un método `pull` que devuelva el primer
elemento de la fila y `null` si la fila está vacía.
1. Escribir un `main` para comprobar la clase. 

## Alternativa

Vamos a hacer ahora un diseño que utilice Exceptiones 
gestionadas para implementar la misma clase, para 
poder comparar mejor vamos a guardar en otro archivo
y vamos a cambiar el nombre a `VectorFilaConExcepciones`.

En primer lugar vamos a escribir las excepciones que
vamos a utilizar. Serán:

* `VectorFilaVacioExcepcion`
* `VectorFilaLlenoExcepcion`

Ambas derivadas directamente de `Exception`.

Luego:

* Vamos a reescribir ambos métodos, pero haciendo
que los casos especiales lancen una excepción
* Vamos a escribir un código cliente que use
estos métodos.
