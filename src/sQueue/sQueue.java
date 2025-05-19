package sQueue;


public class sQueue<T> {
	T[] arr;
	int n = 0;
	sQueue()
	{
		arr = (T[]) new Object[1];
	}
	public void print()
	{
		for(int i = 0; i < n; i++)
		{
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public void enqueue(T item)
	{
		if(n >= arr.length)
			resize(arr.length * 2);
		for(int i = n; i > 0; i--)
			arr[i] = arr[i -1];
		arr[0] = item;
		n++;
	}
	
	public T dequeue()
	{
		if(n == 0)
			throw new IllegalStateException("Queue underflow");
		T item = arr[--n];
		arr[n] = null;
		if(n >= arr.length / 4)
			resize(arr.length / 2);
		return item;
	}
	
	public boolean isEmpty()
	{
		return n == 0;
	}
	
	public int size()
	{
		return n;
	}
	
	private void resize(int size)
	{
		T[] a = (T[]) new Object[size];
		for(int i = 0; i < n; i++)
		{
			a[i] =  arr[i];
		}
		arr = a;
	}
	
	public static void main(String[] args)
	{
		sQueue a = new sQueue();
		a.enqueue(0);
		a.enqueue(4);
		a.enqueue(1);
		a.enqueue(3);
		a.print();
		a.dequeue();
		a.print();
	}
}
