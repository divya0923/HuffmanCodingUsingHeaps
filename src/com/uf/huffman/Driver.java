package com.uf.huffman;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uf.heap.BinaryHeap;
import com.uf.heap.HeapNode;

/**
 * @author dmahendran
 * Driver class to test the program flow
 */
public class Driver {

	final static Logger logger = Logger.getLogger(Driver.class.getSimpleName());

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputFile = scanner.next();
		scanner.close();
		logger.log(Level.INFO, "Input file from command line : " + inputFile);
		HuffmanImplementationUtil.constructFrequencyTable("/Users/dmahendran/Documents/ADS/project/sample1/sample_input_small.txt");
	
		// test heap nodes compare 
		HeapNode node = new HeapNode(2,6);
		HeapNode node1 = new HeapNode(2,7);
		HeapNode node2 = new HeapNode(2,12);
		HeapNode node3 = new HeapNode(2,10);
		HeapNode node4 = new HeapNode(2,15);
		HeapNode node5 = new HeapNode(2,17);
		HeapNode node6 = new HeapNode(2,5);		
		System.out.println(node2.compareTo(node1));
		
		// test binaryHeap Impl
		BinaryHeap binaryHeap = new BinaryHeap();
		binaryHeap.insert(node);
		binaryHeap.insert(node1);
		binaryHeap.insert(node2);
		binaryHeap.insert(node3);
		binaryHeap.insert(node4);
		binaryHeap.insert(node5);
		binaryHeap.insert(node6);
		
		binaryHeap.printHeap();
		
		try {
			HeapNode minNode = binaryHeap.removeMin();
			System.out.println(minNode.getData() + " " + minNode.getFrequency());
			binaryHeap.printHeap();
			HeapNode minNode1 = binaryHeap.removeMin();
			System.out.println(minNode1.getData() + " " + minNode1.getFrequency());
			binaryHeap.printHeap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(binaryHeap.isEmpty());
		System.out.println(binaryHeap.heapSize());;
	}

}