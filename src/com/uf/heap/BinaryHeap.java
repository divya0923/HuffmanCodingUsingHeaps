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

public class BinaryHeap implements PriorityQueue{
	
	final static Logger logger = Logger.getLogger(BinaryHeap.class.getSimpleName());
	private List<HeapNode> binaryHeap;
	
	public BinaryHeap(){
		binaryHeap = new ArrayList<HeapNode>();
	}

	@Override
	public void insert(HeapNode node) {
		binaryHeap.add(node);
		/*for(int i = (binaryHeap.size() -1)/2; i >= 0; i--)
			heapify(i);*/
		int currentIndex = binaryHeap.size() - 1;
    	int parentIndex = getParent(currentIndex);
        while (parentIndex != -1 && (binaryHeap.get(parentIndex).compareTo(binaryHeap.get(currentIndex)) == 1)) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = getParent(currentIndex);
        }
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

	//@Override
	public boolean isEmpty() {
		return binaryHeap.size() == 0;
	}

	//@Override
	public int heapSize() {
		return binaryHeap.size();
	}
	
	private int getParent(int n) {	
		return n == 0 ? -1 : (n - 1) >>> 1; 
	}
	
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
			logger.log(Level.INFO, node.toString());
		}
	}
	 
}
