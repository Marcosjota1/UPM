package PracticaPDL.entrega3;

import java.io.*;
import java.util.*;


public class entregaFinalV1 {

    // Clase tokens
    static class Token {
        String elem;
        String atrib;
        //int atrib2;

        Token(String elem, String atrib){//, int atrib2) {
            this.elem = elem;
            this.atrib = atrib;
            //this.atrib2 = atrib2;
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
        String paramTypes;
        String returnType;
        int despl;
        int pos;

        TS(String lexema, String tipo, int numParams, String paramTypes, String returnType, int despl, int pos) {
            this.lexema = lexema;
            this.tipo = tipo;
            this.numParams = numParams;
            this.paramTypes = paramTypes;
            this.returnType = returnType;
            this.despl = despl;
            this.pos = pos;
        }
        public String toString() {
            return "* LEXEMA : '" + lexema + "'\n" +
            " Atributos :\n" +
                    " + tipo : " + tipo + "\n" +
                    " + despl : " + despl + "\n" +
                    " + pos : " + pos + "\n" +
                    " + numParams : " + numParams + "\n" +
                    " + paramTypes : " + paramTypes + "\n" +
                    " + returnType : " + returnType + "\n";
        }

        public String toString2(){
            return "* LEXEMA : '" + lexema + "'\n" +
            " Atributos :\n" +
                    " + tipo : " + tipo + "\n" +
                    " + despl : " + despl + "\n" +
                    " + pos : " + pos + "\n";
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
        // Reserved Wor
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
        
        /*  if(ch == '\n'){
            tokens.add(new Token("SALTO_LINEA", " ", ""));
            linea++;
            continue;
        } */

        public static List<Token> generateTokens(String data, List<List<TS>> tablaGlobal, int[] numTabla, BufferedWriter errorWriter) {
            List<Token> tokens = new ArrayList<>();
            boolean zonaDecl = false;
            boolean inFuncion = false;  // Para saber si estamos dentro de una función
            int currTable = 1; // Empezamos en la tabla global
            boolean isComment = false;
            StringBuilder currStr = new StringBuilder();
            int linea = 1;
            
            
            // Variables para manejar parámetros temporales de una función
            // Tokenization method
            List<TS> parametrosTemporales = new ArrayList<>();  // Lista temporal de parámetros
            boolean capturaParametros = false;  // Indica si estamos capturando parámetros
        
                                                
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
                        tokens.add(new Token("asig", ""));
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
                        tokens.add(new Token("opArSuma", ""));
                        break;
                    case ':':
                        tokens.add(new Token("dospuntos", ""));
                        break;
                    case ',':
                        tokens.add(new Token("coma", ""));
                        break;
                    case ';':
                        tokens.add(new Token("puntocoma", ""));
                        break;
                    case '(':
                        tokens.add(new Token("parAbrir", ""));
                        if (zonaDecl) {
                            capturaParametros = true; // Comenzamos a capturar parámetros
                        }
                        break;
                    case ')':
                        //tokens.add(new Token("parCerrar", ""));
                        //capturaParametros = false; // Terminamos de capturar parámetros
                        //break;
                        tokens.add(new Token("parCerrar", " "));
                        if (capturaParametros) {
                            capturaParametros = false; // Terminamos de capturar parámetros
                            if (inFuncion) {
                                List<TS> localTable = tablaGlobal.get(currTable - 1);
                                for (TS param : parametrosTemporales) {
                                    if (!localTable.contains(param)) {
                                        param.pos = localTable.size(); // Asignar posición secuencial
                                        localTable.add(param);
                                        tokens.add(new Token("id", String.valueOf(localTable.indexOf(param))));
                                    }
                                }
                            }
                            parametrosTemporales.clear(); // Limpiar parámetros temporales
                        }
                        break;
                    case '{':
                        if (zonaDecl) {
                            // Nueva tabla para la función
                            numTabla[0]++;
                            currTable = numTabla[0]; // Cambiar a la tabla de la función actual
                            tablaGlobal.add(new ArrayList<>()); // Añadir nueva tabla local para la función
                            inFuncion = true; // Estamos dentro de una función
        
                            // Añadir los parámetros temporales capturados a la nueva tabla local
                            List<TS> localTable = tablaGlobal.get(currTable - 1);
                            for (TS param : parametrosTemporales) {
                                localTable.add(param); // Agregar el parámetro a la tabla local
                                //tokens.add(new Token("id", String.valueOf(localTable.indexOf(param)))); // Generar token con índice correcto en la tabla local
                            }
                            parametrosTemporales.clear(); // Limpiar lista temporal de parámetros después de añadirlos
                        }
                        tokens.add(new Token("llaveAbrir", ""));
                        break;
                    case '}':
                        tokens.add(new Token("llaveCerrar", ""));
                        zonaDecl = false;
                        currTable = 1; // Volver a la tabla global
                        inFuncion = false; // Salimos de la función
                        break;
                    case '<':
                        tokens.add(new Token("opRelMenor", ""));
                        break;
                    case '|':
                        if (i + 1 < data.length() && data.charAt(i + 1) == '|') {
                            tokens.add(new Token("opLogO", ""));
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
                        tokens.add(new Token(reservadas.get(word), ""));
                        if (word.equals("function")) zonaDecl = true;
                    } else {
                        List<TS> currentTable = tablaGlobal.get(currTable - 1); // Tabla actual
                        List<TS> globalTable = tablaGlobal.get(0); // Tabla global
                
                        // Verificar si el símbolo ya existe
                        Optional<TS> tsOptionalLocal = currentTable.stream().filter(ts -> ts.lexema.equals(word)).findFirst();
                        if (!tsOptionalLocal.isPresent()) {
                            Optional<TS> tsOptionalGlobal = globalTable.stream().filter(ts -> ts.lexema.equals(word)).findFirst();
                            if (tsOptionalGlobal.isPresent() && currTable > 1) {
                                tokens.add(new Token("id", String.valueOf(globalTable.indexOf(tsOptionalGlobal.get()))));
                            } else {
                                TS nuevoSimbolo = new TS(word, "", 0, "", "", 0, currentTable.size());
                                /*if (capturaParametros) {
                                    // Añadir el parámetro a la lista temporal
                                    parametrosTemporales.add(nuevoSimbolo);
                                    tokens.add(new Token("id", String.valueOf(parametrosTemporales.indexOf(nuevoSimbolo))));
                                } else { */
                                    currentTable.add(nuevoSimbolo);
                                    tokens.add(new Token("id", String.valueOf(currentTable.indexOf(nuevoSimbolo))));
                                
                            }
                        } else {
                            tokens.add(new Token("id", String.valueOf(currentTable.indexOf(tsOptionalLocal.get()))));
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
                        String CurrentInt= Integer.toString(currentInt);
                        tokens.add(new Token("entero", CurrentInt));
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
                            tokens.add(new Token("cadena", currStr.toString()));
                        }
                    } else {
                        manejarError("Léxico", "Cadena abierta sin cerrar antes de EOF", linea, errorWriter);
                    }
                    
                }
            }
    
            if (isComment) {
                manejarError("Léxico", "Comentario abierto y EOF encontrado antes de cerrar comentario", linea, errorWriter);
            } else {
                tokens.add(new Token("EOF", ""));
            }
    
            return tokens;
        }
    
    
        public static class Parser {
            private  List<Token> Tokens;
            private int line;
            private  int CurrentToken = 0;
            private  String result = "Descendente ";
            BufferedWriter parseWriter;
            List<List<TS>> TablaGlobal;
            private int DESPG;
            
