package com.uf.heap;

/**
 * @author dmahendran
 *
 */
public interface Heap {
	
	public boolean insert(HeapNode node); 
	public int removeMin();
	public boolean isEmpty();
}
