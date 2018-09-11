/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 1
 * SingleLinkedList.java

 * This class implements a singly linked list

 */	
public class SingleLinkedList
{
	private SingleNode head;
	private SingleNode tail;

	public SingleLinkedList()
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
	public void addCharAtEnd(char c)
	{
		if(this.isEmpty()) // Special case, empty list
		{
			SingleNode newNode = new SingleNode();
			newNode.setC(c);
			head = newNode;
			tail = newNode;
		}
		else
		{	
			SingleNode newNode = new SingleNode(null, c); 
			tail.setNext(newNode);
			tail = newNode;
		}		
	}

	/**
	 * Returns the front of the list
	 * @return  returns the SingleNode at the front of the list
	 */
	public SingleNode getHead()
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
			SingleNode currNode = head;
			while(currNode != null)
			{
				currNode = currNode.getNext();
				count++;
			}
		}	
		return count;
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

		SingleNode currNode = head;
		String list = "";
		while(currNode != null)
		{
			list = list + currNode.getC();
			currNode = currNode.getNext();
		}
		return list;
	}

	/**
	 * A small driver program to test basic functionality 
	 * @param args String array of commandline arguments
	 */
	public static void main(String [] args)
	{
		SingleLinkedList list = new SingleLinkedList();
		list.addCharAtEnd('d');
		list.addCharAtEnd('e');
		System.out.println(list);
	}
}	