/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 3
 * TwoDTree.java
 * Implementation of a 2d-tree data structure
 *
 * A note on the complexity of runtime analysis:
 * 
 * Runtime analysis on K-D Trees is challenging in the average case, but not so much in the best and worst cases. 
 * Worst case is easy -- pick the wrong path, and out performance degrades tothat of a linked list. 
 * Similarly, analysis for the best case is trivial, as best case is a balanced tree, and has lg(n) performance.
 * 
 * The average case however, is hard because we need to consider how likely it is for the tree to be balanced or 
 * unbalanced. The chance of the tree being balanced can be improved by picking a "smarter" (closer to the true median) 
 * root of each subtree, such as using a median of three approach. \
 *
 */	

import java.io.*;

public class TwoDTree
{
	private TwoDTreeNode root;
	private int treeSize;
	private int visited = 0;

	/**
	 * Constructs a TwoDTree object 
	 * @param filepath The path to a file containing lat/long data
	 * pre-condition: The String filepath contains the path to a file formatted in the exact same way as CrimeLatLonXY.csv
	 * post-condition: The 2-d tree is constructed and may be printed or queried.
	 */
	public TwoDTree(String filepath){
		// Open file and parse CSV, creating a new node for each line
		try {
			File file = new File(filepath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;
			br.readLine(); // skip header
			while ((temp= br.readLine()) != null){
			    String[] fields = temp.split(",");
			    TwoDTreeNode newNode = new TwoDTreeNode(Double.parseDouble(fields[0]), 
			    	Double.parseDouble(fields[1]), 
			    	Integer.parseInt(fields[2]), 
			    	fields[3],
			    	fields[4], 
			    	fields[5], 
			    	Integer.parseInt(fields[6]),
			    	Double.parseDouble(fields[7]),
			    	Double.parseDouble(fields[8]));
			    insert(newNode);
			}
		}
		catch (IOException e){
			System.err.println("Error reading file. Exiting");
			return;
		}	
		System.out.println("Successfully imported file into 2d-tree");
	}

 	/**
 	 * Inserts a node into the tree
 	 * @param newNode The node to insert
 	 * Preconditions: You have a (potentially empty) tree
 	 * Postconditions: A new node has been added to the tree
 	 */
 	public void insert(TwoDTreeNode newNode){
 		if(root == null){ // empty tree
 			root = newNode;
 		}
 		else {
 			insertHelperX(root, newNode);
 		}
 		treeSize++; 
 	}

 	/**
 	 * Recursive insert helper function along the x-axis
 	 * @param subtreeRoot The root of the subtree
 	 * @param newNode The node to insert
	 * Preconditions: You have a tree
 	 * Postconditions: You have a tree of size n+1
 	 */
 	private void insertHelperX(TwoDTreeNode subtreeRoot, TwoDTreeNode newNode){
 		// Nodes That are equal get inserted to the right
		if(newNode.getX() < subtreeRoot.getX()){
			if(subtreeRoot.getLeftChild() == null){ // Add node to the left
				subtreeRoot.setLeftChild(newNode);
			}
			else {
				insertHelperY(subtreeRoot.getLeftChild(), newNode); // Not null, recurse in the other plane
			}
		}
		else {
			if(subtreeRoot.getRightChild() == null){ // Add node to the right
				subtreeRoot.setRightChild(newNode);
			}
			else {
				insertHelperY(subtreeRoot.getRightChild(), newNode); // Not null, recurse in the other plane
			}
		}
 	}

 	/**
 	 * Recursive insert helper function along the y-axis
 	 * @param subtreeRoot The root of the subtree
 	 * @param newNode The node to insert
	 * Preconditions: You have a tree
 	 * Postconditions: You have a tree of size n+1
 	 */
 	private void insertHelperY(TwoDTreeNode subtreeRoot, TwoDTreeNode newNode){
		if(newNode.getY() < subtreeRoot.getY()){
			if(subtreeRoot.getLeftChild() == null){ // Add node to the left
				subtreeRoot.setLeftChild(newNode);
			}
			else {
				insertHelperX(subtreeRoot.getLeftChild(), newNode); // Not null, recurse in the other plane
			}
		}
		else {
			if(subtreeRoot.getRightChild() == null){ // Add node to the right
				subtreeRoot.setRightChild(newNode);
			}
			else {
				insertHelperX(subtreeRoot.getRightChild(), newNode); // Not null, recurse in the other plane
			}
		}
 	}
    

	/**
	 * Returns the root of the tree
	 * @return The root of the tree
	 * Preconditions: You have a tree
 	 * Postconditions: No changes
	 */ 
	public TwoDTreeNode getRoot() {
        return root;
    }

    /**
	 * Returns the size of the tree
	 * @return The size of the tree
	 * Preconditions: You have a (potentially empty) tree
 	 * Postconditions: No changes
	 */ 
	public int getSize() {
        return treeSize;
    }

 
	/* Begin Algorithms required for assignment 
	*******************************************
	*******************************************
	*/ 


	/**
	 * Performs a preorder traversal of the tree
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a preorder traversal
	*/
	public void preOrderPrint(){
		preOrderHelper(root);
	}

	/**
	 * Helper function for preorder traversal
	 * @param rootNode The root of the tree
	*/
	private void preOrderHelper(TwoDTreeNode rootNode) {
		if(rootNode != null){
			System.out.println(rootNode);
			// Print left
			preOrderHelper(rootNode.getLeftChild());

			// Print Right
			preOrderHelper(rootNode.getRightChild());
		}
	}


	/**
	 * Performs an inorder traversal of the tree
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a in-order traversal
	*/
	public void inOrderPrint(){
		inOrderHelper(root);
	}

	/**
	 * Helper function for inorder traversal
	 * @param rootNode The root of the tree
	*/
	private void inOrderHelper(TwoDTreeNode rootNode) {
		if(rootNode != null){
			// Print left
			preOrderHelper(rootNode.getLeftChild());
			// Print Root
			System.out.println(rootNode);
			// Print right
			preOrderHelper(rootNode.getRightChild());
		}
	}

	/**
	 * Performs a postorder traversal of the tree
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a post-order traversal
	*/
	public void postOrderPrint(){
		postOrderHelper(root);
	}

	/**
	 * Helper function for postorder traversal
	 * @param rootNode The root of the tree
	*/
	private void postOrderHelper(TwoDTreeNode rootNode) {
		if(rootNode != null){
			// Print left
			preOrderHelper(rootNode.getLeftChild());
			// Print right
			preOrderHelper(rootNode.getRightChild());
			// Print Root
			System.out.println(rootNode);
		}
	}

	/**
	 * Performs a Level Order traversal of the tree
	 * pre-condition: The 2-d tree has been constructed.
	 * post-condition: The 2-d tree is displayed with a level-order traversal
	*/
	public void levelOrderPrint(){
		Queue q = new Queue(treeSize);
		if(root == null){
			return;
		}

		q.addToRear(root);
		while(!q.isEmpty()) { 
			TwoDTreeNode currNode = q.removeFromFront();
			System.out.println(currNode); // Visit the node
			if(currNode.getLeftChild() != null){
				q.addToRear(currNode.getLeftChild());
			}
			if(currNode.getRightChild() != null) {
				q.addToRear(currNode.getRightChild());
			}
		}
	}

	/**
	 * Performs a Level Order traversal of the tree
	 * @param x1 The x coordinate of the first point
	 * @param y1 The y coordinate of the first point
	 * @param x2 The x coordinate of the second point
	 * @param y2 The y coordinate of the first point
	 * @return A list of crimes within the bounding box
	 * pre-condition: The 2-d tree has been constructed
	 * post-condition: A list of 0 or more crimes is returned. These crimes occurred within the rectangular range specified by the four parameters. The pair (x1, y1) is the left bottom of the rectangle. The pair (x2, y2) is the top right of the rectangle. 
	*/
	public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2) {
		ListOfCrimes crimeList = new ListOfCrimes();
        System.out.println("Examined " + rangeHelperX(root, x1, x2, y1, y2, crimeList) + " nodes during search.");
        return crimeList;
	}

