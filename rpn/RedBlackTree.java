/** 
 * @author Samantha L. Misurda
 * RedBlackTree.java
 * An implementation of a RedBlackTree from CLR
 */
import java.math.BigInteger;

public class RedBlackTree 
{
	private static final RedBlackNode NIL = new RedBlackNode(null, null, RedBlackNode.BLACK, null, null, null);
	
	private RedBlackNode tree = NIL; 
	private int numberOfInserts; 
	private int recentCompares;

	/**
	 * The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise. 
	 * It counts each comparison it makes in the variable recentCompares.
	 * @param v The value to search for
	 * @return True if v is in the tree, false otherwise
	 */
	public boolean contains(String v){
		recentCompares = 0;

		return contains(tree, v);
	}

	private boolean contains(RedBlackNode root, String v) {
		recentCompares++;

		if(root == NIL) {
			return false;
		}
		int result = v.compareTo(root.getKey());
		if(result == 0) {
			return true;
		}
		else if(result < 0) {
			return contains(root.getLc(), v);
		}
		else {
			return contains(root.getRc(), v);
		}
	}

	/**
	 * @return The number of comparisons made in the last call on the contains method
	 */
	public int getRecentCompares() {
		return recentCompares;
	}

	/**
	 * @return number of values inserted into the tree.
	 */
	public int getSize(){
		return numberOfInserts;
	} 

	/**
	 * This method calls the recursive method.
	 * @return The height of the red black tree
	 */
	public int height() {
		return height(tree);
	}

	/**
	 * This a recursive routine that is used to compute the height of the red black tree. 
	 * It is called by the height() method. 
	 * The height() method passes the root of the tree to this method. This method would normally be private.
	 * @param t a pointer to a node in the tree.
	 * @return The height of node t
	 */
	public int height(RedBlackNode t){
		if(t == NIL) {
			return 0;
		}
		return Math.max(height(t.getLc()), height(t.getRc())) + 1;
	}

	/**
	 * The no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) - passing the root.
	 */
	public void	inOrderTraversal(){
		inOrderTraversal(tree);
	}

	/**
	 * Perfrom an inorder traversal of the tree. The inOrderTraversal(RedBlackNode) method is recursive and displays 
	 * the content of the tree. It makes calls on System.out.println(). 
	 * This method would normally be private.
	 * @param t The root of the tree on the first call
	 */
	public void inOrderTraversal(RedBlackNode t){
		if(t.getLc() != NIL) {
			inOrderTraversal(t.getLc());
		}
		System.out.println(t.getKey() + ": " + t.getData());
		if(t.getRc() != NIL) {
			inOrderTraversal(t.getRc());
		}
	}

	/**
	 * Places a data item into the tree, executing the algorithm from CLR
	 * @param value is an integer to be inserted 
	 * @param key 
	 * Pre-condition: memory is available for insertion
	 */
	public void	insert(String key, BigInteger value){

		numberOfInserts++;
		RedBlackNode y = NIL;
	    RedBlackNode x = tree;
	    RedBlackNode z = new RedBlackNode(key, value, RedBlackNode.RED, NIL, NIL, NIL);
	    while(x != NIL){
	        y = x;
	        int comparison = z.getKey().compareTo(x.getKey());
	        if(comparison < 0) {
	             x = x.getLc();
	        }
	        else if(comparison > 0) {
	             x = x.getRc();
	        }
	        else {
	        	x.setData(value);
	        	return;
	        }
	    }     
	    z.setP(y);
	    if(y == NIL){
	        tree = z;
	    }     
	    else{
	    	int comparison = z.getKey().compareTo(y.getKey());
	    	if(comparison < 0){
				y.setLc(z);
			}  
			else if(comparison > 0) {
				y.setRc(z);
			}
			else {
				y.setData(value);
				return;
			}
		}  

	    RBInsertFixup(z);
	}

