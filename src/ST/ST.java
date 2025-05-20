package ST;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> {

    private class Node {
        public Key key;
        public Value value;
        public Node left, right;
        public int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    private Node root;
    
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();

        System.out.println("== Inserting ==");
        st.put("E", 5);
        st.put("A", 1);
        st.put("C", 3);
        st.put("B", 2);
        st.put("D", 4);
        st.put("G", 7);
        st.put("F", 6);
        st.put("H", 8);

        System.out.println("Size: " + st.size()); // 8

        System.out.println("== Getting Keys ==");
        System.out.println("Value for A: " + st.get("A")); // 1
        System.out.println("Value for G: " + st.get("G")); // 7
        System.out.println("Value for Z (not present): " + st.get("Z")); // null

        System.out.println("== Min / Max ==");
        System.out.println("Min key: " + st.min()); // A
        System.out.println("Max key: " + st.max()); // H

        System.out.println("== Floor / Ceiling ==");
        System.out.println("Floor of E: " + st.floor("E")); // E
        System.out.println("Floor of D.5: " + st.floor("D.5")); // D
        System.out.println("Ceiling of D: " + st.ceiling("D")); // D
        System.out.println("Ceiling of D.5: " + st.ceiling("D.5")); // E

        System.out.println("== Rank / Select ==");
        System.out.println("Rank of D: " + st.rank("D")); // 3
        System.out.println("Select key with rank 3: " + st.select(3)); // D

        System.out.println("== Range Queries ==");
        System.out.println("Size of range [B, F]: " + st.size("B", "F")); // 5

        System.out.println("== Keys in Range B to F ==");
        for (String key : st.keys("B", "F")) {
            System.out.println(key + " -> " + st.get(key));
        }

        System.out.println("== All Keys In Order ==");
        for (String key : st.keys()) {
            System.out.println(key + " -> " + st.get(key));
        }

        System.out.println("== Deleting ==");
        st.delete("A"); // delete min
        st.delete("H"); // delete max
        st.delete("D"); // delete internal node

        System.out.println("Keys after deletion:");
        for (String key : st.keys()) {
            System.out.println(key + " -> " + st.get(key));
        }

        System.out.println("Height of tree: " + st.height());
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.value = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return 0;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        return (t != null) ? t : x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        return (t != null) ? t : x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Key select(int k) {
        Node x = select(root, k);
        return x == null ? null : x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, q, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
        if (cmphi > 0) keys(x.right, q, lo, hi);
    }

    private class Queue<T> implements Iterable<T> {
        private int N = 0;
        private NodeQ first;
        private NodeQ last;

        private class NodeQ {
            T item;
            NodeQ next;
        }

        public void enqueue(T item) {
            NodeQ oldlast = last;
            last = new NodeQ();
            last.item = item;
            if (isEmpty()) first = last;
            else oldlast.next = last;
            N++;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public Iterator<T> iterator() {
            return new QueueIterator();
        }

        private class QueueIterator implements Iterator<T> {
            private NodeQ current = first;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T item = current.item;
                current = current.next;
                return item;
            }

            public void remove() {}
        }
    }
}
