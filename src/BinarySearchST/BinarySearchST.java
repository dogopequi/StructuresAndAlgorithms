package BinarySearchST;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 8;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public BinarySearchST() {
        keys = (Key[]) new Comparable[INIT_CAPACITY];
        vals = (Value[]) new Object[INIT_CAPACITY];
    }

    // Número de pares chave-valor
    public int size() {
        return n;
    }

    // Está vazia?
    public boolean isEmpty() {
        return n == 0;
    }

    // Aumenta capacidade dos arrays
    private void resize(int capacity) {
        keys = Arrays.copyOf(keys, capacity);
        vals = Arrays.copyOf(vals, capacity);
    }

    // A chave está presente?
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        return get(key) != null;
    }

    // Busca binária: retorna posição da chave ou onde ela deveria estar
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");

        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // Busca valor associado à chave
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        if (isEmpty()) return null;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    // Insere ou atualiza par chave-valor
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Key is null");

        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val; // atualiza valor
            return;
        }

        if (n == keys.length) resize(2 * keys.length); // dobra capacidade

        // desloca para a direita
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    // Remove par chave-valor
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        if (isEmpty()) return;

        int i = rank(key);
        if (i == n || keys[i].compareTo(key) != 0) return; // chave não encontrada

        // desloca para a esquerda
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        n--;
        keys[n] = null;
        vals[n] = null;

        if (n > 0 && n == keys.length / 4) resize(keys.length / 2); // reduz capacidade se necessário
    }

    // Retorna todas as chaves
    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(keys[i]);
        }
        return list;
    }
}
