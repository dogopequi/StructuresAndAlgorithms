package arrayDequeue;


public class arrayDequeue<T> {
	T[] arr;
	int first, last;
	int size = 0;
	arrayDequeue()
	{
		arr = (T[]) new Object[2];
		first = 0;
		last = 0;
	}
	public void print()
	{
		if (isEmpty()) {
	        System.out.println("empty dequeue");
	        return;
	    }
	    int i = first;
	    for (int j = 0; j < size; j++) {
	        System.out.println(arr[i]);
	        i = next(i);
	    }
	    System.out.println();
	}
	
	public void pushRight(T item)
	{
		if(size >= arr.length)
			resize(arr.length * 2);
		arr[last] = item;
		last = next(last);
		size++;
	}
	public void pushLeft(T item)
	{
		if(size >= arr.length)
			resize(arr.length * 2);
		first = previous(first);
		arr[first] = item;
		size++;
	}
	
	public T popRight()
	{
		if(size == 0)
			throw new IllegalStateException("Queue underflow");
		last = previous(last);
		T item = arr[last];
		arr[last] = null;
		size--;
		if (size > 0 && size == arr.length / 4 && arr.length > 2)
		    resize(arr.length / 2);
		return item;
	}
	
	public T popLeft()
	{
		if(size == 0)
			throw new IllegalStateException("Queue underflow");
		T item = arr[first];
		arr[first] = null;
		first = next(first);
		size--;
		if (size > 0 && size == arr.length / 4 && arr.length > 2)
		    resize(arr.length / 2);
		return item;
	}
	
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	private void resize(int cap) {
	    T[] a = (T[]) new Object[cap];
	    int i = first;
	    for (int j = 0; j < size; j++) {
	        a[j] = arr[i];
	        i = next(i);
	    }
	    arr = a;
	    first = 0;
	    last = size;
	}
	
	private int next(int i)
	{
		return (i + 1) % arr.length;
	}
	private int previous(int i)
	{
		if(i > 0)
			return i - 1;
		else return arr.length - 1;
	}
	
	public static void main(String[] args)
	{
		arrayDequeue a = new arrayDequeue();
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
