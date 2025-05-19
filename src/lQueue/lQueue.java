package lQueue;

import sQueue.sQueue;

public class lQueue<T> {
	class Node
	{
		Node next;
		T value;
		Node prev;
	}
	int n = 0;
	Node first;
	Node last;
	lQueue()
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
	
	void enqueue(T item)
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
		n++;
	}
	
	T dequeue()
	{
		if(first == null) throw new IllegalStateException("Queue underflow");
		T item = first.value;
		Node oldfirst = first;
		first = first.next;
		first.prev = null;
		oldfirst.next = null;
		oldfirst.prev = null;
		n--;
		return item;
	}
	public static void main(String[] args)
	{
		lQueue a = new lQueue();
		a.enqueue(0);
		a.enqueue(4);
		a.enqueue(1);
		a.enqueue(3);
		a.print();
		a.dequeue();
		a.print();
	}
}
