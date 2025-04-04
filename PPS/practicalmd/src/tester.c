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








    break;
  default:
    fprintf(stderr, "Parámetros (%d) incorrectos. No hace nada\n", argc);
    fprintf(stderr, "Ejecución modo básico: %s base -f [path_fichero]\n", argv[0]);
    fprintf(stderr, "Ejecución modo plus: %s plus -r path_fichero [-f path_fichero]\n", argv[0]);
    return NOARGS;

  }
  return rcod;
}
