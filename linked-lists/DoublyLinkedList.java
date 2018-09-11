/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 1
 * DoublyLinkedList.java

 * This class implements a doubly linked list

 */	
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
	public void addCharAtEnd(char c)
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
	 * Adds a character to the front of the list 
	 * 
	 * Preconditions: You have a list of length N
	 * Postconditions: You have a list of length N+1 with the newly inserted character at the head
	 * Runtime: O(1)
	 * @param  c  The character to add
	 */
	public void addCharAtFront(char c)
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
	 * Deletes the first occurence of the specified character
	 * 
	 * Preconditions: You have a nonempty list of length N
	 * Postconditions: You have a list of length N-1, where the first occurrence of c is removed
	 * Runtime: O(N)
	 * @param  c  The character to delete
	 * @return A true/false value indicating whether or not the delete was successful
	 */
	public boolean deleteChar(char c)
	{
		if(!isEmpty())
		{
			DoubleNode currNode = head;
			if(countNodes() == 1 && currNode.getC() == c) // Check for the special case of one node, just set it to null
			{
				currNode = null;
				head = null;
				tail = null;
				return true;
			}
			while(currNode != null)
			{
				if(currNode.getC() == c)
				{		
					if(currNode == head) // Is this the front of the list? 
					{
						head = currNode.getNext();
						head.setPrev(null);
						return true;
					}
					if(currNode == tail) // Is this the back of the list? 
					{
						tail = currNode.getPrev();
						tail.setNext(null);
						return true;
					}
					// Normal case
					DoubleNode tempNode = currNode.getPrev(); // Grab the reference to the previous node
					tempNode.setNext(currNode.getNext()); // Set the next of the previous node to be the next of the node to delete
					currNode.getNext().setPrev(tempNode);
					return true;
				}
				currNode = currNode.getNext();
			}	
		}
		return false; // Not found
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
	 * Removes a character from the end of the list 
	 * 
	 * Preconditions: You have a nonempty list of length N
	 * Postconditions: You have a list with n-1 elements
	 * Runtime: O(N) - count is linear search
	 * @return The character that was removed
	 */
	public char removeCharAtEnd()
	{
		char removedChar = '_';
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
	 * Removes a character from the front of the list 
	 * 
	 * Preconditions: You have a nonempty list of length N
	 * Postconditions: You have a list with n-1 elements
	 * Runtime: O(N) - count is linear search
	 * @return The character that was removed
	 */
	public char removeCharFromFront()
	{
		char removedChar = '_';
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
			char toAdd='_';
			
			DoublyLinkedList temp = new DoublyLinkedList();
			int numNodes = countNodes();
			for(int i=0; i<numNodes; i++)
			{
				toAdd = removeCharAtEnd();
				temp.addCharAtFront(toAdd);
			}
			for(int i=0; i<numNodes; i++)
			{
				toAdd = temp.removeCharFromFront();
				addCharAtFront(toAdd);
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
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('d');
		list.addCharAtEnd('e');
		list.addCharAtEnd('f');
		list.addCharAtFront('g');
		list.addCharAtFront('h');
		//System.out.println(list);
		list.removeCharAtEnd();
		//System.out.println(list);
		list.deleteChar('d');
		System.out.println(list);
		list.reverse();
		System.out.println(list);
	}
}	