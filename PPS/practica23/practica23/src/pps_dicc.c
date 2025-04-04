#include "wordle_dicc.h"
#include <string.h>
#include <stdlib.h>

void cifrado_cesar(int op, char *texto) {
  int i;

  fprintf(fich_log, "DBG[cifrado_cesar]: INICIO\n");

  if(op == 0) { /* codifica -> sumo la clave cesar */
    fprintf(fich_log, "DBG[cifrado_cesar]: Se procede a la codificación\n");
    for(i=0; i < (int)strlen(texto); i++) {
      texto[i] += clave_cesar;
      if(texto[i] > 'Z') 
        texto[i] -= 26;
    }
  }

  if(op == 1) { /* decodifica -> resto la clave cesar */
    fprintf(fich_log, "DBG[cifrado_cesar]: Se procede a la decodificación\n");
    for(i=0; i < (int)strlen(texto); i++) {
      texto[i] -= clave_cesar;
      if(texto[i] < 'A') 
        texto[i] += 26;
    }
  }

  else {
    fprintf(fich_log, "ERR[cifrado_cesar]: Opción incorrecta (0 para codificar "\
      "y 1 para decodificar)\n");
  }

  fprintf(fich_log, "DBG[cifrado_cesar]: FIN\n");
}

int en_diccionario(char *palabra) {
  int res = 0, i;

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
  cifrado_cesar(1,palabra); /* antes de abandonar el metodo la vuelvo a decodificar */

  return res;
}

void elegir_palabra(char *palabra, int pos) {
  fprintf(fich_log, "DBG[elegir_palabra]: INICIO\n");

  if(pos < 0 || pos >= (int)n_palabras) {
    fprintf(fich_log, "ERR[elegir_palabra]: pos DEBE pertenecer "\
      "al intervalo [0, n_palabras)\n");
  }

  else {
    strcpy(palabra, diccionario[pos]);
  }

  fprintf(fich_log, "DBG[elegir_palabra]: FIN\n");
}

int cargar_diccionario(FILE *fich_dicc) {
  int i;
  wordle_cabecera_dicc_t formato; 
  char pal[LONG_PALABRA_WORDLE+2]; /* + salto + nulo */

  fprintf(fich_log, "DBG[cargar_diccionario]: INICIO\n");

  /* leo la primera linea del fichero que contiene */
  fread(&formato, sizeof(wordle_cabecera_dicc_t), 1, fich_dicc); 

  if(formato.clave < 1 || formato.clave > 25) {
    fprintf(fich_log, "ERR[cargar_diccionario]: La clave césar debe pertenecer "\
      "al rango [1,25]\n");
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
    fprintf(fich_log, "ERR[cargar_diccionario]: No se ha podido asignar mem din\n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOMEM;
  }

  for(i=0; i < (int)n_palabras; i++) {
    diccionario[i] = (char *) calloc(LONG_PALABRA_WORDLE+1, sizeof(char));
    if(diccionario[i] == NULL) {
      fprintf(fich_log, "ERR[cargar_diccionario]: No se ha podido asignar mem din\n");
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
    
    fprintf(fich_log, "DBG[cargar_diccionario]: Palabra %d-ésima: %s\n", i+1, 
      diccionario[i]);

    i++;
  }

  if(i != (int)n_palabras) {
    fprintf(fich_log, "ERR[cargar_diccionario]: Se han cargado %d palabras en "\
      "lugar de %d\n", i, n_palabras);
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOPAL;
  }

  if(ferror(fich_dicc)) {
    fprintf(fich_log, "ERR[cargar_diccionario]: Error en la lectura del "\
      "fichero diccionario\n");
    fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
    return NOLEE;
  }

  fprintf(fich_log, "DBG[cargar_diccionario]: FIN\n");
  return n_palabras;
}

void vaciar_diccionario(void) {
  int i;
  fprintf(fich_log, "DBG[vaciar_diccionario]: INICIO\n");

  if (diccionario!= NULL){
    for(i=0; i < (int)n_palabras; i++) 
    free(diccionario[i]);
  free(diccionario);
  }
  fprintf(fich_log, "DBG[vaciar_diccionario]: FIN\n");
}
