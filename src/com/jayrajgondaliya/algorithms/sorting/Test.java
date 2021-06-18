package com.jayrajgondaliya.algorithms.sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String args[]) throws IOException {
		testBubbleSort(getInputArray());
		testSelectionSort(getInputArray());
		testInsertionSort(getInputArray());
		testMergeSort(getInputArray());
	}

	private static int[] getInputArray() throws FileNotFoundException {
		File file = new File("./src/com/jayrajgondaliya/algorithms/sorting/input.txt");
		Scanner sc = new Scanner(new FileReader(file));
		int n = Integer.parseInt(sc.nextLine());
		String[] strArr = sc.nextLine().split(" ");
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(strArr[i]);
		}
		return nums;
	}

	private static void testBubbleSort(int[] nums) {
		long startTime = System.currentTimeMillis();
		BubbleSort.bubbleSort(nums);
		long endTime = System.currentTimeMillis();
		System.out.println("\nBubble Sort :: " + (endTime - startTime) + " miliseconds.");
	}

	private static void testSelectionSort(int[] nums) {
		long startTime = System.currentTimeMillis();
		SelectionSort.selectionSort(nums);
		long endTime = System.currentTimeMillis();
		System.out.println("\nSelection Sort :: " + (endTime - startTime) + " miliseconds.");
	}

	private static void testInsertionSort(int[] nums) {
		long startTime = System.currentTimeMillis();
		InsertionSort.insertionSort(nums);
		long endTime = System.currentTimeMillis();
		System.out.println("\nInsertion Sort :: " + (endTime - startTime) + " miliseconds.");
	}
	
	private static void testMergeSort(int[] nums) {
		long startTime = System.currentTimeMillis();
		MergeSort.mergeSort(nums);
		long endTime = System.currentTimeMillis();
		System.out.println("\nMerge Sort :: " + (endTime - startTime) + " miliseconds.");
	}
}
