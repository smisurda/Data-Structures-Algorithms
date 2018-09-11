/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * Knapsack.java
 * Interface for sharing common operations amongst the list and array implementations
 */

import java.math.BigInteger;

public interface Knapsack 
{
	/**
	* Returns the head of the list
	* @return the head of the list
	*/
	public BigInteger getHead();

	/**
	* Returns the next item in the list
	* @return the next item in the list
	*/
	public BigInteger getNext();

	/**
	* Adds an item to the end of a list
	* @param b - The item to add
	*/
	public void addNodeAtEnd(BigInteger b);
	
	/**
	* Reverse the list
	*/
	public void reverse();
	
	/**
	* Counts the number of items in the list
	* @return the number of items
	*/
	public int countNodes();
}

interface KnapsackFactory {
	public Knapsack createKnapsack();
}
