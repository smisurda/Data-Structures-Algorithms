/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * ArrayKnapsack.java
 * Implementations of the Array operations for the MHK Problem
 */

import java.math.BigInteger;

public class ArrayKnapsack implements Knapsack 
{
	private BigInteger array[];

	private int cursor;
	private int size;

	public ArrayKnapsack() {
		array = new BigInteger[80*8];
	}	

	/**
	* Returns the head of the list
	* @return the head of the list
	*/
	public BigInteger getHead() {
		cursor = 0;
		if(size == 0) {
			return null;
		}
		else {
			return array[cursor++];
		}
	}

	/**
	* Returns the next item in the list
	* @return the next item in the list
	*/
	public BigInteger getNext() {
		if(cursor >= size) {
			return null;
		}
		return array[cursor++];
	}

	/**
	* Adds an item to the end of a list
	* @param b - The item to add
	*/
	public void addNodeAtEnd(BigInteger b) {
		array[size++] = b;
	}

	/**
	* Reverse the list
	*/
	public void reverse() {
		for(int i=0;i<size/2;i++) {
			BigInteger temp = array[i];
			array[i] = array[size - i - 1];
			array[size - i - 1] = temp;
		}
	}

	/**
	* Counts the number of items in the list
	* @return the number of items
	*/
	public int countNodes() {
		return size;
	}

	public String toString() {
		return array.toString();
	}

}

class ArrayKnapsackFactory implements KnapsackFactory {
	public Knapsack createKnapsack() {
		return new ArrayKnapsack();
	}
}