package com.uf.heap;

/**
 * @author dmahendran
 * Interface for PriorityQueue Implementation
 */

public interface PriorityQueue {
	
	/**
	 * Insert a new node into the PriorityQueue
	 * @param node
	 */
	public void insert(HeapNode node); 
	
	/**
	 * Remove the min element from the PriorityQueue
	 * @return
	 * @throws Exception
	 */
	public HeapNode removeMin() throws Exception;
	
	/**
	 * Check if PriorityQueue is empty
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Size of the PriorityQueue
	 * @return
	 */
	public int heapSize();
	
	/**
	 * Print the PriorityQueue
	 */
	public void printHeap();

}
