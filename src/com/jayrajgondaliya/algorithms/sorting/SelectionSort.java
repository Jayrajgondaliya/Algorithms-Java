package com.jayrajgondaliya.algorithms.sorting;

public class SelectionSort {

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int smallest = findMin(arr, i);
			if (smallest != i)
				swap(arr, i, smallest);
		}

//		for (int n : arr) {
//			System.out.print(n + ", ");
//		}
	}

	private static int findMin(int[] arr, int start) {
		int smallest = start;
		int temp = arr[start];
		for (int i = start; i < arr.length; i++) {
			if (temp > arr[i]) {
				smallest = i;
				temp = arr[i];
			}
		}
		return smallest;
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
