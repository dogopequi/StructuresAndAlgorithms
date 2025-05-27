package LinearProbingHashST;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }


        keys[i] = null;
        values[i] = null;


        i = (i + 1) % m;
        while (keys[i] != null) {
            Key rehashKey = keys[i];
            Value rehashValue = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(rehashKey, rehashValue);
            i = (i + 1) % m;
        }

        n--;


        if (n > 0 && n <= m / 8) resize(m / 2);
    }
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

        System.out.println("Adding keys A, B, C, D");
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D", 4);

        System.out.println("Current keys and values:");
        for (String key : st.keys()) {
            System.out.println(key + " → " + st.get(key));
        }

        System.out.println("\nUpdating B to 22");
        st.put("B", 22);

        System.out.println("Get B: " + st.get("B"));  // should print 22

        System.out.println("\nDeleting C");
        st.delete("C");

        System.out.println("Keys after deletion:");
        for (String key : st.keys()) {
            System.out.println(key + " → " + st.get(key));
        }

        System.out.println("\nTable size: " + st.size());
        System.out.println("Contains A? " + st.contains("A"));
        System.out.println("Contains C? " + st.contains("C"));
        System.out.println("Is empty? " + st.isEmpty());

        System.out.println("\nClearing all keys...");
        st.delete("A");
        st.delete("B");
        st.delete("D");

        System.out.println("Is empty after clearing? " + st.isEmpty());
    }



    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) keyList.add(keys[i]);
        }
        return keyList;
    }
}
