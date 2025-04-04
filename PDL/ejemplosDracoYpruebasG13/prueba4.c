int main(int argc, char *argv[]) {

    printf("Argc: %d\n",argc);
    int a;
    for(a=0; a<argc; a++){
        printf("ARGV[%d]: %s\n",a,argv[a]);
    }
    if (argc != 2) {  // Verificar si se pasó el argumento de la longitud de la cadena
        printf("Uso incorrecto. Se debe proporcionar un número de caracteres como argumento.\n");
        return 1;
    }

    int i = atoi(argv[1]);  // Convertir el argumento a entero

    if (i <= 0) {  // Verificar si el valor de i es positivo
        printf("El número de caracteres debe ser un número positivo.\n");
        return 1;
    }

    char c;
    char *res = calloc(i + 1, sizeof(char));  // Asignar memoria para la cadena (extra espacio para '\0')

    if (res == NULL) {
        printf("Error al asignar memoria.\n");
        return 1;  // Salir si no se pudo asignar memoria
    }

    printf("Introduzca una cadena de %d caracteres:\n", i);

    // Leer los caracteres y almacenarlos en 'res'
    for (int x = 0; x < i; x++) {
        scanf("%c", &c);  // Leer un carácter
        getchar();
        res[x] = c;  // Guardar el carácter en la posición correspondiente
    }

    res[i] = '\0';  // Agregar el terminador nulo al final de la cadena

    printf("Cadena introducida: %s\n", res);  // Imprimir la cadena introducida

    free(res);  // Liberar la memoria asignada

    return 0;
}