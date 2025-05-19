package rBagArray;

import java.util.Random;

public class rBagArray<T> {
    private T[] rq;
    private int N = 0;

    public rBagArray() {
        rq = (T[]) new Object[1];
    }

    public void enqueue(T item) {
        if (N == rq.length) resize(2 * rq.length);
        rq[N++] = item;
    }

    public void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(rq[i]);
        }
        System.out.println();
    }

    public T dequeue() {
        if (N == 0) throw new IllegalStateException("Random Bag underflow");
        Random random = new Random();
        int r = random.nextInt(N);
        T item = rq[r];
        rq[r] = rq[--N];
        rq[N] = null;
        if (N > 0 && N == rq.length / 4) resize(rq.length / 2);
        return item;
    }

    private void resize(int size) {
        T[] a = (T[]) new Object[size];
        for (int i = 0; i < N; i++) {
            a[i] = rq[i];
        }
        rq = a;
    }

    public static void main(String[] args) {
        rBagArray a = new rBagArray<>();
        a.enqueue(0);
        a.enqueue(4);
        a.enqueue(1);
        a.enqueue(3);
        a.print();
        a.dequeue();
        a.print();
    }
}
