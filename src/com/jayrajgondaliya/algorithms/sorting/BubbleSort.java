package com.jayrajgondaliya.algorithms.sorting;

public class BubbleSort {

	public static void bubbleSort(int[] arr) {
		int swaps = 0;
		do {
			swaps = 0;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					swaps++;
				}
			}
			if (swaps == 0)
				break;
		} while (swaps > 0);
		
//		for(int n : arr) {
//			System.out.print(n+", ");
//		}
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	

}
