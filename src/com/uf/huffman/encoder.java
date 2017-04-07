package com.uf.huffman;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uf.heap.HeapNode;


/**
 * @author dmahendran
 * Encoder class that encodes the data in the input file to Huffman codes and generates code table
 */ 

public class Encoder {

	final static Logger logger = Logger.getLogger(Encoder.class.getSimpleName());
	
	public static void main(String[] args) throws Exception {
		
		// read commandLine args
		String inputFile = args[0];
		logger.log(Level.INFO, "Input file from commandLine: " + inputFile);
		
		// construct frequencyTable from inputFile
		Map<String, Integer> frequencyTable = EncoderUtil.constructFrequencyTable(inputFile);
		logger.log(Level.INFO, "FrequencyTable construction completed");
		
		// build HuffmanTree from inputFile using binary heap
		HeapNode root = EncoderUtil.buildHuffmanTree_BinaryHeap(frequencyTable);	
		logger.log(Level.INFO, "HuffmanTree construction completed");
		
		// construct code table
		EncoderUtil.constructCodeTable(root);
		logger.log(Level.INFO, "CodeTable construction completed");

		// encode input data
		EncoderUtil.encodeData(inputFile);
		logger.log(Level.INFO, "Input encoding completed");
		
	}

}
