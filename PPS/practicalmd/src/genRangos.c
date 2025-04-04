/*
 * genRangos.c
 *
 *  Created on: 30 nov 2023
 *      Author: santiago
 */

#define OK 0
#define KO -1

typedef struct rango {
  int ini;
  int num;
} rango_t;

typedef rango_t *rango_p;

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


int main(int argc, char * argv[]) {
  rango_t rst;
  rango_p ptr = &rst;
  FILE *fd;
  int salir = 0, rcod;
  char ch;

  fd = fopen (argv[argc-1], "w");
  if(fd == NULL) {
    fprintf(stderr, "Error abriendo el fichero de rangos\n");
    return KO;
  }
  else {
    fprintf(stdout, "Generando el fichero de rangos\n");
    fprintf(stdout, "==============================\n");
  }

  while(!salir) {
    fprintf(stdout, "\nTeclear índice de línea:\t");
    scanf("%d%*c", &rst.ini);
    fprintf(stdout, "Teclear nº líneas del rango:\t");
    scanf("%d%*c", &rst.num);
    /*
     *  Escribir struct mediante fwrite
     */
    rcod = fwrite(ptr, sizeof(rango_t), 1, fd);
    if(rcod != 1) {
      printf("\nError escribiendo al fichero !\n");
      return KO;
    }
    fprintf(stdout, "\nTeclear S para salir o Enter para continuar:\t");
    ch = fgetc(stdin);
    if(toupper(ch) == 'S') {
      salir = 1;
      fclose(fd);
    }
  }
  return OK;
}