	/** 
     * Helper function for performing range search. Enqueues nodes once visited
     * @param currNode: The current node we are considering
     * @param rectX1 One x-coordinate of the search rectangle
     * @param rectX2 One x-coordinate of the search rectangle
     * @param rectY1 One y-coordinate of the search rectangle
     * @param rectY2 One y-coordinate of the search rectangle
     * @param crimeList The ListOfCrimes containing the points we've visited inside the rectangle 
     */
    private int rangeHelperX(TwoDTreeNode currNode, double recX1, double recX2, double recY1, double recY2, ListOfCrimes crimeList) {
        if (currNode == null) {
        	return 0;
        }
        // Check if the current crime is within the search radius
        if ((currNode.getX() >= recX1 && currNode.getX() <= recX2) && (currNode.getY() >= recY1 && currNode.getY() <= recY2)) {
            crimeList.addCrimeAtEnd(currNode.toString());
        }

        int visited=0;

        if(currNode.getX() >= recX1) {
        	visited += rangeHelperY(currNode.getLeftChild(), recX1, recX2, recY1, recY2, crimeList) ;
        }
        if(currNode.getX() <= recX2) {
        	visited += rangeHelperY(currNode.getRightChild(), recX1, recX2, recY1, recY2, crimeList) ;
        }
        return visited + 1;
    }

