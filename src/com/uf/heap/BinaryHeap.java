package com.uf.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmahendran
 * BinaryHeap PriorityQueue Implementation Class
 * 
 */

public class BinaryHeap implements PriorityQueue {
	
	final static Logger logger = Logger.getLogger(BinaryHeap.class.getSimpleName());
	private List<HeapNode> binaryHeap;
	
	public BinaryHeap(){
		binaryHeap = new ArrayList<HeapNode>();
	}

	@Override
	public void insert(HeapNode node) {
		binaryHeap.add(node);
		for(int i = (binaryHeap.size() -1)/2; i >= 0; i--)
			heapify(i);
		//bottomUpHeapify(binaryHeap.size() - 1);
	}

	@Override
	public HeapNode removeMin() throws Exception {
		if(isEmpty()) throw new Exception("Heap is empty");
		HeapNode minNode = binaryHeap.get(0);
		binaryHeap.set(0, binaryHeap.get(binaryHeap.size() -1));
		binaryHeap.remove(binaryHeap.size()-1);
		heapify(0);
		return minNode;
	} 

	@Override
	public boolean isEmpty() {
		return binaryHeap.size() == 0;
	}

	@Override
	public int heapSize() {
		return binaryHeap.size();
	}
	
	/* private int getParent(int index) { 
		return index == 0 ? -1 : (index - 1) >>> 1;
	} */
	
	private int getRight(int index) {
		return index * 2 + 2;
	}
	
	private int getLeft(int index) {
		return index * 2 + 1;
	}
	  
	private void heapify(int index) {
		int minChild; 
		int left = getLeft(index);
		int right = getRight(index);
		if(left < binaryHeap.size() && binaryHeap.get(left).compareTo(binaryHeap.get(index)) <= 0) 
			minChild = left;
		else 
			minChild = index;
		if(right < binaryHeap.size() && binaryHeap.get(right).compareTo(binaryHeap.get(minChild)) <= 0) 
			minChild = right;
		if(minChild != index) {
			swap(minChild, index);
			heapify(minChild);
		}	
	}
	
	/*private void bottomUpHeapify(int index) {
		int parentIndex = getParent(index);
		while(index > 0 && binaryHeap.get(parentIndex).compareTo(binaryHeap.get(index)) >= 0) {
			swap(parentIndex, index);
			index = parentIndex;
			parentIndex = getParent(parentIndex);
		}
	}*/
		
	private void swap(int index1, int index2) {
		HeapNode temp = binaryHeap.get(index1);
		binaryHeap.set(index1, binaryHeap.get(index2));
		binaryHeap.set(index2, temp);
	}

	@Override
	public void printHeap() {
		Iterator<HeapNode> iterator = binaryHeap.iterator();
		while(iterator.hasNext()) {
			HeapNode node = iterator.next();
			logger.log(Level.INFO, "Heap Node: data -> "+ node.getData()  + " fruequency -> " + node.getFrequency());
		}
	}
	 
}