	/**
	 * @param z The node to insert
	 * Performs the rotations and recolorings
	 */
	public void RBInsertFixup(RedBlackNode z) {
		while(z.getP().getColor() == RedBlackNode.RED) {
    		if(z.getP() == z.getP().getP().getLc()) {
	         	RedBlackNode y = z.getP().getP().getRc();
	         	if(y.getColor() == RedBlackNode.RED) {
	            	z.getP().setColor(RedBlackNode.BLACK); 
	            	y.setColor(RedBlackNode.BLACK);
	             	z.getP().getP().setColor(RedBlackNode.RED);
	             	z = z.getP().getP();
	         	}
	         	else {
	            	if(z == z.getP().getRc()) {
	                  z = z.getP();
	                  leftRotate(z);
	             	}
	             	z.getP().setColor(RedBlackNode.BLACK);
	             	z.getP().getP().setColor(RedBlackNode.RED);
	             	rightRotate(z.getP().getP());
	         	}
	    	}
	   		else {
	        	RedBlackNode y = z.getP().getP().getLc();
	         	if(y.getColor() == RedBlackNode.RED) {
	            	z.getP().setColor(RedBlackNode.BLACK); 
	            	y.setColor(RedBlackNode.BLACK);
	             	z.getP().getP().setColor(RedBlackNode.RED);
	             	z = z.getP().getP();
	         	}
	         	else {
	            	if(z == z.getP().getLc()) {
	                	z = z.getP();
	                	rightRotate(z);
	             	}
	             	z.getP().setColor(RedBlackNode.BLACK);
	             	z.getP().getP().setColor(RedBlackNode.RED);
	             	leftRotate(z.getP().getP());
	         	} 
	    	} 
	  	} 
  		tree.setColor(RedBlackNode.BLACK);
  	}

  	/**
  	 * performs a single left rotation This would normally be a private method.
  	 * @param x The node to rotate
  	 */
  	public void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRc();
	    x.setRc(y.getLc());
	    y.getLc().setP(x);
	    y.setP(x.getP());

	    if(x.getP() == NIL) {
	    	tree = y;
	    } 
	    else {
	       if(x == x.getP().getLc()) {
	       		x.getP().setLc(y);
	       } 
	       else {
	       		x.getP().setRc(y);
	       }
	    }
	    y.setLc(x);
	    x.setP(y);
  	}

  	/**
  	 * rightRotate() performs a single right rotation This would normally be a private method.
  	 * @param x The node to rotate
  	 */
  	public void rightRotate(RedBlackNode x) {
  		RedBlackNode y = x.getLc();           // y now points to node to left of x
	    x.setLc(y.getRc());    // y's right subtree becomes x's left subtree
	    y.getRc().setP(x);      // right subtree of y gets a new parent
	    y.setP(x.getP());           // y's parent is now x's parent

	    // if x is at root then y becomes new root
	    if(x.getP() == NIL) {
	    	tree = y;
	    }
	    else{
			// if x is a left child then adjust x's parent's left child or...
			if(x == x.getP().getLc()) {
				x.getP().setLc(y);
			}
			else {
			// adjust x's parent's right child
				x.getP().setRc(y);
			}
	   	}
	    // the right child of y is now x
	    y.setRc(x);
	    // the parent of x is now y
	    x.setP(y);
  	}

  	/**
     * Returns the value stored in the Node associated with key.
     *
     * @param  key The key
     * @return the value associated with the requested key
     *         
     */
    public BigInteger get(String key) {
        return get(tree, key);
    }

    /**
     * Helper function for get 
     *
     * @param node The node to search 
     * @param key 
     * @param data   
     */
    private BigInteger get(RedBlackNode node, String key) {
        if (node == NIL){
        	return null;
        }	

        int result = key.compareTo(node.getKey());
     	if(result == 0) {
     		return node.getData();
     	}
        else if(result < 0){
            return get(node.getLc(), key);
        }
        else {
        	return get(node.getRc(), key);
        }
    }

	/**
	 * Prints the contents of the stack in a human readable format
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: Nothing changes
	 * @return The tree's contents as a String
	 */
	public String toString(){
		return "";
	}

	public void sidewaysPrint() {
		sidewaysPrint(tree, 0);
	}

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	private void sidewaysPrint( RedBlackNode root, int depth)
	{
		if (root == NIL)
			return;
		sidewaysPrint( root.getRc(), depth + 1 );
		for( int i = 0; i < depth; ++i )
			System.out.print("   ");
		if(root.getColor() == RedBlackNode.RED) {
			System.out.print(ANSI_RED);
		}
		else {	
			System.out.print(ANSI_RESET);
		}
		System.out.println(root.getKey());
		sidewaysPrint( root.getLc(), depth + 1 );
		System.out.print(ANSI_RESET);
	}

	/**
	 * Driver program for testing the RedBlackTree class
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		RedBlackTree t = new RedBlackTree();

		BigInteger biValue = BigInteger.valueOf(0);
		for(int i=0;i<5;i++) {
			biValue = biValue.add(BigInteger.valueOf(i));
			t.insert(String.valueOf((char)('A'+i)),biValue);
			//System.out.println("Height: " + t.height());
		}

		BigInteger answer;
		if(t.contains("C")){
			System.out.println("Found key, Data: ");
			answer = t.get("C");
		}
		else {
			answer = null;
		}
		System.out.println(answer);
			
		//t.inOrderTraversal();
		//t.sidewaysPrint();
	}
}	