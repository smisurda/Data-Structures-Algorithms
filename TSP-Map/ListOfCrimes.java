/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * ListOfCrimes.java
 * A list of crimes, built using a singly linked list
 */	

public class ListOfCrimes
{
	private TwoDTreeNode head;
	private TwoDTreeNode tail;

	public ListOfCrimes(){
		head = null;
		tail = null;
	}

	/**
	 * Adds a crime to the end of the list 
	 * 
	 * Preconditions: You have a list of length N
	 * Postconditions: You have a list of length N+1 with the newly inserted crime at the tail
	 * Runtime: O(N)
	 * @param  crime  A line from a csv file
	 */
	public void addCrimeAtEnd(String crime){
			
		String [] temp = crime.split(",");
		if(this.isEmpty()) // Special case, empty list
		{
			TwoDTreeNode newNode = new TwoDTreeNode(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), Integer.parseInt(temp[2]), temp[3], temp[4], temp[5], Integer.parseInt(temp[6]), Double.parseDouble(temp[7]), Double.parseDouble(temp[8]));
			head = newNode;
			tail = newNode;
		}
		else
		{	
			TwoDTreeNode newNode = new TwoDTreeNode(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), Integer.parseInt(temp[2]), temp[3], temp[4], temp[5], Integer.parseInt(temp[6]), Double.parseDouble(temp[7]), Double.parseDouble(temp[8]));
			tail.setNext(newNode);
			tail = newNode;
		}		
	}

	/**
	 * Fetches a Crime from the list
	 * @return The crime requested
	 * Preconditions: You have a list constructed
	 * Postconditions: Nothing has changed
	*/ 
	public TwoDTreeNode retrieveCrime(){
		TwoDTreeNode currNode;
		if(!isEmpty()){
			//return currNode;
		}
		return null;
	}

	/**
	 * Returns the front of the list
	 * @return  returns the TwoDTreeNode at the front of the list
	 * Preconditions: You have a list constructed
	 * Postconditions: Nothing has changed
	 */
	public TwoDTreeNode getHead() {
		return head;
	}

	/**
	 * Checks if the list contains any nodes
	 * Preconditions: You have a list constructed
	 * Postconditions: Nothing has changed
	 * Runtime: O(1)
	 * @return  A boolean value indicating whether or not the list is empty
	 */
	public boolean isEmpty()
	{
		if(head == null) {
			return true;
		}
		else{
			return false;
		}	
	}

	/**
	 * Counts the number of nodes in the list
	 * Preconditions: You have a list
	 * Postconditions: The list is unmodified, and the number of elements is returned
	 * Runtime: O(N)
	 * @return  The number of nodes in the list
	 */
	public int countNodes(){
		int count = 0;
		if(head != null){
			TwoDTreeNode currNode = head;
			while(currNode != null){
				currNode = currNode.getNext();
				count++;
			}
		}	
		return count;
	}

	/**
	 * Prints the contents of the list in a human readable format 
	 * Preconditions: You have a list
	 * Postconditions: The list is returned as a String of concatenated character values
	 * @return The contents of the list
	 */
	public String toString()
	{
		if(isEmpty())
		{
			return "";
		}

		TwoDTreeNode currNode = head;
		String list = "";
		while(currNode != null)
		{
			list = list + currNode.toString() + "\n";
			currNode = currNode.getNext();
		}
		return list;
	}

	/**
	 * Prints the crime data in a KML usable format
	 * @param typeOfPath Specifies if the optimal or approximate TSP is calculated
     * @param style The style class to apply
	 * @return The List of crimes in KML format
	 * Preconditions: You have a list
	 * Postconditions: The list is returned in KML format
	*/ 
	public String toKML(String typeOfPath, int style){
		if(isEmpty())
		{
			return "";
		}

		String kmlFormat = "<Placemark>"
		+"<name>"+typeOfPath+"</name>"
		+"<description>"+typeOfPath+"</description>"
		+"<styleUrl>#style"+style+"</styleUrl>"
		+"<LineString>"
		  +"<tessellate>1</tessellate>"
		  +"<coordinates>";
		  // Iterate over list
	   		TwoDTreeNode currNode = head;
	   		while(currNode != null){
			   kmlFormat += currNode.getLon()+","+currNode.getLat()+"\n";  
			   currNode = currNode.getNext();
			}
		  
		kmlFormat+=" </coordinates>";
		kmlFormat+="</LineString>";
		kmlFormat+= "</Placemark>";
		return kmlFormat;	

	}
}	