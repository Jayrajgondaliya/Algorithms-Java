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
 *            AVLTree is a BST(Binary Search Tree), in that each node have at
 *            most 2 children and left is less and right is greater than root.
 *            but it has one more functionality where it self corrects it self
 *            and try to match balance factor with 1, 0 or -1.
 * 
 *            Balance Factor : Difference between height of left sub tree and
 *            height of right sub tree.
 * 
 */
public class AVLTree<T extends Comparable<T>> implements Tree<T> {

	int size = 0;
	Node<T> root = null;

	private class Node<T> {
		Node<T> left, right;
		T data;
		int height = -1, balanceFactor;

		public Node(T data, Node<T> left, Node<T> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	@Override
	public void add(T data) {
		root = add(root, data);
		size++;
	}

	private Node<T> add(Node<T> root, T data) {
		if (root == null) {
			return new Node<T>(data, null, null);
		} else {
			int compareValue = root.data.compareTo(data);
			if (compareValue > 0) {
				root.left = add(root.left, data);
			} else if (compareValue < 0) {
				root.right = add(root.right, data);
			} else {
				throw new IllegalArgumentException("Duplicates are not allowed...");
			}
		}
		update(root);
		return balance(root);
	}

	private void update(Node<T> node) {
		int leftSubTreeHeight = node.left == null ? -1 : node.left.height;
		int rightSubTreeHeight = node.right == null ? -1 : node.right.height;
		node.height = 1 + Math.max(leftSubTreeHeight, rightSubTreeHeight);
		node.balanceFactor = rightSubTreeHeight - leftSubTreeHeight;
	}

	private Node<T> balance(Node<T> node) {
		if (node.balanceFactor == -2) {
			if (node.left.balanceFactor <= 0) {
				return leftLeftCase(node);
			} else {
				return leftRightCase(node);
			}
		} else if (node.balanceFactor == +2) {
			if (node.right.balanceFactor >= 0) {
				return rightRightCase(node);
			} else {
				return rightLeftCase(node);
			}
		}
		return node;
	}

	private Node<T> leftLeftCase(Node<T> node) {
		return rotateRight(node);
	}

	private Node<T> leftRightCase(Node<T> node) {
		node.left = rotateLeft(node);
		return rotateRight(node);
	}

	private Node<T> rightRightCase(Node<T> node) {
		return rotateLeft(node);
	}

	private Node<T> rightLeftCase(Node<T> node) {
		node.right = rotateRight(node.right);
		return rotateLeft(node);
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> newNode = node.left;
		node.left = newNode.right;
		newNode.right = node;
		return newNode;
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> newNode = node.right;
		node.right = newNode.left;
		newNode.left = node;
		return newNode;
	}

	@Override
	public T remove(T data) {
		root = remove(root, data);
		return data;
	}

	private Node<T> remove(Node<T> node, T data) {
		if (size() == 0)
			throw new IllegalArgumentException("AVLTree is empty...");
		if (node == null)
			return null;
		int compareValue = node.data.compareTo(data);
		if (compareValue < 0) {
			node.left = remove(node.left, data);
		} else if (compareValue > 0) {
			node.right = remove(node.right, data);
		} else {
			if (node.left == null) {
				Node<T> rightNode = node.right;
				node.data = null;
				node = null;
				return rightNode;
			} else if (node.right == null) {
				Node<T> leftNode = node.left;
				node.data = null;
				node = null;
				return leftNode;
			} else {
				Node<T> leftMax = findMax(node.left);
				node.data = leftMax.data;
				node.left = remove(leftMax, leftMax.data);
			}
		}
		return node;
	}

	private Node<T> findMax(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	private Node<T> findMin(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	@Override
	public boolean contains(T data) {
		Node<T> node = contains(root, data);
		return node != null;
	}

	private Node<T> contains(Node<T> node, T data) {
		if (node == null)
			return node;
		int compareValue = node.data.compareTo(data);
		if (compareValue < 0) {
			return contains(node.left, data);
		} else if (compareValue > 0) {
			return contains(node.right, data);
		} else {
			return node;
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
		Node<T> minNode = findMin(root);
		return minNode == null ? null : minNode.data;
	}

	@Override
	public T findMax() {
		Node<T> maxNode = findMax(root);
		return maxNode == null ? null : maxNode.data;
	}

	public Iterator<T> preOrderIterator() {
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		nodeTracker.add(root);
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return root != null && nodeTracker.size() != 0;
			}

			@Override
			public T next() {
				Node<T> currentNode = nodeTracker.pop();
				if (currentNode.right != null)
					nodeTracker.add(currentNode.right);
				if (currentNode.left != null)
					nodeTracker.add(currentNode.left);
				return currentNode.data;
			}
		};
	}

	public Iterator<T> postOrderIterator() {
		Stack<Node<T>> stack = new Stack<>();
		stack.push(root);
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		while (!stack.isEmpty()) {
			Node<T> node = stack.pop();
			nodeTracker.add(node);
			if (node.left != null)
				stack.add(node.left);
			if (node.right != null)
				stack.add(node.right);
		}

		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return root != null && nodeTracker.size() != 0;
			}

			@Override
			public T next() {
				return nodeTracker.pop().data;
			}
		};
	}

	public Iterator<T> inOrderIterator() {
		Stack<Node<T>> nodeTracker = new Stack<Node<T>>();
		nodeTracker.add(root);

		return new Iterator<T>() {
			Node<T> traverse = root;

			@Override
			public boolean hasNext() {
				return root != null && !nodeTracker.isEmpty();
			}

			@Override
			public T next() {
				while (traverse != null && traverse.left != null) {
					nodeTracker.add(traverse.left);
					traverse = traverse.left;
				}

				Node<T> node = nodeTracker.pop();

				if (node.right != null) {
					nodeTracker.add(node.right);
					traverse = node.right;
				}
				return node.data;
			}
		};
	}

	public Iterator<T> levelOrderIterator() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);

		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return root != null & !queue.isEmpty();
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
