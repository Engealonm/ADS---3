public class MyHashTable<K , V> {
    private static final int DEFAULT_CAPACITY = 10;


    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }

    }

    private HashNode<K, V>[] table;
    private int size;
    private int capacity;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        table = (HashNode<K, V>[]) new HashNode[capacity];
    }
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity);
    }
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            HashNode<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update value if key already exists
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = table[index];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null; // Key not found
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : table) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : table) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }
}
