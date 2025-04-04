package PracticaPDL.entrega3;

import java.io.*;
import java.util.*;

public class lastAnLex {

    // Clase tokens
    static class Token {
        String elem;
        String cod;
        Object atrib;

        Token(String elem, String cod, Object atrib) {
            this.elem = elem;
            this.cod = cod;
            this.atrib = atrib;
        }

        @Override
        public String toString() {
            return "<" + elem + ", " + atrib + ">";
        }
    }

    // Clase TS (Tabla de Símbolos)
    static class TS {
        String name;
        String type;
        int numParams;
        String paramTypes;
        String returnType;
        int despl;
        int pos;

        TS(String name, String type, int numParams, String paramTypes, String returnType, int despl, int pos) {
            this.name = name;
            this.type = type;
            this.numParams = numParams;
            this.paramTypes = paramTypes;
            this.returnType = returnType;
            this.despl = despl;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "* LEXEMA : '" + name + "'\n";
                    //" Atributos :\n" +
                    //" + tipo : " + type + "\n" +
                    //" + despl : " + despl + "\n" +
                    //" + pos : " + pos + "\n" +
                    //" + numParams : " + numParams + "\n" +
                    //" + paramTypes : " + paramTypes + "\n" +
                    //" + returnType : " + returnType + "\n";
        }
    }

    // Reserved Words Map
    private static final Map<String, String> reservadas = new HashMap<>();

