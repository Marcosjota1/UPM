
## Introducción

(Inspirado en el patrón Flyweight, algo así como peso pluma)

## Descripción

Se desea implementar un modelo de objetos para 
manejar grandes matrices de manera que se cumpla que:

1. Las matrices sean cuadradas de tamaño fijo, 
para desarrollo se puede suponer que son de 10x10.
1. Son inmutables.
1. Se pueden generar aleatoriamente.
1. Al modificar alguna de sus componentes se obtiene
una nueva matriz. 
1. Se pueden obtener las componentes de la matriz 
de una en una.

Y ESPECIALMENTE:

Se debe hacer un diseño que MINIMICE el uso de memoria.


