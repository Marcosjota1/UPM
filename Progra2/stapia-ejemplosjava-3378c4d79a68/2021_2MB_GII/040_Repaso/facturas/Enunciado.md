
# Enunciado

## Gestionando facturas

Se pide:

Escribir una clase `Factura` que permita guardar los datos relevante de una 
factura comercial. En principio se va a implementar un modelo muy simplificado, 
pero se invita al alumno a completarlo seg�n su propio criterio. 

La clase `Factura` debe cumplir, al menos, los siguientes requisitos:

1. Tener un concepto
1. Tener una fecha de emisi�n
1. Fijar una fecha de pago a partir de la hora actual del *sistema*
1. Tener un importe
1. Mostrar toda la informaci�n relevante mediante `toString`

Nota: Se debe utilizar como fecha la clase: `java.time.LocalDate`, son especialmente
relevantes los m�todos que permite construir las fechas a partir de los datos de a�o,
mes y d�a:

https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#of-int-int-int-

O a partir del reloj:

https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#now--

La factura deber� reflejar que tiene *dos estados*: pagada o impagada. En el primer
caso debe ser posible obtener la fecha de pago. Inicialmente todas las facturas 
deben estar impagadas.

> Nota: Para ampliar el detalle de la *factura* se puede incluir la gesti�n del IVA...
> Pero no se pide de entrada. 

## Programa principal

Para probar la clase escrita vamos a escribir un programa que haga unas pocas
manipulaciones de un objeto de esta clase. 

Se pide:

* Crear una nueva factura con fecha de emisi�n de hace unos d�as con un importe y 
concepto ficticios
* Mostrar los datos de esa factura por pantalla
* Crear una nueva factura con fecha de emisi�n de hace unos d�as con un importe 
negativo y arreglar "lo que sea necesario"
* *Pagar* la factura y mostrar los nuevos datos por pantalla

## Ordenar las facturas

Vamos a comprobar ahora c�mo se puede ordenar f�cilmente varios objetos. Para ello,
vamos a usar una clase de utilidades respecto de objetos de tipo `Array`, esta clase
de utilidades se denomina `Arrays` (en plural). En concreto, el m�todo:

https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(java.lang.Object[],%20int,%20int)

Permite ordenar un vector de objetos, pero �Atenci�n! como requisito se pide que
los objetos implementen `Comparable`:

https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html

Se pide: 

* Completar la clase `Factura` para que se pueda ordenar un Array de facturas
utilizando como criterio el concepto de la factura.

* Escribir un programa en una clase `OrdenarFacturas` que ordene con ese 
criterio varias facturas ficticias y comprobar el resultado.

## Otros criterios

Se puede usar otra variante de `sort`:

https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(T[],%20java.util.Comparator)

Aunque pueda parecer un poco m�s dificil la idea es la misma: hay un criterio para ordenar 
los objetos y, en este caso, ese criterio se pasa a la funci�n mediante un objeto que 
implementa una interfaz, la interfaz `Comparator`. 

La clase de dicho objeto solo tiene como requisito implementar el m�todo `compare` y, por tanto,
no es necesario que tenga atributos (aunque podr�a tenerlos). 

Se pide:

* Implementar una clase `OrdenarPorFecha` que compare dos facturas por la fecha de emisi�n
* Escribir un programa en la misma clase que compruebe el resultado
* Usar la misma clase para que el criterio sea:
1. Por fecha de emisi�n (igual que antes)
1. Por fecha de pago (se supone que las factura no pagadas son iguales y mayores
que las pagadas). 
* En el programa ordenar las facturas por el nuevo criterio. Para poder generar distintas
fechas de pago, implementar un nuevo m�todo que permita utilizar una fecha anterior a 
la actual como fecha de pago.

## Modificar una lista de `Facturas`

Se pide:

* Escribir una clase ListaFacturas que guarde una lista (tads.LinkedList) de 
objetos `Factura`

> Hay dos opciones:
> 1. Usar herencia
> 2. Usar composici�n

> Razonar la opci�n escogida. En realidad ambas opciones tienen sus ventajas
> e inconvenientes...

* Escribir un m�todo para dicha clase que sume los importes de todas las
facturas.
* Escribir un m�todo que sume por un lado el importe de todas las facturas
pagadas y por otro todas las facturas sin pagar.

> Discuta distintas opciones para declarar dicho m�todo (en sentido amplio)

* Escribir un m�todo para dicha clase que "pague" todas las facturas en la
lista.

* Pruebe cada uno de los puntos anteriores en un programa (`main`)