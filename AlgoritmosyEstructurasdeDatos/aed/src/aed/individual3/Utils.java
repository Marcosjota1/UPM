package aed.individual3;

import java.util.Iterator;

public class Utils {
    public static boolean isArithmeticSequence(Iterable<Integer> l) {
        Iterator<Integer> it = l.iterator();

        if (!it.hasNext()) {
            return true; // La lista vacía siempre es una secuencia aritmética
        }

        Integer first = it.next();
        Integer second = null;
        Integer diff = null;

        while (it.hasNext()) {
            Integer current = it.next();

            if (current == null) {
                continue; // Saltar elementos nulos
            }

            if (second == null) {
                second = current;
                diff = second - first;
            } else {
                if (current - second != diff) {
                    return false;
                }
                second = current;
            }
        }

        return true;
    }
}
