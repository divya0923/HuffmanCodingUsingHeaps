package com.uf.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmahendran
 *
 */
public class BinaryHeap implements Heap {
	
	int heapSize;
	List<HeapNode> binaryHeap;
	
	public BinaryHeap(){
		heapSize = 0;
		binaryHeap = new ArrayList<HeapNode>();
	}

	@Override
	public boolean insert(HeapNode node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return heapSize == 0;
	}
	  
}
