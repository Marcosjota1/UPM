package PracticaPDL.pruebasLexic;


import java.io.*;
import java.util.*;


public class tokensFuncional {
        // Token Class
        static class Token {
            String elem;
            String cod;
            String atrib;
    
            Token(String elem, String cod, String atrib) {
                this.elem = elem;
                this.cod = cod;
                this.atrib = atrib;
            }
    
            @Override
            public String toString() {
                return "<" + elem + ", " + atrib + ">";
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
    
        // Tokenization method
        public static List<Token> generateTokens(String data) {
            List<Token> tokens = new ArrayList<>();
    
            boolean isComment = false;
            StringBuilder currStr = new StringBuilder();
    
            for (int i = 0; i < data.length(); i++) {
                char ch = data.charAt(i);
    
                /*No SE QUE HACER SALTOS DE LINEA, incoprporar luego
                if (ch == '\n') {
                    tokens.add(new Token("ERR", " ", "salto linea"));
                }*/
    
                // Look for end of comment
                if (isComment) {
                    if (ch == '*' && i + 1 < data.length() && data.charAt(i + 1) == '/') {
                        isComment = false;
                        i++; // Skip '/'
                    }
                    continue;
                }
    
                // Handle characters and operators
                switch (ch) {
                    case '=':
                        tokens.add(new Token("asig", " ", ""));
                        break;
                    case '/':
                        if (i + 1 < data.length() && data.charAt(i + 1) == '*') {
                            isComment = true;
                            i++;
                        }else if (i + 1 < data.length() && data.charAt(i + 1) == '='){
                            tokens.add(new Token("divAsig", " ", ""));
                        }else{
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
                        tokens.add(new Token("corAbrir", " ", ""));
                        break;
                    case '}':
                        tokens.add(new Token("corCerrar", " ", ""));
                        break;
                    case ':':
                        tokens.add(new Token("dospuntos", " ", ""));
                        break;
                    case '<':
                        tokens.add(new Token("opRelMenor", " ", ""));
                        break;
                    case '|':
                        if (i + 1 < data.length() && data.charAt(i + 1) == '|') {
                            tokens.add(new Token("opLogO", " ", ""));
                            i++;
                        } else {
                            tokens.add(new Token("ERR", " ", "No existe transicion con solo un caracter"+ String.valueOf(ch)));
                        }
                        break;
                    default:
                        if(ch == '\''|| Character.isDigit(ch) ||Character.isLetter(ch) || ch == '_' || ch == '\n' || ch == ' ' || ch == '\t'){ //Tratas despues
                            break; 
                        }else{
                            tokens.add(new Token("ERR", " ", "Caracter -> " + String.valueOf(ch) + " no definido" ));
                        }
                }
    
                // Handle strings,
                if (ch == '\'') { //Empieza en comillas
                    currStr = new StringBuilder();
                    while (i + 1 < data.length() && data.charAt(i + 1) != '\'') { //Mientras no encuentres las comillas
                        currStr.append(data.charAt(++i));
                    }
                    tokens.add(new Token("string", "", currStr.toString()));
                    i++; //Puede dar errores mirar luego
                }
    
                // Handle numbers (digits)
                if (Character.isDigit(ch)) {
                    currStr = new StringBuilder();
                    while (Character.isDigit(ch)) {
                        currStr.append(ch);
                        if (++i >= data.length()) break;
                        ch = data.charAt(i);
                    }
                    tokens.add(new Token("entero", "", currStr.toString()));
                    i--; // Adjust for next loop
                }
    
                // Handle identifiers and reserved words
                if (Character.isLetter(ch) || ch == '_') {
                    currStr = new StringBuilder();
                    while (Character.isLetterOrDigit(ch) || ch == '_') {
                        currStr.append(ch);
                        if (++i >= data.length()) break;
                        ch = data.charAt(i);
                    }
                    i--; // Adjust for next loop
    
                    String word = currStr.toString();
                    if (reservadas.containsKey(word)) {
                        tokens.add(new Token(reservadas.get(word), " ",""));
                    } else {
                        tokens.add(new Token("id", "", word)); //EL atributo va a ser la posicion tabla simbolos
                        //                                                modificar cuando tabla de simbolos implementada
                    }
                }
            }
    
            tokens.add(new Token("EOF", " ", ""));
            return tokens;
        }
    
    
    
    
        /*Main programa crear fichero tokens */
        public static void main(String[] args) {
            // File paths
            File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/input.txt");
            File outputFile = new File("output.txt"); // Creas fichero tokens
            try {
                if (outputFile.createNewFile())
                    System.out.println("Archivo creado correctamente");
                else
                    System.out.println("Archivo no creado correctamente");
    
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
    
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
    
                StringBuilder data = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line).append("\n");
                }
    
                // Generate tokens from input data
                List<Token> tokens = generateTokens(data.toString());
    
                // Write tokens to output file
                for (Token token : tokens) {
                    writer.write(token.toString());
                    writer.newLine();
                }
    
                System.out.println("Tokens procesados y escritos en el archivo: " + outputFile);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
