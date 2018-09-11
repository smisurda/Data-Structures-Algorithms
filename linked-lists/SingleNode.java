/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 1
 * SingleLinkedList.java

 * The class SingleNode holds one pointer and a character. It is used to represent a single node on a single linked list.

 */		

public class SingleNode 
{
	private SingleNode nextNode;
	private char characterData;

	/**
	 * Constructs an empty SingleNode object
	 */
	public SingleNode()
	{
		nextNode = null;
		characterData = ' ';
	}

	/**
	 * Constructs a SingleNode object 
	 * @param next a pointer to the next SingleNode
	 * @param ch the character data stored in the node
	 */
	public SingleNode(SingleNode next, char ch)
	{
		nextNode = next;
		characterData = ch;
	}

	/**
	 * Returns the character data stored in a node
	 * @return The character that is stored in the node
	 */
	public char getC()
	{
		return characterData;
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
	 * Preconditions: You have a node with character data
	 * Postconditions: The character data is returned, but the list is unchanged
	 * Runtime: O(1)
	 * @param c The character data
	 */        
	public void	setC(char c) 
	{
		characterData = c;
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
		return ""+getC()+"";
	}

	/**
	 * A small driver program to test basic functionality 
	 * 
	 * Preconditions:
	 * Postconditions:
	 * Runtime:
	 * @param args String array of commandline arguments
	 */
	public static void main(String [] args)
	{
		SingleNode second = new SingleNode(null, 'b');
		SingleNode first = new SingleNode(second, 'c');
		System.out.println(first);
	}

}