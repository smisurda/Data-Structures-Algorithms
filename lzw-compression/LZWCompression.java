/**
 * @author Samantha L. Misurda
 * LZWCompression.java
 * Driver file for demoing the LZW Compression algorithm
 *
 * This program works for both binary and ASCII files, with the following compression ratios:
 * words.html: Compressed = 1070307, Original = 2493531
 * CrimeLatLonXY1990: Compressed = 135917, Original = 275980
 * Overview.mp4: Compressed = 25008838, Original = 33773775
 *
 * Speed is reported using the Bash time command
 * Speed with HashMap
 * words.html: real    0m1.340s, user    0m0.000s, sys     0m0.015s
 * CrimeLatLonXY1990: real    0m0.344s, user    0m0.000s, sys     0m0.015s
 * Overview.mp4: real    0m12.465s, user    0m0.000s, sys     0m0.000s
 * 
 * Speed with TreeMap
 * words.html: real    0m2.253s, user    0m0.000s, sys     0m0.015s
 * CrimeLatLonXY1990: real    0m0.577, user    0m0.000s, sys     0m0.000s
 * Overview.mp4: real    0m27.294s, user    0m0.000s, sys     0m0.015s
 *
 * In general, HashMap performs faster, as it is an O(1) insert and lookup. TreeMap will be O(lgn) in the average case.
 */

import java.util.HashMap;
import java.util.TreeMap;
import java.io.*;

public class LZWCompression 
{
 	/**
 	 * The main driver program
 	 * @param args Command line arguments
 	 * Expected args are of the form {c, d} filename filename
 	 */
 	public static void main(String [] args){
 		if(args.length != 3) {
 			System.out.println("Error, invalid number of arguments");
 			return;
 		}
 		//c shortwords.txt zippedFile.txt 
 		if(args[0].equals("c")){
 			DataInputStream in = null;
 			DataOutputStream out = null;
 			try {
 				in = new DataInputStream( 
						new BufferedInputStream(
							new FileInputStream(args[1]) )); 
			}		
 			catch(IOException ie) {
 				System.err.println("Unable to open input file " + args[1]);
 				ie.printStackTrace();
 			}
    		try {
				out = new DataOutputStream( 
		        		new BufferedOutputStream(
		        			new FileOutputStream(args[2]) )); 
			}
			catch(IOException ie) {
				System.err.println("Unable to open output file " + args[2]);
 				ie.printStackTrace();
			}
 			try {
 				LZW_Compress(in, out); 
 			}
 			catch(IOException ie) {
 				System.err.println("Failure during compression");
 				ie.printStackTrace();
 			}
 		}
 		//d zippedFile.txt unzippedFile.txt 
 		else if(args[0].equals("d")){
 			DataInputStream in = null;
 			DataOutputStream out = null;
 			try {
 				in = new DataInputStream( 
						new BufferedInputStream(
							new FileInputStream(args[1]) )); 
			}		
 			catch(IOException ie) {
 				System.err.println("Unable to open input file " + args[1]);
 				ie.printStackTrace();
 			}
    		try {
				out = new DataOutputStream( 
		        		new BufferedOutputStream(
		        			new FileOutputStream(args[2]) )); 
			}
			catch(IOException ie) {
				System.err.println("Unable to open output file " + args[2]);
 				ie.printStackTrace();
			}
 			try {
 				LZW_Decompress(in, out); 
 			}
 			catch(IOException ie) {
 				System.err.println("Failure during decompression");
 				ie.printStackTrace();
 			}
 		}
 		else{
 			System.out.println("Invalid operation flag, exiting");
 			return;
 		}
 	}

