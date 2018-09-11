/*
*	Samantha L. Misurda
*	Assignment 4
*/

package dealOrNoDeal;

import java.util.*;

public class Banker {

	private int offer; 
	private ArrayList<Briefcase> briefcases;
	private int round;

	public Banker(ArrayList<Briefcase> briefcases) {
		this.briefcases = briefcases;
	}

	public int getOffer() {
		setOffer();
		return offer;
	}

	public void setRound(int round) {
		this.round = round;
	}

	/*
	 * Calculates and sets the offer based on round and number of briefcases
	 */
	private void setOffer() {
		
		int average = 0; 
		for(Briefcase curr: briefcases) {
			if(curr.isInPlay()) {
				average += curr.getValue();
			}
		}

		average = average / briefcases.size(); 
		offer = (int)(average * (round / 10.0));
	}
	
}