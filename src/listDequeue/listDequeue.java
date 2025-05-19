package listDequeue;


public class listDequeue<T> {
	class Node
	{
		Node next;
		T value;
		Node prev;
	}
	int size = 0;
	Node first;
	Node last;
	listDequeue()
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
	
	
	public void pushRight(T item)
	{
		Node l = new Node();
		l.value = item;
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
		size++;
	}
	public void pushLeft(T item)
	{
		Node l = new Node();
		l.value = item;
		if(first == null)
		{
			first = l; last = l;
		}
		else
		{
			first.prev = l;
			l.next = first;
			first = l;
		}
		size++;
	}
	
	public T popRight()
	{
		if(first == null) throw new IllegalStateException("Queue underflow");
		T item = last.value;
		last = last.prev;
		if (last != null)
		    last.next = null;
		else
		    first = null;
		size--;
		return item;
	}
	
	public T popLeft()
	{
		if(first == null) throw new IllegalStateException("Queue underflow");
		T item = first.value;
		first = first.next;
		if (first != null)
		    first.prev = null;
		else
		    last = null;
		size--;
		return item;
	}
	
	
	public boolean isEmpty()
	{
		return size == 0;
	}

	public static void main(String[] args)
	{
		listDequeue a = new listDequeue();
	    a.pushLeft(0);
	    a.pushLeft(4);
	    a.pushRight(1);
	    a.pushLeft(3);
	    a.print();

	    a.popLeft(); 
	    a.popRight();
	    a.print();
	}
}

