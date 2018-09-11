/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * ListKnapsack.java
 * Implementations of the List operations for the MHK Problem
 */

import java.math.BigInteger;

public class ListKnapsack implements Knapsack 
{
	private DoublyLinkedList list = new DoublyLinkedList();
	private DoubleNode cursor = null;

	public ListKnapsack() {
		// Empty constructor
	}

	/**
	* Returns the head of the list
	* @return the head of the list
	*/
	public BigInteger getHead() {
		cursor = list.getHead();
		return list.getHead().getC();
	}

	/**
	* Returns the next item in the list
	* @return the next item in the list
	*/
	public BigInteger getNext() {
		cursor = cursor.getNext();

		if(cursor == null) {
			return null;
		}
		return cursor.getC();
	}

	/**
	* Adds an item to the end of a list
	* @param b - The item to add
	*/
	public void addNodeAtEnd(BigInteger b) {
		list.addNodeAtEnd(b);
	}

	/**
	* Reverse the list
	*/
	public void reverse() {
		list.reverse();
	}

	/**
	* Counts the number of items in the list
	* @return the number of items
	*/
	public int countNodes() {
		return list.countNodes();
	}

	public String toString() {
		return list.toString();
	}

}

class ListKnapsackFactory implements KnapsackFactory {
	public Knapsack createKnapsack() {
		return new ListKnapsack();
	}
}
