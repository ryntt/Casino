package casino;

import java.util.Scanner;

public class GameMechanics
{
    public static int placeBet(Player player) {
        Scanner scan = new Scanner (System.in);
        System.out.println("Place your bets now!");
        int bet = scan.nextInt();
        boolean validMoney = false;
        while (!validMoney) {
            if (bet > player.getFunds()) {
                System.out.println("You don't have enough money! Input another amount.");
                bet = scan.nextInt();
            } else {
                player.setFunds(player.getFunds() - bet);
                validMoney = true;
            }
        }
        return bet;
    }

    public static String makeChoice() {
        Scanner scan = new Scanner (System.in);
        String choice = scan.nextLine();
        boolean choiceValid = false;
        while (!choiceValid)
        {
            if (choice.equals("y") || choice.equals("n")) {
                choiceValid = true;
            } else {
                System.out.println("Type y or n.");
                choice = scan.nextLine();
            }
        }
        return choice;
    }


}
