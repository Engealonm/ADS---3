import java.util.LinkedList;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public void put(K key, V val) {
            root = put(root, key, val);
        }

        private Node put(Node node, K key, V val) {
            if (node == null) return new Node(key, val);
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node.left = put(node.left, key, val);
            else if (cmp > 0) node.right = put(node.right, key, val);
            else node.val = val;
            return node;
        }

        public V get(K key) {
            Node node = root;
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) node = node.left;
                else if (cmp > 0) node = node.right;
                else return node.val;
            }
            return null;
        }

        public void delete(K key) {
            root = delete(root, key);
        }

        private Node delete(Node node, K key) {
            if (node == null) return null;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node.left = delete(node.left, key);
            else if (cmp > 0) node.right = delete(node.right, key);
            else {
                if (node.right == null) return node.left;
                if (node.left == null) return node.right;
                Node temp = node;
                node = min(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
            }
            return node;
        }

        private Node min(Node node) {
            if (node.left == null) return node;
            return min(node.left);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }

        public Iterable<K> iterable() {
            Queue<K> keys = new LinkedList<>();
            inorder(root, keys);
            return keys;
        }

        private void inorder(Node node, Queue<K> keys) {
            if (node == null) return;
            inorder(node.left, keys);
            keys.add(node.key);
            inorder(node.right, keys);
        }
    }

    public void put(K key, V val) {
        if (root == null) root = new Node(key, val);
        else root.put(key, val);
    }

    public V get(K key) {
        if (root == null) return null;
        return root.get(key);
    }

    public void delete(K key) {
        if (root == null) return;
        root.delete(key);
    }

    public Iterable<K> iterable() {
        if (root == null) return null;
        return root.iterable();
    }
}