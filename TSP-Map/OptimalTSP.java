/**
 * @author Samantha L. Misurda
 * OptimalTSP.java
 * 
 * Selects the optimal (shortest) TSP path, using an adaptation to the CLSR algorithm from ApproximateTSP
 */

import java.util.Scanner; 
import java.io.*;

public class OptimalTSP
{
    private TwoDTreeNode root;

	/**
	 * The driver program for part 2
	 * @param args Command line arguments
	 */
	public static void main(String [] args){

		Graph graph = new Graph();
		int [] tour;

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
		tour = findOptimalTSP(graph);

	}


	/**
	 * Finds the optimal TSP path by brute force checking all permutations of the vertices
	 * @param graph An adjacency matrix representation of the graph
	 * @return tour The tour
	*/ 
	public static int[] findOptimalTSP(Graph graph){
		int numVertices = graph.getSize();
		int [] tour = new int [numVertices];
		int [] minTour = new int[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            tour[i] = i;
        }

        double minDistance = Double.POSITIVE_INFINITY;

        /* 
        	Generate all permutations 
        	Algorithm L, Section 7.2.1.2
        	TAOCP Vol 4A
        	Donald Knuth
        */
        while(true){ // Generate all permutations
        	double distance = findDistance(tour, graph, numVertices);

        	if(distance < minDistance) {
        		minDistance = distance;
        		for(int i=0;i<numVertices;i++) {
        			minTour[i] = tour[i];
        		}
        	}

        	int j = numVertices - 2;
        	while(j>=0 && tour[j] >= tour[j+1]){
        		j--;
        	}
        	if(j < 0){
        		break;
        	}
        	int l = numVertices - 1;
        	while(tour[j] >= tour[l]){
        		l--;
        	}
        	int temp = tour[j];
        	tour[j] = tour[l];
        	tour[l] = temp;
        	int k = j + 1;
        	l = numVertices - 1;
        	while(k < l){
        		temp = tour[k];
        		tour[k] = tour[l];
        		tour[l] = temp;
     			k++;
     			l--;
        	}
        }

        System.out.print("Hamiltonan Cycle (minimal): ");
       	for(int i=0;i<numVertices;i++) {
			System.out.print(minTour[i] + ",");
		}
		System.out.println(minTour[0]);
		System.out.printf("Length of Cycle: %f\n", minDistance*0.00018939);
        return minTour;
	}

	/**
	 * Helper function to find the minimum distance in each tour 
	 * @param tour The tour to examine
	 * @param g The graph
	 * @param numVertices The number of vertices in each graph
	 */
	private static double findDistance(int tour[], Graph g, int numVertices) {
	   
	   double distance = 0;
	 
		for (int v = 1; v < numVertices; v++) {
			distance += g.getDistance(tour[v-1], tour[v]);
		}
		distance += g.getDistance(tour[numVertices-1], tour[0]); // Add back in origin
	   return distance;
	}
}