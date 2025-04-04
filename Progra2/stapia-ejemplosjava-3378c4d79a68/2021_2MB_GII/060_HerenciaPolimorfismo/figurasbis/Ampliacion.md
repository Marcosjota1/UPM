# Enunciado 

## Introducción
Se va a ampliar y complementar el enunciado del paquete de las figuras. El objetivo, de nuevo, 
es programar clases que tengan relaciones de herencia entre ellas. En esta ocasión se va a 
incidir en:

* Llamadas a los métodos comunes de la clase base, incluido el constructor.
* Uso combinado de la sobreescritura de métodos (*override*) y reutilización de la 
implementación base. 

## Se pide

Se busca diseñar varias clases que representen figuras planas cerradas que se puedan 
dibujar mediante `StdDraw`. En concreto se pretende:

#### Escribir una clase `Punto`

Es una clase auxiliar, solo necesita dos coordenadas en el plano XY.

#### Escribir una clase `Figura` que:

1. Se pueda construir a partir de un *array* de `Puntos`

1. Tenga como atributo una secuencia de puntos (vértices) en el plano XY 
de clase `LinkedList<Punto>`

1. Tenga un método `dibujar` que dibuje líneas de cada vértice 
al siguiente y del último al primero. 


#### Escribir una clase `Cuadrado` que:

1. Se construya como figura calculando sus vértices a partir del
vértice inferior izquierda y de la longitud del lado

1. Se dibuje con las líneas azules.

#### Escribir una clase `TrianguloRectangulo` que:

1. Se construya como figura calculando sus vértices a partir del
vértice inferior izquierda, la longitud de la base y de la altura suponiendo
que el ángulo recto está en ese vértice dado. 

1. Se dibuje con las líneas Magenta.

#### Escribir un programa que:

1. Cree una lista con 2 cuadrados y 2 triángulos rectángulos

1. y los dibuje.
