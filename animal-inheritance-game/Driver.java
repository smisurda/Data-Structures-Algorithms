/*
Samantha L. Misurda
Assignment 2
*/

import java.util.*;

public class Driver
{
	private static final int WORLD_ROWS = 20;
	private static final int WORLD_COLS = 20;
	private static final int NUMBER_OF_INSECTS = 100;
	private static final int NUMBER_OF_BIRDS = 15;
	private static final int NUMBER_OF_CATS = 10;

	public static void main (String [] args){
		Scanner inScan = new Scanner(System.in);
		World predatorPreyBoard = new World(WORLD_ROWS, WORLD_COLS); // initialize the game board
		predatorPreyBoard.initBoard(NUMBER_OF_INSECTS, NUMBER_OF_BIRDS, NUMBER_OF_CATS);
		predatorPreyBoard.printBoard(); // Print initial board
		System.out.println(" ");
		
		// Simulate round until user wishes to quit
		do {
			predatorPreyBoard.simulateRound();
			predatorPreyBoard.printBoard();
			System.out.println("Would you like to simulate another round?");
			System.out.println("Hit enter to continue, or 'Q' to quit");
			String userAction = inScan.nextLine();
			if(userAction.equals("q") || userAction.equals("Q")) {
				break; 
			}
		} while(true);
	}	
}