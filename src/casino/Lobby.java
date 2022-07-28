package casino;

import java.util.Scanner;

public class Lobby {

	//this is the actual main lobby of the casino
	public static void mainMenu() {
		System.out.println("\nMake a selection: " +
				"\n1. Try your hand at the slot machine!" +
				"\n2. Outmatch the dealer at blackjack!" +
				"\n3. Try your luck at bau cua ca cop, a Vietnamese gambling game!" +
				"\n4. Order food and drinks from Gibson's!" +
				"\n5. View info and balance" +
				"\n6. Leave the casino");
	}

	//this is the start of the execution of the program
	//initialize the player --> they "hang out" in the casino until they're finished
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		//create the player
		System.out.println("\nWelcome to Hyperspace Landing! Please enter your information " +
				"according to \nthe following prompts so we can register you in the system.");
		System.out.print("Enter your name: ");
		String name = scan.nextLine();
		System.out.print("Enter your age: ");
		String age;
		int ageChoice = 0;
		boolean choiceValid = false;
		do {
			age = scan.next();
			try {
				ageChoice = Integer.parseInt(age);
				if (ageChoice >= 21) {
					choiceValid = true;
				} else {
					System.out.println("You must be 21 to enter the casino");
					System.out.print("Enter your age: ");
				}
			} catch (Exception e) {
				System.out.println("Enter an integer");
				System.out.print("Enter your age: ");
			}
		} while (!choiceValid);
		System.out.print("Enter your funds: ");
		String funds;
		int fundsChoice = 0;
		choiceValid = false;
		do {
			funds = scan.next();
			try {
				fundsChoice = Integer.parseInt(funds);
				if (fundsChoice > 0) {
					choiceValid = true;
				} else {
					System.out.println("You can't play without money!");
					System.out.print("Enter your funds: ");
				}
			} catch (Exception e) {
				System.out.println("Enter an integer");
				System.out.print("Enter your funds: ");
			}
		} while (!choiceValid);
		Player player1 = new Player(name, ageChoice, fundsChoice);
		System.out.println("You're in! You may now explore the area.");
		//every time the person finishes with an aspect of the casino,
		//this while loop makes it so that they go to the main menu
		//(the lobby) every time, until they decide they want to leave the casino
		while (true) {
			//if they have no money they get kicked out
			if (!player1.hasCash())
			{
				System.out.println("You have no money left! Goodbye!");
				scan.close();
				System.exit(0);
			}
			mainMenu();
			String choice = "";
			int numChoice = 99;
			choiceValid = false;
			//basically, you input choice
			//the try catch watches out for a non integer input
			//if choice is non integer, the catch kicks in
			//	else, choice is converted to an integer
			//		if the integer (numChoice) is in the range, the loop breaks
			//		otherwise, the loop continues
			do {
				choice = scan.next();
				try {
					numChoice = Integer.parseInt(choice);
					if (numChoice >= 1) {
						choiceValid = true;
					} else {
						System.out.println("Enter a number between 1-6");
						mainMenu();
					}
				} catch (Exception e){
					System.out.println("Enter a valid number");
					mainMenu();
				}
			} while (!choiceValid);

			//play slots
			if (numChoice == 1) {
				SlotMachine newGame = new SlotMachine();
				newGame.runGame(player1);
			}
			//play blackjack
			else if (numChoice == 2) {
				Blackjack newGame = new Blackjack();
				newGame.runGame(player1);
			}
			//play bau cua ca cop
			else if (numChoice == 3) {
				BauCuaCaCop newGame = new BauCuaCaCop();
				newGame.runGame(player1);
			}
			//order food
			else if (numChoice == 4) {
				Gibsons.runRestaurant(player1);
			}
			//check info
			else if (numChoice == 5) {
				System.out.printf("Name: %s\nAge: %d\nBalance: $%d\n", player1.getName(), player1.getAge(),
						player1.getFunds());
			}
			//leave casino
			else if (numChoice == 6) {
				System.out.println("Goodbye! Please come again!");
				scan.close();
				System.exit(0);
			}
			else {
				System.out.println("Enter a number between 1-6");
			}
		}
	}
}
