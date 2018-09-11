/*
Samantha L. Misurda
Assignment 2
*/

public class Bird extends Creature
{
	private int turnsSinceLastMeal;

	public Bird(int col, int row){
		super(col, row);
		turnsSinceLastMeal = 0;
	}

	public int getTurnsSinceLastMeal(){
		return turnsSinceLastMeal;
	}

	private void setTurnsSinceLastMeal(int turns){
		turnsSinceLastMeal = turns;
	}

	public boolean hasStarvedToDeath(){
		if(turnsSinceLastMeal >= 3){
			return true;
		}
		return false;
	}

	public void move(Creature [][] board){ 
		// Update coordinates
		int row = getRowLocation();
		int col = getColumnLocation();

		setMovedThisTurn();
		if(eat(board)) {
			return;
		}
		
		if( row > 0 && board[row - 1][col] == null) { // up
			this.setRowLocation(row-1);
			board[row - 1][col] = this;
			board[row][col] = null;
		}
		else if(row < (board.length - 1) && board[row + 1][col] == null) { // down
			this.setRowLocation(row + 1);
			board[row + 1][col] = this;
			board[row][col] = null;
		}
		else if(col > 0 && board[row][col - 1] == null) { // left
			this.setColumnLocation(col-1);
			board[row][col - 1] = this;
			board[row][col] = null;
		}
		else if (col < (board[row].length - 1) && board[row][col + 1] == null){ // right
			this.setColumnLocation(col+1);
			board[row][col + 1] = this;
			board[row][col] = null;
		} 
		
		else {
			// Can't move
		}
	}

	private boolean eat(Creature [][] board) {
		// Update coordinates
		int row = getRowLocation();
		int col = getColumnLocation();

		if( row > 0 && board[row - 1][col] instanceof Insect) { // up
			this.setRowLocation(row-1);
			board[row - 1][col] = this;
			board[row][col] = null;
			this.setTurnsSinceLastMeal(0);
			return true;
		}
		else if(row < (board.length - 1) && board[row + 1][col] instanceof Insect) { // down
			this.setRowLocation(row + 1);
			board[row + 1][col] = this;
			board[row][col] = null;
			this.setTurnsSinceLastMeal(0);
			return true;
		}
		else if(col > 0 && board[row][col - 1] instanceof Insect) { // left
			this.setColumnLocation(col-1);
			board[row][col - 1] = this;
			board[row][col] = null;
			this.setTurnsSinceLastMeal(0);
			return true;
		}
		else if (col < (board[row].length - 1) && board[row][col + 1] instanceof Insect){ // right
			this.setColumnLocation(col+1);
			board[row][col + 1] = this;
			board[row][col] = null;
			this.setTurnsSinceLastMeal(0);
			return true;
		}
		
		else { // No Insects, just call move
			this.setTurnsSinceLastMeal(getTurnsSinceLastMeal() + 1);
			return false;
		}

	}

	public void breed(Creature [][] board){
		if(this.getRoundsSinceBred() >= 8) { // It has been 8 turns, try to breed
			// Update coordinates
			int row = getRowLocation();
			int col = getColumnLocation();

			if( row > 0 && board[row - 1][col] == null) { // up
				Bird newBird = new Bird(row - 1, col);
				board[row - 1][col] = newBird;
				this.setRoundsSinceBred(0);
			}
			else if (col < (board[col].length - 1) && board[row][col + 1] == null){ // right
				Bird newBird = new Bird(row, col + 1);
				board[row][col + 1] = newBird;
				this.setRoundsSinceBred(0);
			}
			else if(col > 0 && board[row][col - 1] == null) { // left
				Bird newBird = new Bird(row, col - 1);
				board[row][col - 1] = newBird;
				this.setRoundsSinceBred(0);
			}
			else if(row < (board.length - 1) && board[row + 1][col] == null) { // down
				Bird newBird = new Bird(row + 1, col);
				board[row + 1][col] = newBird;
				this.setRoundsSinceBred(0);
			}
			else { // Can't breed due to space, increment
				this.setRoundsSinceBred(this.getRoundsSinceBred() + 1);
			}
		}
		else { // Can't breed rowet, increment counter
			this.setRoundsSinceBred(this.getRoundsSinceBred() + 1);
		}
	}

	public String toString(){
		return "B";	
	}
}