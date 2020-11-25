package com.jayrajgondaliya.datastructures.List;

import java.util.Iterator;

/**
 * @author Jayraj Gondaliya
 *
 * @param <T>
 * 
 *            This class file contains implementation of Doubly Linked List, it
 *            is a list that have support for forward and backward traversal , -
 *            Dynamic in Size - Can have any number of elements
 */
public class DoublyLinkedList<T> implements Iterable<T> {

	/**
	 * An Integer to keep track of currently available number of elements.
	 */
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	private class Node<T> {
		Node<T> next, prev;
		T data;

		Node(T data, Node<T> next, Node<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return super.toString();
		}

	}

	/**
	 * Adding Element to Doubly Linked List at last.
	 * 
	 * @param T
	 */
	public void add(T t) {
		addLast(t);
	}

	/**
	 * Adding Element to Doubly Linked List at last.
	 * 
	 * @param T
	 */
	public void addLast(T t) {
		Node node = new Node(t, null, null);
		if (size == 0)
			head = tail = node;
		else {
			Node<T> traverse = head;
			while (traverse.next != null) {
				traverse = traverse.next;
			}
			traverse.next = node;
			node.prev = traverse;
			tail = node;
		}
		size++;
	}

	public int size() {
		return size;
	}

	/**
	 * Adding Element to Doubly Linked List at Beginning of List.
	 * 
	 * @param T
	 */
	public void addFirst(T t) {
		Node node = new Node(t, null, null);
		head.prev = node;
		node.next = head;
		head = node;
		size++;
	}

	/**
	 * Returns Boolean representing whether Doubly Linked List is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns data of first element or Head.
	 */
	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty Doubly Linked List");
		return head.data;
	}

	/**
	 * Returns data of Last element or Tail.
	 */
	public T peekLast() {
		if (isEmpty())
			throw new RuntimeException("Empty Doubly Linked List");
		return tail.data;
	}

	/**
	 * Returns boolean representing whether Doubly Linked List contains element.
	 */
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	/**
	 * Returns int representing index of provided object if present otherwise -1.
	 */
	public int indexOf(Object obj) {
		int index = 0;
		Node<T> traverse = head;
		if (obj == null) {
			while (traverse != null) {
				if (traverse.data == null)
					return index;
				traverse = traverse.next;
				index++;
			}
		} else {
			while (traverse != null) {
				if (obj.equals(traverse.data))
					return index;
				traverse = traverse.next;
				index++;
			}
		}
		return -1;
	}

	/**
	 * Returns data and removed that element present at provided index.
	 */
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Index Out of Bound...");
		Node<T> traverse;
		int i;
		if (index < size / 2) {
			for (i = 0, traverse = head; i != index; i++)
				traverse = traverse.next;
		} else {
			for (i = size - 1, traverse = tail; i != index; i--)
				traverse = traverse.prev;
		}
		return remove(traverse);
	}

	private T remove(Node<T> node) {
		if (node.prev == null)
			return removeFirst();
		else if (node.next == null)
			return removeLast();
		else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			T data = node.data;
			node.data = null;
			node.prev = null;
			node.next = null;
			size--;
			return data;
		}
	}

	/**
	 * Returns data of tail and removes tail or last element from Doubly Linked
	 * List.
	 */
	public T removeLast() {
		T data = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return data;
	}

	/**
	 * Returns data of head and removes head or first element from Doubly Linked
	 * List.
	 */
	public T removeFirst() {
		T data = head.data;
		head = head.next;
		head.prev = null;
		size--;
		return data;
	}

	/**
	 * Returns data of provided Object and removes it from Doubly Linked List.
	 */
	public T remove(Object obj) {
		Node<T> traverse = head;
		if (obj == null) {
			while (traverse.data != null)
				traverse = traverse.next;
		} else {
			while (!obj.equals(traverse.data))
				traverse = traverse.next;
		}
		return remove(traverse);
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
		StringBuilder stringBuilder = new StringBuilder("DoublyLinkedList [size=" + size);
		Node<T> traverse = head;
		while (traverse != null) {
			stringBuilder.append(", " + traverse.data);
			traverse = traverse.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
