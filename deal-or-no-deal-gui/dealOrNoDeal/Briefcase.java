/*
*	Samantha L. Misurda
*	Assignment 4
*/

package dealOrNoDeal;

import javax.swing.JButton;
import java.util.Locale;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class Briefcase extends JButton {

	private final int value; // I think this should be a double, but the instructions say int in the game class
	private boolean inPlay;
	private boolean isPlayerCase;


	public Briefcase(int v, String caseNumber) {
		value = v;
		inPlay = true; 
		isPlayerCase = false;
		this.setText(caseNumber);
	}

	/*
	 * The setRemoved method should also set the Briefcase button text when the
	 * case is removed. If it is the player case, the text should be "X",
	 * otherwise it should display its value. Once, the briefcase is removed, a
	 * player should not be able to click the button.
	 */

	public void setRemoved() {
		if(this.isPlayerCase) {
			this.setText("X");
		}
		else {
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
			currencyFormat.setGroupingUsed(true);
			currencyFormat.setMaximumFractionDigits(0);
			this.setText(currencyFormat.format(this.value));
		}
		this.inPlay = false;
		this.setEnabled(false); // Disable the button
	}

	// gotta fix this
	public void setPlayerCase() {
		isPlayerCase = true;
	}

	public boolean isPlayerCase() {
		return isPlayerCase;
	}

	public int getValue() {
		return value;
	}

	public boolean isInPlay() {
		return inPlay;
	}

}