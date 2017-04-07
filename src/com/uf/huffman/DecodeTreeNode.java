package com.uf.huffman;

/**
 * @author dmahendran
 * Bean for DecodeTree Nodes
 */

public class DecodeTreeNode {
	String data;
	DecodeTreeNode left;
	DecodeTreeNode right;
	
	DecodeTreeNode(String data, DecodeTreeNode left, DecodeTreeNode right){
		this.data = data;
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
	 * @return the left
	 */
	public DecodeTreeNode getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(DecodeTreeNode left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public DecodeTreeNode getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(DecodeTreeNode right) {
		this.right = right;
	}
}
