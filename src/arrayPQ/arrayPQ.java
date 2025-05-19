package arrayPQ;

public class arrayPQ<T extends Comparable<T>> {
	T[] arr;
	int n = 0;
	
	arrayPQ()
	{
		arr = (T[]) new Comparable[10];
	}
	public void print()
	{
		for(int i = 0; i < n; i ++)
		{
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public void insert(T x)
	{
		int i = n++;
		for(; i > 0 && less(x, arr[i - 1]); i--)
			arr[i] = arr[i-1];
		arr[i] = x;
	}
	
	public T delete()
	{
		T max = arr[--n];
		arr[n] = null;
		return max;
	}
	
	private static boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b) < 0;
	}
	public static void main(String[] args)
	{
		arrayPQ<Integer> a = new arrayPQ();
		a.insert(0);
		a.insert(4);
		a.insert(1);
		a.insert(3);
		a.print();
		a.delete();
		a.print();
	}
}
