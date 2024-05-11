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


}
