/**
 * @author Samantha L. Misurda
 * ByteOutputBuffer.java
 * Utility class for inserting and manipulating codewords
 */

import java.io.*;

class ByteOutputBuffer {
	private byte[] buffer = new byte[3];
	private int count = 0;


	/**
	 * @param data The data to compress
	 * @param out The output stream
	 *	Pack two codewords into three bytes and write them to a file
	 *
	 *	
	 *	    [0]         [1]        [2]
	 *	+---------+-----------+----------+
	 *	| upper 8 | 1st CW  / | lower 8  |
	 *	| of first| Low  / Hi | of second|
	 *	| codeword| 4b/ 2nd CW| codeword |
	 *	+---------+-----------+----------+
  	 *
	 * @throws IOException	
	 */
 	public void insertCodeword(int data, DataOutputStream out) throws IOException {
 		if(count%2 == 0) {
 			buffer[0] = (byte)((data & 0xFF0) >>> 4);
 			buffer[1] = (byte)((data & 0xF) << 4);
 			count++;
 		}
 		else if(count%2 == 1) {
 			buffer[1] |= (byte)((data & 0xF00) >>> 8);
 			buffer[2] = (byte)(data & 0xFF);
 			count++;
 			flushBuffer(out);
 		}
 	} 

 	/**
 	 * Returns the number of elements
 	 * @return count
 	 */
 	public int getCount() {
 		return count;
 	}

 	/**
 	 * @param out The output stream
 	 * Write the codewords to the file. If there is an odd number of codewords, write out with padding
 	 * @throws IOException
 	 */
 	public void flushBuffer(DataOutputStream out) throws IOException {
 		out.writeByte(buffer[0]);
 		out.writeByte(buffer[1]); 
 		if(count%2 == 0) {
 			out.writeByte(buffer[2]); 
 		}

 		count = 0;
 	}
}