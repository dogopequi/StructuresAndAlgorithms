package rBagList;

import java.util.Random;


public class rBagList<T> {
	private class Node{
		T item;
		Node next;
		}
		private int N;
		private Node first;
		public void put(T item) {
			Node newNode = new Node();
			newNode.item = item;
			newNode.next = first;
			first = newNode;
			N++;
		}
		public void print()
		{
			Node f = first;
			while(f != null)
			{
				System.out.println(f.item);
				f = f.next;
			}
			System.out.println();
		}
		public T remove(){
			if (N == 0) throw new IllegalStateException("Random Bag underflow ");

		    Random random = new Random();
		    int r = random.nextInt(N);
		    T item;
		    if (r == 0) {
		        item = first.item;
		        first = first.next;
		    } 
		    else {
		        Node prev = first;
		        for (int i = 0; i < r - 1; i++) {
		            prev = prev.next;
		        }
		        Node toRemove = prev.next;
		        item = toRemove.item;
		        prev.next = toRemove.next;
		    }
		    N--;
		    return item;
		}
		
		public static void main(String[] args)
		{
			rBagList a = new rBagList();
			a.put(0);
			a.put(4);
			a.put(1);
			a.put(3);
			a.print();
			a.remove();
			a.print();
		}
}
