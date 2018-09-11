/**
 * @author Samantha L. Misurda
 * ApproximateTSP.java
 * 
 * The algorithm is implemented from CLSR: 
 * 		1. Select a vertex r to be a root vertex
 * 		2. Compute a minimum spanning tree T for G from root r using MST-Prim(G,c,r)
 * 		3. Let L be the list of vertices visited in a preorder tree walk of T
 * 		4. Return the Hamiltonian cycle H that visits the vertices in the order L
 */

import java.util.Scanner; 
import java.io.*;

public class ApproximateTSP
{
    private TwoDTreeNode root;

	/**
	 * The driver program for part 1
	 * @param args Command line arguments
	 */
	public static void main(String [] args){

		Graph graph = new Graph();

		Scanner inScan = new Scanner(System.in);
		System.out.println("Enter start index:");
		int startIndex = inScan.nextInt();
		System.out.println("Enter end index:");
		int endIndex = inScan.nextInt();

		try {
			File file = new File("CrimeLatLonXY1990.csv");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;
			br.readLine(); // skip header
			int lines = -1;

			System.out.println("Crime Records Processed:");
			while ((temp= br.readLine()) != null){
				lines ++;
				if(lines < startIndex || lines > endIndex) {
					continue;
				}
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
			    graph.add(newNode);
			    System.out.println(newNode);
			}
		}
		catch (IOException e){
			System.err.println("Error reading file. Exiting");
			return;
		}

		// Create graph and pass in
		MSTPrim(graph);
	}

	/**
	 * From Wikipedia: 
	 * Associate with each vertex v of the graph a number C[v] (the cheapest cost of a connection to v) and an edge E[v] (the edge providing that cheapest connection). 
	 * To initialize these values, set all values of C[v] to +∞ (or to any number larger than the maximum edge weight) and set each E[v] to a special flag value 
	 * indicating that there is no edge connecting v to earlier vertices.
	 * Initialize an empty forest F and a set Q of vertices that have not yet been included in F (initially, all vertices).
	 * Repeat the following steps until Q is empty:
	 *	Find and remove a vertex v from Q having the minimum possible value of C[v]
	 *	Add v to F and, if E[v] is not the special flag value, also add E[v] to F
	 *	Loop over the edges vw connecting v to other vertices w. For each such edge, if w still belongs to Q and vw has smaller weight than C[w], perform the following 
	 *	steps:
	 *		Set C[w] to the cost of edge vw
	 *		Set E[w] to point to edge vw.
	 * 	return F
	 * @param graph An adjacency matrix representation of the graph
	 * @return finalTour The tour selected
	*/ 
	public static int [] MSTPrim(Graph graph){
		int numVertices = graph.getSize();
		
        int [] mst = new int[numVertices]; // Store vertices of MST
        double [] distance = new double [numVertices]; // Distance array
       	boolean visited[] = new boolean[numVertices];
        int [] finalTour = new int [numVertices];
 
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }
 			
        distance[0] = 0; // Arbitrarily select the first node as the root
        mst[0] = -1; 

        MinPQ priorityQ = new MinPQ(numVertices*numVertices);
        priorityQ.insert(0,0); //Insert the root with lowest weight

        while(!priorityQ.isEmpty()) {
        	int u = priorityQ.extractMinimum();
        	visited[u] = true;
			for (int v = 0; v < numVertices; v++) {
				if(visited[v] == false && graph.getDistance(u,v) < distance[v]) {
					mst[v] = u;
                    distance[v] = graph.getDistance(u,v);
                    priorityQ.insert(distance[v], v);
				}
			}

        }

        finalTour = showTree(mst, graph, numVertices);
        return finalTour;
	}

	/**
	 * Prints the MST in a preorder traversal
	 * @param mst The minimum spanning tree
	 * @param graph The graph
	 * @param numVertices The number of vertices in the graph
	 * @return The array of vertices in the tour 
	 */
	public static int [] showTree(int mst[], Graph graph, int numVertices){
        SingleLinkedList tree[] = new SingleLinkedList[mst.length];
        for(int i=0;i<numVertices;i++) {
        	tree[i] = new SingleLinkedList();
        }

        for (int i = 1; i < numVertices; i++){
        	tree[mst[i]].addAtEnd(i);
        }

        SingleLinkedList tour = new SingleLinkedList();
        preorder(0, tree, tour);
        tour.addAtEnd(0);
        System.out.println("Hamiltonan Cycle (not necessarily optimum): " + tour);

        double totalDistance = 0;
        int tourArray[] = new int[tour.countNodes()];
        int index = 0;
        SingleNode prevNode = tour.removeHead();
        while(!tour.isEmpty()) {
    		SingleNode node = tour.removeHead();
    		tourArray[index++] = prevNode.getData();
    		totalDistance += graph.getDistance(prevNode.getData(), node.getData());
    		prevNode = node;
    	}
    	System.out.printf("Length of Cycle: %.2f miles" , totalDistance*0.00018939);
    	return tourArray;
    }

    /**
     * Prints the MST using a preorder traversal
     * @param root The root index
     * @param tree The MST 
     * @param tour The parent list
     */
    private static void preorder(int root, SingleLinkedList tree[], SingleLinkedList tour) {
    	tour.addAtEnd(root);
    	for(int i=tree[root].countNodes()-1;i>=0;i--) {
    		SingleNode child = tree[root].removeHead();
    		preorder(child.getData(), tree, tour);
    	}
    }

}