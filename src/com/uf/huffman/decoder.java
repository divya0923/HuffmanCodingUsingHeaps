package com.uf.huffman;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Decoder {
	final static Logger logger = Logger.getLogger(Decoder.class.getSimpleName());
		
	public static void main (String[] args) throws Exception {		
		
		// read commandLine args
		String encodedFile = args[0];
		String codeTableFile = args[1];
		logger.log(Level.INFO, "Input files from command line: encoded file -> " + encodedFile + " codeTable -> " + codeTableFile);
		
		// construct DecodeTree from codeTable
		DecoderUtil.constructDecodeMap(codeTableFile);
		DecoderUtil.constructDecodeTree();
		logger.log(Level.INFO, "DecodeTree construction completed");
		
		// decode encoded file and generate decoded text
		DecoderUtil.generateDecodedText(encodedFile);
		logger.log(Level.INFO, "Input decoding/DecodeText generation completed");
		
	}
}
