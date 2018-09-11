/*  An object of class PairOfDice represents a pair of dice,
 *  where each die shows a number between 1 and 6.  The dice
 *  can be rolled, which randomizes the numbers showing on the
 *  dice.
*/

public class PairOfDice {

    private int die1;
    private int die2;

    // Constructor.  Rolls the dice, so that they initially
    // show some random values.
    public PairOfDice() {
        roll();
    }

    // Roll the dice by setting each of the dice to be
    // a random number between 1 and 6.
    public void roll() {
        die1 = (int)(Math.random()*6) + 1;
        die2 = (int)(Math.random()*6) + 1;
    }

    // Return the number showing on the first die.
    public int getDie1() {
        return die1;
    }

    // Return the number showing on the second die.
    public int getDie2() {
            return die2;
    }

    // Return the total showing on the two dice.
    public int getTotal() {
        return die1 + die2;
    }
}
