/**
 * @author Samantha L. Misurda
 * Decompressor.java
 * Test class for Decompression
 */
import java.io.*;

public class DeCompressor {
	public static void main(String [] args){
 		if(args.length != 2) {
 			System.out.println("Error, invalid number of arguments");
 			return;
 		}

 		doDeCompression(args[0], args[1]);
 	}

 	/** 
 	 * Helper function for performing decompression
 	 * @param infileName The input file
 	 * @param outfileName The output file
 	 */
 	public static void doDeCompression(String infileName, String outfileName) {
 	    try {
 	    	DataInputStream in = new DataInputStream( 
        		new BufferedInputStream(new FileInputStream(infileName) )); 
    
		    DataOutputStream out = new DataOutputStream( 
		        	new BufferedOutputStream(new FileOutputStream(outfileName) )); 

		    byte byteIn; 
		    ByteInputBuffer buffer = new ByteInputBuffer();
		    try { 
				while(true) {
					byteIn = in.readByte();
					buffer.insertByte(byteIn);
					if(buffer.getCount()%3 == 0) {
						out.writeByte(buffer.getFirstCodeword());
						out.writeByte(buffer.getSecondCodeword());
					}
				} 
			} catch(EOFException e) { 
				in.close(); 
				if(buffer.getCount()%3 != 0) {
					out.writeByte(buffer.getFirstCodeword());
				}
				out.close(); 
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
 	}
}

