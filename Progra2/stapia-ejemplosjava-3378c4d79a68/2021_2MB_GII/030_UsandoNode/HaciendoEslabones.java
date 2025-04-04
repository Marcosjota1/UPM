public class HaciendoEslabones {
    public static void main(String[] args) {
      OtroNodo<Integer> nodeB = new OtroNodo<>(39, null);
      OtroNodo<Integer> nodeA = new OtroNodo<>(44, nodeB);
      nodeB.next = new OtroNodo<>(68, null);
      System.out.println(nodeA.element);
      System.out.println(nodeA.next.element);
    }
}

class OtroNodo<E> {
  E element;
  OtroNodo<E> next;

  OtroNodo(E element, OtroNodo<E> next) {
    this.element = element;
    this.next = next;
  }
}
