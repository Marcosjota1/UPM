#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "pps_errno.h"


/*
 * Notas:
 * - no es necesario acceder al array p->adrerr[]
 * - el argumento se compara con todos los mensajes de error (bucle)
 * - strcmp() no sirve porque el segundo argumento es subcadena del mensaje
 * - puede haber múltiples coincidencias, no sólo una
 */
int buscar(pps_errtab_t *p, char *texto)  {
  char *msg;
  int i, rcod = 0;

  for (i = 0; i < p->nerrs; ++i) {
    msg = strerror(i+1);
    if (strstr(msg, texto) != NULL) {
      rcod++;
    }
  }
  return rcod;
}

/*
 * Notas:
 * - se debe asignar memoria dinámica para el string a devolver
 * - la cadena resultante tiene la longitud estrictamente necesaria
 * - no se puede devolver una variable local estática
 * - el número de error n puede tener hasta 3 cifras (134 errores distintos)
 * - no es necesrio ningún tipo de bucle. Se accede al array directamente
 * - puesto que los códigos de error empiezan en 1, no en 0, el índice es n-1
 */
char *mostrar(pps_errtab_t *p, unsigned int n) {
  char snum[4] = {'\0', '\0', '\0', '\0'};
  char *cadena;
  char *msg = strerror(n);
  int len = strlen(p->adrerr[n-1].etiq) + strlen(msg) + 4;

  sprintf(snum, "%d", p->adrerr[n-1].nerr);
  len += strlen(snum);
  cadena = (char *) calloc(len + 1, sizeof(char));
  if(cadena != NULL)
    sprintf(cadena, "%s::%d::%s", p->adrerr[n-1].etiq, p->adrerr[n-1].nerr, msg);
  return cadena;
}

/*
 * Notas:
 * - la lectura es binaria
 * - se carga el array en 1 sóla operación I/O (tercer argumento fread)
 * - el valor de retorno depende de que fread() lea una sóla vez
 */
pps_errnos_t *cargar(FILE *fich, int n) {
  pps_errnos_t *errnos;
  int rcod = 0;

  errnos = (pps_errnos_t *) calloc(n, sizeof(pps_errnos_t));
  if(errnos != NULL)
    rcod = fread(errnos, n * sizeof(pps_errnos_t), 1, fich);
  if(rcod == 1)
    return errnos;
  else
    return NULL;
}

/*
 * Notas:
 * - solo un free() ya que sólo la memoria del array es asignada dinámicamente
 * - lo anterior solo ocurre en la función cargar()
 * - p->nerrs = 0 indica que el array está vacío
 * - p->adrerr = NULL es redundante
 */
void vaciar(pps_errtab_t *p) {
  free(p->adrerr);
  p->nerrs = 0;
}

/*
 * Notas:
 * - la escritura es binaria
 * - se escribe el array en 1 sóla operación I/O (tercer argumento fread)
 * - el valor de retorno indica cuántas veces escribe fwrite()
 */
int volcar(FILE *fich, pps_errtab_t *p) {
  int rcod;

  rcod = fwrite(p->adrerr, p->nerrs * sizeof(pps_errnos_t), 1, fich);
  return rcod;
}
