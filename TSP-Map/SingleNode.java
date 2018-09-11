/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * SingleLinkedList.java
 *
 * The class SingleNode holds one pointer and a character. It is used to represent a single node on a single linked list.
 *
 */		

public class SingleNode 
{
	private SingleNode nextNode;
	private int data;

	/**
	 * Constructs an empty SingleNode object
	 */
	public SingleNode()
	{
		nextNode = null;
		data = 0;
	}

	/**
	 * Constructs a SingleNode object 
	 * @param next a pointer to the next SingleNode
	 * @param ch the data stored in the node
	 */
	public SingleNode(SingleNode next, int ch)
	{
		nextNode = next;
		data = ch;
	}

	/**
	 * Returns the data stored in a node
	 * @return The that is stored in the node
	 */
	public int getData()
	{
		return data;
	} 
         
    /**
	 * Finds the next node in the list with respect to the current node
	 * @return The pointer to the next node in the list
	 */    
	public SingleNode getNext()
	{
		return nextNode;
	} 
	
	/**
	 * Sets the current node's character data
	 * 
	 * Preconditions: You have a node with data
	 * Postconditions: The data is returned, but the list is unchanged
	 * Runtime: O(1)
	 * @param c The data
	 */        
	public void	setData(int c) 
	{
		data = c;
	}
	           
	/**
	 * Sets the current node's next pointer 
	 * 
	 * Preconditions: You have at least one node
	 * Postconditions: The current node's next pointer is set to the specified new node
	 * Runtime: O(1)
	 * @param next The next node in the list
	 */ 
	public void	setNext(SingleNode next) 
	{
		nextNode = next;
	}
	           
	/**
	 * Prints the contents of the node in a human readable format 
	 * 
	 * Preconditions:
	 * Postconditions:
	 * Runtime:
	 * @return The contents of the node
	 */        
	public String toString() 
	{
		return ""+getData()+"";
	}

}