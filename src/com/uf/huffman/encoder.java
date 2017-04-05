package com.uf.huffman;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uf.heap.HeapNode;

/**
 * @author dmahendran
 * Driver class to test the program flow
 */
public class encoder {

	final static Logger logger = Logger.getLogger(encoder.class.getSimpleName());

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		String inputFile = scanner.next();
		scanner.close();
		logger.log(Level.INFO, "Input file from command line : " + inputFile);
		Map<String, Integer> constructFrequencyTable = HuffmanImplementationUtil.constructFrequencyTable(inputFile);
		
		long startTime = System.currentTimeMillis();
		HeapNode root = HuffmanImplementationUtil.buildHuffmanTree_BinaryHeap(constructFrequencyTable);	
		HuffmanImplementationUtil.constructCodeTable(root);		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime; 
	    logger.log(Level.INFO, "elapsedTime: " + elapsedTime); 
	    HuffmanImplementationUtil.encodeData(inputFile);
	    
	    /* long startTime1 = System.currentTimeMillis();
		HeapNode root1 = HuffmanImplementationUtil.buildHuffmanTree_CacheOptimizedHeap(constructFrequencyTable);
		HuffmanImplementationUtil.constructCodeTable(root1);	
		long stopTime1 = System.currentTimeMillis();
		long elapsedTime1 = stopTime1 - startTime1; 
	    logger.log(Level.INFO, "elapsedTime: " + elapsedTime1);  */
		  
	    

	}

}
