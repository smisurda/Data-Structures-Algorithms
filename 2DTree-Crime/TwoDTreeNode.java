/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 3
 * TwoDTreeNode.java
 * A node representing the data for a single crime
 */	

public class TwoDTreeNode
{
	private double x;
	private double y;      
	private int time;
	private String street;        
	private String offense;
	private String date;
	private int tract;
	private double lat;
	private double lon;

	//A little confused here...
	private TwoDTreeNode nextNode;
	private TwoDTreeNode leftChild;
	private TwoDTreeNode rightChild;
	private int size;

	/**
	 * Constructor for the TwoDTreeNode class
	 * @param x1 the x-coordinate
	 * @param y1 The y-coordinate
	 * @param timeOfDay The time the crime occurred
	 * @param streetName The street the crime occurred on
	 * @param offenseName The name of the crime
	 * @param dateOccur When the crime occurred
	 * @param tractLocation The tract of land where the crime took place
	 * @param latitude The latitude of the crime 
	 * @param longtitude The longtitude of the crime
	*/ 
	public TwoDTreeNode(double x1, double y1, int timeOfDay, String streetName, String offenseName, String dateOccur, int tractLocation, double latitude, double longtitude){
		x = x1;
		y = y1;
		time = timeOfDay;
		street = streetName;
		offense = offenseName;
		date = dateOccur; 
		tract = tractLocation; 
		lat = latitude;
		lon = longtitude;
		nextNode = null;
		leftChild = null;
		rightChild = null;
		size = 1;
	}

	/**
	 * No-arg constructor for the TwoDTreeNode class
	 */
	public TwoDTreeNode() {}

	/**
	* This method clones the data of a node into the specified other node
	* @param other The destination TwoDTreeNode to set with this one's data
	*/
	public void cloneInto(TwoDTreeNode other) {
		other.x = x;
		other.y = y;
		other.time = time;
		other.street = street;
		other.offense = offense;
		other.date = date; 
		other.tract = tract; 
		other.lat = lat;
		other.lon = lon;
		other.nextNode = nextNode;
		other.leftChild = leftChild;
		other.rightChild = rightChild;
		other.size = size;
	}
   
    /** 
	 * Finds the next node in the list with respect to the current node
	 * @return The pointer to the next node in the list
	 */    
	public TwoDTreeNode getNext(){
		return nextNode;
	} 

	/**
	 * Finds the left child of the current node
	 * @return The pointer to the left child in the list
	 * Preconditions: You have a tree
	 * Postconditions: The left child of that tree is set to the new node
	 */    
	public TwoDTreeNode getLeftChild(){
		return leftChild;
	} 

	/**
	 * Finds the right child of the current node
	 * @return The pointer to the right child in the list
	 * Preconditions: You have a tree
	 * Postconditions: The right child of that tree is set to the new node
	 */    
	public TwoDTreeNode getRightChild(){
		return rightChild;
	}  

	/**
	 * @return The size of the subtree
	 * Preconditions: You have a tree
	 * Postconditions: The size of that tree is returned
	 */    
	public int getSize(){
		return size;
	} 
	

	//SHOULD THIS STILL BE HERE? 
	/**	
	 * Sets the current node's next pointer 
	 * 
	 * Preconditions: You have at least one node
	 * Postconditions: The current node's next pointer is set to the specified new node
	 * Runtime: O(1)
	 * @param next The next node in the list
	 */ 
	public void	setNext(TwoDTreeNode next) {
		nextNode = next;
	}

	/**
	 * Sets the current node's left child pointer 
	 * 
	 * @param newNode The new left child
	 * Preconditions: You have a tree
	 * Postconditions: The left child of that tree is set to the new node
	 */ 
	public void	setLeftChild(TwoDTreeNode newNode) {
		leftChild = newNode;
	}

	/**
	 * Sets the current node's right child pointer 
	 * @param newNode The new right child
	 * Preconditions: You have a tree
	 * Postconditions: The right child of that tree is set to the new node
	 */ 
	public void	setRightChild(TwoDTreeNode newNode) {
		rightChild = newNode;
	}

	/**
	 * Fetches the x-coordinate of the crime 
	 * Preconditions: You have a Crime node
	 * Postconditions: The x-coordinate of the crime is returned
	 * @return The x-coordinate of the crime 
	 */
	public double getX(){
		return x;
	}

	/**
	 * Fetches the y-coordinate of the crime 
	 * Preconditions: You have a Crime node
	 * Postconditions: The y-coordinate of the crime is returned
	 * @return The y-coordinate of the crime 
	 */
	public double getY(){
		return y;
	}

	/**
	 * Fetches the latitude of the crime 
	 * Preconditions: You have a Crime node
	 * Postconditions: The latitude of the crime is returned
	 * @return The latitude 
	 */
	public double getLat(){
		return lat;
	}

	/**
	 * Fetches the longitude of the crime 
	 * Preconditions: You have a Crime node
	 * Postconditions: The longtitude of the crime is returned
	 * @return The longtitude 
	 */
	public double getLon(){
		return lon;
	}

	/**
	 * Fetches the offense of the crime 
	 * Preconditions: You have a Crime node
	 * Postconditions: The name of the offense is returned
	 * @return The offense name
	 */
	public String getOffense(){
		return offense;
	}

	/**
	 * Fetches the street name of the crime
	 * Preconditions: You have a Crime node
	 * Postconditions: The street where the crime occurred is returned
	 * @return The street name
	 */
	public String getStreet(){
		return street;
	}

	/**
	 * Prints the contents of the list in a human readable format 
	 * Preconditions: You have a list
	 * Postconditions: The list is returned as a String of concatenated character values
	 * @return The contents of the list
	 */
	public String toString(){
		return ""+x+","+y+","+time+","+street+","+offense+","+date+","+tract+","+lat+","+lon+" ";
	}

	/**
	* Simple Driver program
	* @param args Command line arguments
	*/
	public static void main(String [] args){
		TwoDTreeNode crime1 = new TwoDTreeNode(1358205.688,412438.5393,32898,"5000 FORBES AV","ROBBERY","1/25/90",140100,40.44471042,-79.94295871);
		System.out.println(crime1);
	}
}