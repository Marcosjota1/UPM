#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "lee_movil.h"

/*
 * Versión PPS de strndup(). Tomada literalmente
 * de la solución de los ejercicios prácticos
 */
char *ppsndup(const char *str, size_t len) {
    char *dst = calloc(len+1, sizeof(char));
    if (dst != NULL)
    	memcpy (dst, str, len);
    return dst;
}

/*
 * n es el número de líneas que se van a leer
 * Siempre es mayor que 0
 * f es el descriptor del fichero (o stdin) desde
 * donde se leen los datos. Nunca es NULL
 */
char **lee_movil(int n, FILE *f) {
	/*
	 * Items objeto de evaluación (4p):
	 * - el código compila y la ejecución no provoca core
	 * - reserva de memoria dinámica para la matriz resultado
	 * - procesado de las líneas de datos
	 * - consumo de memoria mínimo
	 * - control de la respuesta de las llamadas al sistema
	 * - sencillez de la solución
	 * - estructura del código y legilibilidad
	 */
	char **mm = NULL;
	/*
	 * Array para leer con fgets()
	 */
	char linea[MaxLinea];
    /*
     * Puntero auxiliar para strtok()
     */
    char *token = NULL;
    /*
     * Resultado de leer la línea
     * Es para comprobar que todas tienen
     * exactamente NumCampos
     */
    int rlin = OK;
    /*
     * nm: número de móviles (filas)
     * nc: numero de campos (columnas)
     */
    int nm = 0, nc = 0;
    int slen;

    /*
     * Definir la matriz de punteros para
     * indexar las cadenas de caracteres
     */
	mm = (char **) calloc(n * NumCampos, sizeof(char *));
    if(mm != NULL) {
        /*
    	 * Bucle para procesar las líneas de datos
    	 */
    	while(fgets(linea, MaxLinea, f) != NULL && nm < n && rlin == OK) {
    		/* El if es para ignorar líneas en blanco en los datos */
    		if (linea[0] != '\n') {
    			token = strtok(linea, ",");
    			while(nc < NumCampos && token != NULL) {
    				slen = (int) strlen(token);
    				if(token[slen - 1] == '\n')
    					token[slen - 1] = '\0';
    				mm[nm + n*nc] = (char *) ppsndup(token, slen);
    				nc++;
    				token = strtok(NULL, ",");
    			}
    			if (token != NULL || nc != NumCampos) {
    				free(mm);
    				mm = NULL;
    				rlin = KO;
    			}
    			else {
    				nm++;
					nc = 0;
    			}
    		}
    	}
    }
    return mm;
}

