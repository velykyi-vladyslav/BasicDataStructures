package velykyi.vladyslav;

/**
 * Custom implementation of a {@link java.util.HashMap} to improve knowledge of this data structure.
 * It has a simplified version of the implementation, without a load factor, additional methods, etc.
 *
 * @param <K> key
 * @param <V> value under the key
 */
public class CustomHashMap<K, V> {

    /**
     * Size in the original is 16.
     * This number is for getting more collisions while testing.
     */
    private final int size = 4;

    private final Entry<K, V>[] table;

    public CustomHashMap() {
        this.table = new Entry[size];
    }

    public void put(K key, V value) {
        /*
        Hash computing:
        - bitwise AND (&) here to get the number from 0 to size - 1
        - the table.length should be probably be multiple of 2, by doing that we are rejecting the sign bits, so
         we will have only positive number
        */
        int hash = key.hashCode() & size;
        Entry<K,V> entry = table[hash];

        if (entry == null) {
            //creating first node in the bucket if not exists
            table[hash] = new Entry<>(key, value);
        } else {
            //checking for the same key to reset the value
            while (entry.next != null) {
                if (entry.getKey() == key) {
                    entry.setValue(value);
                    return;
                }
                entry = entry.next;
            }
            //creating new node for the bucket
            entry.next = new Entry<>(key, value);
        }
    }

    public V get(K key) {
        int hash = key.hashCode() & size;
        Entry<K,V> entry = table[hash];

        if (entry == null) {
            return null;
        }

        while (entry != null) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }

            entry = entry.next;
        }

        return null;
    }

    public Entry<K, V> remove(K key) {
        int hash = key.hashCode() & size;
        Entry<K, V> entry = table[hash];

        if (entry == null) {
            return null;
        }

        // Checking the head in the bucket as it is not containing the prev node
        if (entry.getKey() == key) {
            table[hash] = entry.next;
            entry.next = null;

            return entry;
        }

        //As it is the second node - init prev node and point the current head to the next node
        Entry<K, V> prev = entry;
        entry = entry.next;

        while (entry != null) {
            if (entry.getKey() == key) {
                prev.next = entry.next;
                entry.next = null;
                return entry;
            }

            // prev now == current
            prev = entry;
            // current now = next
            entry = entry.next;
        }

        return null;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
