/** 
 * @author Samantha L. Misurda
 * Stack.java
 * An array implementation of a Stack
 */

public class Stack 
{
	private Object [] stack;
	private int topIndex;
	private int size;

	/**
	 * Constructs an array based implementation of a stack
	 * Size is initialized to 6, as per assignment specs
	 * Preconditions: N/A
	 * Postconditions: A Stack has been created
	 */
	public Stack(){
		stack = new Object [6];
		topIndex = -1;
		size = 0;
	}

	/**
	 * Doubles the size of the underlying array, and copies existing data
	 * Preconditions: You have a reference to a full Stack object of size N
	 * Postconditions: The stack contains the same data, but is now size 2N
	 */
	public void resize() {
		Object [] temp = new Object [stack.length * 2];
		for(int i = 0; i < stack.length; i++){
			temp[i] = stack[i];
		}
		stack = temp;
	}

	/**
	 * Pushes an item onto the top of the stack
	 * @param itemToAdd The item to add to the top of the Stack
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: The stack contains n+1 elements
	 *
	 * In the best case, push is theta(1). In the worst case however, runtime can reach theta(n), as a resize of the array may be required 
	 */
	public void push(Object itemToAdd){
		if (size == stack.length){ // The stack is full, resize
			resize();
		}
		stack[size] = itemToAdd;
		if(size == 0){ // Special case, first item
			topIndex = 0;
		}
		else{
			topIndex = size;
		}
		size++;
	}

	/**
	 * Pops an item off of the top of the stack
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: The stack contains n-1 elements
	 * @return The item that was popped off
	 */
	public Object pop(){
		if(!isEmpty()){
			Object elementToReturn = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			size--;
			return elementToReturn;
		}
		return null;
	}

	/**
	 * Returns a Boolean value reflecting whether or not the Stack is empty
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: Nothing changes
	 * @return Boolean value reflecting whether or not the Stack is empty
	 */
	public boolean isEmpty(){
		if(topIndex == -1){
			return true;
		}
		return false;
	}

	/**
	 * Returns the size of the array
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: Nothing changes
	 * @return The number of elements in the array
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Prints the contents of the stack in a human readable format
	 * Preconditions: You have a reference to a Stack object
	 * Postconditions: Nothing changes
	 * @return The stack's contents as a String
	 */
	public String toString() {
		String returnedString = "";
		for(int i = 0; i < size; i++){
			returnedString += stack[i]+" ";
		}
		return returnedString;
	}


	/**
	 * Driver program for testing the Stack class
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Stack testStack = new Stack(); 

		// Loop to push 1000 objects
		for(int i = 0; i < 1000; i++){
			testStack.push(i);
		}

		Object popped = new Object();
		// Another loop to pop them all off and display
		for(int i = 0; i < 1000; i++){
			popped = testStack.pop();
			System.out.println(popped);
		}
	}
}
