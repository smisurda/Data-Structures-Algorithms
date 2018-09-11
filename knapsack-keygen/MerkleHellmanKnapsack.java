/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 2
 * MerkleHellmanKnapsack.java
 *
 * The class implements operations related to key generation, encryption, and decryption using the Merkle-Hellman Knapsack Cryptosystem.
 */		
import java.util.Random;
import java.math.BigInteger;

public class MerkleHellmanKnapsack
{

    private static final boolean DEBUG = true;
    private int MAX_RANDOM_BITS = 10;

	private Knapsack w; // Holds superincreasing sequence
    private Knapsack b; // Holds public key material for encryption
    private BigInteger r;
	private BigInteger q;
	private Random rand = new Random();
	private static final int inputSize = 80*8;
    
    /**
	 * Constructs the default Merkle-Hellman Knapsack object
     * @param f Specifies which implementation should be built
	 */
	public MerkleHellmanKnapsack(KnapsackFactory f) {
		w = f.createKnapsack(); 
		b = f.createKnapsack(); 

        generateKeys(inputSize); 

	}

    /**
    * @param input - The array of bytes in the string to encrypt
    * @return The encrypted string
    * Transforms a plaintext string into a BigInteger
    * Big Theta Best: theta(n) - where n is the number of bits in the input string
    * Big Theta Worst: theta(n)
    * Preconditions: You have a plaintext string greater than 0, but less than 80 characters
    * Postconditions: This "message" has been encrypted as a BigInteger, representing ciphertext
    */
	public BigInteger encrypt(byte[] input) {
        BigInteger encryptedMessage = new BigInteger("0");

        BigInteger curr = b.getHead();
        for (int i = 0; i < input.length; i++) {
            int inputByte = input[i];
            for (int j = 0; j < 8; j++) {
                if ((inputByte & (1 << (7-j))) != 0) { // Iterate through each bit of each byte, if bit is set, add in B
                    encryptedMessage = encryptedMessage.add(curr);
                }
                curr = b.getNext();
            }
            
        }
        
        return encryptedMessage;

	}	

    /**
    * Transforms the encrypted ciphertext into the original string that was entered
    * Theta Worst Case: theta(n^2), where n is the number of bytes
    * Theta Best Case: theta(n^2)
    * The runtime is influenced by the multiplication operation, as implemented in BigInteger
    * Preconditions: You have an encrypted String, stored as a BigInteger
    * Postconditions: The original plaintext is presented
    * @param output - the encrypted string
    * @return A decrypted byte array of the message 
    */
	public byte[] decrypt(BigInteger output) {

        BigInteger temp1 = output.mod(q);
        BigInteger s = r.modInverse(q);
        BigInteger temp2 = temp1.multiply(s);

        
        BigInteger value = temp2.mod(q);

       
        int[] bitMask = new int[inputSize];


        w.reverse(); // reverse the list to have numbers in decreasing order
        BigInteger curr = w.getHead();
        for (int i = inputSize - 1; value.compareTo(BigInteger.ZERO) != 0; i--) {
            if (value.compareTo(curr) >= 0) {
                value = value.subtract(curr);
                bitMask[i] = 1;
            }
            curr = w.getNext();
        }

        byte[] decrypted = new byte[bitMask.length / 8];
        byte mask = 0x01;

        for (int i = 0; i < bitMask.length; i++) {
            if (bitMask[i] == 1) {
                decrypted[i / 8] = (byte) (decrypted[i / 8] | (mask << (7 - i % 8)));
            }
            mask = 0x01;
        }

        return decrypted;
    }


	/**
	 * Helper function to generate public and private keys based on the size of the input string.  
     * Big Theta Best: theta(n^2), where n is the number of bytes 
     * Big Theta Worst: theta(n^2)
     * theta(n) from nextSuperIncreasingNumber() being executed n times
     * Preconditions: You have a Merkle-Hellman object, with a constructed w and b list
     * Postconditions: a list b of super increasing numbers is populated
	 * @param numBytes - The number of bytes in the input
	 */
	
	private void generateKeys(int numBytes) {
        BigInteger firstNumber = new BigInteger(MAX_RANDOM_BITS, rand);

        //We need a natural number, so if the random start is less than 1, try again
        while(firstNumber.compareTo(BigInteger.ONE) < 0) {
            firstNumber = new BigInteger(MAX_RANDOM_BITS, rand);
        }

        MAX_RANDOM_BITS+=2;

        w.addNodeAtEnd(firstNumber);

        for (int i = 1; i < numBytes; i++) {
            BigInteger nextNumber = nextSuperIncreasingNumber(w);
            w.addNodeAtEnd(nextNumber);
        }
        
        q = nextSuperIncreasingNumber(w);
       
        do {
            r = new BigInteger(MAX_RANDOM_BITS, rand);
        } while ((r.compareTo(BigInteger.ZERO) > 0) && (q.gcd(r).compareTo(BigInteger.ONE) != 0));

        // Generate b such that b = w * r mod q
        BigInteger curr = w.getHead();
        for (int i = 0; i < numBytes; i++) {
            b.addNodeAtEnd(curr.multiply(r).mod(q));
            curr = w.getNext();
        }

    }

    /**
     * Helper function to generate the next number in the sequence
     * @param sequenceList - the list of numbers in the super increasing number list
     * Big Theta Best: theta(n), where n is the number of items in the list
     * Big Theta Worst: theta(n)
     * rand.nextInt() (which is used for MAX_RANDOM_BITS) is theta(1), but executed n times (where n is number of bits)
     * Preconditions: You have a list of super increasing integers
     * Postconditions: The newly generated value is appended to the list 
     * @return The next number in the sequence
     */
    private BigInteger nextSuperIncreasingNumber(Knapsack sequenceList) {
        BigInteger curr = sequenceList.getHead();
        BigInteger sum = new BigInteger("0"); 

        while (curr != null) {
            sum = sum.add(curr);
            curr = sequenceList.getNext();
        }

        BigInteger nextNumber = new BigInteger(MAX_RANDOM_BITS, rand);

        //Try to generate a number who is larger than the sum of all of the previous numbers
        while(nextNumber.compareTo(sum) <= 0) {
            nextNumber = new BigInteger(MAX_RANDOM_BITS, rand);
        }

        MAX_RANDOM_BITS+=2;

        return nextNumber;
    }

     /**
    *   Test function using the example from Wikipedia
    * @param factory - Which implementation is used
    */
    public void testKeyGeneration(KnapsackFactory factory) {

        w = factory.createKnapsack(); 
        b = factory.createKnapsack(); 

        int[] keys = {2, 7, 11, 21, 42, 89, 180, 354};

        for(int i=0;i<keys.length;i++) {

            w.addNodeAtEnd(new BigInteger(""+keys[i]));
        }

        q = new BigInteger("881");
        r = new BigInteger("588");

        BigInteger curr = w.getHead();
        for (int i = 0; i < w.countNodes(); i++) {
            b.addNodeAtEnd(curr.multiply(r).mod(q));
            curr = w.getNext();
        }

        if(DEBUG) {
            System.out.println("b: " + b);
        }

    }
}