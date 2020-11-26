package com.jayrajgondaliya.datastructures.Queue;

import java.util.Iterator;

/**
 * @author Jayraj Gondaliya
 *
 * @param <T>
 * 
 *            Class represents implementation of Data Structure Queue
 */
public class Queue<T> implements Iterable<T> {

	/**
	 * Integer representing number of elements in Queue;
	 */
	private int size = 0;
	Node<T> head = null;
	Node<T> tail = null;

	private class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			super();
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * @return boolean
	 * 
	 *         Returns boolean representing whether Queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return T Returns data from First element.
	 */
	public T peek() {
		if (size == 0)
			throw new IllegalArgumentException("Empty Queue");
		return head.data;
	}

	/**
	 * @param data
	 * 
	 *             Adds element into Queue
	 */
	public void enque(T data) {
		Node<T> node = new Node<T>(data, null);
		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	/**
	 * @return T Remove element from top and returns value
	 */
	public T deque() {
		if (size == 0)
			throw new IllegalArgumentException("Empty Queue");
		T data = head.data;
		head = head.next;
		size--;
		return data;
	}

	/**
	 * @return integer
	 * 
	 *         Returns integer, representing number of elements in Queue.
	 */
	public int size() {
		return size;
	}

	/**
	 * @param obj
	 * @return boolean Returns Boolean representing, whether it contains provided
	 *         element or not.
	 */
	public boolean contains(Object obj) {
		Node<T> traverse = head;
		if (obj == null) {
			for (traverse = head; traverse.data != null; traverse = traverse.next) {
				if (traverse.data == null)
					return true;
			}
		} else {
			for (traverse = head; traverse.data != null; traverse = traverse.next) {
				if (obj.equals(traverse.data))
					return true;
			}
		}
		return false;
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Queue [size=" + size);
		Node<T> traverse = head;
		while (traverse != null) {
			stringBuilder.append(", " + traverse.data);
			traverse = traverse.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
