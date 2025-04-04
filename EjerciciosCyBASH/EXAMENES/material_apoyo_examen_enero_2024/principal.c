
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *leer(const char *filename, size_t *tam);
char *convertir(const char *buffer, size_t tam);
int escribir(const char *filename, const char *str);

#define MAX_TAM_FILENAME 256

int principal(int argc, char* argv[]) {
    char* filename_in;
    char filename_out[MAX_TAM_FILENAME];
    size_t tam;
    char* texto_win;
    char* texto_utf_8;

    if (argc < 2) {
        return 1;
    }

    filename_in = argv[1];
    strcpy(filename_out, filename_in);
    strcat(filename_out, ".utf-8");

    texto_win = leer(filename_in, &tam);
    if (texto_win == NULL) {
        fprintf(stderr, "Fallo leer\n");
        return 2;
    }

    texto_utf_8 = convertir(texto_win, tam);
    if (texto_utf_8 == NULL) {
        free(texto_win);
        fprintf(stderr, "Fallo convertir\n");
        return 2;
    }

    if (!escribir(filename_out, texto_utf_8)) {
        free(texto_utf_8);
        free(texto_win);
        fprintf(stderr, "Fallo escribir\n");
        return 2;
    }

    free(texto_utf_8);
    free(texto_win);

    return 0;
}