package linkedList;


public class linkedList<T> {
	class Node
	{
		Node next;
		T value;
		Node prev;
	}
	int size = 0;
	Node first;
	Node last;
	linkedList()
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
	
	
	public void add(T item)
	{
		Node l = new Node();
		l.value = item;
		l.prev = last;
		l.next = null;
		if(last != null)
			last.next = l;
		else
			first = l;
		last = l;
		size++;
	}
	
	public T get(int index)
	{
	    if (index < 0 || index >= size)
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		Node f = first;
		int i = 0;
		T item = null;
		while(f != null)
		{
			if(i == index)
			{
				item = f.value;
				break;
			}
			f = f.next;
			i++;
		}
		return item;
	}
	
	public T remove(int index)
	{
	    if (index < 0 || index >= size)
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		Node f = first;
		int i = 0;
		T item = null;
		while(f != null)
		{
			if(i == index)
			{
				item = f.value;
				break;
			}
			f = f.next;
			i++;
		}
		if(item == null)
			return item;
		Node prev = f.prev;
		Node next = f.next;
		  if (prev != null)
		        prev.next = next;
		    else
		        first = next;

		    if (next != null)
		        next.prev = prev;
		    else
		        last = prev;
		f.next = null;
		f.prev = null;
		size--;
		return item;
	}
	
	public boolean contains(T item)
	{
		Node f = first;
		while(f != null)
		{
			if(f.value.equals(item))
				return true;
			f = f.next;
		}
		return false;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}

	public static void main(String[] args)
	{
		linkedList<Integer> a = new linkedList<>();
	    a.add(0);
	    a.add(4);
	    a.add(1);
	    a.add(3);
	    a.print();
	    int b = a.get(3);
	    System.out.println(b);
	    System.out.println();
	    a.remove(0);
	    a.print();
	    if(a.contains(4))
	    	System.out.println("4 exists");
	    else
	    	System.out.println("4 doesnt exist");
	}
}

