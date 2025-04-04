package PracticaPDL.pruebasLexic;

import java.io.*;
public class ficheros {
    File file;
private void crear(){
    file = new File("file.txt");
    try {
        if(file.createNewFile()){
            System.out.println("El fichero se ha creado correctamente.");
        }else{
            System.out.println("El fichero no se ha creado correctamente.");
        }
    } catch (IOException e) {
        e.printStackTrace(System.out);
    }
    }

    private void eliminar(){
            if(file.delete()){
                System.out.println("El fichero se ha eliminado correctamente.");
            }else{
                System.out.println("El fichero no se ha eliminado correctamente.");
            }
        }

public static void main(String[] args) {
    ficheros f = new ficheros();
    f.crear();
    f.eliminar();
}



















}