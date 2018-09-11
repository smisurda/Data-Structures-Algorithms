/*
* 	Samantha L. Misurda
*	Assignment 1
*	DiceRollDriver.java
*
*	Driver application for the PairOfDice class
*/

public class DiceRollDriver {
	public static void main( String args[] ) {
		PairOfDice dicePair1 = new PairOfDice();
		PairOfDice dicePair2 = new PairOfDice();

		int totalRoll1 = -2;
		int totalRoll2 = -1;
		int rollCount = 0; // Running total for the number of rolls

		// If the values are not equal, reroll and increment the number of turns count
		while(totalRoll1 != totalRoll2) {
			dicePair1.roll();
			totalRoll1 = dicePair1.getTotal();
			System.out.println("Pair 1 rolled a "+totalRoll1);

			dicePair2.roll();
			totalRoll2 = dicePair2.getTotal();	
			System.out.println("Pair 2 rolled a "+totalRoll2);

			rollCount++;
		}

		System.out.println("It took "+rollCount+ " turns for the two pairs to have the same value.");
	}
}