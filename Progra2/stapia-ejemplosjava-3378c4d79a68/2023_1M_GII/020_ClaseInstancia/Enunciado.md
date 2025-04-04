
# De Clase y De Instancia

## Objetivo 

Se trata de entender los conceptos "de clase" y "de instancia". 

Distinguiendo: la manera en que se definen y se llaman y la existencia o no de this 
y la posibilidad de usar otros atributos o métodos.

Además se va a introducir otros conceptos:

* **final**: no reasignable.
* **Inmutable**: que su estado no puede cambiar. 

## Escribir (otra vez) una clase Punto

Escribir una clase Punto que tenga las siguientes características:

* Dos atributos con las coordenadas del punto.
* La coordenada x debe ser "no reasignables", es decir, `final`.
* Completar la clase para que se pueda usar.
* Escribir un método toString
* Escribir un método mover. ¿Qué limitación tiene este método?

## Escribir una clase Segmento

Escribir una clase Segmento tenga las siguientes características:

* Debe tener dos puntos (sus extremos). 
* Uno de esos puntos debe ser `final`.
* Completar la clase para que se pueda usar.
* Escribir un método mover. ¿Qué limitación tiene este método?

## Volvemos a Punto

* Escribir un método **de clase** que construya un segmento a partir de dos puntos.
* Escribir el mismo método, pero **de instancia**. 

## Inmutable de varias formas

* Escribir una clase PuntoFinal cuyo estado no se pueda reasignar.
* Escribir una clase PuntoInmutable que sea inmutable, pero sin usar final.

## Preguntas

* ¿Es posible hacer que los segmentos sean inmutables? 
* ¿Qué es necesario?
* ¿Alternativas?
