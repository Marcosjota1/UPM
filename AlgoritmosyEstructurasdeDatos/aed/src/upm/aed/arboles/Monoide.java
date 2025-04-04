package upm.aed.arboles;

import java.util.function.Function;

public interface Monoide<E> {
	
	public <E> E neutro();
	public <E> E op (E arg1, E arg2);
//	default <E> Function<E, E> compose(Function<? super E, ? extends T> before) {
//        Objects.requireNonNull(before);
//        return (V v) -> apply(before.apply(v));
//    }
//    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
//        Objects.requireNonNull(after);
//        return (T t) -> after.apply(apply(t));
//    }
    static <E> Function<E, E> identity() {
        return t -> t;
    }

}
