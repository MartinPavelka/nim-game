package nim;

import java.util.*;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static String player, player1, player2, choice;
	public static int graphics;
	static List<Pile> piles = new ArrayList<Pile>();

	public static void main(String[] args) throws java.io.IOException {
		System.out.println("This is a Nim game\n" + "\nThe rules are:\n" +
						"1) Remove any number of sticks from a pile\n" +
						"2) The last person to remove a stick looses the game\n");

		createPlayer();
		player = player1;
		initializePile();
		selectGraphics();

		playGame();

	}

	// Asks for name of player 1 and player 2
	static void createPlayer() {
		boolean isValid;
		do {
			isValid = false;
			System.out.print("Player 1, enter your name: ");
			player1 = scan.nextLine();
			if (player1.equals("") || player1.equals(" "))
				System.out.println("Invalid input, please input a name");
			else
				isValid = true;
		} while (!isValid);
		do {
			isValid = false;
			System.out.print("Player 2, enter your name: ");
			player2 = scan.nextLine();
			if (player2.equals("") || player2.equals(" "))
				System.out.println("Invalid input, please input a name");
			else
				isValid = true;
		} while (!isValid);
	}

	// The whole game code
	static void playGame() {
		do {
			printPiles(graphics);

			System.out.println("\n" + player + ", choose a pile:");
			choice = scan.next();
			validateChoice(choice);

		} while (!isEnd());
		if (getPileSum() == 1) {
			System.out.println("There is only one thing you can do, " + player + ", your opponent wins the game");
		} else
			System.out.println("Congratulations, " + player + ", you won!");
	}

	// Checks if all piles are empty
	static boolean isEnd() {
		if (getPileSum() == 0)
			return true;
		else if (getPileSum() == 1) {
			return true;
		}
		return false;
	}

	// Switch current player... hopefully
	static void takeTurn() {
		if (player.equals(player1)) {
			player = player2;
		} else if (player.equals(player2)) {
			player = player1;
		}
	}

	/////////////////////////////////////////////
	////////////////// PILES //////////////////
	/////////////////////////////////////////////

	static void selectGraphics() {
		boolean inputError = true;
		do {
			System.out.println("In what graphical mode do you want to play in?\n" + "1) Text mode\n"
					+ "2) Horizontalni graphical\n" + "3) Vertical Graphical\n");
			try {
				graphics = scan.nextInt();
				inputError = false;
			} catch (Exception e) {
				System.out.println("Please choose either 1, 2 or 3");
				scan.next();
			}
		} while (inputError);
		if (graphics != 1 && graphics != 2 && graphics != 3) {
			System.out.println(graphics);
			System.out.println("Please choose either 1, 2 or 3");
			selectGraphics();
		}
	}

	// Prints pile names and their containments in selected graphics mode
	static void printPiles(int graphics) {
		if (graphics == 1) {

			if (isEnd()) {
				System.exit(0);
			} else {
				for (Pile o : piles) {
					System.out.print(o.getName() + ": " + o.getNumber() + "\t");
				}
			}
		} else if (graphics == 2) {
			if (isEnd()) {
				System.exit(0);
			} else {
				for (Pile o : piles) {
					System.out.print("\n" + o.getName() + ": ");
					for (int i = 0; i < o.getNumber(); i++) {
						System.out.print("* ");
					}
				}
			}
		} else if (graphics == 3) {
			String[][] pilePrint = new String[7][4];
			for (int x = 0; x < piles.size(); x++) {
				pilePrint[0][x] = piles.get(x).getName();
				for (int y = 1; y <= piles.get(x).getNumber(); y++) {
					pilePrint[y][x] = "*";
				}
			}

			// Print pilePrint array
			for (int index = pilePrint.length-1; index >= 0; index--) {
			    for (int inner = 0; inner < pilePrint[index].length; inner++) {
			    	if (pilePrint[index][inner] != null) {
			    		System.out.print(pilePrint[index][inner] + " ");
			    	}
			    	else System.out.print(" " + " ");continue;
			    }
			    System.out.println();
			}
		}
	}

	// Creates number of piles based on user input
	static void initializePile() {
		System.out.println("\nDo you want to play with 3 or 4 piles?");
		boolean inputError = true;
		int pileNumber = 0;
		do {
			try {
				pileNumber = scan.nextInt();
				inputError = false;
			} catch (Exception e) {
				System.out.println("You need to input a number");
				scan.next();
			}
		} while (inputError);
		List<Character> chars = Arrays.asList('A', 'B', 'C', 'D');

		if (pileNumber == 3) {
			for (int i = 0; i < pileNumber; i++) {
				piles.add(new Pile(String.valueOf(chars.get(i))));
			}
		} else if (pileNumber == 4) {
			for (int i = 0; i < pileNumber; i++) {
				piles.add(new Pile(String.valueOf(chars.get(i))));
			}
		} else {
			System.out.println("You have to choose either 3 or 4");
			initializePile();
		}
	}

	// Gets a sum of piles for isEnd purpose
	static int getPileSum() {
		int pileSum = 0;

		for (Pile o : piles) {
			pileSum += o.getNumber();
		}
		return pileSum;
	}

	static void validateChoice(String choice) {
		boolean existujici = false;
		for (Pile o : piles) {
			if (choice.equals(o.getName()) && !o.isEmpty()) {
				existujici = true;
				System.out.println("How much do you want to remove?");
				int remove = scan.nextInt();
				o.remove(remove);
				takeTurn();
				break;
			} else
				continue;
		}
		if (existujici == false) {
			System.out.println("Choose an existing pile");
			printPiles(graphics);
			String newScan = scan.next();
			validateChoice(newScan);
		}
	}

}
