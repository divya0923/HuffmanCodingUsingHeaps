package com.uf.heap;

/**
 * @author dmahendran
 * Bean to pack the data and frequency of heap nodes
 */

public class HeapNode implements Comparable<HeapNode>{

	private String data;
	private int frequency;
	private HeapNode left;
	private HeapNode right;
	private HeapNode previous;
	
	public HeapNode(String data, int frequency){
		this.data = data;
		this.frequency = frequency;
	}
	
	public HeapNode(String data, int frequency, HeapNode left, HeapNode right){
		this.data = data;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(HeapNode o) {
		return Integer.compare(this.frequency, o.frequency);
	} 
	
	@Override
	public String toString() {
		return "frequency -> " + this.frequency + " data -> " + this.data;
	}

	/**
	 * @return the right
	 */
	public HeapNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(HeapNode right) {
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public HeapNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(HeapNode left) {
		this.left = left;
	}

	/**
	 * @return the previous
	 */
	public HeapNode getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(HeapNode previous) {
		this.previous = previous;
	}
}
