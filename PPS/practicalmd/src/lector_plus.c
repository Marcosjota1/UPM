/*
 * lector_plus.c
 *
 *  Created on: 29 nov 2023
 *      Author: santiago
 */

#include "lector_base.h"
#include "lector_plus.h"


/*
 * Función que realiza las siguientes operaciones:
 * Lee las líneas de un fichero y las guarda en memoria
 * Para leer invoca fgets()
 * Define un controlador basado en el tipo lector_t para
 * un control más claro y sencillo de la lectura
 * Reserva memoria dinámica para todas las variables salvo
 * el buffer donde lee fgets() que se reutiliza en cada llamada
 * En particular, el índice de líneas se implementa mediante un
 * array de punteros dinámico
 * Los argumentos son, respectivamente, los descriptores del fichero
 * de datos (líneas) y del fichero con los rangos de visualización
 *
 * Devuelve OK o NOMEM
 */
int lector_plus(FILE *dfd, FILE *rfd) {

  return OK;
}
