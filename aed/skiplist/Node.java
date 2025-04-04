package aed.skiplist;

import es.upm.aedlib.Position;
import es.upm.aedlib.EntryImpl;

public class Node<K, V> extends EntryImpl<K, V> {
	private Position<Node<K, V>> below;

	public Node(K key, V value, Position<Node<K, V>> below) {
		super(key, value);
		this.below = below;
	}

	public Position<Node<K, V>> getBelow() {
		return this.below;
	}

	public String toString() {
		String belowString = getBelow() == null ? "null" : "...";
		return "<" + nullToString(getKey()) + "," + nullToString(getValue()) + "," + belowString + ">";
	}

	private String nullToString(Object obj) {
		if (obj == null)
			return "null";
		else
			return obj.toString();
	}
}
