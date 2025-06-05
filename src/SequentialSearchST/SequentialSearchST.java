package SequentialSearchST;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;

public class SequentialSearchST<Key, Value> {
    private Node first; // primeira entrada da lista ligada
    private int n;      // número de pares chave-valor

    // classe aninhada Node
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // Construtor
    public SequentialSearchST() {
        first = null;
        n = 0;
    }

    // Retorna o número de pares chave-valor
    public int size() {
        return n;
    }

    // Verifica se está vazia
    public boolean isEmpty() {
        return size() == 0;
    }

    // Verifica se contém a chave
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        return get(key) != null;
    }

    // Retorna o valor associado à chave, ou null se não existir
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }

    // Insere ou atualiza a chave-valor
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first); // insere no início
        n++;
    }

    // Remove o par associado à chave
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        first = delete(first, key);
    }

    // Método auxiliar recursivo para delete
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    // Devolve um iterable com todas as chaves
    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }
}
