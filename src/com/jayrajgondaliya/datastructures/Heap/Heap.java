package com.jayrajgondaliya.datastructures.Heap;

/**
 * @author jayrajgondaliya
 *
 * @param <T>
 * 
 *            Interface for HEAP data Structure.
 */
public interface Heap<T extends Comparable<T>> extends Iterable<T> {

	/**
	 * Method used for Heapifying HEAP.
	 * 
	 * HEAPIFYING means rearranging all elements after adding or removing element
	 * 
	 */
	public void heapify();

	/**
	 * @param data
	 * 
	 *             Insert element into HEAP
	 * 
	 */
	public void insert(T data);

	/**
	 * @param data
	 * @return boolean
	 * 
	 *         delete element if exist and return true, or return false.
	 */
	public boolean delete(T data);

	/**
	 * @return boolean
	 * 
	 *         Method checks whether heap contains provided element and returns
	 *         boolean.
	 */
	public boolean contains(T data);

	/**
	 * @return boolean
	 * 
	 *         Method returns boolean true if heap is empty
	 */
	public boolean isEmpty();

	/**
	 * @return int Method returns int value of size of Heap
	 */
	public int size();

	/**
	 * Deletes all elements and all its references.
	 */
	public void clear();

	/**
	 * @return T
	 * 
	 *         Method returns first or root element but it does not remove element.
	 */
	public T getRoot();

	/**
	 * @return T
	 * 
	 *         Method returns and remove first or root element also it heapifies
	 *         Heap.
	 */
	public T extractRoot();
}
