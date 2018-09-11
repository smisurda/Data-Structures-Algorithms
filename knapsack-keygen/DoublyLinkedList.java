/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * DoublyLinkedList.java

 * This class implements a doubly linked list

 */	
import java.math.BigInteger;
public class DoublyLinkedList
{
	private DoubleNode head;
	private DoubleNode tail;

	public DoublyLinkedList()
	{
		head = null;
		tail = null;
	}

	/**
	 * Adds a character to the end of the list 
	 * 
	 * Preconditions: You have a list of length N
	 * Postconditions: You have a list of length N+1 with the newly inserted character at the tail
	 * Runtime: O(N)
	 * @param  c  The character to add
	 */
	public void addNodeAtEnd(BigInteger c)
	{
		if(this.isEmpty()) // Special case, empty list
		{
			DoubleNode newNode = new DoubleNode();
			newNode.setC(c);
			head = newNode;
			tail = newNode;
		}
		else
		{	
			DoubleNode currNode = head;
			// Iterate over the list and add it to the end
			while(currNode.getNext() != null)
			{
				currNode = currNode.getNext();
			}
			DoubleNode newNode = new DoubleNode(currNode, c, null); // Create a new DoubleNode 
			currNode.setNext(newNode);
			tail = newNode;
		}		
	}

	/**
	 * Adds a node to the front of the list 
	 * 
	 * Preconditions: You have a list of length N
	 * Postconditions: You have a list of length N+1 with the newly inserted node at the head
	 * Runtime: O(1)
	 * @param  c  The node to add
	 */
	public void addNodeAtFront(BigInteger c)
	{
		if(this.isEmpty()) // Special case, empty list
		{
			DoubleNode newNode = new DoubleNode();
			newNode.setC(c);
			head = newNode;
			tail = newNode;
		}
		else
		{	
			DoubleNode currNode = head;
			DoubleNode newNode = new DoubleNode(null, c, head); // Create a new DoubleNode 
			currNode.setPrev(newNode);
			head = newNode;
		}	
	}


	/**
	 * Counts the number of nodes in the list
	 * 
	 * Preconditions: You have a list
	 * Postconditions: The list is unmodified, and the number of elements is returned
	 * Runtime: O(N)
	 * @return  The number of nodes in the list
	 */
	public int countNodes()
	{
		int count = 0;
		if(head != null)
		{
			DoubleNode currNode = head;
			while(currNode != null)
			{
				currNode = currNode.getNext();
				count++;
			}
		}	
		return count;
	}

	/**
	 * returns the head of the node
	 * @return  A reference to the front of the list
	 */
	public DoubleNode getHead()
	{
		return head;
	}

	/**
	 * Checks if the list contains any nodes
	 * 
	 * Preconditions: You have a list constructed
	 * Postconditions: Nothing has changed
	 * Runtime: O(1)
	 * @return  A boolean value indicating whether or not the list is empty
	 */
	public boolean isEmpty()
	{
		if(head == null)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}

	/**
	 * Removes a node from the end of the list 
	 * 
	 * Preconditions: You have a nonempty list of length N
	 * Postconditions: You have a list with n-1 elements
	 * Runtime: O(N) - count is linear search
	 * @return The BigInteger that was removed
	 */
	public BigInteger removeNodeAtEnd()
	{
		BigInteger removedChar = null;
		if(isEmpty())
		{
			return removedChar;
		}
		else if(countNodes() == 1)
		{
			removedChar = head.getC();
			tail = null;
			head = null;
			return removedChar;
		}
		else
		{
			DoubleNode currNode = tail;
			removedChar = currNode.getC();
			currNode = currNode.getPrev();
			currNode.setNext(null);
			tail = currNode;
			return removedChar;
		}	
	}

	/**
	 * Removes a node from the front of the list 
	 * 
	 * Preconditions: You have a nonempty list of length N
	 * Postconditions: You have a list with n-1 elements
	 * Runtime: O(N) - count is linear search
	 * @return The BigInteger that was removed
	 */
	public BigInteger removeNodeFromFront()
	{
		BigInteger removedChar = null;
		if(isEmpty())
		{
			return removedChar;
		}
		else if(countNodes() == 1)
		{
			removedChar = head.getC();
			tail = null;
			head = null;
			return removedChar;
		}
		else
		{
			DoubleNode currNode = head;
			removedChar = currNode.getC();
			currNode = currNode.getNext();
			currNode.setPrev(null);
			head = currNode;
			return removedChar;
		}	
	}


	/**
	 * Reverses the list  
	 * 
	 * Preconditions: You have a constructed, nonempty list of length N
	 * Postconditions: You now have a list of length N whose elements are the reverse order of the starting list
	 * Runtime: O(N)
	 */
	public void reverse()
	{
		if(!isEmpty())
		{
			BigInteger toAdd=null;
			
			DoublyLinkedList temp = new DoublyLinkedList();
			int numNodes = countNodes();
			for(int i=0; i<numNodes; i++)
			{
				toAdd = removeNodeAtEnd();
				temp.addNodeAtFront(toAdd);
			}
			for(int i=0; i<numNodes; i++)
			{
				toAdd = temp.removeNodeFromFront();
				addNodeAtFront(toAdd);
			}
		}	
	}

	/**
	 * Prints the contents of the list in a human readable format 
	 * 
	 * Preconditions: You have a list
	 * Postconditions: The list is returned as a String of concatenated character values
	 * Runtime: O(N)
	 * @return The contents of the list
	 */
	public String toString()
	{
		if(isEmpty())
		{
			return "";
		}

		DoubleNode currNode = head;
		String list = "";
		while(currNode != null)
		{
			list = list + "," + currNode.getC();
			currNode = currNode.getNext();
		}
		return list;
	}
}	