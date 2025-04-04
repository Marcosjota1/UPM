package entregaFinal.procesador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entregaFinal.procesador.lexic.TS;
import entregaFinal.procesador.lexic.Token;


public class sintactSemantic {
    public static class Parser {
            private  List<Token> Tokens; //Guardas los tokens pasador por el lexico
            private  int CurrentToken = 0;
            private  String result = "Descendente ";
            List<List<TS>> TablaFinal; //Tabla tras analisis semantico           
            private int DESPG; //Manejo de desplazamientos
            private int DESPL;
            private String retornoFuncion; //Actualizar retorno funcion
            private String nombreFuncion; //Actualizar nombre funcion
            private boolean inFuncion; //Manejar si es return se usa fuera funcion
            private boolean inSwitch; //Manejar break se utiliza dentro de case
            private int numeroFuncion; //Para etiquetas
            private int TSA; //Manejo de la tabla en la que nos encontramos
            private int TSL;
            private int linea; //Manejo de linea en la que se produce el error
            private Map<String, FunctionSignature> functionSignatures = new HashMap<>(); //Manejo de las funciones existentes

// Clase para representar la firma de una función
private static class FunctionSignature {
    //String returnType;
    List<String> parameterTypes;
    int numParametros; //Para identificar la función en la etiqueta de retorno

    public FunctionSignature(String returnType, List<String> parameterTypes, int numParametros) {
        //this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.numParametros = numParametros;
    }
}
            
            public String parse( List<Token> tokens, List<List<TS>> tablaGlobal2 ) throws IOException {
                // Leer tokens desde el TokenReader y almacenar en la lista Tokens
                linea = 1;
                Tokens  = tokens;
                CurrentToken = 0;
                DESPG = 0;
                DESPL = 0;
                inFuncion = false;
                inSwitch = false;
                result = "Descendiente ";
                TablaFinal = tablaGlobal2; // Uso directo de la tabla global pasada por el lexico
                TSA = 0;
                TSL = 0;
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
                        System.err.println("\n");
                        System.out.println(result);
                    
                    } else if (token.equals("wordFunction")) { 
                        result += " 1 ";
                        F();
                        P();
                    } else if (token.matches("id|wordIf|wordInput|wordOutput|wordReturn|wordSwitch|wordVar")) {
                        result += " 2 ";
                        B();
                        P();
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en P se esperaba: wordFunction, id, If, Input, Output, Return, Switch, Var");
                    }
                }
            
                // Procedimiento para funciones
                private void F() {
                    result += " 4 ";
                    equipara("wordFunction");
                    H();  // Tipo de retorno de la función
                    String posTabla = getCurrentAtrib();
                    //System.err.println("Posicion de la tabla: " + posTabla);
                    addTipoTipoRetFunction(posTabla, "function");

                    TablaFinal.add(new ArrayList<>()); //Creas tabla local
                    TSL++; //Creas tabla local
                    TSA = TSL; //Actualizas la

                    List<String> tiposParametros = new ArrayList<>();
                    int numParametros = 0;
                    
                    nombreFuncion = getNombreFuncion(posTabla);
                    String funcionActual = nombreFuncion;

                    equipara("id");
                    equipara("parAbrir");

                    if (!getCurrentToken().equals("parCerrar")) { // Si hay parámetros
                        numParametros = A(tiposParametros);  // Obtener parámetros
                    }  // Argumentos de la función
                    functionSignatures.put(nombreFuncion, new FunctionSignature(retornoFuncion, tiposParametros, numParametros));                   

                    equipara("parCerrar");
                    equipara("llaveAbrir");

                    inFuncion = true;
                    C();  // Cuerpo de la función
                    equipara("llaveCerrar");
                    /* if (!hayReturn && !retornoFuncion.equals("vacio")) {
                        ErrorSemantico("Función '" + funcionActual + "' no tiene sentencia de retorno y es tipo distinto a vacio.");
                    }*/
                    inFuncion = false;
                    DESPL = 0;
                    TSA = 0;                                        
                    String etiqueta =generarEtiquetaFuncion(funcionActual) ;
                    //System.err.println("Parametros " + tiposParametros);
                    addParamsEtiq(posTabla, "function", retornoFuncion, numParametros, tiposParametros,etiqueta);
                }
            
