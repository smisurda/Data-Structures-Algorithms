/*
Samantha L. Misurda
Assignment 2
*/

public abstract class Creature
{
	private int rowLocationCell;
	private int columnLocationCell;
	private int roundsSinceBred;
	private boolean hasMovedThisTurn;

	public Creature(int x, int y){
		rowLocationCell = x;
		columnLocationCell = y;
		roundsSinceBred = 0;
	}
	// Returns the current x coordinate of the creature
	public int getRowLocation(){
		return rowLocationCell;
	}

	// Returns the current y coordinate of the creature
	public int getColumnLocation(){
		return columnLocationCell;
	}

	protected void setRowLocation(int cellXIndex){
		rowLocationCell = cellXIndex;
	}

	protected void setColumnLocation(int cellYIndex){
		columnLocationCell = cellYIndex;
	}

	public int getRoundsSinceBred(){
		return roundsSinceBred;
	}

	protected void setRoundsSinceBred(int rounds){
		roundsSinceBred = rounds;
	}

	public boolean hasMovedThisTurn() {
		return hasMovedThisTurn;
	}

	public void resetMovedThisTurn() {
		hasMovedThisTurn = false;
	}

	protected void setMovedThisTurn() {
		hasMovedThisTurn = true;
	}

	public abstract void move(Creature [][] board);
	public abstract void breed(Creature [][] board);
	public abstract boolean hasStarvedToDeath();

}