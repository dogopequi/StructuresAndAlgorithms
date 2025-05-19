package listArray;

public class ListArray<T> {
	class Node
	{
		Node next;
		T value;
		Node prev;
	}
	int n = 0;
	Node first;
	Node last;
	ListArray()
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
	void push(T value)
	{
		Node l = new Node();
		l.value = value;
		if(first == null)
		{
			first = l; last = l;
		}
		else
		{
			last.next = l;
			l.prev = last;
			last = l;
		}
		n++;
	}
	T pop()
	{
		if(first == null) throw new IllegalStateException("Empty stack.");
		T value = last.value;
		last.value = null;
		if(last.prev == null)
		{
			first = null;
			last = null;
		}
		else
		{
			last = last.prev;
			last.next = null;
		}
		n--;
		return value;
	}
	public static void main(String[] args)
	{
		ListArray la = new ListArray();
		la.push(0);
		la.push(2);
		la.push(4);
		la.print();
		la.pop();
		la.print();
	}
}
