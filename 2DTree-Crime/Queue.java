/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 3
 * Queue.java
 * Implementation of an array based queue structure
 */	

public class Queue
{
	private int headIndex;
	private int rearIndex;
	private int maxQueueSize;
	TwoDTreeNode [] q;

	/**
	* Constructor for a Queue
	* @param size The size of the Queue
	*/ 
	public Queue(int size){
		headIndex = -1;
		rearIndex = -1;
		maxQueueSize = size;
		q = new TwoDTreeNode[size];
	}

	/**
	 * Checks if the queue is empty
	 * @return Boolean value indicating whether or not the queue is empty
	*/  
	public boolean isEmpty(){
		if(headIndex == rearIndex){
			return true;
		}
		return false;
	}

	/**
	 * Returns the size of the Queue
	 * @return Size of Queue
	*/ 
	public int getSize(){
		return rearIndex - headIndex;
	}

	/**
	 * Adds to the rear of the Queue
	 * @param nodeToAdd The Node to add to the list
	 * Preconditions: A queue exists
	 * Postconditions: The queue is now size n+1, with a node added to the rear
	*/ 
	public void addToRear(TwoDTreeNode nodeToAdd){
		if(getSize() < maxQueueSize){
			if(isEmpty()){
				headIndex = 0;
				rearIndex = 0;
			}
			
			q[rearIndex] = nodeToAdd;
			rearIndex = rearIndex + 1;
		}
		else {
			// Resize
		}
	}

	/**
	 * Removes from the front of the Queue
	 * @return The node that has been removed
	 * Preconditions: A queue exists
	 * Postconditions: The first element in the Queue has been removed
	*/ 
	public TwoDTreeNode removeFromFront(){
		if(isEmpty()){
			return null;
		}
		int temp = headIndex;
		TwoDTreeNode removedNode = q[temp];
		q[temp] = null;
		headIndex = temp + 1;
		return removedNode;
	}

	/**
	 * Prints the Queue in a human readable format
	 * @return The string representation of the Queue
	*/ 
	public String toString(){
		String queueString = "";
		for(int i = headIndex; i<rearIndex; i++){
			queueString += q[i];
		}
		return queueString;
	}
}	