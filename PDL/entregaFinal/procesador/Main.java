package entregaFinal.procesador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entregaFinal.procesador.lexic.*;
import entregaFinal.procesador.sintactSemantic.*;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/ej3.txt");
        //File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL\\ejemplosDracoYpruebasG13/DracoSem13.txt");
        //File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL\\entregaFinal\\ejemplosFinales/ejemplo1.txt");
        File tokenFileFinal = new File("ficherotokensFinal.txt");        
        File TSFileFinal = new File("ficheroTSFinal.txt");
        File parse = new File("parse.txt");
        File errorFile = new File("ficheroError.txt");


        List<List<TS>> tablaGlobal = new ArrayList<>();
        tablaGlobal.add(new ArrayList<>()); // Crear tabla global inicialmente (Tabla 1)


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriterFinal = new BufferedWriter(new FileWriter(tokenFileFinal));
             BufferedWriter TSwriterFinal = new BufferedWriter(new FileWriter(TSFileFinal));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));
             BufferedWriter parseWriter = new BufferedWriter(new FileWriter(parse))) {

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            //El analizador lexico genera los tokens e introduce ID en la tabla de simbolos
            List<Token> tokens = lexic.generateTokens(data.toString(), tablaGlobal, errorWriter);

            //El analizador sintactico genera el parse y aplica las acciones semanticas mediante la Traduccion dirigida por la sintaxis
            String result;
            Parser parser = new Parser();
            result=parser.parse(tokens,tablaGlobal);
            //Imprimes parse en parse.txt
            parseWriter.write(result);
            
            //Imprimes Tabla de simbolos en tablaSimbolosFinal.txt
            for (int i = 0; i < tablaGlobal.size(); i++) {
                TSwriterFinal.write("CONTENIDO DE LA TABLA DE SIMBOLOS #" + (i + 1) + ":\n");
                for (TS symbol : tablaGlobal.get(i)) {
                    if(symbol.tipo == "") continue; //Variables que estan en local(solo tendran tipo en local) al encontrar en global saltamos("Borramos de la global generada del lexico")
                    else{
                        if(symbol.tipo == "function"){
                            TSwriterFinal.write(symbol.toString());
                            TSwriterFinal.write("--------- ----------\n");
                        }else{
                            TSwriterFinal.write(symbol.toString2());
                            TSwriterFinal.write("--------- ----------\n");
                        }
                    }                   
                }
            }  
            
            //Imprimes tokens, sin saltos de linea usados para localizar en que linea ocurre el error
            for (Token token : tokens) {
                if (!token.elem.equals("SALTO_LINEA")) { 
                    tokenWriterFinal.write(token.toString()); // Escribe el token si no es "SALTO_LINEA"
                    tokenWriterFinal.newLine(); // Agrega una nueva línea para separar los tokens
                }
            }            
        }
             catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    

