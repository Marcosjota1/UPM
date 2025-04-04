/*
 * tester.c
 *
 *  Created on: 9 nov. 2022
 *      Author: santiago
 */

#include "lector_base.h"
#include "lector_plus.h"


int main(int argc, char *argv[]) {
  char *fpath = NULL, *rpath = NULL;
  FILE *dfd, *rfd;
  int i, rcod = 1;

  switch(argv[1][0]) {
  case 'b':        /* Ejecución modo básico */
    if(argc == 3) {
      dfd = fopen(argv[2], "r");
      if(dfd == NULL) {
        fprintf(stderr, "ERROR: Fichero %s no válido\n", argv[2]);
        rcod = NOPATH;
      }
      else
        rcod = lector_base(dfd);
    }
    else
      rcod = NOARGS;
    break;
  case 'p':        /* Ejecución modo plus */
    for (i = 2; rcod == 1 && i < argc; i+=2) {   /* bucle argumentos */
      switch(argv[i][1]) {
      case 'f':    /* Fichero a leer (opcional) */
        dfd = fopen(argv[i+1], "r");
        if(dfd == NULL) {
          fprintf(stderr, "ERROR: Fichero datos %s no válido\n", argv[2]);
          rcod = NOPATH;
        }
        else
          fpath = argv[i+1];
        break;
      case 'r':    /* Fichero rangos (obligatorio) */
        rfd = fopen(argv[i+1], "r");
        if(rfd == NULL) {
          fprintf(stderr, "ERROR: Fichero rangos %s no válido\n", argv[2]);
          rcod = NOPATH;
        }
        else
          rpath = argv[i+1];
        break;
      default:
        rcod = NOARGS;
      }
    }
    if(rcod == 1 && rpath != NULL) {
      if(fpath != NULL) {
        rcod = lector_plus(dfd, rfd);
        fclose(dfd);
        fclose(rfd);
      }
      else {
        rcod = lector_plus(stdin, rfd);
        fclose(rfd);
      }
    }
    break;
  default:
    fprintf(stderr, "parámetros (%d) incorrectos. No hace nada\n", argc);
    fprintf(stderr, "Ejecución modo básico: %s base -f [path_fichero]\n", argv[0]);
    fprintf(stderr, "Ejecución modo plus: %s plus -r path_fichero [-f path_fichero]\n", argv[0]);
    return NOARGS;

  }
  return rcod;
}
