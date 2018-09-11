/** 
 * @author Samantha L. Misurda
 * Transition.java
 * Class to represent a state transition
 */

 public class Transition
 {
 	public static final int RIGHT = 1;
 	public static final int LEFT = -1;

 	private char output;
 	private int direction;
 	private int nextState;

 	/**
 	 * The Transition object constructor
 	 * @param output The symbol to output
 	 * @param direction The direction to move
 	 * @param nextState The next state to enter
 	 */ 
 	public Transition(char output, int direction, int nextState) {
 		this.output = output;
 		this.direction = direction;
 		this.nextState = nextState;
 	}

 	/**
 	 * Accessor for the output field
 	 * @return The output character
 	 */
 	public char getOutput() {
 		return output;
 	}

 	/**
 	 * Accessor for the direction field
 	 * @return The transition direction
 	 */
 	public int getDirection() {
 		return direction;
 	}

 	/**
 	 * Accessor for the nextState field
 	 * @return The next state to enter
 	 */
 	public int getNextState()  {
 		return nextState;
 	}

 }