public class CadenasEnlazadas {
  
  NodoEntero primero /* = null */;
  
  public void insertarDelante(int dato) {
    NodoEntero nodo = new NodoEntero();
    nodo.dato = dato;
    nodo.sig = primero;
    primero = nodo;
  }
  
  public void insertarDespuesDe(int dato, NodoEntero anterior) {
    NodoEntero nodo = new NodoEntero();
    nodo.dato = dato;
    nodo.sig = anterior.sig;
    anterior.sig = nodo;
  }
  
  public int sumarTodos() {
    NodoEntero cursor; /* Cuidado NO hace falta new!!!! */
    cursor = primero;
    int suma = 0;
    while ( cursor != null )  {
      suma += cursor.dato; /* suma = suma + cursor.dato */
      cursor = cursor.sig;
    }
    return suma;
  }
  
  public NodoEntero buscarMenorQueOUltimo(int valor) {
    NodoEntero cursor = primero;
    while (cursor != null && cursor.sig != null && cursor.dato >= valor) {
      cursor = cursor.sig;
    }
    return cursor;
  }
  
  public void eliminarPrimero() {
    primero = primero.sig;
  }

  public void eliminarDespuesDe(NodoEntero anterior) {
    anterior.sig = anterior.sig.sig;
  }
  
  public boolean estaVacia() {
    return primero != null;
  }
  
  public static void main(String[] args) {
    CadenasEnlazadas data = new CadenasEnlazadas();
    data.insertarDelante(-5);
    data.insertarDelante(8);
    data.insertarDespuesDe(33, data.primero);
    
    data.eliminarPrimero();
    
    data.insertarDelante(4);
    
    /*
    int suma = data.sumarTodos();
    System.out.println(suma);
    */
    /*
    NodoEntero res1 = data.buscarMenorQueOUltimo(0);
    System.out.println(res1.dato);
    NodoEntero res2 = data.buscarMenorQueOUltimo(-40);
    System.out.println(res2.dato);
    */
  }
}

class NodoEntero {
  int dato;
  NodoEntero sig;
}