 	/**
 	 * Implementation of the LZW Compression algorithm
 	 * @param in The input stream
 	 * @param out The output stream
 	 * @throws IOException an IOException
 	 */
 	public static void LZW_Compress(DataInputStream in, DataOutputStream out) throws IOException {
	 /* enter all symbols in the table;
	  read(first character from w into string s);   
	  while(any input left) {
	    read(character c); 
	    if(s + c is in the table) {
	      s = s + c; 
	    } else { 
	      output codeword(s); 
	      Enter s + c into the table; 
	      s = c; 
	    } // end if/else 
	  } // end while 
	  output codeword(s); */

		byte byteIn; 
        HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
        ByteOutputBuffer buffer = new ByteOutputBuffer();
        String s ="";
        String temp = "";
        Integer codeWord = null;
        int i;

        for(i = 0; i < 256; i++) {
        	String key = "" + (char)i;
            symbolTable.put(key, new Integer(i));
        }

        try { 
			while(true) {
				byteIn = in.readByte();
				temp = s + (char)(0xFF & byteIn);
	            if (symbolTable.containsKey(temp)){
	            	s = s + (char)(0xFF & byteIn); 
	            } 
	            else{
					codeWord = ((Integer)symbolTable.get(s)).intValue(); 
					buffer.insertCodeword(codeWord, out);
					symbolTable.put(temp, new Integer(i++)); 
					if(i >= (1 << 12)) {
						symbolTable = new HashMap<>();
						//reset the codeword dictionary
						for(i = 0; i < 256; i++) {
							String key = "" + (char)i;
							symbolTable.put(key, new Integer(i));
						}
					}
					s = "" + (char)(0xFF & byteIn); 
	            }
				
			} 
		} catch(EOFException e) { 
			in.close(); 
			codeWord = ((Integer)symbolTable.get(s)).intValue();
			buffer.insertCodeword(codeWord, out);
			if(buffer.getCount()%2 == 1) {
				buffer.flushBuffer(out);
			}
			out.close(); 
		}
	} 

	private static int nextSymbol = 0;


	/**
	 * PErforms the LZW decompression algorithm 
	 * @param in The input data stream
	 * @param out The output data stream
	 * @throws IOException an IOException
	 */
	public static void LZW_Decompress(DataInputStream in, DataOutputStream out) throws IOException {
		/*
		enter all symbols into the table; 
		read(priorcodeword) and output its corresponding character; 
		while(codewords are still left to be input){ 
			read(codeword); 
			if(codeword not in the table) { 
			  enter string(priorcodeword) + firstChar(string(priorcodeword)) into table; 
			  output string(priorcodeword) + firstChar(string(priorcodeword)); 
			} else { 
			  enter string(priorcodeword) + firstChar(string(codeword)) into the table; 
			  output codeword; 
			} 
			priorcodeword = codeword; 
		} */
		int i;
		boolean first = true;
		int priorCodeWord = 0;
		int codeWord = 0;
		String[] symbolTable = new String[1<<12];

		for(i = 0; i < 256; i++) {
        	String value = "" + (char)i;
            symbolTable[i] = value;
        }
        nextSymbol = i;

		byte byteIn; 
		ByteInputBuffer buffer = new ByteInputBuffer();
		try { 
			while(true) {
				byteIn = in.readByte();
				buffer.insertByte(byteIn);
				if(buffer.getCount()%3 == 0) {
					if(first) {
						first = false;
						priorCodeWord = buffer.getFirstCodeword();
						String text = symbolTable[priorCodeWord];
						out.writeBytes(text);
						codeWord = buffer.getSecondCodeword();
						priorCodeWord = doDecompression(codeWord, priorCodeWord, symbolTable, out);
					}
					else {
						codeWord = buffer.getFirstCodeword();
						priorCodeWord = doDecompression(codeWord, priorCodeWord, symbolTable, out);
						codeWord = buffer.getSecondCodeword();
						priorCodeWord = doDecompression(codeWord, priorCodeWord, symbolTable, out);
					}
				}
			} 
		} catch(EOFException e) { 
			in.close(); 
			if(buffer.getCount()%3 != 0) {
				codeWord = buffer.getFirstCodeword();
				doDecompression(codeWord, priorCodeWord, symbolTable, out);
			}
			out.close(); 
		}
	}

	/**
	 * Helper function for decompression
	 * @param codeWord The int representation of the codeword
	 * @param priorCodWord The previous codeword
	 * @param symbolTable The codeword dictionary
	 * @param out The output data stream
	 * @throws IOException
	 * @return The decompressed codeword
	 */
	private static int doDecompression(int codeWord, int priorCodeWord, String[] symbolTable, DataOutputStream out) throws IOException {
		String text = symbolTable[priorCodeWord];
		if(text == null) {System.err.println("Null in decompress " + priorCodeWord + " nextSymbol: " + nextSymbol); System.exit(1);}
		if(codeWord>=nextSymbol){
			String newText = text + text.charAt(0);
			symbolTable[nextSymbol++] = newText;
			if(nextSymbol>=(1<<12)) {
		        nextSymbol = 256;
			}
			out.writeBytes(newText);
		}
		else {
			String s = symbolTable[codeWord];
			String newText = text + s.charAt(0);
			symbolTable[nextSymbol++] = newText;
			if(nextSymbol>=(1<<12)) {
		        nextSymbol = 256;
			}
			out.writeBytes(s);
		}
		return codeWord;
	}
 }