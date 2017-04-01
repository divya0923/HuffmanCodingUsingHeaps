package com.uf.heap;

/**
 * @author dmahendran
 * Bean to pack the data and frequency of heap nodes
 */

public class HeapNode implements Comparable<HeapNode>{

	private int data;
	private int frequency;
	
	public HeapNode(int data, int frequency){
		this.data = data;
		this.frequency = frequency;
	}
	
	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
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
		
}
