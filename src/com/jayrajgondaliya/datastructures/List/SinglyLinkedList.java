package com.jayrajgondaliya.datastructures.List;

import java.util.Iterator;

/**
 * @author Jayraj Gondaliya
 * 
 * @version 1.0
 *
 * @param <T>
 *
 *            This class file contains implementation of Singly Linked List, it
 *            is a list that have support for forward traversal only, - Dynamic
 *            in Size - Can have any number of elements
 * 
 * 
 */
public class SinglyLinkedList<T> implements Iterable<T> {

	/**
	 * An Integer to keep track of currently available number of elements.
	 */
	private int size = 0;
	private Node<T> head = null;

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
	 * Clear an Singly Linked List by deleting or setting all nodes to null.
	 */
	public void clear() {
		Node<T> traverse = head;
		while (traverse != null) {
			Node<T> next = traverse.next;
			traverse.next = null;
			traverse.data = null;
			traverse = next;
		}
		head = null;
		size = 0;
	}

	/**
	 * Returns an integer, describing available no of elements.
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns boolean value representing whether Singly Linked List is Empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Adds an object to Singly Linked List as a last element ot at tail.
	 * 
	 * @param Any Type
	 * 
	 */
	public void add(T t) {
		addLast(t);
	}

	public void addLast(T t) {
		Node<T> node = new Node<T>(t, null);
		Node<T> traverse = head;
		if (isEmpty()) {
			head = node;
		} else {
			while (traverse.next != null) {
				traverse = traverse.next;
			}
			traverse.next = node;
		}
		size++;
	}

	/**
	 * 
	 * Returns First element in Singly Linked List.
	 * 
	 * @return T
	 */
	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty SinglyLinkedList");
		return head.data;
	}

	/**
	 * 
	 * Removes head or first element and returns that element.
	 * 
	 * @return first Element
	 */
	public T removeFirst() {
		if (isEmpty())
			throw new RuntimeException("SinglyLinkedList is Empty, Can't remove First Element");
		Node<T> removed = head;
		head = removed.next;
		size--;
		return removed.data;
	}

	/**
	 * 
	 * Removes Last element and returns that element.
	 * 
	 * @return Last Element
	 */
	public T removeLast() {
		T data;
		if (isEmpty())
			throw new RuntimeException("SinglyLinkedList is Empty, Can't remove Last Element");
		else if (size() == 1) {
			data = head.data;
			head = null;
		} else {
			Node<T> traverse = head;
			while (traverse.next.next != null) {
				traverse = traverse.next;
			}
			data = traverse.next.data;
			traverse.next = null;
		}
		size--;
		return data;
	}

	/**
	 * 
	 * Removes element at provided index if present and returns that element.
	 * 
	 * @param index
	 * @throws IllegalArgumentException
	 * @return Element
	 */
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Index out of bound");
		T data;
		if (index == 0) {
			data = head.data;
			head = head.next;
		} else {
			Node<T> traverse = head;
			Node<T> previous = null;
			int i = 0;
			while (i != index) {
				previous = traverse;
				traverse = traverse.next;
				i++;
			}
			previous.next = traverse.next;
			data = traverse.data;
		}
		size--;
		return data;
	}

	/**
	 * Returns index of provided element, if present otherwise -1.
	 * 
	 * @param obj
	 * @return
	 */
	public int indexOf(Object obj) {
		int index = 0;
		Node<T> traverse = head;
		if (obj == null) {
			for (; traverse != null; traverse = traverse.next, index++) {
				if (traverse.data == null)
					return index;
			}
		} else {
			for (; traverse != null; traverse = traverse.next, index++) {
				if (obj.equals(traverse.data))
					return index;
			}
		}
		return -1;
	}

	/**
	 * Returns boolean, representing whether provided object is present.
	 * 
	 * @param obj
	 * @return
	 */
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	/**
	 * Remove provided object, if present and returns that element.
	 * 
	 * @param obj
	 * @return
	 */
	public boolean remove(Object obj) {
		int index = 0;
		Node<T> traverse = head;
		if (obj == null) {
			for (; traverse != null; traverse = traverse.next, index++) {
				if (traverse.data == null) {
					removeAt(index);
					return true;
				}
			}
		} else {
			for (; traverse != null; traverse = traverse.next, index++) {
				if (obj.equals(traverse.data)) {
					removeAt(index);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns iterators.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> traverse = head;

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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SinglyLinkedList [size=" + size);
		Node<T> traverse = head;
		while (traverse != null) {
			stringBuilder.append(", "+traverse.data);
			traverse = traverse.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
