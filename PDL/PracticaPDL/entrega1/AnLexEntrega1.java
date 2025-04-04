package PracticaPDL.entrega1;

import java.io.*;
import java.util.*;


public class AnLexEntrega1 {

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
            e.printStackTrace();
        }
    }

    // Tokenization method
    public static List<Token> generateTokens(String data, List<TS> tablaGlobal, BufferedWriter errorWriter) {
        List<Token> tokens = new ArrayList<>();
        boolean zonaDecl = false;
        boolean isComment = false;
        StringBuilder currStr = new StringBuilder();
        String lastWord = null;
        int linea = 1;

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

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
                    break;
                case ')':
                    tokens.add(new Token("parCerrar", " ", ""));
                    break;
                case '{':
                    tokens.add(new Token("llaveAbrir", " ", ""));
                    break;
                case '}':
                    tokens.add(new Token("llaveCerrar", " ", ""));
                    zonaDecl = false;
                    break;
                case '<':
                    tokens.add(new Token("opRelMenor", " ", ""));
                    break;
                case '|':
                    if (i + 1 < data.length() && data.charAt(i + 1) == '|') {
                        tokens.add(new Token("opLogO", " ", ""));
                        i++;
                    } else {
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

            if (Character.isLetter(ch)) {
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
                    Optional<TS> tsOptional = tablaGlobal.stream().filter(ts -> ts.name.equals(word)).findFirst();
                    if (!tsOptional.isPresent()) {
                        TS nuevoSimbolo = new TS(word, "", 0, "", "", 0, tablaGlobal.size());
                        tablaGlobal.add(nuevoSimbolo);
                        tokens.add(new Token("id", "", tablaGlobal.indexOf(nuevoSimbolo)));
                    } else {
                        tokens.add(new Token("id", "", tablaGlobal.indexOf(tsOptional.get())));
                    }
                }
            }
            else if (Character.isDigit(ch)) {
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
            manejarError("Léxico", "Comentario abierto y EOF encontrado antes de cerrar comentario", linea, errorWriter);
        } else {
            tokens.add(new Token("EOF", " ", ""));
        }

        return tokens;
    }


    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/fallo3.txt");
        File tokenFile = new File("ficherotokens.txt");
        File TSFile = new File("ficheroTS.txt");
        File errorFile = new File("ficheroError.txt");

        List<TS> tablaGlobal = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(tokenFile));
             BufferedWriter TSwriter = new BufferedWriter(new FileWriter(TSFile));
             BufferedReader Tokenreader = new BufferedReader(new FileReader(tokenFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile))) {

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            List<Token> tokens = generateTokens(data.toString(), tablaGlobal, errorWriter);

            for (Token token : tokens) {
                tokenWriter.write(token.toString());
                tokenWriter.newLine();
            }

            TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS GLOBAL:\n");
            for (TS symbol : tablaGlobal) {
                TSwriter.write(symbol.toString());
                TSwriter.write("--------- ----------\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}