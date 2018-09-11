/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 1
 * DoubleNode.java

 * The class DoubleNode holds one pointer and a character. It is used to represent a single node on a doubly linked list.

 */		



public class DoubleNode 
{
	private DoubleNode previousNode;
	private DoubleNode nextNode;
	private char characterData;

	/**
	 * Constructs an empty DoubleNode object
	 */
	public DoubleNode()
	{
		previousNode = null;
		nextNode = null;
		characterData = ' ';
	}

	/**
	 * Constructs a DoubleNode object 
	 * @param p a pointer to the previous DoubleNode
	 * @param ch the character data stored in the node
	 * @param n a pointer to the next DoubleNode
	 */
	public DoubleNode(DoubleNode p, char ch, DoubleNode n)
	{
		previousNode = p;
		nextNode = n;
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
	public DoubleNode getNext()
	{
		return nextNode;
	} 
	     
	/**
	 * Finds the previous node in the list with respect to the current node
	 * @return The pointer to the previous node in the list
	 */        
	public DoubleNode getPrev() 
	{
		return previousNode;
	}
	           
	/**
	 * Sets the current node's character data
	 * 
	  * Preconditions: You have at least one node
	 * Postconditions: The current node's char data is set to c
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
	public void	setNext(DoubleNode next) 
	{
		nextNode = next;
	}
	   
	/**
	 * Sets the current node's previous pointer 
	 * 
	  * Preconditions: You have at least one node
	 * Postconditions: The current node's prev pointer is set to the specified new node
	 * Runtime: O(1)
	 * @param prev The previous node in the list
	 */         
	public void	setPrev(DoubleNode prev) 
	{
		previousNode = prev;
	}
	     
	/**
	 * Prints the contents of the node in a human readable format 
	 * @return The contents of the node
	 */      
	public String toString() 
	{
		return ""+getC()+"";
	}

	/**
	 * A small driver program to test basic functionality 
	 * @param args String array of commandline arguments
	 */
	public static void main(String [] args)
	{
		DoubleNode first = new DoubleNode();
		DoubleNode second = new DoubleNode(first, 'c', null);
		System.out.println(second);
	}

}