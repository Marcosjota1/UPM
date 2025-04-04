package PracticaPDL.entrega3;

import java.io.*;
import java.util.*;

public class entregaFinalV25 {

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
            this.numParams = numParams;
            this.paramTypes = paramTypes;
            this.returnType = returnType;
            this.etiq = etiq;
            this.modoParam = modoParam;
            this.despl = despl;
            this.pos = pos;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LEXEMA: '").append(lexema).append("'\n");
            sb.append("ATRIBUTOS:\n");
            sb.append(" + Tipo: '").append(tipo).append("'\n");
            sb.append(" + Numero de Parametros: ").append(numParams).append("\n");
            sb.append(" + Posicion: ").append(pos).append("\n");

            for (int i = 0; i < numParams; i++) {
                sb.append(" + TipoParam").append(String.format("%02d", i + 1)).append(": '").append(paramTypes.get(i)).append("'\n");
                sb.append(" + ModoParam").append(String.format("%02d", i + 1)).append(": '1'\n");
            }

            sb.append(" + Tipo Retorno: '").append(returnType).append("'\n");
            sb.append(" + Etiqueta Funcion: '").append(etiq).append("'\n");

            return sb.toString();
        }
        public String toString2(){
            return "* LEXEMA : '" + lexema + "'\n" +
            " Atributos :\n" +
                    " + tipo : '" + tipo + "'\n" +
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
        System.err.println("Lexic Error at line: " +  linea + " -> " +  mensaje );
        System.exit(1);
    }

    public static List<Token> generateTokens(String data, List<List<TS>> tablaGlobal,int[] numTabla, BufferedWriter errorWriter) {
        List<Token> tokens = new ArrayList<>();
        boolean zonaDecl = false;
        boolean isComment = false;
        StringBuilder currStr = new StringBuilder();
        String lastWord = null;
        int linea = 1;
        int currtable = 1;

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            if(ch == '\n'){
                tokens.add(new Token("SALTO_LINEA", ""));
                linea++;
                continue;
            }

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
                    zonaDecl = false;
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
                    tokens.add(new Token(reservadas.get(word),  ""));
                    if (word.equals("function")) zonaDecl = true;
                    lastWord = word;
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

        if (isComment) {
            errorLexico("Léxico", "Comentario abierto y EOF encontrado antes de cerrar comentario", linea, errorWriter);
        } else {
            tokens.add(new Token("EOF", ""));
        }

        return tokens;
    }

    
   
        public static class Parser {
            private  List<Token> Tokens;
            private  int CurrentToken = 0;
            private  String result = "Descendente ";
            BufferedWriter parseWriter;
            List<List<TS>> TablaGlobal;
            
            private int DESPG;
            private int DESPL;
            private String retornoFuncion;
            private String nombreFuncion;
            private boolean inFuncion; 
            private boolean hayReturn;
            private String lastFuncion;
            private int numeroFuncion; //Para etiquetas
            private int TSA;
            private int TSL;
            private int linea;
            private boolean esParametro;
            private Map<String, FunctionSignature> functionSignatures = new HashMap<>();

// Clase para representar la firma de una función
private static class FunctionSignature {
    String returnType;
    List<String> parameterTypes;

    public FunctionSignature(String returnType, List<String> parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }
}
            
            public String parse(BufferedWriter parseWriter, List<Token> tokens, List<List<TS>> tablaGlobal2 ) throws IOException {
                // Leer tokens desde el TokenReader y almacenar en la lista Tokens
                linea = 1;
                Tokens  = tokens;
                CurrentToken = 0;
                DESPG = 0;
                DESPL = 0;
                inFuncion = false;
                result = "Descendiente ";
                TablaGlobal = tablaGlobal2; // Uso directo de la tabla global pasada
                TSA = 0;
                TSL = 0;
                hayReturn = false;
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
                        System.out.println("");
                        System.out.println("EXITO");
                        System.out.println("");

                        //System.out.println(result);
                    
                    } else if (token.equals("wordFunction")) { //Conjunto first de F
                        result += " 1 ";
                        F();
                        P();
                    } else if (token.matches("id|wordIf|wordInput|wordOutput|wordReturn|wordSwitch|wordVar")) {
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
                    String posTabla = getCurrentAtrib();
                    //System.err.println("Posicion de la tabla: " + posTabla);
                    addTipoFunc(posTabla, "function");
                    TablaGlobal.add(new ArrayList<>()); //Creas tabla local
                    TSL++; //Creas tabla local
                    TSA = TSL; //Actualizas la
                    List<String> tiposParametros = new ArrayList<>();
                    int numParametros = 0;
                    nombreFuncion = getNombreFuncion(posTabla);
                    equipara("id");
                    equipara("parAbrir");
                    esParametro=true;
                    if (!getCurrentToken().equals("parCerrar")) { // Si hay parámetros
                        numParametros = A(tiposParametros);  // Obtener parámetros
                    }  // Argumentos de la función
                    functionSignatures.put(nombreFuncion, new FunctionSignature(retornoFuncion, tiposParametros));                   
                    equipara("parCerrar");
                    esParametro=false;
                    equipara("llaveAbrir");
                    hayReturn = false;
                    inFuncion = true;
                    C();  // Cuerpo de la función
                    equipara("llaveCerrar");
                    if (!hayReturn && !retornoFuncion.equals("void")) {
                        ErrorSemantico("Función '" + nombreFuncion + "' no tiene sentencia de retorno y es tipo distinto a vacio.");
                    }
                    inFuncion = false;
                    DESPL = 0;
                    TSA = 0;                                        
                    String etiqueta =generarEtiquetaFuncion(nombreFuncion) ;
                    //System.err.println("Parametros " + tiposParametros);
                    addToFunctio(posTabla, "function", retornoFuncion, numParametros, tiposParametros,etiqueta);
                }
            
                // Procedimiento para el tipo de retorno de función
                private void H() {
                    String token = getCurrentToken();
                    if (token.equals("wordInt") || token.equals("wordBoolean") || token.equals("wordString")) {
                        result += " 5 ";
                        retornoFuncion = T();
                        //addReturnFunction(retornoFuncion);
                    } else if (token.equals("wordVoid")) {
                        result += " 6 ";
                        retornoFuncion = "void";
                        equipara("wordVoid");
                        //addReturnFunction(retornoFuncion);
                    
                    }  else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in H expecting: wordInt, wordBoolean, wordString, wordVoid");
                    }
                }
            
                // Procedimiento para argumentos  A / K Son al declarar funciones
                private int A(List<String> tiposParametros) {
                    String token = getCurrentToken();
                    int count = 0;
                    if (token.matches("wordInt|wordBoolean|wordString")) {
                        result += " 7 ";
                        String tipo = T();
                        addArgumentToTable(tipo); //Antes o despues de equipara??
                        tiposParametros.add(tipo);
                        count++;
                        equipara("id");
                        count += K(tiposParametros);
                    } else if (token.equals("wordVoid")) {
                        result += " 8 ";
                        equipara("wordVoid");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in A expecting: wordInt, wordBoolean, wordString, wordVoid");
                    }
                    return count;
                }
            
                // Procedimiento para el manejo de múltiples argumentos
                private int K(List<String> tiposParametros) {
                    String token = getCurrentToken();
                    int count = 0;
                    if (token.equals("coma")) {
                        result += " 9 ";
                        equipara("coma");
                        String tipo = T();
                        addArgumentToTable(tipo); //Antes o despues de equipara??
                        tiposParametros.add(tipo);
                        count++;
                        equipara("id");
                        count += K(tiposParametros);
                    } else if(token.equals("parCerrar")){
                        result += " 10 ";
                    }else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in K expecting: coma, parCerrar");
                    }
                    return count;
                }
            
                // Procedimiento para el cuerpo de la función
                private void C() {
                    String token = getCurrentToken();
                    if (token.matches("wordIf|wordBreak|wordVar|wordSwitch|id|wordOutput|wordInput|wordReturn")) {
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
                        String tipoE = E();
                        //System.out.println("El tipo del IF '" + tipoE + "' y el tipo esperado es logico'.");
                        if (!tipoE.equals("logico")) {                            
                            ErrorSemantico("El tipo de la sentencia IF '" + tipoE + "' debe ser de tipo logico'.");
                        }
                        equipara("parCerrar");
                        S();
                    } else if (token.equals("wordVar")){
                        result += " 14 ";
                        equipara("wordVar");
                        String tipo = T();
                        String posTabla = getCurrentAtrib();                        
                        equipara("id");
                        if (yaTieneTipo(posTabla)) { //MIrar si declarar dos veces mismo tipo debe dar error
                            ErrorSemantico("Declaración múltiple detectada para el identificador: " + posTabla);
                        } else {
                            int ancho = getAncho(tipo);
                            addTypeDesp(posTabla, tipo, ancho);
                        }                    
                        equipara("puntocoma");
                    } else if ( token.matches("id|wordOutput|wordInput|wordBreak|wordReturn")){
                        result += " 15 ";
                        S();
                    } else if(token.equals("wordSwitch")){
                        result += " 16 ";
                        equipara("wordSwitch");
                        equipara("parAbrir");
                        String tipoE = E();  // Argumentos de la función
                        //System.out.println("El tipo de expresion del switch '" + tipoE + "' ha de ser un entero'.");
                        if (!tipoE.equals("entero")) {                            
                            ErrorSemantico("El tipo de la expresion del switch '" + tipoE + "'solo puede ser entero'.");
                        }
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
                        inicializar(getCurrentAtrib());
                        nombreFuncion = getNombreFuncion(getCurrentAtrib());
                        String tipoIzq = buscaTipoTS(getCurrentAtrib());
                        equipara("id");
                        S1(tipoIzq);
                        equipara("puntocoma"); 
                    } else if (token.equals("wordOutput")) {
                        result += " 31 ";
                        equipara("wordOutput");
                        String tipoE = E();
                        if(!tipoE.equals("entero") && !tipoE.equals("cadena")){
                            ErrorSemantico("El tipo de la expresion del output '" + tipoE + "' ha de ser entero o cadena'.");
                        }
                        equipara("puntocoma");
                    }else if (token.equals("wordBreak")) {
                        result += " 30 ";                    
                        equipara("wordBreak");
                        equipara("puntocoma");
                    } else if (token.equals("wordInput")) {
                        result += " 32 ";                
                        equipara("wordInput");
                        inicializar(getCurrentAtrib());
                        String posTabla = getCurrentAtrib();
                        String tipo = buscaTipoTS(posTabla);
                        if(tipo != "entero" && tipo != "cadena"){
                            ErrorSemantico("El tipo de la variable del input es '" + tipo + "' y ha de ser entero o cadena'.");
                        }                        
                        equipara("id");
                        equipara("puntocoma");
                    } else if (token.equals("wordReturn")) {
                        result += " 33 ";
                        if (!inFuncion){
                            ErrorSemantico("Return no se puede encontrar fuera de una funcion");
                        }
                        equipara("wordReturn");
                        String tipoRetorno = X();
                        //System.out.println("El tipo de retorno '" + tipoRetorno + "' y el tipo esperado '" + retornoFuncion + "'.");
                        if (!tipoRetorno.equals(retornoFuncion)) {                            
                            ErrorSemantico("El tipo de retorno '" + tipoRetorno + "' no coincide con el tipo esperado '" + retornoFuncion + "'.");
                        }
                        hayReturn = true;
                        equipara("puntocoma");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in S expecting: id, puntocoma, wordOutput, wordBreak, wordInput, wordReturn");
                    }
                }
            
                // Continuación de sentencias (S1)
                private void S1(String tipoIzq) {
                    String token = getCurrentToken();
                    if (token.equals("asig")) {
                        result += " 23 ";
                        equipara("asig");
                         //Va a dar ERROR ya que current no es el id, fixear luego
                        String tipoDer = E();
                        if (!tipoIzq.equals(tipoDer)) {
                            //System.out.println("El tipo de la izquierda '" + tipoIzq + "' y el tipo de la derecha '" + tipoDer + "'.");
                            ErrorSemantico("Tipos incompatibles en asignación: " + tipoIzq + " = " + tipoDer);
                        }
                    } else if (token.equals("divAsig")) {
                        result += " 24 ";                    
                        equipara("divAsig");
                        String tipoDer = E();
                        if (!tipoIzq.equals(tipoDer)) {
                            ErrorSemantico("Tipos incompatibles en asignación: " + tipoIzq + " /= " + tipoDer);
                        }
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
                private void L() {   //L / Q para llamadas a a funciones
                    FunctionSignature signature = functionSignatures.get(nombreFuncion);
                    if (signature == null) {
                        ErrorSemantico("La función '" + nombreFuncion + "' no está definida.");
                        return;
                    }
                    List<String> argumentos = new ArrayList<>();
                    String token = getCurrentToken();

                    if (token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 26 ";
                        argumentos.add(E()); // Guarda el tipo del argumento actual
                        Q(argumentos);  // Comprueba parámetros adicionales
                    } else if (token.equals("parCerrar")) {
                        result += " 27 ";
                    } else {
                        ErrorSintaxis("Recibio " + token + " -> Unexpected token in L expecting: id, entero, cadena, parAbrir, parCerrar");
                    }
                    if (argumentos.size() != signature.parameterTypes.size()) {
                        System.out.println("Argumentos: " + argumentos + "Size: " + argumentos.size() + " Signature: " + signature.parameterTypes.size());
                        ErrorSemantico("La función '" + nombreFuncion + "' esperaba " + signature.parameterTypes.size()
                                + " parámetros, pero recibió " + argumentos.size() + ".");
                    } else {
                        for (int i = 0; i < argumentos.size(); i++) {
                            if (!argumentos.get(i).equals(signature.parameterTypes.get(i))) {
                                ErrorSemantico("El parámetro " + (i + 1) + " de la función '" + nombreFuncion
                                        + "' esperaba '" + signature.parameterTypes.get(i) + "', pero recibió '" + argumentos.get(i) + "'.");
                            }
                        }
                    }
                }

            
                private void Q(List<String> argumentos) {
                    String token = getCurrentToken();
                    if (token.equals("coma")) {
                        result += " 28 ";
                        equipara("coma");
                        argumentos.add(E()); // Agrega el tipo del argumento actual
                        Q(argumentos);  // Comprueba parámetros adicionales
                    } else if (token.equals("parCerrar")) {
                        result += " 29 ";
                    } else {
                        ErrorSintaxis("Recibio " + token + " -> Unexpected token in Q expecting: coma, parCerrar");
                    }
                }
                
            
                // Expresión de retorno
                private String X() {
                    String token = getCurrentToken();
                    if ( token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 34 ";                    
                        return E();
                    } else if(token.equals("puntocoma")) {
                        result += " 35 ";
                        return "void";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Unexpected token in X expecting: id, entero, cadena, parAbrir, puntocoma");
                        return "ERROR";
                    }
                }
            
                // Expresión principal (E)
                private String E() {
                    result += " 36 ";
                    String tipoR = R(); // Evaluamos la primera expresión
                    String tipoEE = EE(tipoR); // Evaluamos el complemento
                    return tipoEE;
                }
                
                // Complemento de expresión (EE)
                private String EE(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opLogO")) {
                        result += " 37 ";
                        equipara("opLogO");
                        String tipoR = R();
                        if (!tipoR.equals("logico")) {
                            ErrorSemantico("Operador lógico OR solo aplica a valores lógicos.");
                        }
                        return EE("logico"); // Continuamos evaluando como lógico
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma")) {
                        result += " 38 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in EE expecting: opLogO, parCerrar, coma, puntocoma");
                        return "error";
                    }
                }
                
                private String R() {
                    result += " 39 ";
                    String tipoU = U(); // Obtiene el tipo de la operación U
                    return RR(tipoU); // Evaluamos el complemento
                }
                
                // Complemento de relación (RR)
                private String RR(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opRelMenor")) {
                        result += " 40 ";
                        equipara("opRelMenor");
                        String tipoU = U();
                        if (!tipoU.equals("entero")) {
                            ErrorSemantico("Operador relacional '<' solo aplica a valores enteros.");
                        }
                        return RR("logico"); // El resultado de una comparación siempre es lógico
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO")) {
                        result += " 41 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in RR expecting: opRelMenor, parCerrar, coma, puntocoma, opLogO");
                        return "error";
                    }
                }
                            
                private String U() {
                    result += " 42 ";
                    String tipoV = V(); // Parte izquierda de la operación
                    return UU(tipoV); // Evaluamos el complemento
                }
                
                private String UU(String tipoAcumulado) { 
                    String token = getCurrentToken();
                    if (token.equals("opArSuma")) {
                        result += " 43 ";
                        equipara("opArSuma");
                        String tipoV = V(); // Expresión después del operador
                        //System.out.println("El tipo de la suma '" + tipoV + "' y el tipo esperado es entero'.");
                        if (!tipoV.equals("entero")) {
                            ErrorSemantico("Operador '+' no aplica a '" + tipoV + "' solo aplica a valores enteros.");
                        }
                        return UU("entero"); // Continuamos evaluando como entero
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 44 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in UU expecting: opArSuma, parCerrar, coma, puntocoma, opLogO, opRelMenor");
                        return "error";
                    }
                }
            
                private String V() {
                    result += " 45 ";
                    String tipoW = W(); // Parte izquierda de la operación
                    return VV(tipoW); // Evaluamos el complemento
                }
                
                private String VV(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opArDiv")) {
                        result += " 46 ";
                        equipara("opArDiv");
                        String tipoW = W(); // Expresión después del operador
                        if (!tipoW.equals("entero")) {
                            ErrorSemantico("Operador '/' solo aplica a valores enteros.");
                        }
                        return VV("entero"); // Continuamos evaluando como entero
                    } else if (token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 47 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in VV expecting: opArDiv, parCerrar, opArSuma, coma, puntocoma, opLogO, opRelMenor");
                        return "error";
                    }
                }
                
                private String W() {
                    String token = getCurrentToken();
                    if (token.equals("id")) {
                        result += " 48 ";
                        inicializar(getCurrentAtrib());
                        String tipoID = buscaTipoTS(getCurrentAtrib()); 
                        if (tipoID.equals("function")){
                            tipoID = buscaTipoRetornoTS(getCurrentAtrib());
                        }
                        equipara("id");
                        W1(); // Posibles llamadas de función o lambda
                        return tipoID;
                    } else if (token.equals("parAbrir")) {
                        result += " 49 ";
                        equipara("parAbrir");
                        String tipoE = E(); // Expresión dentro de los paréntesis
                        equipara("parCerrar");
                        return tipoE;
                    } else if (token.equals("entero")) {
                        result += " 50 ";
                        equipara("entero");
                        return "entero";
                    } else if (token.equals("cadena")) {
                        result += " 51 ";
                        equipara("cadena");
                        return "cadena";
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in W expecting: id, parAbrir, entero, cadena");
                        return "error";
                    }
                }
                
                private void W1() {
                    String token = getCurrentToken();
                    if (token.equals("parAbrir")) {
                        result += " 52 ";
                        equipara("parAbrir");
                        L(); // Lista de argumentos
                        equipara("parCerrar");
                    } else if (token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor") || token.equals("opArDiv")) {
                        result += " 53 ";
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Unexpected token in W1 expecting: parAbrir, parCerrar, opArSuma, coma, puntocoma, opLogO, opRelMenor, opArDiv");
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
                        return "logico";
                    } else if(token.equals("wordString")){
                        result += " 19 ";
                        equipara(token);
                        return "cadena";
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
                    while (CurrentToken < Tokens.size()) {
                        String tokenActual = Tokens.get(CurrentToken).elem; // Obtiene el tipo del token
                        if (tokenActual.equals("SALTO_LINEA")) { // Reemplaza "SALTO_LINEA" con el token específico que incrementa la línea
                            linea++; // Incrementa la variable de línea
                            CurrentToken++; // Avanza al siguiente token
                        } else {
                            return tokenActual; // Retorna el token actual si no es "SALTO_LINEA"
                        }
                    }
                    return "EOF"; // Retorna "EOF" si no hay más tokens
                }
                
                private String getCurrentAtrib() {
                    if (CurrentToken < Tokens.size()) {
                        while (Tokens.get(CurrentToken).elem.equals("SALTO_LINEA")) {
                            linea++;
                            CurrentToken++; // Avanzar sobre SALTO_LINEA
                        }
                        return Tokens.get(CurrentToken).atrib; // Devuelve el atributo del token actual
                    }
                    return "EOF"; // Retorna null si no hay más tokens
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
                    System.err.println("Syntax Error at line: " +  linea + " -> " +  message );
                    System.err.println(result);
                    System.exit(1);
                }
                private void ErrorSemantico(String message) {
                    System.err.println("Semantic Error at line: " +  linea + " -> " +  message );
                    //System.err.println(result);
                    System.exit(1);
                }

                public void addArgumentToTable(String tipo) {
                    String posTabla = getCurrentAtrib();
                    int ancho = getAncho(tipo);
                    addTypeDesp(posTabla, tipo, ancho);
                }
        
                public void addTypeDesp(String posicionTabla, String tipo, int ancho) {
                    try {
                        //System.err.println("Posicion de la tabla: " + posicionTabla + " Tipo: " + tipo + " Ancho: " + ancho + " TABLA: " + TSA);
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaGlobal.get(0);
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                if (TSA == 0) {
                                    simbolo.tipo = tipo;
                                    simbolo.despl = DESPG;
                                    DESPG += ancho;
                                } else {
                                    //System.err.println("CHECKPOINT" );
                                    moveIdentifierToLocal(posicionTabla);
                                    List<TS> tablaLoc = TablaGlobal.get(TSA);
                                    for (TS simboloLoc : tablaLoc) {
                                        if (simboloLoc.pos == posBuscada) {
                                            simboloLoc.tipo = tipo;
                                            simboloLoc.despl = DESPL;
                                            DESPL += ancho;
                                        }
                                    }
                                }
                                //System.out.println("Tipo actualizado: Tabla " + TSA + ", Posicion " + posBuscada + ", Tipo: " + tipo);
                                return;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                }

                public String getNombreFuncion(String posicionTabla) {
                    try {
                        String nombreFuncion = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaGlobal.get(0);
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                //System.err.println("SIMBOLO: " + simbolo.lexema + " TIPO: " + simbolo.tipo);
                                if(simbolo.tipo.equals("function")){
                                    nombreFuncion = simbolo.lexema;
                                    lastFuncion = nombreFuncion;
                                    return nombreFuncion;
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                    return lastFuncion;
                }

                public String buscaTipoTS(String posicionTabla) {
                    try {
                        String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Recorrer la tabla `TSA`
                        List<TS> tablaTSA = TablaGlobal.get(TSA);
                        for (TS simbolo : tablaTSA) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.tipo;
                                return tipo;
                            }
                        }
                
                        // Si no se encuentra en `TSA`, recorrer la tabla obtenida con `TablaGlobal.get(0)`
                        List<TS> tablaGlobal0 = TablaGlobal.get(0);
                        for (TS simbolo : tablaGlobal0) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.tipo;
                                return tipo;
                            }
                        }
                
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                    return "ERRORR";
                }
                public String buscaTipoRetornoTS(String posicionTabla) {
                    try {
                        String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaGlobal.get(0);
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.returnType;
                                return tipo;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                    return "ERRORR";
                }

                
                public int getAncho(String tipo){
                    if(tipo.equals("entero")){
                        return 1;
                    }else if(tipo.equals("boolean")){
                        return 1;
                    }else if(tipo.equals("cadena")){
                        return 8;
                    }
                    else { //Void
                        return 1;
                    }
                }
                public void addToFunctio(String posicionTabla, String tipo, String retornoFuncion, int numParametros, List<String> tiposParametros, String etiqueta) {
                    try {
                        // Convertir la posición de la tabla a un entero
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Obtener la tabla correspondiente al índice TSA
                        List<TS> tabla = TablaGlobal.get(0);
                        if (tabla == null) {
                            System.out.println("Error: Tabla para TSA " + TSA + " no encontrada.");
                            return;
                        }
                
                        // Buscar el símbolo con el campo pos igual a la posición buscada
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                // Actualizar el tipo del símbolo
                                //simbolo.tipo = tipo;
                                //simbolo.returnType = retornoFuncion;
                                simbolo.numParams = numParametros;
                                simbolo.paramTypes = tiposParametros;
                                simbolo.etiq = etiqueta;
                                //System.out.println("Tipo actualizado: Tabla " + TSA + ", Posición " + posBuscada + ", Tipo: " + tipo);
                                return;
                            }
                        }
                
                        // Si no se encuentra el símbolo
                        System.out.println("Error: Símbolo en posición " + posBuscada + " no encontrado en la tabla " + TSA);
                
                    } catch (NumberFormatException e) {
                        System.out.println("Error: La posición de tabla no es un número válido: " + posicionTabla);
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                public void addTipoFunc(String posicionTabla, String tipo) {
                    try {
                        // Convertir la posición de la tabla a un entero
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Obtener la tabla correspondiente al índice TSA
                        List<TS> tabla = TablaGlobal.get(0);
                        if (tabla == null) {
                            System.out.println("Error: Tabla para TSA " + TSA + " no encontrada.");
                            return;
                        }
                
                        // Buscar el símbolo con el campo pos igual a la posición buscada
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                // Actualizar el tipo del símbolo
                                simbolo.tipo = tipo;
                                simbolo.returnType = retornoFuncion;
                                //simbolo.numParams = numParametros;
                                //simbolo.paramTypes = tiposParametros;
                                //simbolo.etiq = etiqueta;
                                //System.out.println("Tipo actualizado: Tabla " + TSA + ", Posición " + posBuscada + ", Tipo: " + tipo);
                                return;
                            }
                        }
                
                        // Si no se encuentra el símbolo
                        System.out.println("Error: Símbolo en posición " + posBuscada + " no encontrado en la tabla " + TSA);
                
                    } catch (NumberFormatException e) {
                        System.out.println("Error: La posición de tabla no es un número válido: " + posicionTabla);
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                private boolean yaTieneTipo(String posicionTabla) { //Evitar declaraciones multiples
                    String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Recorrer la tabla `TSA`
                        List<TS> tablaTSA = TablaGlobal.get(TSA);
                        for (TS simbolo : tablaTSA) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.tipo;
                            }
                        }
                    if(tipo.equals("entero") || tipo.equals("cadena") || tipo.equals("boolean")){
                        return true;
                    }else{
                        return false;
                    }
                }
                private void moveIdentifierToLocal(String posicionTablaGlobal) {
                    try {
                        int posGlobal = Integer.parseInt(posicionTablaGlobal);
                        TS simboloGlobal = null;
                
                        // Buscar el símbolo en la tabla global
                        List<TS> tablaGlobal = TablaGlobal.get(0); // Tabla global está en la posición 0
                        for (TS simbolo : tablaGlobal) {
                            if (simbolo.pos == posGlobal) {
                                simboloGlobal = simbolo;
                                break;
                            }
                        }
                
                        if (simboloGlobal == null) {
                            System.err.println("No se encontró el identificador en la tabla global: " + posicionTablaGlobal);
                            return;
                        }
                
                        // Eliminar el símbolo de la tabla global
                        //System.err.println("Borrando");
                        if(!esParametro){
                            //tablaGlobal.remove(simboloGlobal);
                        }

                
                        // Agregar el símbolo a la tabla local
                        List<TS> tablaLocal = TablaGlobal.get(TSA); // Tabla local actual
                        TS nuevoSimboloLocal = new TS(simboloGlobal.lexema, simboloGlobal.tipo,simboloGlobal.numParams,simboloGlobal.paramTypes,simboloGlobal.returnType, simboloGlobal.despl,simboloGlobal.etiq,simboloGlobal.modoParam,simboloGlobal.pos);
                        tablaLocal.add(nuevoSimboloLocal);
                
                        //System.out.println("Identificador movido de la tabla global a la local: " + posicionTablaGlobal);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTablaGlobal);
                    }
                }
                
                private void inicializar(String pos) {
                    String tipo = buscaTipoTS(pos);
                
                    if (tipo == null || tipo.isEmpty()) {
                        if (TSA == 0) { // Global scope
                            addTypeDesp(pos, "entero", getAncho("entero")); // Default to "entero"
                        } else { // Local scope
                            try {
                                boolean enGlobal = false;
                                int posBuscada = Integer.parseInt(pos);
                                TS simboloLocal = null;
                
                                // Search in local table
                                List<TS> tablaLocal = TablaGlobal.get(TSA);
                                for (TS simbolo : tablaLocal) {
                                    if (simbolo.pos == posBuscada) {
                                        simboloLocal = simbolo;
                                        break;
                                    }
                                }
                                List<TS> tablaLocalG = TablaGlobal.get(0);
                                for (TS simbolo : tablaLocalG) {
                                    if (simbolo.pos == posBuscada) {
                                        enGlobal = true;
                                        simboloLocal = simbolo;
                                        break;
                                    }
                                }                                
                                if (simboloLocal == null) {
                                    System.err.println("Symbol not found in local table for position: " + pos);
                                    return;
                                }
                
                                // Move to global table
                                //tablaLocal.remove(simboloLocal);
                                simboloLocal.despl = DESPG;
                                DESPG += getAncho("entero");
                                simboloLocal.tipo = "entero";
                                if (!enGlobal) {
                                    TablaGlobal.get(0).add(simboloLocal);
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Error: Position '" + pos + "' is not a valid number.");
                            }
                        }
                    }
                }                               
                                                             

                private String generarEtiquetaFuncion(String nombreFuncion) {
                    numeroFuncion++;                    
                    return "Et_" + nombreFuncion + String.format("_%02d", numeroFuncion);
                }    
                                                                                                                                          
    public static void main(String[] args) {
        //File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/pruebasSemantico.txt");
        File inputFile = new File("C:\\Users\\marco\\OneDrive\\Documentos\\UPM\\PDL/DracoSintact4.txt");
        File tokenFile = new File("ficherotokens.txt");
        File tokenFileFinal = new File("ficherotokensFinal.txt");
        File TSFile = new File("ficheroTS.txt");
        File TSFileFinal = new File("ficheroTSFinal.txt");
        File errorFile = new File("ficheroError.txt");
        File parse = new File("parse.txt");


        List<List<TS>> tablaGlobal = new ArrayList<>();//Lista de todas las tablas de simbolos, dentro lista de todos los id de las tablas
        tablaGlobal.add(new ArrayList<>()); // Crear tabla global inicialmente (Tabla 1)


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(tokenFile));
             BufferedWriter tokenWriterFinal = new BufferedWriter(new FileWriter(tokenFileFinal));
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
             

             for (int i = 0; i < tablaGlobal.size(); i++) {
                TSwriter.write("CONTENIDO DE LA TABLA DE SIMBOLOS #" + (i + 1) + ":\n");
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
                TSwriterFinal.write("CONTENIDO DE LA TABLA DE SIMBOLOS #" + (i + 1) + ":\n");
                for (TS symbol : tablaGlobal.get(i)) {
                    if(symbol.tipo == "") continue;
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
                
            for (Token token : tokens) {
                if (!token.elem.equals("SALTO_LINEA")) { // Verifica si el token no es un salto de línea
                    tokenWriterFinal.write(token.toString()); // Escribe el token si no es "SALTO_LINEA"
                    tokenWriterFinal.newLine(); // Agrega una nueva línea para separar los tokens
                }
            }
            
            //System.out.println("Tokens y tabla de símbolos procesados y escritos en los archivos: " + tokenFile.getName() + ", " + TSFile.getName());
    }
             catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}