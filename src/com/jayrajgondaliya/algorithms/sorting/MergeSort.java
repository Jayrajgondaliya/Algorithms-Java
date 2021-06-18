package com.jayrajgondaliya.algorithms.sorting;

public class MergeSort {
	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
//		for (int n : arr) {
//			System.out.print(n + ", ");
//		}
	}

	private static void mergeSort(int[] arr, int start, int end, int[] temp) {
		if (start >= end) {
			return;
		}

		int leftEnd = (start + end) / 2;
		int rightStart = leftEnd + 1;

		mergeSort(arr, start, leftEnd, temp);
		mergeSort(arr, rightStart, end, temp);
		merge(arr, start, leftEnd, rightStart, end, temp);
		for (int x = start; x <= end; x++) {
			arr[x] = temp[x];
		}
	}

	private static void merge(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] temp) {
		int current = leftStart;
		//System.out.println(leftStart+" : "+leftEnd+" : "+rightStart+" : "+rightEnd);
		while (current <= rightEnd) {
			if (leftStart <= leftEnd && (rightStart > rightEnd || arr[leftStart] < arr[rightStart])) {
				temp[current] = arr[leftStart];
				leftStart++;
			} else if (rightStart <= rightEnd && (leftStart > leftEnd || arr[leftStart] >= arr[rightStart])) {
				temp[current] = arr[rightStart];
				rightStart++;
			}
			current++;
		}
	}

}
