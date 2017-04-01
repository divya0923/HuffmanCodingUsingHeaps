package com.uf.huffman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author dmahendran
 * Utility class with operations to build the Huffman tree
 */
public class HuffmanImplementationUtil {
	final static Logger logger = Logger.getLogger(HuffmanImplementationUtil.class.getSimpleName());
	
	//Map to store data and its frequency in the input file
	private static Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
	
	
	/**
	 * Read from input file and construct the frequency table
	 * @param inputFile
	 */
	public static void constructFrequencyTable(String inputFile) {
		try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
			stream.forEach(data -> addToFrequencyTable(data));
		} 
		catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in reading the file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Build the frequency table 
	 * @param data
	 */
	private static void addToFrequencyTable(String data) {
		logger.log(Level.FINEST, "data to be inserted: " + data);
		
		//element does'nt exist in the map, add new key with frequency 1
		if (!frequencyMap.containsKey(data)) {
			frequencyMap.put(data, 1);
		}
		
		//element exists in the map, update the frequency
		else {
			int frequency = frequencyMap.get(data);
			frequencyMap.put(data, frequency+1);
		}
	}
		
	public void buildHuffmanTree_BinaryHeap() {
		Iterator<Entry<String, Integer>> iterator = frequencyMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Integer> next = iterator.next();
			logger.log(Level.INFO, "key: " + next.getKey() + " frequency: " + next.getValue());
		}
	}
	
	public void buildHuffmanTree_PairingHeap() {
		
	}
	
	public void buildHuffmanTree_CacheOptimizedHeap() {
		
	}

}
