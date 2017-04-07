package com.uf.huffman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmahendran
 * Utility class with helper methods to decode the encoded text
 */
public class DecoderUtil {
	final static Logger logger = Logger.getLogger(DecoderUtil.class.getSimpleName());
	private static DecodeTreeNode root;
	private static HashMap<String, String> codeTableMap = new HashMap<String, String>();

	/**
	 * Construct DecodeMap from the codeTable
	 * @param codeTableFile
	 */
	public static void constructDecodeMap(String codeTableFile){
		String data;
		try {		
			BufferedReader reader = new BufferedReader(new FileReader(codeTableFile));
			while ((data = reader.readLine()) != null) {
				String[] split = data.split(" ");
				codeTableMap.put(split[0], split[1]);	
			}
			reader.close();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "error occured in reading input file");
			e.printStackTrace();
		} 
	}
	
	/**
	 * Construct DecodeTree from the codeTable
	 */
	public static void constructDecodeTree () {
		Iterator<Entry<String, String>> iterator = codeTableMap.entrySet().iterator();
		root = new DecodeTreeNode("-1", null, null);
		DecodeTreeNode currRoot = root;
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String value = next.getValue();
			for (int i = 0; i < value.length() - 1; i++) {
				if (value.charAt(i) == '0') {
					if(currRoot.getLeft() == null)
						currRoot.setLeft(new DecodeTreeNode("-1", null, null)); 
					currRoot = currRoot.getLeft();
				}	
				else {
					if(currRoot.getRight() == null)
						currRoot.setRight(new DecodeTreeNode("-1", null, null));
					currRoot = currRoot.getRight();	
				}		
			}
			if((char) value.charAt(value.length() - 1) == '0') 
				currRoot.setLeft(new DecodeTreeNode(next.getKey(), null, null));
			else
				currRoot.setRight(new DecodeTreeNode(next.getKey(), null, null));
			currRoot = root;
		}
	}
	
	/**
	 * Decode the text in the encoded file and generate a decoded file
	 * @param encodedFile
	 */
	public static void generateDecodedText(String encodedFile) {
		StringBuilder decodeText = new StringBuilder();
		DecodeTreeNode currRoot = root;
		try {
			// read input encoded bin
			File ipFile = new File(encodedFile);
			FileInputStream ipStream = new FileInputStream(ipFile);
			FileOutputStream opStream = new FileOutputStream(new File("decoded.txt"));
			byte[] fileData = new byte[(int)ipFile.length()];
			ipStream.read(fileData);
			
			// convert byte to string 
			BitSet bitSet = BitSet.valueOf(fileData);
			int length = fileData.length * 8;
			for(int i= 0; i <= length; i++) {
				if(bitSet.get(i))
					decodeText.append("1");
				else
					decodeText.append("0");
			}
			// decode encodedString and write to decoded.txt
			for (int i =0; i < decodeText.length(); i++) {
				if(currRoot.getLeft() == null && currRoot.getRight() == null) {
					opStream.write(currRoot.getData().getBytes());
					opStream.write("\n".getBytes());
					currRoot = root;
				}	
				if(decodeText.charAt(i) == '0' && currRoot.getLeft() != null)
					currRoot = currRoot.getLeft();
				else if(decodeText.charAt(i) == '1' && currRoot.getRight() != null)
					currRoot = currRoot.getRight();		
			}
			
			// close streams
			ipStream.close();
			opStream.close();	
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error occured in decoding file");
			e.printStackTrace();
		}
	}

}
