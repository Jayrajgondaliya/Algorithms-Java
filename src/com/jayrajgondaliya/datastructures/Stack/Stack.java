package com.jayrajgondaliya.datastructures.Stack;

import java.util.Iterator;

/**
 * 
 * @author jayrajgondaliya
 *
 *         This class represents Implementation of Stack Data Structure using
 *         custom place holders.
 *
 */
public class Stack<T> implements Iterable<T> {

	
	/**
	 * int representing Size of Stack;
	 */
	private int size = 0;
	Node<T> head = null;

	private class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			super();
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}

	}

	
	/**
	 * @param data
	 * Adds element to stack
	 */
	public void push(T data) {
		Node node = new Node<T>(data, null);
		node.next = head;
		head = node;
		size++;
	}

	/**
	 * @return T - Value of First element or top of stack
	 * Returns top of stack;
	 */
	public T pop() {
		if (size() <= 0)
			throw new IllegalArgumentException("Empty Stack...");
		T data = head.data;
		head = head.next;
		size--;
		return data;
	}

	/**
	 * Returns int representing current size of Stack;
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Stack [size=" + size);
		Node<T> traverse = head;
		while (traverse != null) {
			stringBuilder.append(", " + traverse.data);
			traverse = traverse.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> traverse = head;

			@Override
			public boolean hasNext() {
				return traverse != null;
			}

			@Override
			public T next() {
				T data = traverse.data;
				traverse = traverse.next;
				return data;
			}
		};
	}
}
