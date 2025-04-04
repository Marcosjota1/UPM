
# Enunciado

## Introducci�n

Es com�n pensar que los objetos siempre tienen un estado
y representan algo *f�sico*, sin embargo, esto no es as�.

De hecho, ocurre con cierta frecuencia que una clase representa
un algoritmo y en estas ocasiones suele ocurrir que el objetivo
de representar ese algoritmo con una clase sea hacer polimorfismo.

En Java, para hacer polimorfismo se puede usar una clase base
o tambi�n una interfaz. Siendo este caso m�s habitual cuando
no hay un estado que se reconozca como com�n sino que solo
existen operaciones comunes.�

## Se pide:

Escribir una interfaz: `ICalcularSobreVector` que declare una operaci�n,
el m�todo `aplicar` que toma como par�metro un vector de n�meros en 
coma flotante (double) y devuelve como resultado tambi�n en coma
flotante. 

Escribir las clases: `Media`, `Minimo` y `Maximo` para que implementen
la interfaz `ICalcularSobreVector` realizando el c�lculo correspondiente
a su nombre.�

Escribir un programa que:

1. Guarde en un vector un objeto de cada una de las clases 
1. Genere un vector de n�meros en coma flotante
1. Llame a cada m�todo y muestre el resultado