    static {
        reservadas.put("function", "wordFunction");
        reservadas.put("if", "wordIf");
        reservadas.put("input", "wordInput");
        reservadas.put("output", "wordOutput");
        reservadas.put("switch", "wordSwitch");
        reservadas.put("case", "wordCase");
        reservadas.put("break", "wordBreak");
        reservadas.put("var", "wordVar");
        reservadas.put("void", "wordVoid");
        reservadas.put("int", "wordInt");
        reservadas.put("string", "wordString");
        reservadas.put("print", "wordPrint");
        reservadas.put("return", "wordReturn");
        reservadas.put("boolean", "wordBoolean");
    }
    static void manejarError(String tipo, String mensaje, int linea, BufferedWriter errorWriter) {
        String errorMsg = "Error en linea " + linea + " del tipo (" + tipo + "): " + mensaje;
        try {
            errorWriter.write(errorMsg);
            errorWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // Por si falla la escritura
        }
    }
    // Tokenization method
    public static List<Token> generateTokens(String data, List<List<TS>> tablas, int[] numTabla, BufferedWriter errorWriter) {
        List<Token> tokens = new ArrayList<>();
        boolean zonaDecl = false;
        boolean inFuncion = false;  // Para saber si estamos dentro de una función
        int currTable = 1; // Empezamos en la tabla global
        boolean isComment = false;
        StringBuilder currStr = new StringBuilder();
        String lastWord = null;
        int linea = 1;

    
        // Variables para manejar parámetros temporales de una función
        List<TS> parametrosTemporales = new ArrayList<>();  // Lista temporal de parámetros
        boolean capturaParametros = false;  // Indica si estamos capturando parámetros
    
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
    
            // Manejo de comentarios
            if (isComment) {
                if (ch == '*' && i + 1 < data.length() && data.charAt(i + 1) == '/') {
                    isComment = false;
                    i++;
                }
                continue;
            }
    
            switch (ch) {
                case '=':
                    tokens.add(new Token("asig", " ", ""));
                    break;
                case '/':
                    if (i + 1 < data.length() && data.charAt(i + 1) == '*') {
                        isComment = true;
                        i++;
                    } else if (i + 1 < data.length() && data.charAt(i + 1) == '=') {
                        tokens.add(new Token("divAsig", " ", ""));
                        i++;
                    } else {
                        tokens.add(new Token("opArDiv", " ", ""));
                    }
                    break;
                case '+':
                    tokens.add(new Token("opArSuma", " ", ""));
                    break;
                case ',':
                    tokens.add(new Token("coma", " ", ""));
                    break;
                case ';':
                    tokens.add(new Token("puntocoma", " ", ""));
                    break;
                case '(':
                    tokens.add(new Token("parAbrir", " ", ""));
                    if (zonaDecl) {
                        capturaParametros = true;  // Comenzamos a capturar parámetros
                    }
                    break;
                case ')':
                    tokens.add(new Token("parCerrar", " ", ""));
                    capturaParametros = false;  // Terminamos de capturar parámetros
                    break;
                    case '{':
                    if (zonaDecl) {
                        // Nueva tabla para la función
                        numTabla[0]++;
                        currTable = numTabla[0];  // Cambiar a la tabla de la función actual
                        tablas.add(new ArrayList<>()); // Añadir nueva tabla local para la función
                        inFuncion = true;  // Estamos dentro de una función
                
                        // Añadir los parámetros temporales capturados a la nueva tabla local
                        List<TS> localTable = tablas.get(currTable - 1);
                        for (TS param : parametrosTemporales) {
                            localTable.add(param); // Agregar el parámetro a la tabla local
                            tokens.add(new Token("id", "", localTable.indexOf(param))); // Generar token con índice correcto en la tabla local
                        }
                        parametrosTemporales.clear();  // Limpiar lista temporal de parámetros después de añadirlos
                    }
                    tokens.add(new Token("llaveAbrir", " ", ""));
                    break;
                case '}':
                    tokens.add(new Token("llaveCerrar", " ", ""));
                    zonaDecl = false;
                    currTable = 1;  // Volver a la tabla global
                    inFuncion = false;  // Salimos de la función
                    break;
                case ':':
                    tokens.add(new Token("dospuntos", " ", ""));
                    break;
                case '\n':
                    linea++;
                    break;
                case '<':
                    tokens.add(new Token("opRelMenor", " ", ""));
                    break;
                case '|':
                    if (i + 1 < data.length() && data.charAt(i + 1) == '|') {
                        tokens.add(new Token("opLogO", " ", ""));
                        i++;
                    } else {
                        //tokens.add(new Token("ERR", " ", "No existe transición con solo un carácter " + ch));
                        manejarError("Léxico", "No existe transición con un único carácter '|'", linea, errorWriter);

                    }
                    break;
                default:
                if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != ' ' && ch != '\t' && ch != '\n') {
                    manejarError("Léxico", "Carácter '" + ch + "' no definido en el lenguaje", linea, errorWriter);
                }
            }
            if( ch == '_'){ //No contar IDs que empiecen por _
                manejarError("Léxico", "ID no puede empezar por el carácter guion bajo", linea, errorWriter);
                while (Character.isLetterOrDigit(ch) || ch == '_'){
                    if (++i >= data.length()) break;
                }
            }
            if (Character.isLetter(ch)) { // ID empieza por letra
                currStr = new StringBuilder();
                while (Character.isLetterOrDigit(ch) || ch == '_') {
                    currStr.append(ch);
                    if (++i >= data.length()) break;
                    ch = data.charAt(i);
                }
                i--;

                String word = currStr.toString();
                if (reservadas.containsKey(word)) {
                    tokens.add(new Token(reservadas.get(word), " ", ""));
                    if (word.equals("function")) zonaDecl = true;
                    lastWord = word;
                } else {
                    List<TS> currentTable = tablas.get(currTable - 1);  // Tabla actual
                    List<TS> globalTable = tablas.get(0);  // Tabla global (Tabla 1)

                    // Buscar primero en la tabla local y luego en la global si no está en la local
                    Optional<TS> tsOptionalLocal = currentTable.stream().filter(ts -> ts.name.equals(word)).findFirst();
                    Optional<TS> tsOptionalGlobal = globalTable.stream().filter(ts -> ts.name.equals(word)).findFirst();

                    if (!tsOptionalLocal.isPresent()) {
                        if (tsOptionalGlobal.isPresent() && currTable > 1) {
                            // Si está en la tabla global y estamos en una función, usar la global
                            tokens.add(new Token("id", "", globalTable.indexOf(tsOptionalGlobal.get())));
                        } else {
                            TS nuevoSimbolo = new TS(word, "", 0, "", "", 0, currTable == 1 ? globalTable.size() : currentTable.size());

                            if (capturaParametros) {
                                // Si estamos capturando parámetros, añadirlos temporalmente
                                parametrosTemporales.add(nuevoSimbolo);
                                tokens.add(new Token("id", "", currentTable.indexOf(nuevoSimbolo))); //DA PROBLEMASSSSS MIRAR 2 ENTREGA

                            } else {
                                // Añadir a la tabla actual (función o global)
                                currentTable.add(nuevoSimbolo);
                                tokens.add(new Token("id", "", currentTable.indexOf(nuevoSimbolo)));
                            }
                        }
                    } else {
                        // Ya existe en la tabla local
                        tokens.add(new Token("id", "", currentTable.indexOf(tsOptionalLocal.get())));
                    }
                }
            } else if (Character.isDigit(ch)) {
                int currentInt = ch - '0';
                while (i + 1 < data.length() && Character.isDigit(data.charAt(i + 1))) {
                    ch = data.charAt(++i);
                    currentInt = currentInt * 10 + (ch - '0');
                }
                if(currentInt<32767){
                    tokens.add(new Token("entero", "", currentInt));
                }else{
                    manejarError("Léxico", "Entero fuera de rango", linea, errorWriter);
                }
            }

