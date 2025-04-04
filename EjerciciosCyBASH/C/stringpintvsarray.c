#include<stdio.h>

int main(){
    char s1[]= "abcdef";  //array de caracteres
    printf("string:%s",s1); // funciona
    s1[0]='X';  //funciona
    //*(s1+0) = 'X'    --> vector[i] == *(vector + i)
    //  vector +1 == &vector[i]

    char *s2 = "abcdef";  //puntero a array de caracteres(primer elemento)
    printf("string:%s",s2); //funciona
    //s2[0]= 'x';  //NO funciona (debido a que no sabemos donde estan los elementos)

    s2++; //puedes pasar asi al siguiente puntero
    printf("string:%s",s2); //haria bcdef, ya que ahora apunta al segundo

    sizeof(s1); //sera 7 ya que sabemos que el arrray consta de 7 bits char
    sizeof(s2); //sera 8 ya que es lo que ocupa un puntero a un char
    
    return 0;
}