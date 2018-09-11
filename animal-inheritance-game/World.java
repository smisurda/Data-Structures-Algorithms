/*
Samantha L. Misurda
Assignment 2
*/

import java.util.*;

public class World
{
	private int numRows;
	private int numColumns;
	private Creature [][] board;

	public World(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		board = new Creature[numRows][numColumns]; 
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public boolean isCellOccupied(int row, int column) {
		if(board[row][column] == null){
			return false;
		}
		else {
			return true;
		}
	}

	// Place the creatures randomly on the board
	public void initBoard(int numInsects, int numBirds, int numCats){

		/*board[0][1] = new Insect(0, 1); 
		board[1][1] = new Bird(1, 1); 
		board[2][1] = new Cat(2, 1); */
		
		Random randomLocations = new Random();
		int row;
		int column;

		// Generate locations for birds
		for(int i = 0; i < numBirds; i++){
			// Continue generating indices if the space is occupied
			do{
				row = randomLocations.nextInt(numRows);
				column = randomLocations.nextInt(numColumns);
			} while(isCellOccupied(row, column));
			board[row][column] = new Bird(row, column); 
		}
		// Generate locations for insects
		for(int i = 0; i < numInsects; i++){
			// Continue generating indices if the space is occupied
			do{
				row = randomLocations.nextInt(numRows);
				column = randomLocations.nextInt(numColumns);
			} while(isCellOccupied(row, column));
			board[row][column] = new Insect(row, column);
		}
		// Generate locations for cats
		for(int i = 0; i < numCats; i++){
			// Continue generating indices if the space is occupied
			do{
				row = randomLocations.nextInt(numRows);
				column = randomLocations.nextInt(numColumns);
			} while(isCellOccupied(row, column));
			board[row][column] = new Cat(row, column);
		}
	}

	// Display the board
	public void printBoard() {
		for(int row = 0; row < numRows; row++){
			for(int column = 0; column < numColumns; column++) {
				if(board[row][column] == null){
					System.out.print(".");
				}
				else {
					System.out.print(board[row][column]);
				}
			}
			System.out.println(); // Print a newline
		}

	}

	public void simulateRound() {
		// Attempt to move
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				if(board[row][column] != null) {
					if(!board[row][column].hasMovedThisTurn()) {
						board[row][column].move(board);
					}
				}
			}
		}
		// Kill off starved
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				if(board[row][column] != null && board[row][column].hasStarvedToDeath() ){
					board[row][column] = null;
				}
			}
		}
		// Breed
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				if(board[row][column] != null) {
					board[row][column].breed(board);
				}
			}
		}
		//Reset all of the creatures' moved states so they may move agarown
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				if(board[row][column] != null) {
					board[row][column].resetMovedThisTurn();
				}
			}
		}
	}
}