    /** 
     * Helper function for performing range search. Enqueues nodes once visited
     * @param currNode: The current node we are considering
     * @param rectX1 One x-coordinate of the search rectangle
     * @param rectX2 One x-coordinate of the search rectangle
     * @param rectY1 One y-coordinate of the search rectangle
     * @param rectY2 One y-coordinate of the search rectangle
     * @param crimeList The ListOfCrimes containing the points we've visited inside the rectangle 
     */
	private int rangeHelperY(TwoDTreeNode currNode, double recX1, double recX2, double recY1, double recY2, ListOfCrimes crimeList) {
		if (currNode == null) {
			return 0;
		}
		// Check if the current crime is within the search radius
        if ((currNode.getX() >= recX1 && currNode.getX() <= recX2) && (currNode.getY() >= recY1 && currNode.getY() <= recY2)) {
            crimeList.addCrimeAtEnd(currNode.toString());
        }

        int visited=0;

        if(currNode.getY() >= recY1) {
        	visited += rangeHelperX(currNode.getLeftChild(), recX1, recX2, recY1, recY2, crimeList) ;
        }
        if(currNode.getY() <= recY2) {
        	visited += rangeHelperX(currNode.getRightChild(), recX1, recX2, recY1, recY2, crimeList) ;
        }
        return visited + 1;
	}


	/**
	 * Finds the distance in feet to a node's nearest neighbor
	 * Loosely based on Sedgewick and Andy Moore pseudocode
	 * @param x1 The x coordinate of the point to consider
	 * @param y1 The y coordinate of the point to consider
	 * @param nearest A reference to the result node
	 * @return The distance to the nearest node
	 * pre-condition: the 2-d tree has been constructed.
	 * post-condition: the distance in feet to the nearest node is returned. The reference parameter now has the nearest neighbor's data
	*/
	public double nearestNeighbor(double x1, double y1, TwoDTreeNode nearest) {
		if (root == null) {
			return Double.NEGATIVE_INFINITY; 
		}

		visited = 0;
		double dist = nearestHelperX(root, x1, y1, nearest, Double.POSITIVE_INFINITY);
        System.out.println("Looked at " + visited + " nodes in tree. Found the nearest crime at:");
        System.out.println(nearest);
        return dist;
	}

