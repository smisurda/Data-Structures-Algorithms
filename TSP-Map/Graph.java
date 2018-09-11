/**
 * @author Samantha L. Misurda
 * A simple class to represent an undirected graph
 */

public class Graph
{
	private TwoDTreeNode [] graph;
	private final int MAX_SIZE = 1000; 
	private int count = 0;
	
	public Graph(){
		graph = new TwoDTreeNode[MAX_SIZE];
	}	

	/**
	 * Add a node to the graph
	 * @param n The node to add
	 */
	public void add(TwoDTreeNode n) {
		graph[count++] = n;
	}

	/**
	 * Returns the number of nodes in the graph
	 * @return The number of vertices in the graph 
	 */
	public int getSize(){
		return count;
	}

	/** 
	 * Returns the node at index n
	 * @param n The index
	 * @return The node at index n
	 */
	public TwoDTreeNode getNode(int n){
		return graph[n];
	}

	/**
	 * Returns the distance between two vertices
	 * @param i Vertex 1
	 * @param j Vertex 2
	 * @return The Euclidean distance between i and j
	 */

	public double getDistance(int i, int j) {
		return distance(graph[i], graph[j]);
	}

	/**
	 * Computes the distance between two vertices
	 * @param src Vertex 1
	 * @param sink Vertex 2
	 * @return The Euclidean distance between i and j
	 */
	private double distance(TwoDTreeNode src, TwoDTreeNode sink) {
		return Math.sqrt(Math.pow(src.getX()-sink.getX(),2)+Math.pow(src.getY()-sink.getY(), 2));
	}
}