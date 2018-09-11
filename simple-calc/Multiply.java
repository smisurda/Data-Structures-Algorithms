/*
*  Samantha L. Misurda
*  Assignment 1
*  Multiply.java
*
*  Program generates single digit multiplication problems
*/

import java.util.*;

public class Multiply {
   private Random randomNumbers = new Random();

   private int answer; // the correct answer
   private int numCorrect = 0; // The number of correct responses
   private int numGuesses = 0; // The number of guesses the student has made

   // ask the user to answer multiplication problems
   public void quiz() {
      Scanner input = new Scanner( System.in );

      int guess; // the user's guess

      // display the question 
      createQuestion();
      System.out.println( "Enter your answer (-1 to exit):" );
      guess = input.nextInt();

      while ( guess != -1) {
         checkResponse(guess);
         System.out.println( "Enter your answer (-1 to exit):" );
         guess = input.nextInt();
       

         if(numGuesses == 10) {
            double percentage = calculatePercentageCorrect();
            System.out.println("Your percentage of correct answers was "+percentage*100+"%");
            if(percentage < .75) {
               System.out.println("Please ask your instructor for extra help. \n");
               // Reset the values for the next student
               numCorrect = 0;
               numGuesses = 0;
               // display a new question 
               createQuestion();
               System.out.println( "Enter your answer (-1 to exit):" );
               guess = input.nextInt();
            }   
         }
      }      
   } 

   // prints a new question and stores the corresponding answer
   public void createQuestion() {
      // get two random numbers between 0 and 9
      int digit1 = randomNumbers.nextInt(10);
      int digit2 = randomNumbers.nextInt(10);

      // multiply the two variables and store the result
      answer = digit1 * digit2;
      System.out.printf( "How much is %d times %d?\n", digit1, digit2 );
   } 

   // checks if the user answered correctly
   public void checkResponse(int userGuess) {
      // Increment number of guesses
      numGuesses++; 

      // Generate a random number to select a response
      int tutorResponse = randomNumbers.nextInt(4);

      if(userGuess != answer) {
         if(tutorResponse == 0) {
            System.out.println("No. Please try again.");
         }
         else if(tutorResponse == 1) {
            System.out.println("Wrong. Try once more.");
         }
         else if(tutorResponse == 2) {
            System.out.println("Don't give up!");
         }
         else {
            System.out.println("No. Keep trying.");
         }
      }   
      else {
         numCorrect++; // Increment correct guesses
         if(tutorResponse == 0) {
            System.out.println("Very good!");
         }
         else if(tutorResponse == 1) {
            System.out.println("Excellent!");
         }
         else if(tutorResponse == 2) {
            System.out.println("Nice work!");
         }
         else {
            System.out.println("Keep up the good work!");
         }
         // display another question 
         createQuestion();
      } 
   } 

   // Calculates the percentage of correct answers
   public double calculatePercentageCorrect() {
      return (double)numCorrect/(double)numGuesses;
   }
} 

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
