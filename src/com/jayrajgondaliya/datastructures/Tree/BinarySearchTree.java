package com.jayrajgondaliya.datastructures.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author jayrajgondaliya
 *
 * @param <T>
 * 
 *            Class provided implementation of BinarySearchTree. - Binary search
 *            tree have time complexity for search, delete, add - O(nlogn) -
 *            which is very good. - Traversing the tree is also important - in
 *            this class 4 different types of traversal is provided. 1. Pre
 *            Order Traversal - root, left, right 2. In Order Traversal - left,
 *            root, right 3. Post Order Traversal - left, right, root 4. Level
 *            Order Traversal - this is based on level - starting with root.
 * 
 *            - Provided Public methods add(), remove(), contains(), findMin(),
 *            findMax(), height(), size(), preOrderIterator, inOrderIterator,
 *            postOrderIterator, levelOrderIterator.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	int size = 0;
	Node<T> root = null;

	private class Node<T> {
		T data;
		Node<T> left, right;

		public Node(T data, Node<T> left, Node<T> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	@Override
	public void add(T data) {
		try {
			root = add(root, data);
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		size++;
	}

	private Node<T> add(Node<T> node, T data) throws IllegalAccessException {
		Node<T> newNode = new Node<T>(data, null, null);
		if (node == null)
			return newNode;
		else if (node.data.compareTo(data) > 0) {
			node.left = add(node.left, data);
		} else if (node.data.compareTo(data) < 0) {
			node.right = add(node.right, data);
		} else {
			throw new IllegalAccessException("Duplicates are not allowed...");
		}
		return node;
	}

	@Override
	public T remove(T data) {
		if (size == 0)
			throw new IllegalArgumentException("Empty Binary Search Tree...");
		Node<T> removed = remove(root, data);
		size--;
		return removed.data;
	}

	private Node<T> remove(Node<T> node, T data) {
		if (node == null)
			return null;
		int compareValue = node.data.compareTo(data);
		if (compareValue < 0) {
			node.left = remove(node.left, data);
		} else if (compareValue > 0) {
			node.right = remove(node.right, data);
		} else {
			if (node.left == null) {
				Node<T> right = node.right;
				node.data = null;
				node = null;
				return right;
			} else if (node.right == null) {
				Node<T> left = node.left;
				node.data = null;
				node = null;
				return left;
			} else {
				Node<T> maxOfLeft = findMax(node.left);
				node.data = maxOfLeft.data;
				node.left = remove(node.left, maxOfLeft.data);
			}
		}
		return node;
	}

	private Node<T> findMin(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	private Node<T> findMax(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	@Override
	public boolean contains(T data) {
		if (size == 0)
			return false;
		return contains(root, data);
	}

	private boolean contains(Node<T> node, T data) {
		if (node == null)
			return false;
		int compareValue = node.data.compareTo(data);
		if (compareValue > 0) {
			return contains(node.left, data);
		} else if (compareValue < 0) {
			return contains(node.right, data);
		} else {
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T findMin() {
		Node<T> node = findMin(root);
		return node != null ? node.data : null;
	}

	@Override
	public T findMax() {
		Node<T> node = findMax(root);
		return node != null ? node.data : null;
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> node) {
		if (node == null)
			return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	public Iterator<T> preOrderIterator() {
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		nodeTracker.push(root);

		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return root != null && !nodeTracker.isEmpty();
			}

			@Override
			public T next() {
				Node<T> node = nodeTracker.pop();
				if (node.right != null)
					nodeTracker.push(node.right);
				if (node.left != null)
					nodeTracker.push(node.left);
				return node.data;
			}

		};
	}

	public Iterator<T> inOrderIterator() {
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		nodeTracker.push(root);
		return new Iterator<T>() {
			Node<T> traverse = root;

			@Override
			public boolean hasNext() {
				return root != null && !nodeTracker.isEmpty();
			}

			@Override
			public T next() {
				while (traverse != null && traverse.left != null) {
					nodeTracker.push(traverse.left);
					traverse = traverse.left;
				}

				Node<T> node = nodeTracker.pop();

				if (node.right != null) {
					nodeTracker.push(node.right);
					traverse = node.right;
				}
				return node.data;
			}

		};
	}

	public Iterator<T> postOrderIterator() {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> node = stack.pop();
			nodeTracker.push(node);
			if (node.left != null)
				stack.push(node.left);
			if (node.right != null)
				stack.push(node.right);
		}

		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return root != null && !nodeTracker.isEmpty();
			}

			@Override
			public T next() {
				return nodeTracker.pop().data;
			}

		};
	}

	public Iterator<T> levelOrderIterator() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.offer(root);
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return root != null && !queue.isEmpty();
			}

			@Override
			public T next() {
				Node<T> node = queue.poll();
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
				return node.data;
			}

		};
	}

}
