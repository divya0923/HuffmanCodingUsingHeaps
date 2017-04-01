package com.uf.huffman;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmahendran
 * Driver class to test the program flow
 */
public class CodeDriver {

	final static Logger logger = Logger.getLogger(CodeDriver.class.getSimpleName());

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputFile = scanner.next();
		scanner.close();
		logger.log(Level.INFO, "Input file from command line : " + inputFile);
		HuffmanImplementationUtil.constructFrequencyTable("/Users/dmahendran/Documents/ADS/project/sample1/sample_input_small.txt");
	}

}
