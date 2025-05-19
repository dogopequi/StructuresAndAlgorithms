package stackArray;


public class StackArray<T> {
	T[] arr;
	int n = 0;
	StackArray()
	{
		arr = (T[]) new Object[1];
	}
	public void print()
	{
		for(int i = 0; i < n; i ++)
		{
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
		StackArray a = new StackArray();
		a.push(0);
		a.push(4);
		a.push(1);
		a.push(3);
		a.print();
		a.pop();
		a.print();
		}
	public void push(T i)
	{
		if(n >= arr.length)
			resize(arr.length * 2);
		arr[n++] = i;
	}
	public Object pop()
	{
		if (n == 0) {
            throw new IllegalStateException("Stack is empty");
        }
		T i = arr[--n];
		arr[n] = null;
		if(n <= arr.length / 4)
			resize(arr.length / 2);
		return i;
	}
	
	public void resize(int size)
	{
		T[] a = (T[]) new Object[size];
		for(int i = 0; i < n; i++)
		{
			a[i] =  arr[i];
		}
		arr = a;
	}
}
