package arrayList;


public class arrayList<T> {
	T[] arr;
	int first, last;
	int size = 0;
	arrayList()
	{
		arr = (T[]) new Object[2];
		first = 0;
		last = 0;
	}
	public void print()
	{
		if (isEmpty()) {
	        System.out.println("empty list");
	        return;
	    }
	    int i = first;
	    for (int j = 0; j < size; j++) {
	        System.out.println(arr[i]);
	        i = next(i);
	    }
	    System.out.println();
	}
	
	
	public void add(T item)
	{
		if(size >= arr.length)
			resize(arr.length * 2);
		arr[last] = item;
		last = next(last);
		size++;
	}
	
	public T get(int index)
	{
		if(index < 0 || index >= size) throw new IllegalStateException("index out of bounds");
		int pos = (first+index-1)%arr.length;
		return arr[pos];
	}
	
	public T remove(int index)
	{
		if(index < 0 || index >= size) throw new IllegalStateException("index out of bounds");
		int pos = (first + index) % arr.length;
		T item = arr[pos];
		if (size == 1) {
		    arr[pos] = null;
		    first = 0;
		    last = 0;
		    size = 0;
		    return item;
		}
		else if(index < size / 2)
		{
			for(int i =  pos; i != first; i = previous(i))
				arr[i] = arr[previous(i)];
			arr[first] = null;
			first = next(first);
		}
		else
		{
			for(int i =  pos; i != previous(last); i = next(i))
				arr[i] = arr[next(i)];
			last = previous(last);
			arr[last] = null;
		}
		size--;
		return item;
	}
	
	public boolean contains(T item)
	{
		
		 int i = first;
		 for (int j = 0; j < size; j++) {
		     if(arr[i] == item)
		    	 return true;
		     i = next(i);
		 }
		 return false;
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
		arrayList<Integer> a = new arrayList<>();
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
