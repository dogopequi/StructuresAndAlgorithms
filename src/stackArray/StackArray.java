package stackArray;


public class StackArray {
	int[] arr;
	int n = 0;
	StackArray()
	{
		arr = new int[1];
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
	public void push(int i)
	{
		if(n >= arr.length)
			resize(arr.length * 2);
		arr[n++] = i;
	}
	public int pop()
	{
		if(n == 0) resize(1);
		int i = arr[--n];
		if(n <= arr.length / 4)
			resize(arr.length / 2);
		return i;
	}
	
	public void resize(int size)
	{
		int[] a = new int[size];
		for(int i = 0; i < arr.length; i++)
		{
			a[i] =  arr[i];
		}
		arr = a;
	}
}
