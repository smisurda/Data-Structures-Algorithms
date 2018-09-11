/** 
 * @author Samantha L. Misurda
 * RedBlackNode.java
 * An implementation of a RedBlackNode for use in the RedBlackTree from CLR
 */
import java.math.BigInteger;

public class RedBlackNode 
{
	public static final int BLACK = 0;
	public static final int RED = 1;

	private String key;
	private BigInteger data; 
	private int color;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;
	
	/**
	 * Construct a RedBlackNode with data, color, parent pointer, left child pointer and right child pointer.
	 * @param key The key
	 * @param data - a simple value held in the tree
	 * @param color - either RED or BLACK
	 * @param p - the parent pointer
	 * @param lc - the pointer to the left child (will be null only for the node that represents all external nulls.
     * @param rc - the pointer to the right child (will be null only for the node that represents all external nulls.
	 */
	public RedBlackNode(String key, BigInteger data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
		this.key = key;
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}

	/**
	 * The getColor() method returns RED or BLACK.
	 * @return The color of the node
	 */ 
	public int getColor(){
		return color;
	}


	/**
	* The getKey() method returns the key in the node.
	* @return the key value in the node
	*/
	public String getKey(){
		return key;
	}

	/**
	* The getData() method returns the data in the node.
	* @return the data value in the node
	*/
	public BigInteger getData(){
		return data;
	}

	/**
	 * The getLc() method returns the left child of the RedBlackNode.
	 * @return the left child
	 */
	public RedBlackNode	getLc(){
		return lc;
	}

	/**
	 * The getP() method returns the parent of the RedBlackNode.
	 * @return the node's parent
	 */
	public RedBlackNode	getP(){
		return p;
	}

	/**
	 * The getRc() method returns the right child of the RedBlackNode.
	 * @return the right child field
	 */
	public RedBlackNode	getRc(){
		return rc;
	}

	/**
	 * The setColor() method sets the color of the RedBlackNode.
	 * @param color - is either RED or BLACK
	 */
	public void	setColor(int color){
		this.color = color;
	}

	/**
	 * The setData() method sets the data or key of the RedBlackNode.
	 * @param data - is an int holding a node's data value
	 */
	public void	setData(BigInteger data){
		this.data = data;
	}

	/**
	 * The setLc() method sets the left child of the RedBlackNode.
	 * @param lc establishes a left child for this node.
	 */
	public void	setLc(RedBlackNode lc){
		this.lc = lc;
	}

	/**
	 * The setP() method sets the parent of the RedBlackNode.
	 * @param p establishes a parent for this node.
	 */
	public void	setP(RedBlackNode p){
		this.p = p;
	}

	/**
	 * The setRc() method sets the right child of the RedBlackNode.
	 * @param rc establishes a right child for this node.
	 */
	public void	setRc(RedBlackNode rc){
		this.rc = rc;
	}

	/**
	 * Prints the contents of the stack in a human readable format
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: Nothing changes
	 * @return The node's contents as a String
	 */
	public String toString(){
		return getKey() + ": " + getData();
	}

	/**
	 * Driver program for testing the RedBlackNode class
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		RedBlackNode n = new RedBlackNode("4", new BigInteger("3"), RED, null, null, null);
		System.out.println(n);
	}		

}	