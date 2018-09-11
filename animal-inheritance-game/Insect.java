/*
Samantha L. Misurda
Assignment 2
*/

public class Insect extends Creature
{

	public Insect(int col, int row){
		super(col, row);
		//System.out.println("Bred an Insect");
	}

	public void move(Creature [][] board){ 
		// Update coordinates
		int row = getRowLocation();
		int col = getColumnLocation();

		//System.out.println("At row: " + row + " col: " + col);

		setMovedThisTurn();
		
		if( row > 0 && board[row - 1][col] == null) { // up
			//System.out.println("Moving up");
			this.setRowLocation(row-1);
			board[row - 1][col] = this;
			board[row][col] = null;
		} 
		else if (col < (board[row].length - 1) && board[row][col + 1] == null){ // right
			//System.out.println("Moving right");
			this.setColumnLocation(col+1);
			board[row][col + 1] = this;
			board[row][col] = null;
		} 
		else if(col > 0 && board[row][col - 1] == null) { // left
			//System.out.println("Moving left");
			this.setColumnLocation(col-1);
			board[row][col - 1] = this;
			board[row][col] = null;
		}
		else if(row < (board.length - 1) && board[row + 1][col] == null) { // down
			//System.out.println("Moving down");
			this.setRowLocation(row + 1);
			board[row + 1][col] = this;
			board[row][col] = null;
		} 
		else {
			// Can't move

		}
	}

	public void breed(Creature [][] board){
		if(this.getRoundsSinceBred() >= 3) { // It has been 3 turns, try to breed
			// Update coordinates
			int row = getRowLocation();
			int col = getColumnLocation();
			if( row > 0 && board[row - 1][col] == null) { // up
				Insect newInsect = new Insect(row - 1, col);
				board[row - 1][col] = newInsect;
				this.setRoundsSinceBred(0);
			}
			else if(row < (board.length - 1) && board[row + 1][col] == null) { // down
				Insect newInsect = new Insect(row + 1, col);
				board[row + 1][col] = newInsect;
				this.setRoundsSinceBred(0);
			}
			else if(col > 0 && board[row][col - 1] == null) { // left
				Insect newInsect = new Insect(row, col - 1);
				board[row][col - 1] = newInsect;
				this.setRoundsSinceBred(0);
			}
			else if (col < (board[row].length - 1) && board[row][col + 1] == null){ // right
				Insect newInsect = new Insect(row, col + 1);
				board[row][col + 1] = newInsect;
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

	//Insects are immortal unless eaten
	public boolean hasStarvedToDeath() {
		return false;
	}

	public String toString(){
		return "o";	
	}
}