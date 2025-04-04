
public class HaciendoPila {
    public static void main(String[] args) {
      UnNodo<Integer> cima = null;
      cima = new UnNodo<>(39, null);
      cima = new UnNodo<>(81, cima);
      cima = new UnNodo<>(12, cima);
      
      UnNodo<Integer> aux = cima;
          
      while ( aux != null ) {
        System.out.println(aux.element);
        aux = aux.next;
      }
    }
}

class UnNodo<E> {
  E element;
  UnNodo<E> next;

  UnNodo(E element, UnNodo<E> next) {
    this.element = element;
    this.next = next;
  }
}
