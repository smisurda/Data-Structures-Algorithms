/** 
 * @author Samantha L. Misurda
 * ReversePolishNotation.java
 * An implementation of Reverse Polish Notation 
 */

import java.util.Scanner;
import java.math.BigInteger;

public class ReversePolishNotation 
{
	private static RedBlackTree symbolTable = new RedBlackTree();
	private static Stack rpn = new Stack();

	/**
	 * Determines if a token is a variable
	 * @param s The token being parsed
	 */
	private static boolean isVariable(String s) {
		return Character.isLetter(s.charAt(0));
	}

	/**
	 * Returns the value of an operand, whether it is a variable or literal
	 * @throws Stack Overflow Exception - Occurs when trying to pop from an empty stack
	 * @throws No Variable Exception - Occurs when referencing a variable that is undefined
	 */
	private static BigInteger getOperandValue() throws Exception {
		Object temp = rpn.pop();
		if(temp == null) {
			throw new Exception("stack underflow exception");
		}
		String s = temp.toString();

		if(isVariable(s)) {
			BigInteger val = symbolTable.get(s);
			if(val == null ) {
				throw new Exception("no variable " + s);	
			}
			return val;
		}
		else {
			return new BigInteger(s);
		}
	} 

/**
 * Main Driver program
 * throws no lvalue exception - Occurs when trying to assign to a non-variable 
 * @param args Command line arguments
 */
	public static void main(String [] args){
		Scanner inScan = new Scanner(System.in);
		
		boolean finished = false;
		BigInteger first;
		BigInteger second;
		BigInteger answer = null;
		while (!finished) {
			String line = inScan.nextLine();
			if(line.equals("")) {
				System.out.println("terminating");
				return;
			}
			String[] tokens = line.split(" ");
			try {
				for(String input: tokens) { 
					switch(input){
						case "+": // 3 2 +
							second = getOperandValue();
							first = getOperandValue();
							answer = first.add(second); 
							rpn.push(answer);
							break;
						case "-":
							second = getOperandValue();
							first = getOperandValue();
							answer = first.subtract(second);
							rpn.push(answer);
							break;
						case "*": 
							second = getOperandValue();
							first = getOperandValue();
							answer = first.multiply(second);
							rpn.push(answer);
							break;
						case "/":
							second = getOperandValue();
							first = getOperandValue();
							answer = first.divide(second);
							rpn.push(answer);
							break;
						case "%":
							second = getOperandValue();
							first = getOperandValue();
							answer = first.mod(second);
							rpn.push(answer);
							break;
						case "=":
							second = getOperandValue();
							Object temp = rpn.pop();
							if(temp == null) {
								throw new Exception("stack underflow exception");
							}
							String name = temp.toString();
							if(!isVariable(name)) {
								throw new Exception(name + " is not an lvalue");
							}
							symbolTable.insert(name, second);
							answer = second;
							rpn.push(second);
							break;
						case "~":
							first = getOperandValue();
							answer = first.negate();
							rpn.push(answer);
							break;
						case "#":
							BigInteger third = getOperandValue();
							second = getOperandValue();
							first = getOperandValue();
							answer = first.modPow(second, third);
							rpn.push(answer);
							break;
						default:
							rpn.push(input); // This is just an operand						
					}
				}
				
				System.out.println(answer);
				rpn = new Stack();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
		}
	}
}