	/**
	 * A recursive helper function for the nearest neighbor function
	 */
	private double nearestHelperX(TwoDTreeNode subtreeRoot, double x1, double y1, TwoDTreeNode nearest, double maxDist){
		if(subtreeRoot == null) {
			return Double.POSITIVE_INFINITY;
		}

		visited++;

		double dist = 0.0;

		//If target-in-left
		if(x1 <= subtreeRoot.getX()) {
			dist = nearestHelperY(subtreeRoot.getLeftChild(), x1, y1, nearest, maxDist);

			maxDist = Math.min(maxDist, dist);

			if( (x1 + maxDist) >= subtreeRoot.getX()) {
				double d = distance(subtreeRoot.getX(), x1, subtreeRoot.getY(), y1);
				if(d < dist) {
					subtreeRoot.cloneInto(nearest);
					dist = d;
					maxDist = dist;
				}
				
				TwoDTreeNode tempNearest = new TwoDTreeNode();
				double tempDist = nearestHelperY(subtreeRoot.getRightChild(), x1, y1, tempNearest, maxDist);
				if(tempDist < dist) {
					tempNearest.cloneInto(nearest);
					dist = tempDist;
				}
			}
		}
		else {
			dist = nearestHelperY(subtreeRoot.getRightChild(), x1, y1, nearest, maxDist);

			maxDist = Math.min(maxDist, dist);

			if( (x1 - maxDist) <= subtreeRoot.getX()) {
				double d = distance(subtreeRoot.getX(), x1, subtreeRoot.getY(), y1);
				if(d < dist) {
					subtreeRoot.cloneInto(nearest);
					dist = d;
					maxDist = dist;
				}
				
				TwoDTreeNode tempNearest = new TwoDTreeNode();
				double tempDist = nearestHelperY(subtreeRoot.getLeftChild(), x1, y1, tempNearest, maxDist);
				if(tempDist < dist) {
					tempNearest.cloneInto(nearest);
					dist = tempDist;
				}
			}
		}

		return dist;
	}

	private double nearestHelperY(TwoDTreeNode subtreeRoot, double x1, double y1, TwoDTreeNode nearest, double maxDist) {
		if(subtreeRoot == null) {
			return Double.POSITIVE_INFINITY;
		}

		visited++;

		double dist = 0.0;

		if(y1 <= subtreeRoot.getY()) {
			dist = nearestHelperX(subtreeRoot.getLeftChild(), x1, y1, nearest, maxDist);

			maxDist = Math.min(maxDist, dist);

			if( (y1 + maxDist) >= subtreeRoot.getY()) {
				double d = distance(subtreeRoot.getX(), x1, subtreeRoot.getY(), y1);
				if(d < dist) {
					dist = d;
					subtreeRoot.cloneInto(nearest);
					maxDist = dist;
				}
				
				TwoDTreeNode tempNearest = new TwoDTreeNode();
				double tempDist = nearestHelperX(subtreeRoot.getRightChild(), x1, y1, tempNearest, maxDist);
				if(tempDist < dist) {
					tempNearest.cloneInto(nearest);
					dist = tempDist;
				}
			}
		}
		else {
			dist = nearestHelperX(subtreeRoot.getRightChild(), x1, y1, nearest, maxDist);

			maxDist = Math.min(maxDist, dist);

			if( (y1 - maxDist) <= subtreeRoot.getY()) {
				double d =distance(subtreeRoot.getX(), x1, subtreeRoot.getY(), y1);
				if(d < dist) {
					dist = d;
					subtreeRoot.cloneInto(nearest);
					maxDist = dist;
				}
				
				TwoDTreeNode tempNearest = new TwoDTreeNode();
				double tempDist = nearestHelperX(subtreeRoot.getLeftChild(), x1, y1, tempNearest, maxDist);
				if(tempDist < dist) {
					tempNearest.cloneInto(nearest);
					dist = tempDist;
				}
			}
		}

		return dist;
	}

	private double distance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
	/**
	* Simple Driver program
	* @param args Command line arguments
	*/
	public static void main(String [] args){
		TwoDTree tree = new TwoDTree("CrimeLatLonXY.csv");
		tree.preOrderPrint();
		tree.inOrderPrint();
		tree.postOrderPrint();
		tree.levelOrderPrint();
	}
}