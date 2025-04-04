/*
 * lector_plus.c
 *
 *  Created on: 29 nov 2023
 *      Author: santiago
 */

#include "lector_base.h"
#include "lector_plus.h"


/*
 * Función que recoje la línea reservando la memoria necesaria
 * Redimensiona el índice si se alcanza el límite de capacidad
 * Fusiona las líneas que debido a su longitud superior
 * al límite MaxLin ocasionan sucesivas llamadas a fgets()
 * Devuelve OK o NOMEM
 */
int indexLin(char *linea, lector_ctrl lctrl) {
  char **auxidx = NULL;      /* auxiliar para realloc */
  char *auxlin = NULL;       /* auxiliar para realloc */
  int slen = strlen(linea);

  /*
   * Comprobar el tamaño del índice. De inicio son NLIN entradas
   * Cuando se llena se incrementa (realloc) en bloques de
   * lctrl->ratio entradas adicionales
   */
  if((lctrl->lins == lctrl->max) && ((lctrl->lins % lctrl->ratio) == 0)) {
    fprintf(stderr, "OJO: línea %d => redimensionar el array\n", lctrl->lins);
  /*
   * OJO: si la llamada fuera
   * lctrl->linidx = (char **) lctrl->linidx, (lctrl->lins + lctrl->ratio) * sizeof(char *));
   * en el caso de que realloc falle (devuelve NULL) se perdería el índice y la memoria
   * asignada quedaría dereferenciada (en el limbo)
   * Por eso es recomendable usar siempre una variable auxiliar
   */
    auxidx = (char **) realloc(lctrl->linidx, (lctrl->lins + lctrl->ratio) * sizeof(char *));
    if(auxidx == NULL) {
      fprintf(stderr, "ERROR redimensionando índice. Memoria insuficiente\n");
      return NOMEM;
    }
    else {         /* actualizar direcc del índice y el tamaño máximo */
      lctrl->linidx = auxidx;
      lctrl->max = lctrl->lins + lctrl->ratio;
    }
  }

  /*
   * Si es continuación de la línea anterior => reasignar memoria
   */
  if(lctrl->cr) {
    auxlin = (char *) realloc(lctrl->linidx[lctrl->lins], (MaxLin+slen+1) * sizeof(char));
    if(auxlin != NULL) {
      memcpy(auxlin + MaxLin, linea, slen);
      auxlin[MaxLin+slen] = 0;    /* asegurar que la línea es Z-ended */
    }
    else {
      fprintf(stderr, "ERROR redimensionando línea. Memoria insuficiente\n");
      return NOMEM;
    }
  }

  /*
   * Si no es continuación de la línea anterior => asignar memoria nueva
   */
  else {
    auxlin = (char *) calloc(slen+1, sizeof(char));
    if(auxlin != NULL)
      memcpy(auxlin, linea, slen);
    else {
      fprintf(stderr, "ERROR redimensionando línea. Memoria insuficiente\n");
      return NOMEM;
    }
  }

  lctrl->linidx[lctrl->lins] = auxlin; /* indexar línea */
  if(linea[slen-1] == '\n') {          /* si finaliza en \n */
    lctrl->cr = 0;                     /* línea completa */
    lctrl->lins++;                     /* contabilizar línea */
  }
  else
    lctrl->cr = 1;                     /* línea incompleta */
  return OK;
}

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
  lector_ctrl lctrl;
  char buff[MaxLin + 1];     /* buffer (estático) para fgets() */
  int nlin = 0;
  int rcod = OK;
  rango_t rango;     /* array de rangos de líneas */
  rango_p pr = &rango;

  /*
   * Definir controlador lctrl
   */
  lctrl = (lector_ctrl) calloc(1, sizeof(lector_t));
  if(lctrl == NULL) {
    fprintf(stderr, "lector_plus(): ERROR: Sin memoria para el controlador\n");
    return NOMEM;
  }
  /*
   * Establecer los valores iniciales (tamaño y ratio)
   */
  lctrl->max = NLIN;
  lctrl->ratio = NLIN / RAIDX;
  /*
   * Definir índice (array de punteros)
   */
  lctrl->linidx = (char **) calloc(lctrl->max, sizeof(char *));
  if(lctrl->linidx == NULL) {
    fprintf(stderr, "lector_plus(): ERROR: Sin memoria para el índice\n");
    return NOMEM;
  }

  /*
   * Leer fichero
   */
  memset(buff, 0, MaxLin+1);      /* inicializar buffer */
  while(rcod == OK && fgets(buff, MaxLin + 1, dfd) != NULL) {
    rcod = indexLin(buff, lctrl); /* indexar línea */
    memset(buff, 0, MaxLin+1);    /* limpiar buffer */
  }

  /*
   * Visualizar rangos
   */
  if(rcod == OK) {
    /*
     * Lectura OK. Mostrar en salida estándar
     */
    fprintf(stdout, "Fichero procesado correctamente\n");

    while(fread(pr, sizeof(rango_t), 1, rfd)) {
      if(rango.ini > 0 && rango.ini <= lctrl->lins) {
        fprintf(stdout, "Rango [%d,+%d]:\n", rango.ini, rango.num);
        for(nlin = 0; rango.ini + nlin <= lctrl->lins && nlin < rango.num; nlin++)
          fprintf(stdout, "lctrl->linidx[%d]: %s", rango.ini+nlin, lctrl->linidx[rango.ini-1+nlin]);
      }
      else
        fprintf(stdout, "Rango[%d,%d] no es válido\n", rango.ini, rango.ini + rango.num);
    }
  }
  
  /*
   * OJO: Liberar memoria
   * Tres operaciones:
   * 1. Liberar la memoria que ocupan las líneas
   * 2. Liberar la memoria que ocupa el índice (array de punteros)
   * 3. Liberar la memoria que ocupa lector_t
   */
  while(lctrl->lins != 0) {
    free(lctrl->linidx[lctrl->lins - 1]);
    lctrl->lins--;
  }
  free(lctrl->linidx);
  free(lctrl);
  return rcod;
}
