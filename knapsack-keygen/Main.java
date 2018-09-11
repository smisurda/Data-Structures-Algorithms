/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * Main.java

 * This class serves as a driver program for the Merkle-Hellman Knapsack Cryptosystem assignment

 */	

import java.util.Scanner;
import java.math.BigInteger;

public class Main
{
	/**
	 * A small driver program to test basic functionality 
	 * @param args String array of commandline arguments
	 */
	public static void main (String [] args) {
		//test();
		//part1
		run(new ListKnapsackFactory());
		//part2
		System.out.println("------------------");
		System.out.println("Executing Part 2");
		System.out.println("------------------");

		run(new ArrayKnapsackFactory());
	}

	/**
	 * Performs the operations required for Part 1 and Part 2 of the assignment
	 * @param factory - Specifies which type of implementation will be used (List or Array)
	 */
	public static void run(KnapsackFactory factory){
		String stringToEncrypt="";

		MerkleHellmanKnapsack mhkObject = new MerkleHellmanKnapsack(factory);
		Scanner scanner = new Scanner(System.in);
		do {
        	System.out.print("Enter a string to encrypt: ");
        	stringToEncrypt = scanner.nextLine();
        } while (stringToEncrypt.length() > 80 || stringToEncrypt.length() <=1);
        System.out.println("Clear text entered: "+stringToEncrypt);
        System.out.println("Number of bytes in input string: "+ stringToEncrypt.getBytes().length);

        // Encrypt the string
        BigInteger encryptInt = mhkObject.encrypt(stringToEncrypt.getBytes()); 
		System.out.println(stringToEncrypt +" is encrypted as this single large integer: "+ encryptInt);
        
		// Decrypt the string
		byte [] decryptionResult = mhkObject.decrypt(encryptInt);
        System.out.println("Result of decryption: "+ new String(decryptionResult));

	}

	/**
	*	A small test driver based on the Wikipedia example
	*/	
	private static void test() {
		System.out.println("=========ListKnapsack=======");

		MerkleHellmanKnapsack mhkObject = new MerkleHellmanKnapsack(new ListKnapsackFactory());
		mhkObject.testKeyGeneration(new ListKnapsackFactory());

		String stringToEncrypt = "a";
		BigInteger encryptInt = mhkObject.encrypt(stringToEncrypt.getBytes()); 
		System.out.println(stringToEncrypt +" is encrypted as this single large integer: "+ encryptInt);
        
		// Decrypt the string
		byte [] decryptionResult = mhkObject.decrypt(encryptInt);
        System.out.println("Result of decryption: "+ new String(decryptionResult));

        System.out.println("=========ArrayKnapsack=======");

        MerkleHellmanKnapsack mhkObject2 = new MerkleHellmanKnapsack(new ArrayKnapsackFactory());
		mhkObject2.testKeyGeneration(new ArrayKnapsackFactory());

		BigInteger encryptInt2 = mhkObject2.encrypt(stringToEncrypt.getBytes()); 
		System.out.println(stringToEncrypt +" is encrypted as this single large integer: "+ encryptInt2);
        
		// Decrypt the string
		byte [] decryptionResult2 = mhkObject2.decrypt(encryptInt);
        System.out.println("Result of decryption: "+ new String(decryptionResult2));

	}
}