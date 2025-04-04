#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define TAM_LINEA 256

struct nodo_linea {
char texto[TAM_LINEA];
char *valor;
struct nodo_linea* next;
};
typedef struct nodo_linea nodo_t;

int es_comentario_o_vacia(char* linea){
    if(linea[0]== '/n' || linea[0]==';'){
        return 1;
    } 
    return 0;
}

int comprobar_line(char *linea, int nlinea){
    int ok = 1;
    if (strchr(linea,'/n') == NULL){   /*STRCHR = BUSCA PRIMERA APARICION DE UN CARACTER EN UNA LINEA*/
        ok = 0;
        fprintf(stderr,"#%d:E:Linea demasiado larga",nlinea);
    }
    else if (linea[0]=='/n'){
        fprintf(stderr,"#%d:V:Linea vacia",nlinea);
    }
    else if (linea[0] ==';') {
        fprintf(stderr, "#%d:C: %s", nlinea, linea + 1);
    } else if (linea[0] == '\n') {
        fprintf(stderr, "#%d:V: vacia\n", nlinea);
    } else if (strchr(linea, '=') == NULL) {
        ok = 0;
        fprintf(stderr, "#%d:E: La linea no contiene '='\n", nlinea);
    } else {
        fprintf(stderr, "#%d:N: %s", nlinea, linea);
}
    return ok;
}
/*
3. La función comprobar_formato toma como argumento el nombre (ruta) de un archivo y:
a) Abre, lee y cierra el archivo cuyo nombre se pasa como argumento.
b) Lee las líneas utilizado un buffer de tamaño dado por la macro TAM_LINEA.
c) Llama a la función comprobar_linea con todas las líneas del archivo (o hasta que encuentra una línea con un
error).
d) Retorna 1 (verdadero) si todas las líneas cumplen con la comprobación implementada en comprobar_linea.
e) Retorna 0 (falso) en caso contrario. También devuelve 0 si el archivo no se puede abrir.
*/


int comprobar_formato(char* filename){
    char buffer[TAM_LINEA];
    int nlineas=0;
    int ok=0;
    FILE *f = fopen(filename,"r");
    if (f != NULL){
        while (ok && fgets(buffer,TAM_LINEA,f) != NULL){
            ok = comprobar_line(buffer,nlineas);
            nlineas++;
        }
    }
    fclose(f);
    return ok;
}

/*
4. La función separar_clave_valor toma como argumento la dirección de memoria de una estructura de tipo nodo_t
(está definida al principio de la hoja de respuestas) y:
a) Encuentra las posiciones de los caracteres ’=’ y ’\n’ en el campo texto de la estructura.
b) Asigna el carácter nulo a las posiciones encontradas.
c) Asigna la dirección de memoria inmediatamente posterior al lugar en que se encontraba el carácter ’=’ al
campo valor.
*/

void separar_clave_valor(nodo_t *pnodo){
    char *igual = strchr(pnodo->texto,'=');
    char *salto = strchr(pnodo->texto,'/n');
    *igual = '/0';
    *salto= '/0';
    pnodo->valor = igual+1;
}

/*
5. La función lista_lineas_inv toma como argumentos: el nombre (ruta) de un archivo y la dirección de memoria
de un entero p_ncv y:
a) Lee el archivo de nombre dado y genera una lista enlazada de estructuras nodo_t que almacenan el contenido
de las líneas (no comentario o vacías) del archivo en orden inverso, es decir, insertando las líneas siempre al
principio, y retorna la dirección de memoria al primer elemento.
b) Cuenta las líneas que son comentarios o vacías y guarda el valor obtenido en el entero apuntado por p_ncv.
*/
nodo_t lista_lineas_inv(char *filename, int *p_ncv){
    nodo_t* leer_archivo(const char *filename, int *p_ncv) {
    char buffer[TAM_LINEA];
    FILE *g = fopen(filename, "r");
    nodo_t *prim = NULL, *aux;

    *p_ncv = 0;

    if (g != NULL) {
        while (fgets(buffer, TAM_LINEA, g) != NULL) {
            if (es_comentario_o_vacia(buffer)) {
                *p_ncv += 1;
            } else {
                aux = calloc(1, sizeof(nodo_t));
                strcpy(aux->texto, buffer);
                aux->next = prim;
                prim = aux;
            }
        }
        fclose(g);
    }

    return prim;
}
}

/*
La función liberar toma como argumento una lista enlazada a cuyo primer elemento apunta las_lineas y elimina
todos los nodos y libera la memoria reservada anteriormente sin provocar fugas de memoria.
*/
void liberar (nodo_t *las_lineas){
    nodo_t *aux;
    while (las_lineas != NULL){
        aux = las_lineas;
        las_lineas = las_lineas->next;
        free(aux);
    }

}


/*
La función main:
a) Comprueba que recibe un único argumento y si no es así termina con un código de error 16.
b) Utiliza el argumento recibido como el nombre de un archivo para comprobar si el archivo tiene el formato
correcto, si no es así termina con un código de error 17.
c) Llama a la función lista_lineas para generar una lista enlazada de estructuras nodo_t a partir del argumento.
d) Muestra el número de líneas leídas y el número de ellas que son comentarios o vacías en el canal de salida
estándar.
*/
int main(int argc, char* argv[]){
    nodo_t *lineas;
    int lineamala;
    if(argc != 2){
        exit(16);
    }
    if (comprobar_formato(argv[1]) == 0){
        exit(17);
    }
    lineas  = lista_lineas_inv(argv[1],&lineamala);
    fprintf(stderr,"Hay %d lineas de comentario o vacias",lineamala)
    procesar_lineas(lineas);
    mostrar_clave_valor(lineas);
    liberar(lineas);
    return 0;
}