            if (ch == '\'') {
                currStr = new StringBuilder();
                currStr.append('"');
                int cont = 0;
            
                while (i + 1 < data.length() && data.charAt(i + 1) != '\'') {
                    i++; // Aumentamos i antes de acceder a data.charAt(i)
                    currStr.append(data.charAt(i));
                    cont++;
                }
                if (i + 1 < data.length()) {
                    i++;
                    currStr.append('"'); // Cerramos la cadena con comillas dobles
                    if (cont > 64) {
                        manejarError("Léxico", "Cadena fuera de rango", linea, errorWriter);
                    } else {
                        tokens.add(new Token("cadena", "", currStr.toString()));
                    }
                } else {
                    manejarError("Léxico", "Cadena abierta sin cerrar antes de EOF", linea, errorWriter);
                }
                
            }
        }
            
        if (isComment) {
            //tokens.add(new Token("ERR", " ", "Comentario abierto y no acabado antes de EOF"));
            manejarError("Léxico", "Comentario abierto y EOF encontrado antes de cerrar comentario", linea, errorWriter);

        } else {
            tokens.add(new Token("EOF", " ", ""));
        }

        return tokens;
    }

    /* Main programa crear fichero tokens */
    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/pruebasSemantico.txt");
        File tokenFile = new File("ficherotokens.txt");
        File TSFile = new File("ficheroTS.txt");
        File errorFile = new File("ficheroError.txt");//Guardar errores en vez de system.err.print

        List<List<TS>> tablas = new ArrayList<>();//Lista de todas las tablas de simbolos, dentro lista de todos los id de las tablas
        tablas.add(new ArrayList<>()); // Crear tabla global inicialmente (Tabla 1)

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(tokenFile));
             BufferedWriter TSwriter = new BufferedWriter(new FileWriter(TSFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile)))
             {

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            int[] numTabla = {1};
            List<Token> tokens = generateTokens(data.toString(), tablas, numTabla, errorWriter);

            for (Token token : tokens) {
                tokenWriter.write(token.toString());
                tokenWriter.newLine();
            }
            
            // Falta añadir los parámetros de la función como variables de la nueva tabla y no de la global
            TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS # 100:\n");
            for (int i = 0; i < tablas.size(); i++) {
                //TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS # " + (i + 1) + ":\n");
                for (TS symbol : tablas.get(i)) {
                    TSwriter.write(symbol.toString());
                    TSwriter.write("--------- ----------\n");
                }
            }
            //System.out.println("Tokens y tabla de símbolos procesados y escritos en los archivos: " + tokenFile.getName() + ", " + TSFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





