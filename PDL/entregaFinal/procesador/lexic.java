package entregaFinal.procesador;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class lexic {
    // Clase tokens
    static class Token {
        String elem;
        String atrib;

        Token(String elem, String atrib) {
            this.elem = elem;
            this.atrib = atrib;
        }

        @Override
        public String toString() {
            return "<" + elem + ", " + atrib + ">";
        }
    }

    // Clase TS (Tabla de Símbolos)
    static class TS {
        String lexema;
        String tipo;
        int numParams;
        List<String> paramTypes;
        String returnType;
        String etiq;
        List<String> modoParam;
        int despl;
        int pos;

        TS(String lexema, String tipo, int numParams, List<String> paramTypes, String returnType, int despl, String etiq, List<String> modoParam, int pos) {
            this.lexema = lexema;
            this.tipo = tipo;
            this.despl = despl;
            this.numParams = numParams;
            this.paramTypes = paramTypes;
            this.returnType = returnType;
            this.etiq = etiq;
            this.pos = pos;
            //this.modoParam = modoParam;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("* LEXEMA: '").append(lexema).append("'\n");
            sb.append("Atributos:\n");
            sb.append(" + Tipo: '").append(tipo).append("'\n");
            sb.append(" + NumParam: ").append(numParams).append("\n");
            //sb.append(" + Posicion: ").append(pos).append("\n");

            for (int i = 0; i < numParams; i++) {
                sb.append(" + TipoParam").append(String.format("%02d", i + 1)).append(": '").append(paramTypes.get(i)).append("'\n");
                //sb.append(" + ModoParam").append(String.format("%02d", i + 1)).append(": '1'\n"); //No necesario un unico tipo de pase parametros
            }

            sb.append(" + TipoRetorno: '").append(returnType).append("'\n");
            sb.append(" + EtiqFuncion: '").append(etiq).append("'\n");

            return sb.toString();
        }
        public String toString2(){
            return "* LEXEMA : '" + lexema + "'\n" +
            " Atributos :\n" +
                    " + Tipo : '" + tipo + "'\n" +
                    " + Despl : " + despl + "\n" ;
                   // " + pos : " + pos + "\n";
        }
        
        public Object getLexema() {
            return lexema;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Object getTipo() {
            return tipo;
            }        
    }

    // Palabras reservadas
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
        reservadas.put("return", "wordReturn");
        reservadas.put("boolean", "wordBoolean");
    }

    // Manejo de errores léxicos
    static void errorLexico(String tipo, String mensaje, int linea, BufferedWriter errorWriter) {
        System.err.println("Error lexico en la linea: " +  linea + " -> " +  mensaje );
        System.exit(1);
    }

    public static List<Token> generateTokens(String data, List<List<TS>> tablaGlobal, BufferedWriter errorWriter) throws IOException {
        List<Token> tokens = new ArrayList<>();
        boolean isComment = false;
        StringBuilder currStr = new StringBuilder();
        int linea = 1;
        int currtable = 1;

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            //Manejo de la linea que contiene el error
            if(ch == '\n'){
                tokens.add(new Token("SALTO_LINEA", ""));
                linea++;
                continue;
            }

            //Pasas los caracteres hasta fin de comentario
            if (isComment) {
                if (ch == '*' && i + 1 < data.length() && data.charAt(i + 1) == '/') {
                    isComment = false;
                    i++;
                }
                continue;
            }

            switch (ch) {
                case '=':
                    tokens.add(new Token("asig",  ""));
                    break;
                case '/':
                    if (i + 1 < data.length() && data.charAt(i + 1) == '*') {
                        isComment = true;
                        i++;
                    } else if (i + 1 < data.length() && data.charAt(i + 1) == '=') {
                        tokens.add(new Token("divAsig", ""));
                        i++;
                    } else {
                        tokens.add(new Token("opArDiv", ""));
                    }
                    break;
                case '+':
                    tokens.add(new Token("opArSuma",  ""));
                    break;
                case ':':
                    tokens.add(new Token("dospuntos", ""));
                    break;
                case ',':
                    tokens.add(new Token("coma",  ""));
                    break;
                case ';':
                    tokens.add(new Token("puntocoma",  ""));
                    break;
                case '(':
                    tokens.add(new Token("parAbrir",  ""));
                    break;
                case ')':
                    tokens.add(new Token("parCerrar",  ""));
                    break;
                case '{':
                    tokens.add(new Token("llaveAbrir", ""));
                    break;
                case '}':
                    tokens.add(new Token("llaveCerrar", ""));
                    break;
                case '<':
                    tokens.add(new Token("opRelMenor",  ""));
                    break;
                case '|':
                    if (i + 1 < data.length() && data.charAt(i + 1) == '|') {
                        tokens.add(new Token("opLogO",  ""));
                        i++;
                    } else {
                        errorLexico("Léxico", "No existe transición con un único carácter '|'", linea, errorWriter);
                    }
                    break;
                default:
                //Si es un caracter no perteneciente al lenguaje genera error
                    if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != ' ' && ch != '\'' && ch != '\t' && ch != '\n') {
                        errorLexico("Léxico", "Carácter '" + ch + "' no definido en el lenguaje", linea, errorWriter);
                    }
            }
            if( ch == '_'){ //No contar IDs que empiecen por _
                errorLexico("Léxico", "ID no puede empezar por el carácter guion bajo", linea, errorWriter);
                while (Character.isLetterOrDigit(ch) || ch == '_'){
                    if (++i >= data.length()) break;
                }
            }

            //Cuentas todos los identificadore cuando empiezan por letra
            if (Character.isLetter(ch)) {
                currStr = new StringBuilder();
                while (Character.isLetterOrDigit(ch) || ch == '_') {
                    currStr.append(ch);
                    if (++i >= data.length()) break;
                    ch = data.charAt(i);
                }
                i--;

                String word = currStr.toString();
                //Generas token palabra reservada si pertenece al mapa de palabras reservadas o ID
                if (reservadas.containsKey(word)) {
                    tokens.add(new Token(reservadas.get(word),  ""));
                } else {
                    List<TS> currentTable = tablaGlobal.get(currtable - 1);
                     Optional<TS> tsOptional = currentTable.stream().filter(ts -> ts.lexema.equals(word)).findFirst();
                    if (!tsOptional.isPresent()) {
                        List<String> tiposParametros = new ArrayList<>();
                        TS nuevoSimbolo = new TS(word, "", 0,tiposParametros, "",0, "", tiposParametros, currentTable.size());
                        currentTable.add(nuevoSimbolo);
                        tokens.add(new Token("id",  String.valueOf(currentTable.indexOf(nuevoSimbolo))));
                    } else {
                        tokens.add(new Token("id",  String.valueOf(currentTable.indexOf(tsOptional.get()))));
                    }
                }
            }
            //Generas token de digito
            else if (Character.isDigit(ch)) {
                int currentInt = ch - '0';
                while (i + 1 < data.length() && Character.isDigit(data.charAt(i + 1))) {
                    ch = data.charAt(++i);
                    currentInt = currentInt * 10 + (ch - '0');
                }
                if(currentInt<32767){
                    String CurrentInt= Integer.toString(currentInt);
                    tokens.add(new Token("entero",  CurrentInt));
                }else{
                    errorLexico("Léxico", "Entero fuera de rango", linea, errorWriter);
                }
            }

                //Generas token de cadenas
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
                        errorLexico("Léxico", "Cadena fuera de rango", linea, errorWriter);
                    } else {
                        tokens.add(new Token("cadena", currStr.toString()));
                    }
                } else {
                    errorLexico("Léxico", "Cadena abierta sin cerrar antes de EOF", linea, errorWriter);
                }
                
            }
        }

        //Generas terminacion correcta con EOF o error lexico
        if (isComment) {
            errorLexico("Léxico", "Comentario abierto y EOF encontrado antes de cerrar comentario", linea, errorWriter);
        } else {
            tokens.add(new Token("EOF", ""));
        }

        return tokens;
    }
}
