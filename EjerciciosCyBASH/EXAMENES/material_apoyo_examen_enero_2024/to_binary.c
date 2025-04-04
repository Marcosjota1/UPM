#include <stdio.h>

int main()
{
    int num = fgetc(stdin);
    int linea = 0;
    while ( num >= 0 ) {
        printf("%4d", num);
        if ( linea >= 20 ) {
            printf("\n");
            linea = 0;
        }
        num = fgetc(stdin);
        ++linea;
    }
    return 0;
}
