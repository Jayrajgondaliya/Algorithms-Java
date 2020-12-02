package com.jayrajgondaliya.datastructures.Queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author jayrajgondaliya
 *
 *
 *         Class represents implementation of Data Structure Queue using
 *         java.util.LinkedList
 * 
 * 
 * @param <T>
 */
public class LinkedListQueue<T> implements Iterable<T> {

	/**
	 * LinkedList as a base data structure.
	 */
	LinkedList<T> linkedList = new LinkedList<T>();

	/**
	 * @param data
	 * 
	 *             Adds element into Queue
	 */
	public void enque(T data) {
		linkedList.addLast(data);
	}

	/**
	 * @return T Remove element from top and returns value
	 */
	public T deque() {
		return linkedList.removeFirst();
	}

	/**
	 * @return integer
	 * 
	 *         Returns integer, representing number of elements in Queue.
	 */
	public int size() {
		return linkedList.size();
	}

	/**
	 * @return T Returns data from First element.
	 */
	public T peek() {
		if (size() == 0)
			throw new NoSuchElementException("Empty LinkedListQueue...");
		return linkedList.getFirst();
	}

	/**
	 * @return boolean
	 * 
	 *         Returns boolean representing whether Queue is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * @param obj
	 * @return boolean Returns Boolean representing, whether it contains provided
	 *         element or not.
	 */
	public boolean contains(T data) {
		return linkedList.contains(data);
	}

	@Override
	public Iterator<T> iterator() {
		return linkedList.iterator();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("LinkedListQueue [Size=" + linkedList.size());
		linkedList.forEach(e -> stringBuilder.append(", " + e));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
