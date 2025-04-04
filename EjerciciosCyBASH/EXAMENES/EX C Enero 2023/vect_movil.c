#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "movil.h"
#include "vect_movil.h"

int vect_movil(int n, const char *sfmt[], const char *vmov[NM][NC]) {
  movil_t **moviles = NULL;
  char *buffer;
  int result = OK;
  int ultimo;
  int i;

  /* Crear array de punteros: 1pt */
  moviles = (movil_t **) calloc(n, sizeof(movil_t *));

  if(moviles == NULL) {
    result = NOMEM;
  }

  /* Procesar array con móviles: 1pt */
  for (i = 0; i < n && result == OK; i++) {
    ultimo = NM - 1 - i;
    moviles[i] = new_movil(vmov[ultimo][0],
                           vmov[ultimo][1],
                           vmov[ultimo][2],
                           (int) atoi(vmov[ultimo][3]),
                           atof(vmov[ultimo][4]),
                           (int) atoi(vmov[ultimo][5]));
    if(moviles[i] == NULL) {
      result = NOMEM;
    }
  };

  /* Imprimir n terminales invocando toString(): 1pt */
  for (i = 0; i < n && result == OK; i++) {
    buffer = calloc(MAXB, sizeof(char));
    if(buffer == NULL) {
      result = NOMEM;
    }
    else {
      buffer = toString(moviles[i], sfmt, buffer);
      printf("%s\n", buffer);
      free(buffer);
    }
  }

  /* Liberar memoria: 1pt */
  for(i = 0; i < n; i++) {
    if (moviles[i] != NULL) {
      del_movil(moviles[i]);
    }
  }
  free(moviles);

  return result;
}
