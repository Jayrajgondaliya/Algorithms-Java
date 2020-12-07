package com.jayrajgondaliya.datastructures.Tree;

/**
 * @author jayrajgondaliya
 *
 * @param <T>
 * 
 *            Tree is Data Structure with tree like structure where root is at
 *            top. We can implement this using various base data structures. -
 *            Tree have O(log(n)) complexity for search, add, remove. - It
 *            requires Comparable objects because we need to compare objects for
 *            arranging purpose. Ex: In Integer Tree we can implement
 *            BinarySearchTree where left node will have key lesser than Parent.
 */
public interface Tree<T extends Comparable<T>> {

	public void add(T data);

	public T remove(T data);

	public boolean contains(T data);

	public boolean isEmpty();

	public int size();

	public T findMin();

	public T findMax();
}
