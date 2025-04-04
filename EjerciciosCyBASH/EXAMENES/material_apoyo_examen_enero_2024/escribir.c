
#include <stdio.h>
#include <string.h>

int escribir(const char *filename, const char *str) {
    /*IniciarRespuesta*/
    FILE *f = fopen(filename,"w");
    if (f == NULL){
        fprintf(stderr, "No se puede abrir el archivo: ’ %s’\n", filename);
        return 0;
    }
    fwrite(str,1,strlen(str),f);
    fclose(f);
    return 1;

    /*FinalDeRespuesta*/
}
