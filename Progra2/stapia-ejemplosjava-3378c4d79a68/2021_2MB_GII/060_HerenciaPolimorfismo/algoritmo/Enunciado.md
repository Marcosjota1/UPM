
# Enunciado

## Introducción

Es común pensar que los objetos siempre tienen un estado
y representan algo *físico*, sin embargo, esto no es así.

De hecho, ocurre con cierta frecuencia que una clase representa
un algoritmo y en estas ocasiones suele ocurrir que el objetivo
de representar ese algoritmo con una clase sea hacer polimorfismo.

En Java, para hacer polimorfismo se puede usar una clase base
o también una interfaz. Siendo este caso más habitual cuando
no hay un estado que se reconozca como común sino que solo
existen operaciones comunes. 

## Se pide:

Escribir una interfaz: `ICalcularSobreVector` que declare una operación,
el método `aplicar` que toma como parámetro un vector de números en 
coma flotante (double) y devuelve como resultado también en coma
flotante. 

Escribir las clases: `Media`, `Minimo` y `Maximo` para que implementen
la interfaz `ICalcularSobreVector` realizando el cálculo correspondiente
a su nombre. 

Escribir un programa que:

1. Guarde en un vector un objeto de cada una de las clases 
1. Genere un vector de números en coma flotante
1. Llame a cada método y muestre el resultado
