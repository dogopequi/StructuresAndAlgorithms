package listPQ;

import java.util.NoSuchElementException;

public class listPQ<T extends Comparable<T>> {
	class Node
	{
		Node next;
		T value;
		Node prev;
	}
	Node first;
	Node last;
	listPQ()
	{
	}
	public void print()
	{
		Node f = first;
		while(f != null)
		{
			System.out.println(f.value);
			f = f.next;
		}
		System.out.println();
	}
	
	public void insert(T x)
	{
		Node oldfirst = first;
		first = new Node();
		first.value = x;
		first.next = oldfirst;
	}
	
	public T delete()
	{
	    if (first == null) throw new NoSuchElementException("Priority queue is empty");

	    Node max = first;
	    Node prevMax = null;
	    Node current = first;
	    Node prev = null;

	    while (current != null) {
	        if (less(max.value, current.value)) {
	            max = current;
	            prevMax = prev;
	        }
	        prev = current;
	        current = current.next;
	    }

	    // Remove max node
	    if (prevMax == null) {
	        // max is the first node
	        first = first.next;
	    } else {
	        prevMax.next = max.next;
	    }

	    return max.value;
	}
	
	private static boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b) < 0;
	}
	public static void main(String[] args)
	{
		listPQ<Integer> a = new listPQ();
		a.insert(0);
		a.insert(4);
		a.insert(1);
		a.insert(3);
		a.print();
		a.delete();
		a.print();
	}
}
