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
                case ':':
                    tokens.add(new Token("dospuntos", " ", ""));
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


    public static class Parser {
        private  List<Token> Tokens;
        private int line;
        private  int CurrentToken = 0;
        private  String result = "Descendente ";
        BufferedWriter parseWriter;

        
        public String parse(BufferedWriter parseWriter, List<Token> tokens) throws IOException {
            // Leer tokens desde el TokenReader y almacenar en la lista Tokens
            line = 0;
            Tokens  = tokens;
            CurrentToken = 0;
            result = "Descendiente ";


            P();
            return result;
        }
            // Procedimiento principal
            private void P() {
                String token = getCurrentToken();
                if (token.equals("EOF")) {
                    result += " 3 ";  // Finalización exitosa
                    System.out.println("EXITO");
                    //System.out.println(result);

                } else if (token.equals("wordFunction")) { //Conjunto first de F
                    result += " 1 ";
                    F();
                    P();
                } else if (token.equals("id") || token.equals("wordIf") || token.equals("wordInput") || token.equals("wordOutput")
                        || token.equals("wordReturn") || token.equals("wordSwitch") || token.equals("wordVar")) {
                    result += " 2 ";
                    B();
                    P();
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in P");
                }
            }
        
            // Procedimiento para funciones
            private void F() {
                result += " 4 ";
                equipara("wordFunction");
                H();  // Tipo de retorno de la función
                equipara("id");
                equipara("parAbrir");
                A();  // Argumentos de la función
                equipara("parCerrar");
                equipara("llaveAbrir");
                C();  // Cuerpo de la función
                equipara("llaveCerrar");
            }
        
            // Procedimiento para el tipo de retorno de función
            private void H() {
                String token = getCurrentToken();
                if (token.equals("wordInt") || token.equals("wordBoolean") || token.equals("wordString")) {
                    result += " 5 ";
                    T();  // Tipo de dato
                } else if (token.equals("wordVoid")) {
                    result += " 6 ";
                    equipara("wordVoid");                    
                }  else {
                    ErrorSintaxis("Recibio " + token  + " -> Expected type in H");
                }
            }
        
            // Procedimiento para argumentos
            private void A() {
                String token = getCurrentToken();
                if (token.equals("wordInt") || token.equals("wordBoolean") || token.equals("wordString")) {
                    result += " 7 ";
                    T();
                    equipara("id");
                    K();
                } else if (token.equals("wordVoid")) {
                    result += " 8 ";
                    equipara("wordVoid");
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Expected argument type or void in A");
                }
            }
        
            // Procedimiento para el manejo de múltiples argumentos
            private void K() {
                String token = getCurrentToken();
                if (token.equals("coma")) {
                    result += " 9 ";
                    equipara("coma");
                    T();
                    equipara("id");
                    K();
                } else if(token.equals("parCerrar")){
                    result += " 10 ";
                }else {
                    ErrorSintaxis("Recibio " + token  + " -> expected token in K ");
                }
            }
        
            // Procedimiento para el cuerpo de la función
            private void C() {
                String token = getCurrentToken();
                if (token.equals("wordIf") || token.equals("wordVar") || token.equals("wordSwitch") || 
                token.equals("id") || token.equals("wordOutput") || token.equals("wordInput") || token.equals("wordReturn")) {
                    result += " 11 ";
                    B();
                    C();
                } else if(token.equals("wordBreak") || token.equals("wordCase") || token.equals("default") ||token.equals("llaveCerrar")){
                    result += " 12 ";
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in C ");
                }
            }
        
            // Procedimiento para declaraciones y sentencias (B)
            private void B() {
                String token = getCurrentToken();
                if (token.equals("wordIf")){
                    result += " 13 ";
                    equipara("wordIf");
                    equipara("parAbrir");
                    E();
                    equipara("parCerrar");
                    S();
                } else if (token.equals("wordVar")){
                    result += " 14 ";
                    equipara("wordVar");
                    T();
                    equipara("id");
                    equipara("puntocoma");
                } else if ( token.equals("id") || token.equals("wordOutput") || token.equals("wordInput") || token.equals("wordReturn")){
                    result += " 15 ";
                    S();
                } else if(token.equals("wordSwitch")){
                    result += " 16 ";
                    equipara("wordSwitch");
                    equipara("parAbrir");
                    E();  // Argumentos de la función
                    equipara("parCerrar");
                    equipara("llaveAbrir");
                    BB();  // Cuerpo de la función
                    equipara("llaveCerrar");
                } else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in B");

                }
            }
        
            // Procedimiento para las declaraciones internas de switch (BB)
            private void BB() {
                String token = getCurrentToken();
                if (token.equals("wordCase")) {
                    result += " 20 ";
                    equipara("wordCase");
                    equipara("entero");
                    equipara("dospuntos");
                    C();
                    D();
                    BB();
                }else if(token.equals("llaveCerrar")){
                    result += " 21 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in BB ");

                } 
            }
        
            // Procedimiento para declaración break o vacío (D)
            private void D() {
                String token = getCurrentToken();
                if (token.equals("wordBreak")) {
                    result += " 22 ";
                    equipara("wordBreak");
                    equipara("puntocoma");
                } else if(token.equals("wordCase") || token.equals("llaveCerrar")) {
                    result += " 23 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in D ");
                }
            }
        
            // Procedimiento para sentencias (S)
            private void S() {
                String token = getCurrentToken();
                if (token.equals("id")) {
                    result += " 24 ";
                    equipara("id");
                    S1();
                    equipara("puntocoma"); 
                } else if (token.equals("wordOutput")) {
                    result += " 32 ";
                    equipara("wordOutput");
                    E();
                    equipara("puntocoma");
                } else if (token.equals("wordInput")) {
                    result += " 33 ";                
                    equipara("wordInput");
                    equipara("id");
                    equipara("puntocoma");
                } else if (token.equals("wordReturn")) {
                    result += " 34 ";
                    equipara("wordReturn");
                    X();
                    equipara("puntocoma");
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in S");
                }
            }
        
            // Continuación de sentencias (S1)
            private void S1() {
                String token = getCurrentToken();
                if (token.equals("asig")) {
                    result += " 25 ";
                    equipara("asig");
                    E();
                } else if (token.equals("divAsig")) {
                    result += " 26 ";                    
                    equipara("divAsig");
                    E();
                } else if (token.equals("parAbrir")) {
                    result += " 27 ";                    
                    equipara("parAbrir");
                    L();
                    equipara("parCerrar");
                }
            }
        
            // Expresión de retorno
            private void X() {
                String token = getCurrentToken();
                if ( token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                    result += " 35 ";                    
                    E();
                } else if(token.equals("puntocoma")) {
                    result += " 36 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in X");
                }
            }
        
            // Expresión principal (E)
            private void E() {
                result += " 37 ";                
                R();
                EE();
            }
        
            // Complemento de expresión (EE)
            private void EE() {
                String token = getCurrentToken();
                if (token.equals("opLogO")) {
                    result += " 38 ";                    
                    equipara("opLogO");
                    R();
                    EE();
                } else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma")) {
                    result += " 39 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in EE ");
                }
            }
        
            private void R() {
                result += " 40 ";                
                U();
                RR();
            }
        
            // Complemento de relación (RR)
            private void RR() {
                String token = getCurrentToken();

                if (token.equals("opRelMenor")) {
                    result += " 41 ";                    
                    equipara("opRelMenor");
                    U();
                    RR();
                }else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO")) {
                    result += " 42 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in RR " );
                }
            }

            private void L() {
                String token = getCurrentToken();
                if (token.equals("id") || token.equals("entero") || token.equals("cadena")) {
                    result += " 28 ";
                    E();  // Expresión
                    Q();  // Posibles parámetros adicionales
                } else if(token.equals("parCerrar")) {
                    result += " 29 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in L ");
                }
            }

            private void Q() {
                String token = getCurrentToken();
                if (token.equals("coma")) {
                    result += " 30 ";
                    equipara("coma");
                    E();  // Expresión
                    Q();  // Posibles parámetros adicionales
                } else if(token.equals("parCerrar")) {
                    result += " 31 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in Q");
                }
            }

            private void U() {
                result += " 43 ";
                V();  // Parte izquierda de la operación
                UU();  // Parte derecha (suma opcional)
            }

            private void UU() {
                String token = getCurrentToken();
                if (token.equals("opArSuma")) {
                    result += " 44 ";
                    equipara("opArSuma");
                    V();  // Expresión después del operador
                    UU();  // Posibles operadores adicionales
                }else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                    result += " 45 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in UU");
                }
            }

            private void V() {
                result += " 46 ";
                W();  // Parte izquierda de la operación
                VV();  // Parte derecha (división opcional)
            }
            
            private void VV() {
                String token = getCurrentToken();

                if (token.equals("opArDiv")) {
                    result += " 47 ";
                    equipara("opArDiv");
                    W();  // Expresión después del operador
                    VV();  // Posibles operadores adicionales
                }else if(token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                    result += " 48 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in VV");
                }
            }

            private void W() {
                String token = getCurrentToken();
                if (token.equals("id")) {
                    result += " 49 ";
                    equipara("id");
                    W1();  // Posibles llamadas de función o lambda
                } else if (token.equals("parAbrir")) {
                    result += " 50 ";
                    equipara("parAbrir");
                    E();  // Expresión dentro de los paréntesis
                    equipara("parCerrar");
                } else if (token.equals("entero")) {
                    result += " 51 ";
                    equipara("entero");
                } else if (token.equals("cadena")) {
                    result += " 52 ";
                    equipara("cadena");
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in W");
                }
            }

            private void W1() {
                String token = getCurrentToken();

                if (token.equals("parAbrir")) {
                    result += " 53 ";
                    equipara("parAbrir");
                    L();  // Lista de argumentos
                    equipara("parCerrar");
                } else if(token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor") || token.equals("opArDiv")) {
                    result += " 54 ";
                }else{
                    ErrorSintaxis("Recibio " + token  + " -> Unexpected token in W1 ");
                }
            }

            private void T() {
                String token = getCurrentToken();

                if (token.equals("wordInt")) {
                    result += " 18 ";                    
                    equipara(token);
                } else if(token.equals("wordBoolean")){
                    result += " 17 ";
                    equipara(token);
                } else if(token.equals("wordString")){
                    result += " 19 ";
                    equipara(token);
                } else {
                    ErrorSintaxis("Recibio " + token  + " -> Expected type in T");
                }
            }

            // Procedimientos auxiliares y manejo de tokens
            private void nextToken() {
                if (CurrentToken < Tokens.size() - 1) {
                    CurrentToken++;
                }
            }
        
            private String getCurrentToken() {
                if (CurrentToken < Tokens.size()) {
                    return Tokens.get(CurrentToken).elem; // Devuelve el tipo del token
                }
                return "EOF";
            }
        
            private void equipara(String expected) {
                String token = getCurrentToken(); // Usar el token actual solo una vez
                if (token.equals(expected)) {
                    nextToken();
                } else {
                    ErrorSintaxis("Expected " + expected + " but found " + token);
                }
            }
        
            private void ErrorSintaxis(String message) {
                System.err.println("Syntax Error at line: " +  line + " -> " +  message );
                System.err.println(result);
                System.exit(1);
            }
        }
        



    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/miDraco5.txt");
        File tokenFile = new File("ficherotokens.txt");
        File TSFile = new File("ficheroTS.txt");
        File errorFile = new File("ficheroError.txt");
        File parse = new File("parse.txt");

        List<TS> tablaGlobal = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(tokenFile));
             BufferedWriter TSwriter = new BufferedWriter(new FileWriter(TSFile));
             BufferedReader Tokenreader = new BufferedReader(new FileReader(tokenFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));
             BufferedWriter parseWriter = new BufferedWriter(new FileWriter(parse))) {

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
            
            String result;
            Parser parser = new Parser();
            result=parser.parse(parseWriter ,tokens); //Añadir despues fichero para que escriba, escriba parse res en fichero
            parseWriter.write(result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}