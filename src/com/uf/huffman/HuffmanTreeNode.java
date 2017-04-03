package com.uf.huffman;

/**
 * @author dmahendran
 *
 */

public class HuffmanTreeNode {
	private int data;
	private int frequency;
	private HuffmanTreeNode left;
	private HuffmanTreeNode right;
	
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
	 * @return the left
	 */
	public HuffmanTreeNode getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public HuffmanTreeNode getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}
}
