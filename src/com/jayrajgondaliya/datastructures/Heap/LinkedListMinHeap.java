package com.jayrajgondaliya.datastructures.Heap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedListMinHeap<T extends Comparable<T>> implements Heap<T> {

	LinkedList<T> linkedList;

	public LinkedListMinHeap() {
		linkedList = new LinkedList<T>();
	}

	public LinkedListMinHeap(List<T> linkedList) {
		this.linkedList = new LinkedList<T>(linkedList);
		heapify();
	}

	@Override
	public Iterator<T> iterator() {
		return linkedList.iterator();
	}

	@Override
	public void heapify() {
		if (size() != 0) {
			for (int i = 0; i < size(); i++) {
				int parent = (i - 1) / 2, left = 2 * i + 1, right = 2 * i + 2;
				if (right < size() && linkedList.get(i).compareTo(linkedList.get(right)) > 0) {
					heapifyDown(i);
				}
				if (left < size() && linkedList.get(i).compareTo(linkedList.get(left)) > 0) {
					heapifyDown(i);
				}
				if (parent >= 0 && linkedList.get(i).compareTo(linkedList.get(parent)) < 0) {
					heapifyUp(i);
				}
			}
		}
	}

	private void heapifyDown(int i) {
		int left = 2 * i + 1, right = 2 * i + 2;
		if (right < size() && linkedList.get(i).compareTo(linkedList.get(right)) > 0) {
			swap(i, right);
			heapifyDown(i);
		}
		if (left < size() && linkedList.get(i).compareTo(linkedList.get(left)) > 0) {
			swap(i, left);
			heapifyDown(i);
		}
	}

	private void heapifyUp(int i) {
		int parent = (i - 1) / 2;
		if (parent >= 0 && linkedList.get(i).compareTo(linkedList.get(parent)) < 0) {
			swap(i, parent);
			heapifyUp(parent);
		}

	}

	@Override
	public void insert(T data) {
		linkedList.add(data);
		heapify();
	}

	@Override
	public boolean delete(T data) {
		boolean response = false;
		for (int i = 0; i < size() - 1; i++) {
			if (linkedList.get(i).equals(data)) {
				removeAt(i);
				response = true;
			}
		}
		return response;
	}

	@Override
	public boolean contains(T data) {
		for (T t : linkedList) {
			if (t.equals(data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public void clear() {
		linkedList.clear();
	}

	private T removeAt(int index) {
		if (index < 0 || index >= size())
			throw new IllegalArgumentException("Index out of bound");
		T data = linkedList.get(index);
		swap(index, size() - 1);
		heapify();
		return data;
	}

	@Override
	public T getRoot() {
		if (isEmpty())
			throw new NoSuchElementException("LinkedListMinHeap is Empty...");
		return linkedList.getFirst();
	}

	@Override
	public T extractRoot() {
//		if (size() == 0)
//			throw new NoSuchElementException("Empty LinkedListMinHeap");
		swap(0, size() - 1);
		T data = linkedList.removeLast();
		heapify();
		return data;
	}

	private void swap(int indexOne, int indexTwo) {
		T data = linkedList.get(indexOne);
		linkedList.set(indexOne, linkedList.get(indexTwo));
		linkedList.set(indexTwo, data);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("LinkedListMinHeap [size=" + size());
		linkedList.forEach(e -> stringBuilder.append(", " + e.toString()));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
