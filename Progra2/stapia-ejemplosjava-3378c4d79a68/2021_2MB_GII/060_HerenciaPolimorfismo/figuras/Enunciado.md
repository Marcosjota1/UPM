# Enunciado

## Introducci�n

Es frecuente, al realizar el modelo de objetos de un problema,
encontrar una estructura de clases derivadas de una misma
clase base que es una categor�a *pura* en el sentido de que
no existen objetos de esa clase base. 

Un ejemplo t�pico son las figuras geom�tricas planas. No existe
una figura que solo sea figura, los objetos figura son tri�ngulos,
cuadrados, rect�ngulos, etc. Sin embargo, todas estas figuras
comparten esa categor�a: son figuras planas. 

Puede ocurrir que esa clase base tenga una serie de atributos
que son comunes a todos los objetos de la categor�a, entonces,
esos atributos se declaran en la clase base y son heredados por
todas las clases derivadas. 

Y tambi�n puede ocurrir que esa categor�a tenga m�todos 
comunes (operaciones propias de la categor�a). En el caso
de los m�todos se pueden los siguientes casos:

1. La implementaci�n es com�n a todas las clases y entonces
se implementan �nicamente en la base. 
1. Es particular a cada clase y, entonces, el m�todo se declara
abstracto (as� como la clase)
1. Es particular a cada clase, pero existe una parte com�n o 
una implementaci�n por defecto y entonces se implementa
en la clase base, pero se sobreescribe (*override*) en las 
clases derivadas.

Se habla de polimorfismo cuando estamos en los �ltimos dos
casos. Esto es as� porque se puede llamar al m�todo con una 
referencia de la clase base y sin embargo se va a llamar a la
implementaci�n particular de la clase derivada (particular
y diferente en cada clase derivada). 

En muchas ocasiones el polimorfismo se utiliza en combinaci�n
con colecciones de objetos de la categor�a correspondiente donde
se guardan instancias de todas las clases derivadas. 

## Se pide

Escribir:

1. una clase abstracta `Figura` 
1. y dos clases derivadas: `Triangulo` y `Cuadrado`. 

La clase `Figura` solo tiene un m�todo abstracto: `calcularArea` que permite calcular
el �rea de la figura.

Las clases `Triangulo` y `Cuadrado` guardan como atributos sus dimensiones y el calculo 
espec�fico de su �rea. 

Escribir otra clase con el programa principal para que:

1. cree una lista de figuras con instancias de tri�ngulos y cuadrados 
1. y calcule el �rea y muestre por pantalla el �rea de todos ellos. 

## Apartados adicionales

Se pueden intentar muchas otras cosas con las figuras:

* Se les puede poner como dato su centro de coordenadas,
* o una lista de puntos con sus v�rtices.
* Se pueden *mover* o *rotar* cambiando sus v�rtices.
* Se pueden dibujar (no es dificil usando `stdlib.StdDraw`).

En cualquiera de esas situaciones resulta particularmente
interesante colocar correctamente lo que es *com�n* en
la clase base y lo que es *espec�fico* en la derivada.
