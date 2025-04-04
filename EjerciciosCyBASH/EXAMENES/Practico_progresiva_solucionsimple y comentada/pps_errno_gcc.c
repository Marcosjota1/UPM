#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "pps_errno.h"


int buscar(pps_errtab_t *p, char *texto) {

  fprintf(stderr, "buscar(): %p, %p\n", p, texto);
  return 0;
}

char *mostrar(pps_errtab_t *p, unsigned int n) {

  fprintf(stderr, "mostrar(): %p, %d\n", p, n);
  return NULL;
}

pps_errnos_t *cargar(FILE *fich, int n) {

  fprintf(stderr, "cargar(): %p, %d\n", fich, n);
  return NULL;
}

void vaciar(pps_errtab_t *p) {

  fprintf(stderr, "vaciar(): %p\n", p);
}

int volcar(FILE *fich, pps_errtab_t *p) {

  fprintf(stderr, "#volcar(): %p, %p\n", p, fich);
  return 0;
}
