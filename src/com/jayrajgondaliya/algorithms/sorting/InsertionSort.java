package com.jayrajgondaliya.algorithms.sorting;

public class InsertionSort {

	public static void insertionSort(int[] arr) {
		int temp;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (arr[j] > temp) {
					arr[j + 1] = arr[j];
				} else
					break;
			}
			arr[j + 1] = temp;
		}

//		for (int n : arr) {
//			System.out.print(n + ", ");
//		}
	}
}
