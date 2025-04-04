#include "pps_iniciar.h"
#include <stdio.h>
#include "libwordle.h"
#include "wordle_dicc.h"
#include "test_dicc.h"

#define GAME_MODE 'j'
#define TEST_MODE 't'

/* Definición de las variables globales "públicas" requeridas por libwordle */
FILE *fich_log ;
unsigned int clave_cesar;           
unsigned int n_palabras;
char **diccionario;

/* Implementación de la función iniciar */


int iniciar(char *fdic, char *flog, char modo) {
    FILE *fich_dicc = fopen(fdic,"r");
    unsigned int dic;    

    /* Load the dictionary */
    fich_dicc = fopen(fdic, "r");
    if(fich_dicc == NULL) {
    fprintf(stderr, "Error al abrir el archivo de diccionario\n");
    return NODIC;
    }
    /* Open the log file */
    fich_log = fopen(flog, "w");
    if (fich_log == NULL) {
        fprintf(stderr, "Error al abrir el log\n");
        return NOLOG;
    }

    /* Check if the mode is valid */
    dic = cargar_diccionario(fich_dicc);
    if(dic == n_palabras){ /*Probar strcomp despues*/
        
        if(modo == TEST_MODE) {
            testear_dicc(fdic,flog);
            dic = 0;
        }
           
        else if(modo == GAME_MODE) {
            jugar_wordle();
            dic = 0;
        }
        else{/*cuando no sea ninguno de los dos casos*/
            fprintf(stderr, "Error: Modo no válido\n");
            dic = NOMOD;
        }  
        }

    /* Cleanup and return success code */
    
    fclose(fich_dicc);
    fclose(fich_log);   /* Close the log file */
    vaciar_diccionario();
    return dic;
}