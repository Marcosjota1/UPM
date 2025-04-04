#include "pps_iniciar.h"
#include <stdio.h>
#include "libwordle.h"
#include "wordle_dicc.h"
#include "test_dicc.h"

/* Definición de las variables globales "públicas" requeridas por libwordle */
FILE *fich_log = NULL;
unsigned int clave_cesar = 0;
unsigned int n_palabras = 0;
char **diccionario = NULL;

/* Implementación de la función iniciar */
int iniciar(char *fdic, char *flog, char modo) {

  FILE *fich_dicc;
  int res;

  fich_dicc = fopen(fdic, "r");
  if(fich_dicc == NULL) {
    return NODIC;
  }

  fich_log = fopen(flog, "w");
  if(fich_log == NULL) {
    fclose(fich_dicc);
    return NOLOG;
  }

  if(modo != 't' && modo != 'j')
    return NOMOD;

  res = cargar_diccionario(fich_dicc);
  if(res < 0) {
    fclose(fich_dicc);
    fclose(fich_log);
    return res;
  }

  if(modo == 't')
    testear_dicc(fdic,flog);
  /*else
    jugar_wordle(); */

  fclose(fich_dicc);
  fclose(fich_log);

  vaciar_diccionario();
  return 0;
}
