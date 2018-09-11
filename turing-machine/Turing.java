/** 
 * @author Samantha L. Misurda
 * Turing.java
 * Simulation of a Turing Machine
 */

 public class Turing
 {

 	private char[] tape = new char[40];
 	private int tapePosition = 19;

 	private int[][] transitions;
 	private char[][] outputs;
 	private int[][] directions;

 	private int numStates;

 	/**
 	 * Constructor for the Turing object
 	 * @param numStates The number of states
 	 */
 	public Turing(int numStates) {
 		this.numStates = numStates;

 		for(int i=0;i<tape.length;i++) {
 			tape[i] = 'B';
 		}

 		transitions = new int[numStates][3];
 		outputs = new char[numStates][3];
 		directions = new int[numStates][3];
 	}

 	/**
     * Turns the tape into an ordinal that represents in the array the position of the character
 	 * @param c The input on the tape
 	 * @return The index
 	 */
 	private int inputToIndex(char c) {
 		switch(c) {
 			case '0': return 0;
 			case '1': return 1;
 			case 'B': return 2;
 		}
 		return -1;
 	}

 	/**
 	 * Adds a transition
 	 * @param stateNumber The state number
 	 * @param input The input character on the tape
 	 * @param transition The transition information
 	 */
 	public void addTransition(int stateNumber, char input, Transition transition) {
 		transitions[stateNumber][inputToIndex(input)] = transition.getNextState();
 		outputs[stateNumber][inputToIndex(input)] = transition.getOutput();
 		directions[stateNumber][inputToIndex(input)] = transition.getDirection();
 	} 

 	/**
 	 * Executes the Turing machine at the tape head
 	 * @param initialState Where to start the tape
 	 * @return The tape as a string
 	 */
 	public String execute(String initialState) {
 		for(int i=0; i<initialState.length();i++) {
 			tape[tapePosition+i] = initialState.charAt(i);
 		}

 		int currentState = 0;
 		while(currentState < numStates) {
 			//System.out.print("In state: " + currentState);
 			char input = tape[tapePosition];
 			//System.out.print(" Saw an: " + input);
 			tape[tapePosition] = outputs[currentState][inputToIndex(input)];
 			//System.out.print(" Output an: " + tape[tapePosition]);
 			tapePosition += directions[currentState][inputToIndex(input)];
 			/*if(directions[currentState][inputToIndex(input)] == Transition.LEFT) {
 				System.out.print(" Moving left");
 			}
 			else {
 				System.out.print(" Moving right");
 			}*/
 			currentState = transitions[currentState][inputToIndex(input)];
 			//System.out.println(" and entering state " + currentState);
 		}

 		return new String(tape);
 	}

 	/** 
 	 * Driver function for the Turing Machine
 	 * Copy and pasted the main function from the project write-up
 	 * Please change as appropriate
 	 * @param args Command line arguments
 	 */
 	public static void main (String [] args){
 		// This machine will have one state.
 		// Note: There is an additional halt state. The values on the input tape are set to all B’s.
 		
 		Turing machine = new Turing(1); 
                                  

	    Transition one =   new Transition('0',Transition.RIGHT, 0);
	    Transition two =   new Transition('1',Transition.RIGHT, 0);
	    Transition three = new Transition('B', Transition.LEFT,1);

	    machine.addTransition(0, '0', two);
	    machine.addTransition(0, '1', one);
	    machine.addTransition(0, 'B', three);

		// The leftmost value of inTape will be placed under the read/write head.
	    String inTape = "11111100010101"; 

	    System.out.println(inTape);

	    String outTape = machine.execute(inTape);
	    System.out.println(outTape);

 	}
 }