package heapSorter;

public class HeapSorter {
	public static void main(String[] args)
	{
		int[] array = {2, 6, 4, 8, 3, 7, 9};
		HeapSorter.print(array);
		HeapSorter.heapify(array, array.length, array.length - 1);
		HeapSorter.print(array);
		HeapSorter.Sort(array);
		HeapSorter.print(array);
	}
	public static void heapify(int[] array, int n, int i)
	{
	    for (int j = n / 2 - 1; j >= 0; j--)
	    	sink(array, n, j);
	}
	public static void sink(int[] array, int n, int i)
	{
			int largest = i;
			int left = left(i);
			int right = right(i);
			
			if(left < n && array[left] > array[largest])
				largest = left;
			if(right < n && array[right] > array[largest])
				largest = right;
			if(largest != i)
			{
				swap(array, i, largest);
				sink(array, n, largest);
			}
	}
	
	public static void Sort(int[] array)
	{
		for(int i = array.length - 1; i >= 0; i--)
		{
			swap(array, 0, i);
			heapify(array, i, 0);
		}
	}
	
	public static int left(int n)
	{
		return n *2 + 1;
	}
	public static int right(int n)
	{
		return n *2 + 2;
	}
	public static void swap(int[] array, int a, int b)
	{
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	public static void print(int[] array)
	{
		for(int i = 0; i < array.length; i ++)
		{
			System.out.println(array[i]);
		}
		System.out.println();
	}
}
