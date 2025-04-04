/*
 * lector_base.h
 *
 *  Created on: 29 nov 2023
 *      Author: santiago
 */

#define OK 0
#define KO -1

#define MaxLin 80       /* Longitud máxima de línea */
#define NLIN 500        /* Máximo número de líneas. Capacidad inicial del buffer */
#define VLIN 5          /* Número de líneas en la ventana de visualización (modo base) */

#define NOARGS -10      /* Error en los args en la línea de orden */
#define NOPATH -11      /* Error en el path del archivo */
#define TOOLIN -12      /* Archivo con más líneas de las contempladas */
#define NOMEM -13       /* Fallo de asignación de memoria */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int lector_base(FILE *fpath);
