package aed.individual4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import es.upm.aedlib.positionlist.PositionList;

public class OrderedIterator implements Iterator<Integer> {

  private Iterator<Integer> iterator;
  private Integer current;

  public OrderedIterator(PositionList<Integer> list) {
    this.iterator = list.iterator();
    this.current = findNext();
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No hay más elementos en la lista");
    }

    Integer result = current;
    current = findNext();

    return result;
  }

  private Integer findNext() {
    while (iterator.hasNext()) { // bucle que recorra todos los elementos
      Integer next = iterator.next();
      if (current == null || next >= current) {
        return next;
      }
    }
    return null;
  }
}

