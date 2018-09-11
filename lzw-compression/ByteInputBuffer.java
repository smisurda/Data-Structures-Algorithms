/**
 * @author Samantha L. Misurda
 * ByteInputBuffer.java
 * Utility class for retrieving and manipulating codewords
 */

import java.io.*;

class ByteInputBuffer {
	private byte[] buffer = new byte[3];
	private int count = 0;

	/**
	 * Insert a byte 
	 * @param data The data to insert
	 */
	public void insertByte(byte data) {
		buffer[count%3] = data;
		count++;
	}

	/**
	 * Returns the first codeword entry
	 * @return the first codeword
	 */
 	public int getFirstCodeword() {
 		return 0xFFF & (0xFF0 & (buffer[0] << 4) | ((buffer[1] & 0xF0) >>> 4));
 	} 

 	/**
	 * Returns the second codeword entry
	 * @return the second codeword
	 */
 	public int getSecondCodeword() {
 		return 0xFFF & (((buffer[1] & 0xF) << 8) | (0xFF & buffer[2]));
 	}

 	/**
	 * Returns the number of codewords
	 * @return number of codewords
	 */
 	public int getCount() {
 		return count;
 	}
}