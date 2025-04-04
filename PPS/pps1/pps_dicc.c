#include "wordle_dicc.h"
#include <string.h>
#include <stdlib.h>

void cifrado_cesar(int op, char *texto) {
  unsigned int i;

  fprintf(fich_log, "DBG[cifrado_cesar]: INICIO\n");
  if (op != 0 && op != 1){
    fprintf(fich_log, "ERR[cifrado_cesar]: OP debe ser 1 o 0");
  }
  else{
    int ajuste = (op == 0) ? 1 : -1; /*Ajuste para codificación o decodificación*/ 
                                     /*0 suma = codifocar, 1 resta = decodificar*/
   fprintf(fich_log, "DBG[cifrado_cesar]: Se procede a la %s\n", (op == 0) ? "codificación" : "decodificación");

    for (i = 0; i < strlen(texto); i++) {
        texto[i] += clave_cesar * ajuste;

        if (texto[i] > 'Z') 
            texto[i] -= 26;
        else if (texto[i] < 'A') 
            texto[i] += 26;
    }

  }
  fprintf(fich_log, "DBG[cifrado_cesar]: FIN\n");
}

int en_diccionario(char *palabra) {
  int res = 0;
  int i;

  fprintf(fich_log, "DBG[en_diccionario]: INICIO\n");

  /* Codifico la palabra para buscarla en el diccionario */
  cifrado_cesar(0, palabra);

  for(i=0; i < (int)n_palabras && res == 0; i++) {
    if(strcmp(palabra, diccionario[i]) == 0) {
      fprintf(fich_log, "DBG[en_diccionario]: La palabra se encuentra en el diccionario\n");
      res = 1;
    }
  }

  fprintf(fich_log, "DBG[en_diccionario]: FIN\n");
  cifrado_cesar(1,palabra); /* decodificar antes de volver */
  return res;
}

void elegir_palabra(char *palabra, int pos) {
  fprintf(fich_log, "DBG[elegir_palabra]: INICIO\n");

  if(pos < 0 || pos >= (int)n_palabras) {
    fprintf(fich_log, "ERR[elegir_palabra]: Error fuera del intervalo [0, n_palabras)\n");
  }
  else {
    strcpy(palabra, diccionario[pos]);
    fprintf(fich_log, "DBG[elegir_palabra]:Se ha elegido la palabra \n");

  }
  fprintf(fich_log, "DBG[elegir_palabra]: FIN\n");
}

int cargar_diccionario(FILE *fich_dicc) {
  int i;
  wordle_cabecera_dicc_t formato; 
  char pal[LONG_PALABRA_WORDLE+2]; /* añadir \0 y \n */

  fprintf(fich_log, "DBG[cargar_diccionario]: INICIO\n");

  /* leo la primera linea del fichero que contiene */
  fread(&formato, sizeof(wordle_cabecera_dicc_t), 1, fich_dicc); 

  if (ferror(fich_dicc)) {
    fprintf(fich_log, "ERR[cargar_diccionario]: Error en la lectura de la cabecera\n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOLEE;
}

  if(formato.clave < 1 || formato.clave > 25) {
    fprintf(fich_log, "ERR[cargar_diccionario]: La clave césar esta fuera de rango\n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOCLA;
  }

  fprintf(fich_log, "DBG[cargar_diccionario]: La clave es %d\n", formato.clave);

  /* me guardo el valor de las vars globales */
  clave_cesar = formato.clave;
  n_palabras = formato.n_palabras;

  /* creo el diccionario con mem dinamica */
  diccionario = (char **) calloc(n_palabras, sizeof(char *));
  if(diccionario == NULL) {
    fprintf(fich_log, "ERR[cargar_diccionario]: No se ha podido asignar memoria\n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOMEM;
  }

  for(i=0; i < (int)n_palabras; i++) {
    diccionario[i] = (char *) calloc(LONG_PALABRA_WORDLE+1, sizeof(char));
    if(diccionario[i] == NULL) {
      fprintf(fich_log, "ERR[cargar_diccionario]: No se ha podido asignar memoria\n");
      fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
      return NOMEM;
    }
  }

  /* relleno el diccionario con las palabras del fichero */
  i = 0;
  while(!ferror(fich_dicc) && i < (int)n_palabras && 
    fgets(pal, LONG_PALABRA_WORDLE+2,fich_dicc)) {

    /* elimino el posible salto de linea final de la palabra */
    if(pal[strlen(pal)-1] == '\n')
      pal[strlen(pal)-1] = '\0';

    /* la guardo dentro de diccionario */
    strcpy(diccionario[i], pal);

    /* diccionario[i][LONG_PALABRA_WORDLE] = '\0'; */
    
    fprintf(fich_log, "DBG[cargar_diccionario]: Palabra %d: %s\n", i+1, diccionario[i]);

    i++;
  }

  if(i != (int)n_palabras) {
    fprintf(fich_log, "ERR[cargar_diccionario]: Error al cargar las palabras \n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOPAL;
  }

  fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
  return n_palabras;
}

void vaciar_diccionario(void) {
  int i;
  fprintf(fich_log, "DBG[vaciar_diccionario]: INICIO\n");

  for(i=0; i < (int)n_palabras; i++) 
    free(diccionario[i]);
  free(diccionario);
  fprintf(fich_log, "DBG[vaciar_diccionario]: FIN\n");
}