/**
 * @author Samantha L. Misurda
 * Compressor.java
 * Test program for compress
 */

import java.io.*;

public class Compressor {
	public static void main(String [] args){
 		if(args.length != 2) {
 			System.out.println("Error, invalid number of arguments");
 			return;
 		}

 		doCompression(args[0], args[1]);
 	}

 	/**
 	 * Helper function for performing compression
 	 * @param infileName The input file
 	 * @param outfileName The output file
 	 */
 	public static void doCompression(String infileName, String outfileName) {
 	    try {
 	    	DataInputStream in = new DataInputStream( 
        		new BufferedInputStream(new FileInputStream(infileName) )); 
    
		    DataOutputStream out = new DataOutputStream( 
		        	new BufferedOutputStream(new FileOutputStream(outfileName) )); 

		    byte byteIn; 
		    ByteOutputBuffer buffer = new ByteOutputBuffer();
		    try { 
				while(true) {
					byteIn = in.readByte();
					//Do LZW, get a CW and replace byteIn with CW in the line below
					buffer.insertCodeword(byteIn, out);
				} 
			} catch(EOFException e) { 
				in.close(); 
				if(buffer.getCount()%2 == 1) {
					buffer.flushBuffer(out);
				}
				out.close(); 
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
 	}
}

