/*
* 	Samantha L. Misurda
*	Assignment 1
*	Craps.java
*
*	Driver application for a game of Craps
*/

public class Craps {
	public static void main(String [] args) {
		final int numGames = 10000; // Play 10,000 games
		int wins = 0;
		int losses = 0;
		int totalRoll = 0;
		
		for(int i = 0; i < numGames; i++) {
			PairOfDice dicePair = new PairOfDice();
			// Come out roll
			dicePair.roll();
			totalRoll = dicePair.getTotal();
			// Player wins
			if(totalRoll == 7 || totalRoll == 11) {
				wins++; 
			}
			// House wins
			else if(totalRoll == 2 || totalRoll == 3 || totalRoll == 12) {
				losses++;
			}
			// Proceed to game
			else {
				int thePoint = totalRoll;
				boolean playing = true;
				while(playing) {	
					//Re-roll
					dicePair.roll();
					totalRoll = dicePair.getTotal();
					// Player wins
					if(totalRoll == thePoint) {
						wins++;
						playing = false;
					}
					// House wins
					else if(totalRoll == 7) {
						losses++;
						playing = false;
					}	
				}	
			}
		}

		double winPercentage = ((double)wins/(double)(wins+losses));
		System.out.println("-----------");
		System.out.println("Total wins: "+wins);
		System.out.println("Total losses: "+losses);
		System.out.println("Probability of Player winning: "+ winPercentage*100 + "%");
		if(winPercentage > .5) {
			System.out.println("The Player is more likely to win than lose");
		}
		else {
			System.out.println("The House is more likely to win than lose");
		} 
	}
}