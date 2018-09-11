/**
 * @author Samantha L. Misurda
 * TSPtoKML.java
 * Converts the output of the TSP into a KML file
 */

import java.util.Scanner; 
import java.io.*;

public class TSPtoKML
{
	/**
	 * A small driver program to test basic functionality 
	 * @param args String array of commandline arguments
	 */
	public static void main(String [] args)
	{
		ListOfCrimes appList = new ListOfCrimes();
		ListOfCrimes optList = new ListOfCrimes();
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

		int numVertices = graph.getSize();
		int [] approxTour = new int [numVertices];
		int [] optimalTour = new int [numVertices];
		TwoDTreeNode currCrime;

		// Call each tour function, iterate through the array, pull each crime node out and add to crime list
		approxTour = ApproximateTSP.MSTPrim(graph);
		optimalTour = OptimalTSP.findOptimalTSP(graph);
		for(int i=0;i<approxTour.length;i++){
			currCrime = graph.getNode(approxTour[i]);
			appList.addCrimeAtEnd(currCrime.toString());
		}
		System.out.println("");
		for(int j=0;j<optimalTour.length;j++){
			currCrime = graph.getNode(optimalTour[j]);
			optList.addCrimeAtEnd(currCrime.toString());
		}

		System.out.println("--- Printing to KML format ---");
		String kmlFormat = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
		+"<kml xmlns=\"http://earth.google.com/kml/2.2\">"
		+"<Document>"
		+"<name>Pittsburgh TSP</name>"
		+"<description>TSP on Crime</description>"
		+"<Style id=\"style6\">"
		+"<LineStyle>"
		  +"<color>73FF0000</color>"
		  +"<width>5</width>"
		+"</LineStyle>"
		+"</Style>"
		+"<Style id=\"style5\">"
		+"<LineStyle>"
		  +"<color>507800F0</color>"
		  +"<width>5</width>"
		+"</LineStyle>"
		+"</Style>";
		kmlFormat += appList.toKML("TSP Path", 5);
		kmlFormat += optList.toKML("Optimal Path",6);
		kmlFormat += "</Document></kml>";
		try (PrintWriter out = new PrintWriter("PGHCrimes.kml")) {
            out.write(kmlFormat);
        }
		catch (IOException e){
		    System.err.println("Error writing file. Exiting");
		    return;
		}	
	}	
}	