                // Procedimiento para el tipo de retorno de función
                private void H() {
                    String token = getCurrentToken();
                    if (token.equals("wordInt") || token.equals("wordBoolean") || token.equals("wordString")) {
                        result += " 5 ";
                        retornoFuncion = T();
                    } else if (token.equals("wordVoid")) {
                        result += " 6 ";
                        retornoFuncion = "vacio";
                        equipara("wordVoid");                    
                    }  else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en H se esperaba: Int, Boolean, String, Void");
                    }
                }
            
                // Procedimiento para argumentos  A / K ,  declarar funciones
                private int A(List<String> tiposParametros) {
                    String token = getCurrentToken();
                    int count = 0;
                    if (token.matches("wordInt|wordBoolean|wordString")) {
                        result += " 7 ";
                        String tipo = T();
                        addTypeDesp(getCurrentAtrib(), tipo, getAncho(tipo));
                        tiposParametros.add(tipo);
                        count++;
                        equipara("id");
                        count += K(tiposParametros);
                    } else if (token.equals("wordVoid")) {
                        result += " 8 ";
                        equipara("wordVoid");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en A se esperaba: Int, Boolean, String, Void");
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
                        addTypeDesp(getCurrentAtrib(), tipo, getAncho(tipo));
                        tiposParametros.add(tipo);
                        count++;
                        equipara("id");
                        count += K(tiposParametros);
                    } else if(token.equals("parCerrar")){
                        result += " 10 ";
                    }else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en K se esperaba: coma, parCerrar");
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
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en C se esperaba: If, Break, Var, Switch, id, Output, Input, Return, Case, llaveCerrar");
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
                        if (yaTieneTipo(posTabla)) { 
                            ErrorSemantico("Declaración múltiple detectada para el identificador: " + buscaLexemaTS(posTabla));
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
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en B expected: If, Var, id, Output, Input, Break, Return, Switch");
                    
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
                        inSwitch = true;
                        C();
                        inSwitch = false;
                        BB();
                    }else if(token.equals("llaveCerrar")){
                        result += " 21 ";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en BB se esperaba: Case, llaveCerrar");
                    
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
                        if(!inSwitch) ErrorSemantico("La palabra reservada break solo puede aparecer dentro de una instrucción 'switch'");                    
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
                        equipara("puntocoma");
                    } else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en S se esperaba: id, puntocoma, Output, Break, Input, Return");
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
                        if(!tipoIzq.equals("entero") && !tipoDer.equals("entero")){
                            ErrorSemantico("Operador de división solo puede aplicarse a valores enteros y se esta aplicando con " + tipoIzq + " /= " + tipoDer);
                        }
                    } else if (token.equals("parAbrir")) {
                        result += " 25 ";                    
                        equipara("parAbrir");
                        L();
                        equipara("parCerrar");
                    }
                    else {
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en S1 se esperaba: asig, divAsig, parAbrir");
                    }
                }

                // Procedimiento para llamadas a una funcion 
                private void L() {
                    // Recupera la firma de la función
                    String funcionActual = nombreFuncion;
                    FunctionSignature signature = functionSignatures.get(funcionActual);
                    if (signature == null) {
                        ErrorSemantico("La función '" + nombreFuncion + "' no está definida.");
                        return;
                    }
                
                    // Crea un nuevo contexto para esta llamada
                    List<String> argumentos = new ArrayList<>();
                
                    String token = getCurrentToken();
                
                    // Procesa el primer argumento o detecta cierre inmediato
                    if (token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 26 ";
                        String tipoPrimerArgumento = E(); // Procesa el primer argumento
                        argumentos.add(tipoPrimerArgumento); // Agrega el tipo del primer argumento a la lista
                        //System.err.println("Argumentos: " + argumentos);
                        Q(argumentos); // Procesa argumentos adicionales
                    } else if (token.equals("parCerrar")) {
                        result += " 27 "; // Llamada sin argumentos
                    } else {
                        ErrorSintaxis("Recibió '" + token + "' -> Token inesperado en L. Se esperaba: id, entero, cadena, parAbrir, parCerrar.");
                        return;
                    }
                
                    // Verifica que el número de argumentos coincida con la firma
                    if (argumentos.size() != signature.numParametros) {
                        ErrorSemantico("La función "  + funcionActual + " esperaba " + signature.parameterTypes.size()
                                + " parámetros, pero recibió " + argumentos.size() + ".");
                        return;
                    }
                
                    // Valida que los tipos de los argumentos coincidan
                    for (int i = 0; i < argumentos.size(); i++) {
                        String tipoArgumento = argumentos.get(i);
                        String tipoEsperado = signature.parameterTypes.get(i);
                        if (!tipoArgumento.equals(tipoEsperado)) {
                            ErrorSemantico("El parámetro " + (i + 1) + " de la función"  + funcionActual 
                                    + " esperaba '" + tipoEsperado + "', pero recibió '" + tipoArgumento + "'.");
                        }
                    }
                }

                //Procedimiento para llamadas a una funcion co ¡n varios parametros
                private void Q(List<String> argumentos) {
                    String token = getCurrentToken();
                
                    if (token.equals("coma")) {
                        result += " 28 ";
                        equipara("coma");
                        String tipoArgumento = E(); // Procesa el siguiente argumento
                
                        argumentos.add(tipoArgumento); // Agrega el tipo del argumento actual a la lista
                        //System.err.println("Argumentos: " + argumentos);
                
                        Q(argumentos); // Llama recursivamente para procesar más argumentos
                    } else if (token.equals("parCerrar")) {
                        result += " 29 "; // Cierra la lista de argumentos
                    } else {
                        ErrorSintaxis("Recibió '" + token + "' -> Token inesperado en Q Se esperaba: coma, parCerrar.");
                    }
                }
                
                                
            
                // Procedimiento para expresión de retorno
                private String X() {
                    String token = getCurrentToken();
                    if ( token.equals("id") || token.equals("entero") || token.equals("cadena") || token.equals("parAbrir")) {
                        result += " 34 ";                    
                        return E();
                    } else if(token.equals("puntocoma")) {
                        result += " 35 ";
                        return "vacio";
                    }else{
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en X se esperaba: id, entero, cadena, parAbrir, puntocoma");
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
                
                //Procedimiento operador logico OR
                private String EE(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opLogO")) {
                        result += " 37 ";
                        equipara("opLogO");
                        String tipoR = R();
                        if (!tipoAcumulado.equals("logico") || !tipoR.equals("logico")) {
                            ErrorSemantico("Operador lógico OR solo aplica a valores lógicos.");
                        }
                        return EE("logico"); // Continuamos evaluando como lógico
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma")) {
                        result += " 38 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en EE se esperaba: opLogO, parCerrar, coma, puntocoma");
                        return "error";
                    }
                }
                
                private String R() {
                    result += " 39 ";
                    String tipoU = U(); // Obtiene el tipo de la operación U
                    return RR(tipoU); // Evaluamos el complemento
                }
                
                //Procedimiento operador relacional menor
                private String RR(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opRelMenor")) {
                        result += " 40 ";
                        equipara("opRelMenor");
                        String tipoU = U();
                        if (!tipoAcumulado.equals("entero") || !tipoU.equals("entero")) {
                            ErrorSemantico("Operador relacional '<' solo aplica a valores enteros.");
                        }
                        return RR("logico"); // El resultado de una comparación siempre es lógico
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO")) {
                        result += " 41 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en RR se esperaba: opRelMenor, parCerrar, coma, puntocoma, opLogO");
                        return "error";
                    }
                }
                            
                private String U() {
                    result += " 42 ";
                    String tipoV = V(); // Parte izquierda de la operación
                    return UU(tipoV); // Evaluamos el complemento
                }
                
                //Procedimiento operador aritmetico sum
                private String UU(String tipoAcumulado) { 
                    String token = getCurrentToken();
                    if (token.equals("opArSuma")) {
                        result += " 43 ";
                        equipara("opArSuma");
                        String tipoV = V(); // Expresión después del operador
                        //System.out.println("El tipo de la suma '" + tipoV + "' y el tipo esperado es entero'.");
                        if (!tipoAcumulado.equals("entero") || !tipoV.equals("entero")) {
                            ErrorSemantico("Operador '+' no aplica a '" + tipoV + "' solo aplica a valores enteros.");
                        }
                        return UU("entero"); // Continuamos evaluando como entero
                    } else if (token.equals("parCerrar") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 44 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en UU se esperaba: opArSuma, parCerrar, coma, puntocoma, opLogO, opRelMenor");
                        return "error";
                    }
                }
            
                private String V() {
                    result += " 45 ";
                    String tipoW = W(); // Parte izquierda de la operación
                    return VV(tipoW); // Evaluamos el complemento
                }
                
                //Procedimiento operador aritmetico division
                private String VV(String tipoAcumulado) {
                    String token = getCurrentToken();
                    if (token.equals("opArDiv")) {                        
                        result += " 46 ";
                        equipara("opArDiv");
                        String tipoW = W(); // Expresión después del operador
                        //System.out.println("El tipo de la división '" + tipoW + "' y el tipo esperado es entero'.");
                        if (!tipoAcumulado.equals("entero") || !tipoW.equals("entero")) {
                            ErrorSemantico("Operador '/' solo aplica a valores enteros.");
                        }
                        return VV("entero"); // Continuamos evaluando como entero
                    } else if (token.equals("parCerrar") || token.equals("opArSuma") || token.equals("coma") || token.equals("puntocoma") || token.equals("opLogO") || token.equals("opRelMenor")) {
                        result += " 47 ";
                        return tipoAcumulado; // Devolvemos el tipo acumulado cuando no hay más complementos
                    } else {
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en VV se esperaba: opArDiv, parCerrar, opArSuma, coma, puntocoma, opLogO, opRelMenor");
                        return "error";
                    }
                }
                
                private String W() {
                    String token = getCurrentToken();
                    if (token.equals("id")) {
                        result += " 48 ";
                        nombreFuncion = getNombreFuncion(getCurrentAtrib());
                        inicializar(getCurrentAtrib());
                        String tipoID = buscaTipoTS(getCurrentAtrib()); 
                        if (tipoID.equals("function")){
                            tipoID = buscaTipoRetornoTS(getCurrentAtrib());
                        }
                        //System.out.println("El tipo de la variable '" + getCurrentAtrib() + "' es '" + tipoID + "'.");
                        equipara("id");
                        W1(); 
                        return tipoID;
                    } else if (token.equals("parAbrir")) {
                        result += " 49 ";
                        equipara("parAbrir");
                        String tipoE = E(); // Evaluar expresión dentro de los paréntesis
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
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en W se esperaba: id, parAbrir, entero, cadena");
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
                        ErrorSintaxis("Recibió " + token + " -> Token inesperado en W1 se esperaba: parAbrir, parCerrar, opArSuma, coma, puntocoma, opLogO, opRelMenor, opArDiv");
                    }
                }
                
                // Procedimiento para tipos de datos (T)
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
                        ErrorSintaxis("Recibio " + token  + " -> Token inesperado en T se esperaba: Int, Boolean, String");
                    }
                    return "ERROR";
                }
                            
                // Pedir token al analizador lexico
                private void nextToken() {
                    if (CurrentToken < Tokens.size() - 1) {
                        CurrentToken++;
                    }
                }
            
                private String getCurrentToken() {
                    while (CurrentToken < Tokens.size()) {
                        String tokenActual = Tokens.get(CurrentToken).elem; // Obtiene el tipo del token
                        if (tokenActual.equals("SALTO_LINEA")) { // Reemplaza "SALTO_LINEA" con el sigtoken e incremeta linea
                            linea++; // Incrementa la variable de línea
                            CurrentToken++; // Avanza al siguiente token
                        } else {
                            return tokenActual; // Retorna el token actual si no es "SALTO_LINEA"
                        }
                    }
                    return "EOF"; // Retorna "EOF" si no hay más tokens
                }
                
                //Obtener atributo de un token 
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
            
                //Equipara los terminales sean los esperados
                private void equipara(String expected) {
                    String token = getCurrentToken(); // Usar el token actual solo una vez
                    if (token.equals(expected)) {
                        nextToken();
                    } else {
                        ErrorSintaxis("Esperaba " + expected + " pero encontro " + token);
                    }
                }
                                

                //Añadir tipo y desplazamiento a una variable y actualizas desplazamiento
                public void addTypeDesp(String posicionTabla, String tipo, int ancho) {
                    try {
                        //System.err.println("Posicion de la tabla: " + posicionTabla + " Tipo: " + tipo + " Ancho: " + ancho + " TABLA: " + TSA);
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaFinal.get(0);
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                if (TSA == 0) {
                                    simbolo.tipo = tipo;
                                    simbolo.despl = DESPG;
                                    DESPG += ancho;
                                } else {
                                    //System.err.println("CHECKPOINT" );
                                    moveIdentifierToLocal(posicionTabla);
                                    List<TS> tablaLoc = TablaFinal.get(TSA);
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

                //Dada una posicion del identificador obtien el nombre de la funcion
                public String getNombreFuncion(String posicionTabla) {
                    try {
                        String nombreFuncion = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        List<TS> tablaGlobal = TablaFinal.get(0);
                        for (TS simbolo : tablaGlobal) {
                            if (simbolo.pos == posBuscada ){ // && "function".equals(simbolo.tipo)) {
                                nombreFuncion = simbolo.lexema;
                                //lastFuncion = nombreFuncion;
                                //System.err.println("Nombre de la ultima funcion(encontrado en global): " + lastFuncion);
                                return nombreFuncion;
                            }
                        }
                
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                    return nombreFuncion; 
                }

                //Busca el tipo del identificador en la posicion dada
                public String buscaTipoTS(String posicionTabla) {
                    try {
                        String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Recorrer la tabla `TSA`
                        List<TS> tablaTSA = TablaFinal.get(TSA);
                        for (TS simbolo : tablaTSA) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.tipo;
                                return tipo;
                            }
                        }
                
                        // Si no se encuentra en la local, recorrer la tabla obtenida con `TablaGlobal`
                        List<TS> tablaGlobal0 = TablaFinal.get(0);
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

                //Busca el tipo de retorno de una funcion en la posicion dada
                public String buscaTipoRetornoTS(String posicionTabla) {
                    try {
                        String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaFinal.get(0);
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
                //Busca el lexema de un identificador en la posicion dada
                public String buscaLexemaTS(String posicionTabla){
                    try {
                        String lex = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                        List<TS> tabla = TablaFinal.get(TSA);
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
                                lex = simbolo.lexema;
                                return lex;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTabla);
                    }
                    return "ERRORR";
                }
                
                //Devuelve el ancho de un tipo
                public int getAncho(String tipo){
                    if(tipo.equals("entero")){
                        return 1;
                    }else if(tipo.equals("boolean")){
                        return 1;
                    }else if(tipo.equals("cadena")){
                        return 64;
                    }
                    else {
                        return 1;
                    }
                }

                //Añade parametros y etiqueta a una funcion
                public void addParamsEtiq(String posicionTabla, String tipo, String retornoFuncion, int numParametros, List<String> tiposParametros, String etiqueta) {
                    try {
                        // Convertir la posición de la tabla a un entero
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Obtener la tabla correspondiente al índice TSA
                        List<TS> tabla = TablaFinal.get(0);
                        if (tabla == null) {
                            System.out.println("Error: Tabla para TSA " + TSA + " no encontrada.");
                            return;
                        }
                
                        // Buscar el símbolo con el campo pos igual a la posición buscada
                        for (TS simbolo : tabla) {
                            if (simbolo.pos == posBuscada) {
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

                //Añade tipo y tipo de retorno a una funcion
                public void addTipoTipoRetFunction(String posicionTabla, String tipo) {
                    try {
                        // Convertir la posición de la tabla a un entero
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Obtener la tabla correspondiente al índice TSA
                        List<TS> tabla = TablaFinal.get(0);
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

                //Comprueba si un identificador ya tiene un tipo para evitar declaraciones multiples
                private boolean yaTieneTipo(String posicionTabla) { //Evitar declaraciones multiples
                    String tipo = "";
                        int posBuscada = Integer.parseInt(posicionTabla);
                
                        // Recorrer la tabla `TSA`
                        List<TS> tablaTSA = TablaFinal.get(TSA);
                        for (TS simbolo : tablaTSA) {
                            if (simbolo.pos == posBuscada) {
                                tipo = simbolo.tipo;
                                //System.err.println(tipo);
                            }
                        }
                    if(tipo.equals("entero") || tipo.equals("cadena") || tipo.equals("logico")){
                        return true;
                    }else{
                        return false;
                    }
                }

                //Mueve un identificador de la tabla global a la local si estamos dentro de una tabla local
                private void moveIdentifierToLocal(String posicionTablaGlobal) {
                    try {
                        int posGlobal = Integer.parseInt(posicionTablaGlobal);
                        TS simboloGlobal = null;
                
                        // Buscar el símbolo en la tabla global
                        List<TS> tablaGlobal = TablaFinal.get(0); // Tabla global está en la posición 0
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
                
                        // Agregar el símbolo a la tabla local
                        List<TS> tablaLocal = TablaFinal.get(TSA); // Tabla local actual
                        TS nuevoSimboloLocal = new TS(simboloGlobal.lexema, simboloGlobal.tipo,simboloGlobal.numParams,simboloGlobal.paramTypes,simboloGlobal.returnType, simboloGlobal.despl,simboloGlobal.etiq,simboloGlobal.modoParam,simboloGlobal.pos);
                        tablaLocal.add(nuevoSimboloLocal);
                
                        //System.out.println("Identificador movido de la tabla global a la local: " + posicionTablaGlobal);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al procesar la posición de la tabla: " + posicionTablaGlobal);
                    }
                }
                
                //Inicializa un identificador que no esta declarado con tipo entero
                private void inicializar(String pos) {
                    String tipo = buscaTipoTS(pos);
                
                    if (tipo == null || tipo.isEmpty()) {
                        if (TSA == 0) { 
                            addTypeDesp(pos, "entero", getAncho("entero")); // Default to "entero"
                        } else { 
                            try {
                                boolean enGlobal = false;
                                int posBuscada = Integer.parseInt(pos);
                                TS simboloLocal = null;
                
                                List<TS> tablaLocal = TablaFinal.get(TSA);
                                for (TS simbolo : tablaLocal) {
                                    if (simbolo.pos == posBuscada) {
                                        simboloLocal = simbolo;
                                        break;
                                    }
                                }
                                List<TS> tablaLocalG = TablaFinal.get(0);
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
                
                                simboloLocal.despl = DESPG;
                                DESPG += getAncho("entero");
                                simboloLocal.tipo = "entero";
                                if (!enGlobal) {
                                    TablaFinal.get(0).add(simboloLocal);
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Error: Position '" + pos + "' is not a valid number.");
                            }
                        }
                    }
                }                                                                                            

                //Genera una etiqueta para meter en la TS a una funcion
                private String generarEtiquetaFuncion(String nombreFuncion) {
                    numeroFuncion++;                    
                    return "Et_" + nombreFuncion + String.format("_%02d", numeroFuncion);
                }    

                //Terminamos ejecucion al encontrar el primer error y mensaje por pantalla indicando el error
                private void ErrorSintaxis(String message) {
                    System.err.println("Error sintactico en linea: " +  linea + " -> " +  message );
                    //System.err.println(result);
                    System.exit(1);
                }
                private void ErrorSemantico(String message) {
                    System.err.println("Error semantico en linea: " +  linea + " -> " +  message );
                    //System.err.println(result);
                    System.exit(1);
                }
        }
}