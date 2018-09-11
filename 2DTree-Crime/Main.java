/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 3
 * Main.java
 * Shows loading data into the 2DTree data structure, and demonstrates basic usage 
 */	

import java.util.Scanner; 
import java.io.*;

public class Main
{

	/**
	* Simple Driver program
	* @param args Command line arguments
	*/
	public static void main (String [] args){
		Scanner inScan = new Scanner(System.in);
		int userSelection = 0;
		String [] temp;
		double x1, y1, x2, y2;
		ListOfCrimes crimes; 

		TwoDTree crimeTree = new TwoDTree("CrimeLatLonXY.csv");
		System.out.println("Crime file loaded into 2-d tree with " + crimeTree.getSize() + " records");
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("1: inorder");
			System.out.println("2: preorder");
			System.out.println("3: levelorder");
			System.out.println("4: postorder");
			System.out.println("5: search for points within rectangle");
			System.out.println("6: search for nearest neighbor");
			System.out.println("7: quit");
			userSelection = inScan.nextInt();
			String newline = inScan.nextLine(); // Consume newline character
			switch(userSelection){
				case 1:
					crimeTree.inOrderPrint();
					break;
				case 2:
					crimeTree.preOrderPrint();
					break;
				case 3:
					crimeTree.levelOrderPrint();
					break;
				case 4:
					crimeTree.postOrderPrint();
					break;
				case 5:
					System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as four doubles each separated by a space.");
					String rectangle = inScan.nextLine();
					temp = rectangle.split(" "); // Split string into proper format
					x1 = Double.parseDouble(temp[0]);
					y1 = Double.parseDouble(temp[1]);
					x2 = Double.parseDouble(temp[2]);
					y2 = Double.parseDouble(temp[3]);
					System.out.println("Searching for points within ("+x1+", "+y1+") and ("+x2+", "+y2+")");
					crimes = crimeTree.findPointsInRange(x1, y1, x2, y2);
					
					System.out.println("Found "+crimes.countNodes()+" crimes");
					// Print crimes
					System.out.println(crimes.toString());
					// Write crimes to KML
					String kmlFile = crimes.toKML(); 
					try{
						FileWriter fileWriter = new FileWriter("PGHCrimes.kml");
					    PrintWriter printWriter = new PrintWriter(fileWriter);
					    printWriter.print(kmlFile);
					    printWriter.close();
					} catch(IOException e){System.out.println("Error writing file");}    
					System.out.println("The crime data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro.");
					break;
				case 6:
					System.out.println("Enter a point to find nearest crime. Separate with a space.");
					String point = inScan.nextLine();
					temp = point.split(" "); // Split string into proper format
					x1 = Double.parseDouble(temp[0]);
					y1 = Double.parseDouble(temp[1]);
					TwoDTreeNode nearest = new TwoDTreeNode();
					double result;
					result = crimeTree.nearestNeighbor(x1, y1, nearest);
					break;
				default:	
					System.out.println("Thank you for exploring Pittsburgh crimes in the 1990's.");	
					return;				
			}
		} 		
	}
}