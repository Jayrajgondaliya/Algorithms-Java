package com.jayrajgondaliya.datastructures.Heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author jayrajgondaliya
 *
 * @param <T>
 * 
 * Implementation of MaxHeap Using Array.
 * 	- Default LoadFactor is 0.75
 *  - Default capacity is 10 
 * 	- Finding Maximum value time complexity - O(1).
 *  - Search Time Complexity - O(n).
 *  - delete TIme complexity - O(n).
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

	private int size = 0, capacity = 10;
	private double loadFactor = 0.75;
	protected T[] array;

	public MaxHeap() {
		this.array = (T[]) new Comparable[capacity];
	}

	public MaxHeap(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Initial Capacity can not be negative");
		this.array = (T[]) new Comparable[initialCapacity];
	}

	public MaxHeap(int initialCapacity, double loadFactor) {
		if (initialCapacity < 0 || loadFactor < 0)
			throw new IllegalArgumentException("Initial Capacity or loadFactor can not be negative");
		this.array = (T[]) new Comparable[initialCapacity];
		this.loadFactor = loadFactor;
	}

	public MaxHeap(T[] array) {
		super();
		this.array = array;
		size = array.length;
		heapify();
		resize();
	}

	private void resize() {
		if ((size * 1.0d / capacity) >= loadFactor) {
			capacity = 2 * capacity;
			array = Arrays.copyOf(array, capacity);
		} else if ((size * 1.0d / capacity) < 0.25) {
			capacity = (capacity / 2 < 10) ? 10 : capacity / 2;
			array = Arrays.copyOf(array, capacity);
		}
	}

	@Override
	public void heapify() {
		for (int i = size - 1; i >= 0; i--) {
			int parent = (i - 1) / 2, left = (2 * i + 1), right = (2 * i + 2);
			if (parent >= 0 && array[i].compareTo(array[parent]) > 0) {
				heapifyUp(i);
			}
			if (right < size && array[i].compareTo(array[right]) < 0) {
				heapifyDown(i);
			}
			if (left < size && array[i].compareTo(array[left]) < 0) {
				heapifyDown(i);
			}
		}
	}

	private void heapifyDown(int index) {
		int left = (2 * index + 1), right = (2 * index + 2);
		if (right < size && array[index].compareTo(array[right]) < 0) {
			swap(index, right);
			heapifyUp(right);
		}
		if (left < size && array[index].compareTo(array[left]) < 0) {
			swap(index, left);
			heapifyUp(left);
		}
	}

	private void heapifyUp(int index) {
		int parent = (index - 1) / 2;
		if (parent >= 0 && array[index].compareTo(array[parent]) > 0) {
			swap(index, (index - 1) / 2);
			heapifyUp((index - 1) / 2);
		}
	}

	private void swap(int indexOne, int indexSecond) {
		T data = array[indexOne];
		array[indexOne] = array[indexSecond];
		array[indexSecond] = data;
	}

	@Override
	public void insert(T data) {
		array[size] = data;
		size++;
		heapify();
		resize();
	}

	@Override
	public boolean delete(T data) {
		int i = 0;
		boolean response = false;
		for (; i < size; i++) {
			if (array[i].equals(data)) {
				removeAt(i);
				response = true;
				break;
			}
		}
		return response;
	}

	private T removeAt(int index) {
		swap(index, size - 1);
		T data = array[size - 1];
		array[size - 1] = null;
		size--;
		heapify();
		resize();
		return data;
	}

	@Override
	public boolean contains(T data) {
		boolean response = false;
		for (int i = 0; i < size; i++) {
			if (array[i].equals(data)) {
				response = true;
				break;
			}
		}
		return response;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		capacity = 10;
		array = (T[]) new Comparable[capacity];
	}

	@Override
	public T getRoot() {
		if (size == 0)
			throw new NoSuchElementException("MaxHeap is Empty...");
		return array[0];
	}

	@Override
	public T extractRoot() {
		if (size == 0)
			throw new NoSuchElementException("MaxHeap is Empty...");
		return removeAt(0);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				if (size == 0)
					return false;
				return i < size;
			}

			@Override
			public T next() {
				if (size == 0)
					throw new NoSuchElementException("MaxHeap is Empty...");
				T data = array[i];
				i++;
				return data;
			}

		};
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("MaxHeap [size=" + size);
		for (int i = 0; i < size; i++) {
			stringBuilder.append(", " + array[i].toString());
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
