import java.util.Scanner;

public class Calc {

	private int firstNumber = 0;
	private int secondNumber = 0;
	private Scanner input = new Scanner(System.in);

	/*
	 * operation specification: first number + second number
	 */
	public int add() {
		return firstNumber + secondNumber;
	}

	/*
	 * operation specification: first number - second number
	 */
	public int subtract() {
		return firstNumber - secondNumber;
	}

	/*
	 * operation specification: first number * second number
	 */
	public int multiply() {
		return firstNumber * secondNumber;
	}

	/*
	 * operation specification: first number toThe second number
	 */
	public int powerOf() {
		// Note that negative exponents will cause rounding to 0
		return (int)Math.pow(firstNumber, secondNumber);
	}

	public int showMenu() {
		System.out.println("Calculator Functions:");
		System.out.println("Addition........1");
		System.out.println("Subtraction.....2");
		System.out.println("Multiplication..3");
		System.out.println("PowerOf.........4");
		System.out.println("Exit............0");
		System.out.println("");
		System.out.println("enter the option:");
		return input.nextInt();
	}

	public void getTwoNumbers() {
		System.out.print("Enter first number: ");
		firstNumber = input.nextInt();
		System.out.print("  Enter second number: ");
		secondNumber = input.nextInt();
		System.out.println();
	}

	public static void main(String[] args) {

		Calc calculator = new Calc();

		// run the calculator engine
		do {
			int option = calculator.showMenu();
			int result = 0;

			if (option == 0) {
				// break the infinite loop
				System.out.println("Bye bye...");
				System.exit(0);

			} else if (option == 1) {

				calculator.getTwoNumbers();
				result = calculator.add();

			} else if (option == 2) {

				calculator.getTwoNumbers();
				result = calculator.subtract();

			} else if (option == 3) {

				calculator.getTwoNumbers();
				result = calculator.multiply();

			} else if (option == 4) {

				calculator.getTwoNumbers();
				result = calculator.powerOf();

			} else {
				System.out.println("invalid option");
			}

			System.out.println("Result: " + result);
			System.out.println("press [Enter] to continue");
			(new Scanner(System.in)).nextLine();

			// create an infinite loop
		} while (true);
	}
}
