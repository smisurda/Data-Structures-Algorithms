/*
*	Samantha L. Misurda
*	Assignment 4
*/

package dealOrNoDeal;

import java.util.*;
import java.text.NumberFormat;

public class DealOrNoDeal {

	
	private List<Integer> briefcaseValues = Arrays.asList(1, 5, 10, 25, 50, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000);

	private ArrayList<Briefcase> briefcases = new ArrayList<Briefcase>();

	private final int NUM_BRIEFCASES = 24;
	private int currentRound;
	private int numCasesRemaining;
	private int numSelectionsToMake;

	private boolean isGameActive;
	private boolean isDealPending;
	private boolean isOfferAccepted;

	private Banker banker;
	private Briefcase playerBriefcase; 

	private NumberFormat currencyFormat;

	// default constructor
	public DealOrNoDeal() {
		banker = new Banker(briefcases);

		currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		currencyFormat.setGroupingUsed(true);
		currencyFormat.setMaximumFractionDigits(0);
	}

	/*
	 * Sets up a new game:
	 * -- create a new ArrayList of Briefcases
	 * -- populate the List with correct number of Briefcases with the possible values
	 * -- mix-up the cases so the values are not in order (HINT Use a method from
	 *    the Collections class (http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html)
	 * -- initialize the class variables to their correct initial values
	 * -- return the newly created randomized Briefcase Collection
	 */
	public ArrayList<Briefcase> gameSetup() {
		Collections.shuffle(briefcaseValues); // Shuffle Briefcase values

		for(int i = NUM_BRIEFCASES; i > 0; i--) {
        	briefcases.add(new Briefcase(briefcaseValues.get(i-1), Integer.toString(i))); // Replace 7 with value
        }

        // Initialize class variables
        currentRound = 0; // Round 0 is not real -- it's the initialization step
		numCasesRemaining = NUM_BRIEFCASES;
		numSelectionsToMake = 1; // We have to select the player's initial case

		isGameActive = true;
		isDealPending = false;
		isOfferAccepted = false;

        return briefcases;
	}

	/*
	 * Returns a String of the next step instruction of the game.
	 */
	public String getInstruction() {
		if(currentRound == 0) {
			return "Ready to start? Select your case.";
		}
		else if (currentRound > 0 && numSelectionsToMake > 0) {
			return "Select " + numSelectionsToMake + " cases";
		}	
		else {
			initiateDeal();
			
			return "The Banker's offer is " + currencyFormat.format(banker.getOffer()); 
		}
	}

	/*
	 * Initiate a deal and set the Banker's offer. 
	 */
	private void initiateDeal() {
		currentRound++;
		banker.setRound(currentRound);

		isDealPending = true;
		// Update cases to pick
		if(currentRound == 2) {
			numSelectionsToMake = 5;
		}
		else if (currentRound == 3) {
			numSelectionsToMake = 4;
		}
		else if (currentRound == 4) {
			numSelectionsToMake = 3;
		}
		else if (currentRound == 5) {
			numSelectionsToMake = 2;
		}
		else {
			numSelectionsToMake = 1;
		}
	}

	/*
	 * Allows outside classes to select a Briefcase
	 */
	public void selectCase(Briefcase briefcase) {
		if(currentRound == 0) { // Initialization step, set player's case
			playerBriefcase = briefcase;
			playerBriefcase.setPlayerCase();
			currentRound = 1;
			numSelectionsToMake = 6;
		}
		else {
			numSelectionsToMake = numSelectionsToMake - 1;
		}
		numCasesRemaining = numCasesRemaining - 1;

	}

	/*
	 * Handle the logic when a player accepts a deal
	 */
	public void deal() {
		isOfferAccepted = true;
		isDealPending = false;
		isGameActive = false;
	}

	/*
	 * Handle the logic when a player does not accept a deal 
	 */
	public void noDeal() {
		isOfferAccepted = false;
		isDealPending = false;
		if(numCasesRemaining == 0) {
			isGameActive = false;
		}
	}

	/*
	 * Determines the result of the game whenever
	 * a player either accepts a deal or rejects the Banker's final offer
	 */
	public String getResult() {
		String prefix = "Your case was worth " + currencyFormat.format(playerBriefcase.getValue() ) + ".  ";

		if(isOfferAccepted) {
			prefix += "You accepted the Banker's offer of " + currencyFormat.format(banker.getOffer()) + ". ";
		}
		else {
			prefix += "You did not accept the Banker's offer of " + currencyFormat.format(banker.getOffer()) + ". ";
		}

		if(isOfferAccepted && playerBriefcase.getValue() < banker.getOffer()) {
			return prefix + "You Win!";
		}
		else if(numCasesRemaining == 0 && playerBriefcase.getValue() > banker.getOffer()) {
			return prefix + "You Win!";
		}
		else {
			return prefix + "You Lose!";
		}
	}

	/* 
	* Member getters/setters 
	*/

	public boolean isGameActive() {
		return isGameActive;
	}

	public boolean isDealPending() {
		return isDealPending;
	}

	public boolean isOfferAccepted() {
		return isOfferAccepted;
	}



}