
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
Convierte un byte (char) codificado en formato Windows-1252 y lo transforma en 2 bytes en uft-8 cuando se trate de 
alguna de las vocales minúsculas con tilde.

Parámetros:
+ (in) win, el byte a convertir
+ (out) un puntero que apunte a una zona de memoria donde se puedan guardar, al menos, dos bytes (char).

+ Retorno: el tamaño de la codificación en utf-8 del byte a convertir, es decir, 1 si no era una vocal 
con tilde y 2 si lo era.

Al convertir una vocal con tilde se va a guardar en el char apuntado por dest y en el que está a continuación 
la codificación en utf-8 (que son 2 bytes). Cualquier otro caracter se escribe directamente en el char apuntado 
por dest sin ninguna modificación.
Nota: se muestra el código de cada letra procesada en el canal de errores para facilitar la depuración de errores.
*/
int win_1252_a_utf_8(int win, char *dest) {
    int n_bytes_copiados = 1;
    fprintf(stderr, "char: %d\n", win); /* Para facilitar la depuración de errores */
    switch (win)
    {
    case -31: /* á en Win 1252 */
        dest[0] = -61;
        dest[1] = -95;
        n_bytes_copiados = 2;
        break;
    case -23:              /* é en Win 1252 */
        dest[0] = -61;
        dest[1] = -87;
        n_bytes_copiados = 2;
        break;
    /* Otras vocales con tilde y la eñe omitidas por brevedad */
    default:
        *dest = win; /* Copia directamente cualquier otro caracter */
        break;
    }
    return n_bytes_copiados;
}
char* convertir(const char* buffer, size_t tam) {
    size_t i, j;
    char* resultado = calloc(2 * tam + 1, 1);

    if (resultado != NULL) {
        for (i = 0, j = 0; i < tam; ++i) {
            /* Salta el CR (#13) y copiará el LF(#10) en la siguiente iteración */
            if (buffer[i] != 13) {
                j += win_1252_a_utf_8(buffer[i], resultado + j);
            }
        }
        /* No hace falta poner 0 al final porque se ha usado calloc */
    }

    return resultado;
}
