
#include <stdio.h>
#include <stdlib.h>

/**
Lee un archivo directamente en binario y devuelve su contenido en un array dinámico de bytes (char).

Parámetros:
+ (in) f: el puntero a un archivo YA abierto correctamente.
+ Retorna: el tamaño del archivo en bytes.

La función vuelve a dejar el archivo preparado para que se pueda leer desde el principio.
*/
size_t tam_de(FILE* f) {
    size_t pos;
    fseek(f, 0L, SEEK_END); /* Busca final de archivo */ 
    pos = ftell(f);         /* Obtiene la posición */  
    rewind(f);              /* Vuelve al principio */ 
    return pos;
}

/* Corrección y estructuración del código proporcionado: */

char* leer(const char *filename, size_t *tam) {
    /* Inicio de la respuesta */

    // Abrir el archivo en modo lectura binaria
    FILE *g = fopen(filename, "rb");

    // Declaración de variables
    char *buffer;
    size_t leidos;

    // Comprobar si el archivo se abrió correctamente
    if (g == NULL) {
        // Imprimir un mensaje de error
        fprintf(stderr, "No se puede abrir el archivo: '%s'\n", filename);

        // Devolver NULL para indicar un error
        return NULL;
    }

    // Obtener el tamaño del archivo
    *tam = tam_de(g);
    // Reservar memoria para el búfer
    buffer = malloc(*tam);

    // Comprobar si se reservó memoria correctamente
    if (buffer == NULL) {
        // Cerrar el archivo
        fclose(g);
        fprintf(stderr, "No se puede reservar más memoria\n");
        // Devolver NULL para indicar un error
        return NULL;
    }
    // Leer el archivo en el búfer
    leidos = fread(buffer, 1, *tam, g);

    // Comprobar si se leyó todo el archivo
    if (leidos != *tam) {
        fprintf(stderr, "No se ha podido leer completamente el archivo '%s'\n", filename);

        // Cerrar el archivo
        fclose(g);

        // Liberar la memoria reservada para el búfer
        free(buffer);

        // Devolver NULL para indicar un error
        return NULL;
    }

    // Imprimir un mensaje informativo
    fprintf(stderr, "Leídos %ld, tamaño %ld\n", leidos, *tam);

    // Cerrar el archivo
    fclose(g);

    // Devolver el búfer con el contenido del archivo
    return buffer;

    /* Fin de la respuesta */
}