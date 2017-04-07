package com.uf.huffman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uf.heap.BinaryHeap;
import com.uf.heap.FourWayCacheOptimizedHeap;
import com.uf.heap.HeapNode;

/**
 * @author dmahendran
 * Utility class with helper methods to encode the input file & generate code table
 * 
 */

public class EncoderUtil {
	final static Logger logger = Logger.getLogger(EncoderUtil.class.getSimpleName());
	
	//Map to store data and its frequency in the input file
	private static Map<String, String> codeTableMap;
	
	/**
	 * Read from input file and construct the frequency table
	 * @param inputFile
	 * @return 
	 */
	public static Map<String, Integer> constructFrequencyTable(String inputFile) {
		Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
		String data;
		try {		
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			while ((data = reader.readLine()) != null) {
				if(data.isEmpty())
					continue;
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
			reader.close();		
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error occured in reading input file");
			e.printStackTrace();
		} 
		return frequencyMap;
	} 

	/**
	 *  Build Huffman tree using Binary Heap
	 * @return
	 */
	public static HeapNode buildHuffmanTree_BinaryHeap(Map<String, Integer> frequencyTable) {
		BinaryHeap binaryHeap = new BinaryHeap();
		HeapNode root = null, tempRoot = null;		
		Iterator<Entry<String, Integer>> iterator = frequencyTable.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Integer> next = iterator.next();
			binaryHeap.insert(new HeapNode(next.getKey(), next.getValue()));
		}	
		try {
			while(!binaryHeap.isEmpty()) {
				HeapNode removeMin1 = binaryHeap.removeMin();
				HeapNode removeMin2 = binaryHeap.removeMin();
				tempRoot = new HeapNode("-1", removeMin1.getFrequency() + removeMin2.getFrequency(), removeMin1, removeMin2);
				binaryHeap.insert(tempRoot);
			}
		}
		catch(Exception e) {
			root = tempRoot;
			return root;
		}
		return null;
	}
		
	/**
	 * Build Huffman tree using FourAryHeap
	 * @return
	 */
	public static HeapNode buildHuffmanTree_CacheOptimizedHeap(Map<String, Integer> frequencyTable) {
		FourWayCacheOptimizedHeap fourAryHeap = new FourWayCacheOptimizedHeap();
		HeapNode root = null, tempRoot = null;		
		Iterator<Entry<String, Integer>> iterator = frequencyTable.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Integer> next = iterator.next();
			fourAryHeap.insert(new HeapNode(next.getKey(), next.getValue()));
		}
		try {
			while(!fourAryHeap.isEmpty()) {
				HeapNode removeMin1 = fourAryHeap.removeMin();
				HeapNode removeMin2 = fourAryHeap.removeMin();
				tempRoot = new HeapNode("-1", removeMin1.getFrequency() + removeMin2.getFrequency(), removeMin1, removeMin2);
				fourAryHeap.insert(tempRoot);
			}
		}
		catch(Exception e) {
			root = tempRoot;
			return root;
		}
		return null;
	}

	/**
	 * Construct code table by traversing root to leaf paths of the Huffman tree
	 * Generate codes and write to file 
	 * @param root
	 */
	public static void constructCodeTable (HeapNode root){
		codeTableMap = new HashMap<String, String>();
		rootToLeafPaths(root,codeTableMap, new StringBuilder());
		FileOutputStream opStream;
		try {
			opStream = new FileOutputStream(new File("code_table.txt"));
			Iterator<Entry<String, String>> iterator = codeTableMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String opText = next.getKey() + " " + next.getValue();
				opStream.write(opText.getBytes());
				opStream.write("\n".getBytes());
			}
			opStream.flush();
			opStream.close();
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception occured in creating/writing the file");
			e.printStackTrace();
		} 
	}
	
	/**
	 * Traverse tree for all paths from root to leaf 
	 * Generate code - leftChild -> 0, rightChild -> 1
	 * @param root
	 * @param str
	 * @param codeTableMap
	 */
	public static void rootToLeafPaths(HeapNode root, Map<String, String> codeTable, StringBuilder str) {
		if(root == null)
			return;	
		if(root.getRight() == null && root.getLeft() == null) {
			codeTable.put(root.getData(), str.toString());
		}
		else {
			rootToLeafPaths(root.getLeft(), codeTable, new StringBuilder(str+"0"));
			rootToLeafPaths(root.getRight(), codeTable, new StringBuilder(str+"1"));
		}
	}
	
	/**
	 * Encode data in the input file using code table and generate encoded file
	 * @param inputFile
	 */
	public static void encodeData(String inputFile) {
		StringBuilder encodedString = new StringBuilder();
		FileOutputStream opStream;
		try {
			// read from input file
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String data;
			while ((data = reader.readLine()) != null) {
				if(data.isEmpty())
					continue;
				encodedString.append(codeTableMap.get(data));
			}
			reader.close();
			// set bits based on encoded string
			BitSet bitSet = new BitSet();
			for(int i = 0; i < encodedString.length() ; i++) {
				if(encodedString.charAt(i) =='1')
					bitSet.set(i);
				else 
					bitSet.clear(i);
			}
			// write encodedString to bin file
			opStream = new FileOutputStream(new File("encoded.bin"));
			opStream.write(bitSet.toByteArray());
			opStream.flush();
			opStream.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error occured in reading input file");
			e.printStackTrace();
		}
	}
	

}
