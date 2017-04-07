package com.uf.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmahendran
 * BinaryHeap PriorityQueue Implementation
 * 
 */

public class BinaryHeap implements PriorityQueue{
	
	final static Logger logger = Logger.getLogger(BinaryHeap.class.getSimpleName());
	private List<HeapNode> binaryHeap;
	
	public BinaryHeap(){
		binaryHeap = new ArrayList<HeapNode>();
	}

	/* (non-Javadoc)
	 * @see PriorityQueue#insert(HeapNode)
	 */
	@Override
	public void insert(HeapNode node) {
		binaryHeap.add(node);
		int currentIndex = binaryHeap.size() - 1;
    	int parentIndex = getParent(currentIndex);
        while (parentIndex != -1 && (binaryHeap.get(parentIndex).compareTo(binaryHeap.get(currentIndex)) == 1)) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = getParent(currentIndex);
        }
	}

	/* (non-Javadoc)
	 * @see PriorityQueue#removeMin()
	 */
	@Override
	public HeapNode removeMin() throws Exception {
		if(isEmpty()) throw new Exception("Heap is empty");
		HeapNode minNode = binaryHeap.get(0);
		binaryHeap.set(0, binaryHeap.get(binaryHeap.size() -1));
		binaryHeap.remove(binaryHeap.size()-1);
		heapify(0);
		return minNode;
	} 

	
	/* (non-Javadoc)
	 * @see PriorityQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return binaryHeap.size() == 0;
	}

	/* (non-Javadoc)
	 * @see PriorityQueue#heapSize()
	 */
	@Override
	public int heapSize() {
		return binaryHeap.size();
	}
	
	/* (non-Javadoc)
	 * @see PriorityQueue#printHeap()
	 */
	@Override
	public void printHeap() {
		Iterator<HeapNode> iterator = binaryHeap.iterator();
		while(iterator.hasNext()) {
			HeapNode node = iterator.next();
			logger.log(Level.INFO, node.toString());
		}
	}
	
	/**
	 * Return parent index of the heap node
	 * @param n
	 * @return
	 */
	private int getParent(int index) {	
		return index == 0 ? -1 : (index - 1) >>> 1; 
	}
	
	/**
	 * Return rightChild index of the heap node
	 * @param index
	 * @return
	 */
	private int getRight(int index) {
		return index * 2 + 2;
	}
	
	/**
	 * Return leftChild index of the heap node 
	 * @param index
	 * @return
	 */
	private int getLeft(int index) {
		return index * 2 + 1;
	}
	  
	/**
	 * Heapify the binaryHeap - ensure minHeap property is satisfied by all nodes in the heap
	 * If minHeap property is violated, swap the nodes 
	 * @param index
	 */
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
		
	/**
	 * Swap two heap nodes
	 * @param index1
	 * @param index2
	 */
	private void swap(int index1, int index2) {
		HeapNode temp = binaryHeap.get(index1);
		binaryHeap.set(index1, binaryHeap.get(index2));
		binaryHeap.set(index2, temp);
	}
	
}
