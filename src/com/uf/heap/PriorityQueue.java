package com.uf.heap;

/**
 * @author dmahendran
 *
 */
public interface PriorityQueue {
	
	public void insert(HeapNode node); 
	public HeapNode removeMin() throws Exception;
	public boolean isEmpty();
	public int heapSize();
	public void printHeap();

}
