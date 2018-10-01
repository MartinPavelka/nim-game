package nim;

import java.util.*;

public class Pile {
	private int number;
	private String name;
	private Random rand = new Random();
	Scanner scan = new Scanner(System.in);

	Pile(String i) {
		name = i;
		number = rand.nextInt(4) + 3;
	}

	void remove(int amount) {
		boolean valid = false;
		do {
			if (amount > 0 && number - amount >= 0) {
				number = number - amount;
				valid = true;
			} else {
				boolean inputError = true;
				do {
					System.out.println("You cannot remove this amount, try again");
					try {
						amount = scan.nextInt();
						inputError = false;
					} catch (Exception e) {
						System.out.println("You have to input a number");
						scan.next();
					}
				} while (inputError);
			}
		} while (!valid);
	}

	boolean isEmpty() {
		if (number == 0) {
			return true;
		}
		return false;
	}

	String getName() {
		return name;
	}

	int getNumber() {
		return number;
	}
}
