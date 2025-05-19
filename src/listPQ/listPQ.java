package listPQ;


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
		Node max = first;
		Node i = first;
		while(i != null)
		{
			if(less(max.value, i.value)) max = i;
			i = i.next;
		}
		T item = max.value;
		max.value = first.value;
		first = first.next;
		return item;
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
