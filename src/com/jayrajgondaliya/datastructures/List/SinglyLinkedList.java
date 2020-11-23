package com.jayrajgondaliya.datastructures.List;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {

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

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

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

	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty SinglyLinkedList");
		return head.data;
	}

	public T removeFirst() {
		if (isEmpty())
			throw new RuntimeException("SinglyLinkedList is Empty, Can't remove First Element");
		Node<T> removed = head;
		head = removed.next;
		size--;
		return removed.data;
	}

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

	// TODO indexOf
	// TODO contains
	// TODO remove(Obj)
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

}