            public String parse(BufferedWriter parseWriter, List<Token> tokens, List<List<TS>> tablaGlobal2 ) throws IOException {
                // Leer tokens desde el TokenReader y almacenar en la lista Tokens
                line = 0;
                Tokens  = tokens;
                CurrentToken = 0;
                DESPG = 0;
                result = "Descendiente ";
                TablaGlobal = tablaGlobal2; // Uso directo de la tabla global pasada
                System.err.println(tokens);
                System.err.println("\n");
                System.err.println("\n");
            
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
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in P expecting: wordFunction, id, wordIf, wordInput, wordOutput, wordReturn, wordSwitch, wordVar");
                    }
                }
            
                // Procedimiento para funciones
                private void F() {
                    result += " 4 ";
                    equipara("wordFunction");
                    H();  // Tipo de retorno de la función
                    String posTabala = getCurrentAtrib();
                    System.err.println("Posicion de la tabla: " + posTabala);
                    addToFunctio(posTabala, "function");
                    equipara("id");
                    //TSA = TSL
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
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in H expecting: wordInt, wordBoolean, wordString, wordVoid");
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
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in A expecting: wordInt, wordBoolean, wordString, wordVoid");
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
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in K expecting: coma, parCerrar");
                    }
                }
            
                // Procedimiento para el cuerpo de la función
                private void C() {
                    String token = getCurrentToken();
                    if (token.equals("wordIf") || token.equals("wordBreak") || token.equals("wordVar") || token.equals("wordSwitch") || 
                    token.equals("id") || token.equals("wordOutput") || token.equals("wordInput") || token.equals("wordReturn")) {
                        result += " 11 ";
                        B();
                        C();
                    } else if (token.equals("wordCase") ||token.equals("llaveCerrar")){
                        result += " 12 ";
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in C expecting: wordIf, wordBreak, wordVar, wordSwitch, id, wordOutput, wordInput, wordReturn, wordCase, llaveCerrar");
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
                        String tipo = T();
                        String posTabla = getCurrentAtrib();
                        equipara("id");
                        int ancho = getAncho(tipo);
                        addTypeDesp(posTabla, tipo, ancho);
                        equipara("puntocoma");
                    } else if ( token.equals("id") || token.equals("wordOutput") || token.equals("wordInput") || token.equals("wordBreak") || token.equals("wordReturn")){
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
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in B expected: wordIf, wordVar, id, wordOutput, wordInput, wordBreak, wordReturn, wordSwitch");
                    
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
                        BB();
                    }else if(token.equals("llaveCerrar")){
                        result += " 21 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in BB expecting: wordCase, llaveCerrar");
                    
                    } 
                }
            
                // Procedimiento para sentencias (S)
                private void S() {
                    String token = getCurrentToken();
                    if (token.equals("id")) {
                        result += " 22 ";
                        equipara("id");
                        S1();
                        equipara("puntocoma"); 
                    } else if (token.equals("wordOutput")) {
                        result += " 31 ";
                        equipara("wordOutput");
                        E();
                        equipara("puntocoma");
                    }else if (token.equals("wordBreak")) {
                        result += " 30 ";                    
                        equipara("wordBreak");
                        equipara("puntocoma");
                    } else if (token.equals("wordInput")) {
                        result += " 32 ";                
                        equipara("wordInput");
                        equipara("id");
                        equipara("puntocoma");
                    } else if (token.equals("wordReturn")) {
                        result += " 33 ";
                        equipara("wordReturn");
                        X();
                        equipara("puntocoma");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in S expecting: id, puntocoma, wordOutput, wordBreak, wordInput, wordReturn");
                    }
                }
            
                // Continuación de sentencias (S1)
                private void S1() {
                    String token = getCurrentToken();
                    if (token.equals("asig")) {
                        result += " 23 ";
                        equipara("asig");
                        E();
                    } else if (token.equals("divAsig")) {
                        result += " 24 ";                    
                        equipara("divAsig");
                        E();
                    } else if (token.equals("parAbrir")) {
                        result += " 25 ";                    
                        equipara("parAbrir");
                        L();
                        equipara("parCerrar");
                    }
                    else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in S1 expecting: asig, divAsig, parAbrir");
                    }
                }
            
                // Expresión de retorno
                private void X() {
                    String token = getCurrentToken();
                    if ( token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 34 ";                    
                        E();
                    } else if(token.equals("puntocoma")) {
                        result += " 35 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in X expecting: id, entero, cadena, parAbrir, puntocoma");
                    }
                }
            
                // Expresión principal (E)
                private void E() {
                    result += " 36 ";                
                    R();
                    EE();
                }
            
                // Complemento de expresión (EE)
                private void EE() {
                    String token = getCurrentToken();
                    if (token.equals("opLogO")) {
                        result += " 37 ";                    
                        equipara("opLogO");
                        R();
                        EE();
                    } else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma")) {
                        result += " 38 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in EE expecting: opLogO, parCerrar, coma, puntocoma");
                    }
                }
            
                private void R() {
                    result += " 39 ";                
                    U();
                    RR();
                }
            
                // Complemento de relación (RR)
                private void RR() {
                    String token = getCurrentToken();
                
                    if (token.equals("opRelMenor")) {
                        result += " 40 ";                    
                        equipara("opRelMenor");
                        U();
                        RR();
                    }else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO")) {
                        result += " 41 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in RR expecting: opRelMenor, parCerrar,coma, puntocoma, opLogO ");
                    }
                }
            
                private void L() {
                    String token = getCurrentToken();
                    if (token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 26 ";
                        E();  // Expresión
                        Q();  // Posibles parámetros adicionales
                    } else if(token.equals("parCerrar")) {
                        result += " 27 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in L expecting: id, entero, cadena, parAbrir, parCerrar");
                    }
                }
            
                private void Q() {
                    String token = getCurrentToken();
                    if (token.equals("coma")) {
                        result += " 28 ";
                        equipara("coma");
                        E();  // Expresión
                        Q();  // Posibles parámetros adicionales
                    } else if(token.equals("parCerrar")) {
                        result += " 29 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in Q expecting: coma, parCerrar");
                    }
                }
            
                private void U() {
                    result += " 42 ";
                    V();  // Parte izquierda de la operación
                    UU();  // Parte derecha (suma opcional)
                }
            
                private void UU() {
                    String token = getCurrentToken();
                    if (token.equals("opArSuma")) {
                        result += " 43 ";
                        equipara("opArSuma");
                        V();  // Expresión después del operador
                        UU();  // Posibles operadores adicionales
                    }else if(token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 44 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in UU expecting: opArSuma, parCerrar,coma, puntocoma, opLogO, opRelMenor ");
                    }
                }
            
                private void V() {
                    result += " 45 ";
                    W();  // Parte izquierda de la operación
                    VV();  // Parte derecha (división opcional)
                }

                private void VV() {
                    String token = getCurrentToken();
                
                    if (token.equals("opArDiv")) {
                        result += " 46 ";
                        equipara("opArDiv");
                        W();  // Expresión después del operador
                        VV();  // Posibles operadores adicionales
                    }else if(token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 47 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in VV expecting: opArDiv, parCerrar, opArSuma,coma, puntocoma, opLogO, opRelMenor ");
                    }
                }
            
                private void W() {
                    String token = getCurrentToken();
                    if (token.equals("id")) {
                        result += " 48 ";
                        equipara("id");
                        W1();  // Posibles llamadas de función o lambda
                    } else if (token.equals("parAbrir")) {
                        result += " 49 ";
                        equipara("parAbrir");
                        E();  // Expresión dentro de los paréntesis
                        equipara("parCerrar");
                    } else if (token.equals("entero")) {
                        result += " 50 ";
                        equipara("entero");
                    } else if (token.equals("cadena")) {
                        result += " 51 ";
                        equipara("cadena");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in W expecting: id, parAbrir, entero, cadena");
                    }
                }
            
                private void W1() {
                    String token = getCurrentToken();
                
                    if (token.equals("parAbrir")) {
                        result += " 52 ";
                        equipara("parAbrir");
                        L();  // Lista de argumentos
                        equipara("parCerrar");
                    } else if(token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor") || token.equals("opArDiv")) {
                        result += " 53 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in W1 expecting: parAbrir, parCerrar, opArSuma,coma, puntocoma, opLogO, opRelMenor, opArDiv ");
                    }
                }
            
                private String T() {
                    String token = getCurrentToken();
                    if (token.equals("wordInt")) {
                        result += " 18 ";                    
                        equipara(token);
                        return "entero";
                    } else if(token.equals("wordBoolean")){
                        result += " 17 ";
                        equipara(token);
                        return "boolean";
                    } else if(token.equals("wordString")){
                        result += " 19 ";
                        equipara(token);
                        return "string";
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in T expecting: wordInt, wordBoolean, wordString");
                    }
                    return "ERROR";
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
                private String getCurrentAtrib() {
                    if (CurrentToken < Tokens.size()) {
                        return Tokens.get(CurrentToken).atrib; // Devuelve el tipo del token
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
        
                public void addTypeDesp(String posicionTabla, String tipo, int ancho) {
                    try {
                        // Convertir la posición de la tabla a un entero
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Recorrer todas las tablas en TablaGlobal
                        for (int i = 0; i < 5; i++) { //5 por probar, cambiar luego
                            List<TS> tabla = TablaGlobal.get(i);
                
                            // Buscar el símbolo con el campo pos igual a la posición buscada
                            for (TS simbolo : tabla) {
                                if (simbolo.pos == posBuscada) {
                                    // Actualizar el tipo del símbolo
                                    simbolo.tipo = tipo;
                                    DESPG += ancho;
                                    simbolo.despl = DESPG;
                                    System.out.println("Tipo actualizado: Tabla " + i + ", Posición " + posBuscada + ", Tipo: " + tipo + ", Desp: " + DESPG);
                                    return;
                                }
                            }
                        }
                
                        // Si no se encuentra el símbolo, lanzar excepción
                        throw new IllegalArgumentException("No se encontró un símbolo con la posición especificada.");
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("La posición de la tabla debe ser un número entero válido. Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error al actualizar el tipo: " + e.getMessage());
                    }
                }
                public int getAncho(String tipo){
                    if(tipo.equals("entero")){
                        return 1;
                    }else if(tipo.equals("boolean")){
                        return 1;
                    }//else if(tipo.equals("string")){
                    else {
                        return 8;
                    }
                }
                public void addToFunctio(String posicionTabla, String tipo){
                    // Convertir la posición de la tabla a un entero
                    int posBuscada = Integer.parseInt(posicionTabla);
                
                    // Recorrer todas las tablas en TablaGlobal
                    for (int i = 0; i < 5; i++) { //5 por probar, cambiar luego
                        List<TS> tabla = TablaGlobal.get(i);
            
                        // Buscar el símbolo con el campo pos igual a la posición buscada
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                // Actualizar el tipo del símbolo
                                simbolo.tipo = tipo;
                                System.out.println("Tipo actualizado: Tabla " + i + ", Posición " + posBuscada + ", Tipo: " + tipo);
                                return;
                            }
                        }
                    }
                }                                                                                          
        }


    
    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/pruebasSemantico.txt");
        File tokenFile = new File("ficherotokens.txt");
        File TSFile = new File("ficheroTS.txt");
        File TSFileFinal = new File("ficheroTSFinal.txt");
        File errorFile = new File("ficheroError.txt");
        File parse = new File("parse.txt");


        List<List<TS>> tablaGlobal = new ArrayList<>();//Lista de todas las tablas de simbolos, dentro lista de todos los id de las tablas
        tablaGlobal.add(new ArrayList<>()); // Crear tabla global inicialmente (Tabla 1)


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(tokenFile));
             BufferedWriter TSwriter = new BufferedWriter(new FileWriter(TSFile));
             BufferedWriter TSwriterFinal = new BufferedWriter(new FileWriter(TSFileFinal));
             BufferedReader Tokenreader = new BufferedReader(new FileReader(tokenFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));
             BufferedWriter parseWriter = new BufferedWriter(new FileWriter(parse))) {

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            int[] numTabla = {1};
            List<Token> tokens = generateTokens(data.toString(), tablaGlobal,numTabla, errorWriter);

            for (Token token : tokens) {
                tokenWriter.write(token.toString());
                tokenWriter.newLine();
            }

            TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS # 1:\n");
            for (int i = 0; i < tablaGlobal.size(); i++) {
                TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS # " + (i + 1) + ":\n");
                for (TS symbol : tablaGlobal.get(i)) {
                    TSwriter.write(symbol.toString());
                    TSwriter.write("--------- ----------\n");
                }
            }
            //System.out.println("Tokens y tabla de símbolos procesados y escritos en los archivos: " + tokenFile.getName() + ", " + TSFile.getName());
            
            String result;
            Parser parser = new Parser();
            result=parser.parse(parseWriter ,tokens,tablaGlobal); //Añadir despues fichero para que escriba, escriba parse res en fichero
            parseWriter.write(result);

            for (int i = 0; i < tablaGlobal.size(); i++) {
                TSwriterFinal.write("CONTENIDO DE LA TABLA DE SIMBOLOS # " + (i + 1) + ":\n");
                for (TS symbol : tablaGlobal.get(i)) {
                    if(symbol.tipo == "function"){
                        TSwriterFinal.write(symbol.toString());
                        TSwriterFinal.write("--------- ----------\n");
                    }else{
                        TSwriterFinal.write(symbol.toString2());
                        TSwriterFinal.write("--------- ----------\n");
                    }

                }
            }
            //System.out.println("Tokens y tabla de símbolos procesados y escritos en los archivos: " + tokenFile.getName() + ", " + TSFile.getName());

    }
             catch (IOException e) {
            e.printStackTrace();
        }
    }
}