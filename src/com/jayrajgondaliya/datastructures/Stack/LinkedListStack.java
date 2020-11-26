package com.jayrajgondaliya.datastructures.Stack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Jayraj Gondaliya
 *
 * @param <T>
 * 
 *            Class represents implementation of Data Structure Stack using
 *            java.util.LinkedList
 */
public class LinkedListStack<T> implements Iterable<T> {

	private LinkedList<T> linkedList = new LinkedList<T>();

	/**
	 * Returns int representing current size of Stack;
	 * 
	 * @return int
	 */
	public int size() {
		return linkedList.size();
	}

	/**
	 * @param data
	 * 
	 *             Adds element to stack
	 */
	public void push(T data) {
		linkedList.addLast(data);
	}

	/**
	 * @return T
	 * 
	 *         Value of First element or top of stack Returns top of stack;
	 */
	public T pop() {
		if (linkedList.isEmpty())
			throw new IllegalArgumentException("Empty LinkedListStack");
		return linkedList.removeLast();
	}

	/**
	 * @return Boolean
	 * 
	 *         Returns boolean representing whether Stack is empty.
	 */
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	/**
	 * @return T
	 * 
	 *         Returns element on the top of stack without removing it from Stack.
	 */
	public T peek() {
		if (linkedList.isEmpty())
			throw new IllegalArgumentException("Empty LinkedListStack");
		return linkedList.getLast();
	}

	@Override
	public Iterator<T> iterator() {
		return linkedList.iterator();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("LinkedListStack [Size=" + linkedList.size());
		linkedList.forEach(e -> stringBuilder.append(", " + e));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
