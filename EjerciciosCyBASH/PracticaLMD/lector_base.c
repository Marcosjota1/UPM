/*
 * lector_base.c
 *
 *  Created on: 29 nov 2023
 *      Author: santiago
 */

#include "lector_base.h"


char *aplins[NLIN];     /* índice para las líneas. OJO: memoria estática */
int nlineas = 0;        /* número total de líneas indexadas */

/*
 * Función que realiza las siguientes operaciones:
 * Lee las líneas de un fichero y las guarda en memoria
 * Para leer invoca fgets(). Conviene fijarse bien en
 * la especificación de la función:
 *
 * char *fgets(char *restrict s, int n, FILE *restrict stream);
 * fgets() function shall read bytes until n-1 bytes are
 * read, or a <newline> is read and transferred to s, or
 * an end-of-file condition is encountered.
 * A null byte shall be written immediately after the
 * last byte read into the array
 *
 * Devuelve OK o TOOLIN
 */
int lector_base(FILE *fpath) {
  char buff[MaxLin + 1];          /* buffer (estático) para fgets() */
  char *aux = NULL;
  int slen;
  int lin = 0;
  int nlin = 0;
  int rcod = OK;

  memset(buff, 0, MaxLin+1);      /* inicializar buffer */
  while(rcod == OK && fgets(buff, MaxLin + 1, fpath) != NULL) {
    if(nlineas < NLIN) {
      slen = strlen(buff);
      /*
       * Reservar memoria para la línea
       */
      aux = (char *) calloc(slen+1, sizeof(char));
      if(aux != NULL) {
        memcpy(aux, buff, slen);  /* copiar línea */
        aplins[nlineas] = aux;    /* indexar línea */
        nlineas++;                /* contabilizar línea */
      }
      else {
        fprintf(stderr, "ERROR procesando línea #%d. Memoria insuficiente\n", nlineas);
        return NOMEM;
      }
      memset(buff, 0, MaxLin+1);  /* limpiar buffer */
    }
    else {
      /*
       * Nos pasamos de líneas :-(
       */
      fprintf(stderr, "ERROR: Demasiadas líneas (%d). Memoria insuficiente. Usar modo VIP\n", nlineas);
      rcod = TOOLIN;
    }
  }
  if(rcod == OK) {
    /*
     * Lectura OK. Mostrar en salida estándar
     */
    fprintf(stdout, "Fichero procesado correctamente\n");
    do {
      fprintf(stdout, "Teclear índice en array (en rango [1, %d]). Teclear 0 para salir)\n", nlineas);
      scanf("%d", &lin);
      if(lin > 0 && lin < nlineas+1)
        for(nlin = 0; lin + nlin <= nlineas && nlin < VLIN; nlin++)
          fprintf(stdout, "aplins[%d]: %s", lin+nlin, aplins[lin-1+nlin]);
      else
        if (lin != 0)
          fprintf(stdout, "Valor fuera del rango [1, %d]!!!\n", nlineas);
    } while(lin != 0);
  }
  /*
   * OJO: Liberar memoria
   */
  while(nlineas != 0) {
    free(aplins[nlineas - 1]);
    nlineas--;
  }
  return rcod;
}
