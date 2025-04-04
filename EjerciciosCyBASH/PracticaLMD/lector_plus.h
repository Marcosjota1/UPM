/*
 * lector_plus.h
 *
 *  Created on: 29 nov 2023
 *      Author: santiago
 */

#define RAIDX 10             /* El ratio de aumento del índice (10%) */
#define SIZAR 5              /* Tamaño del array de rangos */

typedef struct rango {       /* struct que define un rango [ini,+num] de líneas */
  int ini;                   /* primera línea */
  int num;                   /* número de líneas */
} rango_t;

/*
 * Tipo puntero a rango_t
 */
typedef rango_t *rango_p;

/*
 * Struct para el control del array dinámico
 */
typedef struct lector_plus {
  char **linidx;             /* índice (array de punteros, doble indirección) */
  int max;                   /* número max de entradas */
  int lins;                  /* número actual de entradas */
  int ratio;                 /* el ratio de incremento del array */
  unsigned char cr;          /* indica 1 si la línea actual está incompleta (sin \n) */
} lector_t;

/*
 * Tipo puntero a lector_t
 */
typedef lector_t *lector_ctrl;

int lector_plus(FILE *dfd, FILE *rfd);
