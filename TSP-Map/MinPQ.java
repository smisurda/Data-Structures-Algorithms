/**
 * @author Samantha L. Misurda
 * MinPQ.java
 * 
 * A minimum priority queue implemented using a MinHeap
 */

class MinPQ {
	private Tuple[] A;
	private int heapSize;

	/**
	*	For a node i, get its parents location in the heap array
	*	@param i index of node to find parent of
	*	@return the index of the parent node
	*/
	private int getParent(int i) {
		return i/2;
	}

	/**
	 *	For a node i, get its left child's location in the heap array
	 *	@param i index of node to find left child of
	 *	@return the index of the left child node
	 */
	private int getLeft(int i) {
		return 2*i;
	}

	/**
	 *	For a node i, get its right child's location in the heap array
	 *	@param i index of node to find right child of
	 *	@return the index of the right child node
	 */
	private int getRight(int i) {
		return 2*i + 1;
	}
	
	/**
	 * Constructs an empty min priority queue
	 *
	 *	@param maxSize the maximum number of elements the queue can hold
	 */
	public MinPQ(int maxSize) {
		A = new Tuple[maxSize+1];
		heapSize = 0;
	}

	/**
	 *	Returns if the queue is empty
	 *
	 *	@return if the queue is empty
	 */
	public boolean isEmpty() {
		return heapSize < 1;
	}

	/**
	 *	Returns the node with the lowest edge weight  
	 *
	 *	@return the integer index of the node with the lowest associated weight
	 *	@throws IllegalStateException when the heap has no elements
	 */
	public int extractMinimum() {
		if(heapSize < 1) {
			throw new IllegalStateException("Heap Underflow");
		}
		Tuple min = A[1];
		A[1] = A[heapSize];
		heapSize--;
		minHeapify(1);
		return min.getNode();
	}

	/**
	 *	Adjusts an edge's weight lower  
	 *
	 *	@param i the integer index of the node to decrease
	 *  @param key the new weight for the edge
	 *	@throws IllegalStateException when the weight is not lower than the current weight
	 */
	private void heapDecreaseKey(int i, double key) {
		if(key > A[i].getKey()) {
			throw new IllegalStateException("new key is bigger than current key");
		}

		A[i].setKey(key);
		while(i > 1 && A[getParent(i)].getKey() > A[i].getKey()) {
			Tuple temp = A[getParent(i)];
			A[getParent(i)] = A[i];
			A[i] = temp;
			i = getParent(i);
		}
	}

	/**
	 *	insert a new weight into the queue 
	 *
	 *	@param key the weight of the edge
	 *	@param node the integer index of the node
	 */
	public void insert(double key, int node) {
		++heapSize;
		A[heapSize] = new Tuple(Double.POSITIVE_INFINITY, node);
		heapDecreaseKey(heapSize, key);
		//printHeapArray();
	}

	/**
	 *	Helper function to print the heap for debugging
	 */
	private void printHeapArray() {
		for(int i=1;i<=heapSize;i++) {
			System.out.print(A[i].getKey() + ": " + A[i].getNode() + ", ");
		}
		System.out.println();
	}

	/**
	 *	Maintain heap property with subtree root i
	 *
	 *	@param i the subtree to re-heapify
	 */
	private void minHeapify(int i) {
		int l = getLeft(i);
		int r = getRight(i);
		int smallest;

		if(l <= heapSize && A[l].getKey() < A[i].getKey()) {
			smallest = l;
		}
		else {
			smallest = i;
		}

		if(r <= heapSize && A[r].getKey() < A[smallest].getKey()) {
			smallest = r;
		}

		if(smallest != i) {
			Tuple temp = A[i];
			A[i] = A[smallest];
			A[smallest] = temp;
			minHeapify(smallest);
		}
	}

	/**
	 *	Private inner class to hold the edges and their weights
	 */
	private class Tuple {
		private double key;
		private int node;

		/**
		 * Constructor
		 * @param k weight
		 * @param n Node
		 */
		public Tuple(double k, int n) {
			key = k;
			node = n;
		}

		/**
		 * Returns the key
		 * @return the key
		 */
		public double getKey() {
			return key;
		}

		/**
		 * Sets the key
		 * @param k the key
		*/
		public void setKey(double k) {
			key = k;
		}

		/**
		 * Returns the node
		 * @return the node
		 */
		public int getNode() {
			return node;
		}
	}
	
}