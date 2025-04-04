
# Enunciado

## Escribir Punto

Escribir una clase `Punto` que represente un punto en 2D por sus coordenadas
cartesianas, esta clase debe:

* Construirse mediante los valores de sus dos coordenadas.
* Permitir modificar el punto girandolo un ángulo dado respecto del origen de coordenadas.
* Implementar `toString` para mostrar las coordenadas.

Nota:

Giro de alpha (antihorario) equivale a multiplicar por la matriz unitaria:

    ( cos(alpha)   -sin(alpha) )
    ( sin(alpha)    cos(alpha) )
    
## Escribir Modificar

Escribir una clase `Modificar` que implemente un método de clase `giraPuntos` para que, dada una 
lista de objetos de la clase `Punto` inserte todos los puntos de esa lista en una cola (`Queue`) 
que se devuelve como resultado, pero de manera que los gire antes de introducirlos en la cola.

Compruebe el resultado implementando el método main para que llame a `giraPuntos` con una lista
de puntos generados aleatoriamente. Muestre los puntos antes de llamar al método y muestre 
los puntos tanto de la cola como de la lista después de llamar al método.

### Multiplicar números

Repetir el problema con una lista de números en coma flotante cuyos números se deben 
multiplicar por 1000. 

