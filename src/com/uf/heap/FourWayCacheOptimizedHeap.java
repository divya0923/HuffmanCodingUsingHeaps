package com.uf.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmahendran
 *
 */
public class FourWayCacheOptimizedHeap implements PriorityQueue {
	
	final static Logger logger = Logger.getLogger(FourWayCacheOptimizedHeap.class.getSimpleName());
	private List<HeapNode> fourAryHeap;
	
	public FourWayCacheOptimizedHeap() {
		fourAryHeap = new ArrayList<HeapNode>();
		fourAryHeap.add(null);
		fourAryHeap.add(null);
		fourAryHeap.add(null);
	}

	@Override
	public void insert(HeapNode node) {
		fourAryHeap.add(node);
		for(int i = (fourAryHeap.size() -1)/4; i >= 0; i--) 
			heapify(i+3);	
	}

	@Override
	public HeapNode removeMin() throws Exception {
		if(isEmpty()) throw new Exception("Heap is empty");
		HeapNode minNode = fourAryHeap.get(3);
		fourAryHeap.set(3, fourAryHeap.get(fourAryHeap.size() -1));
		fourAryHeap.remove(fourAryHeap.size()-1);
		heapify(3);
		return minNode;
	}

	@Override
	public boolean isEmpty() {
		return fourAryHeap.size() == 3;
	}

	@Override
	public int heapSize() {
		return fourAryHeap.size() - 3;
	}

	@Override
	public void printHeap() {
		Iterator<HeapNode> iterator = fourAryHeap.iterator();
		while(iterator.hasNext()) {
			HeapNode node = iterator.next();
			if(node != null)
				logger.log(Level.INFO, "Heap Node: data -> "+ node.getData()  + " fruequency -> " + node.getFrequency());
		}	
	}
	
	private int getKthChild(int index, int k) {
		//if(index < 3) return -1; // 4*(n-2) + (0 - 3)
		//index = index - 3;
		return 4 * (index -2) + k;
	}

	private void heapify(int index) {
		int minChild = index, kthChild;
		for (int i = 0; i<= 3; i++) {		
			kthChild = getKthChild(index, i);
			if(kthChild < fourAryHeap.size() && fourAryHeap.get(kthChild).compareTo(fourAryHeap.get(index)) <= 0 && fourAryHeap.get(kthChild).compareTo(fourAryHeap.get(minChild)) <= 0) {
				minChild = kthChild;
			}			
		}
		if(minChild != index) {
			swap(minChild, index);
			heapify(minChild);
		} 	
	}
	
	private void swap(int index1, int index2) {
		HeapNode temp = fourAryHeap.get(index1);
		fourAryHeap.set(index1, fourAryHeap.get(index2));
		fourAryHeap.set(index2, temp);
	}

}
