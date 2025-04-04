# Enunciado

## Introducción

Es frecuente, al realizar el modelo de objetos de un problema,
encontrar una estructura de clases derivadas de una misma
clase base que es una categoría *pura* en el sentido de que
no existen objetos de esa clase base. 

Un ejemplo típico son las figuras geométricas planas. No existe
una figura que solo sea figura, los objetos figura son triángulos,
cuadrados, rectángulos, etc. Sin embargo, todas estas figuras
comparten esa categoría: son figuras planas. 

Puede ocurrir que esa clase base tenga una serie de atributos
que son comunes a todos los objetos de la categoría, entonces,
esos atributos se declaran en la clase base y son heredados por
todas las clases derivadas. 

Y también puede ocurrir que esa categoría tenga métodos 
comunes (operaciones propias de la categoría). En el caso
de los métodos se pueden los siguientes casos:

1. La implementación es común a todas las clases y entonces
se implementan únicamente en la base. 
1. Es particular a cada clase y, entonces, el método se declara
abstracto (así como la clase)
1. Es particular a cada clase, pero existe una parte común o 
una implementación por defecto y entonces se implementa
en la clase base, pero se sobreescribe (*override*) en las 
clases derivadas.

Se habla de polimorfismo cuando estamos en los últimos dos
casos. Esto es así porque se puede llamar al método con una 
referencia de la clase base y sin embargo se va a llamar a la
implementación particular de la clase derivada (particular
y diferente en cada clase derivada). 

En muchas ocasiones el polimorfismo se utiliza en combinación
con colecciones de objetos de la categoría correspondiente donde
se guardan instancias de todas las clases derivadas. 

## Se pide

Escribir:

1. una clase abstracta `Figura` 
1. y dos clases derivadas: `Triangulo` y `Cuadrado`. 

La clase `Figura` solo tiene un método abstracto: `calcularArea` que permite calcular
el área de la figura.

Las clases `Triangulo` y `Cuadrado` guardan como atributos sus dimensiones y el calculo 
específico de su área. 

Escribir otra clase con el programa principal para que:

1. cree una lista de figuras con instancias de triángulos y cuadrados 
1. y calcule el área y muestre por pantalla el área de todos ellos. 

## Apartados adicionales

Se pueden intentar muchas otras cosas con las figuras:

* Se les puede poner como dato su centro de coordenadas,
* o una lista de puntos con sus vértices.
* Se pueden *mover* o *rotar* cambiando sus vértices.
* Se pueden dibujar (no es dificil usando `stdlib.StdDraw`).

En cualquiera de esas situaciones resulta particularmente
interesante colocar correctamente lo que es *común* en
la clase base y lo que es *específico* en la derivada.
