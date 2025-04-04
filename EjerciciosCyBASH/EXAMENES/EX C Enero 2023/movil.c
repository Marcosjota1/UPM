#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "movil.h"


/*
 * Crea dato movil_t y copia los argumentos en la nueva estructura
 * Si no hay memoria devuelve NULL
 * En caso contrario, devuelve la dirección del móvil creado
 */
movil_t *new_movil (const char *imei, const char *modelo, const char *ram, int bat, double pvp, int sspec) {
	movil_t *paux = NULL;

	/*
	 * Items objeto de evaluación (2p):
	 * - el código compila y la ejecución no provoca core
	 * - reserva de memoria dinámica para tipo movil_t y string modelo
	 * - copia y asignación de datos en los argumentos de la función
	 * - control de la respuesta de las llamadas al sistema
	 * - sencillez de la solución
	 * - estructura del código y legilibilidad
	 */
	paux = calloc(1, sizeof(movil_t));
	if(paux != NULL) {
		paux->modelo = calloc((int) strlen(modelo) + 1, sizeof(char));
		if(paux->modelo != NULL) {
			memcpy(paux->imei, imei, 15);
			memcpy(paux->modelo, modelo, strlen(modelo));
			memcpy(paux->ram, ram, strlen(ram));
			paux->bat = bat;
			paux->pvp = pvp;
			paux->sspec = sspec;
		}
		else {
			free(paux);
			paux = NULL;
		}
	}
	return paux;
}

/*
 * Elimina el móvil en la dirección pm
 * Libera toda la memoria ocupada el móvil
 */
void del_movil(movil_t *pm) {
	/*
	 * Items objeto de evaluación (1p):
	 * - el código compila y la ejecución no provoca core
	 * - llamadas a free estrictamente necesarias
	 * - sencillez de la solución
	 * - estructura del código y legilibilidad
	 */
	free(pm->modelo);
	free(pm);
}

/*
 * Esta función convierte el terminal en pmov a un string
 * Requiere como argumentos la dirección del terminal
 * y un array de cadenas de formateo del string resultante
 * https://www.cprogramming.com/tutorial/printf-format-strings.html
 * La función devuelve la dirección de un string acorde
 * con los códigos de formato en el segundo argumento
 * Para hacer la conversión invoca la llamada
 * sprintf() sobre el buffer en la dirección mstr
 * Si la cadena de formateo es NULL el campo se ignora
 * Al terminar la conversión invoca realloc() para reasignar
 * mstr a fin de que ocupe la memoria estrictamente necesaria
 * Devuelve NULL si no hay memoria
 * En caso contrario, devuelve la dirección del string
 */
char *toString(movil_t *pmov, const char *sfmt[], char *mstr)
{
	/*
	 * Items objeto de evaluación (3p):
	 * - el código compila y la ejecución no provoca core
	 * - indexación en buffer mstr
	 * - salvaguarda de mstr en realloc (puntero aux movstr)
	 * - control de la respuesta de las llamadas al sistema
	 * - sencillez de la solución
	 * - estructura del código y legilibilidad
	 */
	int bidx = 0;
	char *movstr = NULL;

	if(sfmt[0] != NULL) {
		sprintf(mstr, sfmt[0], pmov->imei);
		bidx = strlen(mstr);
	}
	if(sfmt[1] != NULL) {
		sprintf(mstr + bidx, sfmt[1], pmov->modelo);
		bidx = strlen(mstr);
	}
	if(sfmt[2] != NULL) {
		sprintf(mstr + bidx, sfmt[2], pmov->ram);
		bidx = strlen(mstr);
	}
	if(sfmt[3] != NULL) {
		sprintf(mstr + bidx, sfmt[3], pmov->bat);
		bidx = strlen(mstr);
	}
	if(sfmt[4] != NULL) {
		sprintf(mstr + bidx, sfmt[4], pmov->pvp);
		bidx = strlen(mstr);
	}
	if(sfmt[5] != NULL) {
		sprintf(mstr + bidx, sfmt[5], pmov->sspec);
		bidx = strlen(mstr);
	}
	if(bidx != 0)
		movstr = realloc(mstr, (bidx + 1) * (int) sizeof(char));
	return movstr;
}
