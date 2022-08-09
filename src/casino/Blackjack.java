package casino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Blackjack extends GameMechanics implements GameRequirements{
    public static HashMap<String, Integer> cardValues = new HashMap<>();

    private static void blackjackValues() {
        cardValues.put("2", 2);
        cardValues.put("3", 3);
        cardValues.put("4", 4);
        cardValues.put("5", 5);
        cardValues.put("6", 6);
        cardValues.put("7", 7);
        cardValues.put("8", 8);
        cardValues.put("9", 9);
        cardValues.put("10", 10);
        cardValues.put("J", 10);
        cardValues.put("Q", 10);
        cardValues.put("K", 10);
        cardValues.put("A", 11);
    }

    public void runGame(Player player) {
        blackjackValues();
        System.out.println("\nWelcome to the Blackjack table!\nReady to play? y/n");
        String choice = makeChoice();
        if (choice.equals("y")) {
            playGame(player);
        } else {
            System.out.println("Come again next time!");
        }
    }

    public void playGame(Player player) {
        int bet = placeBet(player);
        boolean aceChange = false;
        DeckOfCards deck = new DeckOfCards();
        System.out.print("\nYour hand: ");
        ArrayList<Card> hand = deck.deal_up(2);
        System.out.print("\nComputer's hand: ");
        ArrayList<Card> compHand = deck.deal_up(1);
        int sum = 0;
        int compSum = 0;
        //basically if both cards are aces then the sum will start out as 12 
        for (Card c : hand) {
            if (c.getRank().equals("A") && ((cardValues.get(c.getRank()) + sum) > 21)) {
                cardValues.replace("A", 1);
                aceChange = true;
            }
            sum += cardValues.get(c.getRank());
        }
        if (sum == 21) {
            System.out.println("\nYou got 21! You will receive 3x the amount you bet with!");
            player.setFunds(player.getFunds() + (bet * 3));
            return;
        }
        System.out.println("\n\nCurrent player sum: " + sum);
        Scanner scan = new Scanner(System.in);
        while (sum <= 21) {
            System.out.println("Enter 1 to hit or 0 to stand.");
            int hitOrStand = scan.nextInt();
            boolean choiceValid = false;
            while (!choiceValid)
            {
                if (hitOrStand == 0 || hitOrStand == 1) {
                    choiceValid = true;
                } else {
                    System.out.println("Not valid. Try again.");
                    hitOrStand = scan.nextInt();
                }
            }
            if (hitOrStand == 0) {
                break;
            } else {
                //draws a card
                deck.add_card(hand);
                //edge cases for aces
                //if the drawn card is an ace and it'll send the sum over 21, change ace's value to 1
                if (hand.get(hand.size() - 1).getRank().equals("A") &&
                        ((cardValues.get(hand.get(hand.size() - 1).getRank()) + sum) > 21)) {
                    cardValues.replace("A", 1);
                    aceChange = true;
                //if one starting card is an ace and the drawn card sends the sum over 21 and ace value wasn't changed yet
                } else if (hand.get(0).getRank().equals("A") || hand.get(1).getRank().equals("A") 
                    && !(hand.get(0).getRank().equals("A") && hand.get(1).getRank().equals("A"))) {
                    if (cardValues.get(hand.get(hand.size() - 1).getRank()) + sum > 21) {
                        if (aceChange == false) {                 
                            sum -= 10;
                        }
                    }
                    cardValues.replace("A", 1);
                    aceChange = true;
                } 
                sum += cardValues.get((hand.get(hand.size() - 1)).rank);
                System.out.println("\n\nCurrent player sum: " + sum);
                if (sum > 21) {
                    System.out.println("\nYou lose! Play again next time!");
                    return;
                } else if (sum == 21) {
                    System.out.println("\nYou got 21! You will receive 3x the amount you bet with!");
                    player.setFunds(player.getFunds() + (bet * 3));
                    return;
                }
            }
        }
        cardValues.replace("A", 11);
        System.out.print("\n");
        deck.add_card(compHand);
        for (Card c : compHand) {
            if (c.getRank().equals("A") && ((cardValues.get(c.getRank()) + compSum) > 21)) {
                cardValues.replace("A", 1);
            }
            compSum += cardValues.get(c.getRank());
        }
        System.out.println("\nCurrent computer sum: " + compSum);
        while (!(compSum >= 17 && compSum <= 21)) {
            deck.add_card(compHand);
            if (compHand.get(compHand.size() - 1).getRank().equals("A") &&
                    ((cardValues.get(compHand.get(compHand.size() - 1).getRank()) + compSum) > 21)) {
                cardValues.replace("A", 1);
            }
            compSum += cardValues.get(compHand.get(compHand.size() - 1).getRank());
            System.out.println("\nCurrent computer sum: " + compSum);
            if (compSum > 21) {
                System.out.println("\nYou win! You will receive double the amount you bet with!");
                player.setFunds(player.getFunds() + (bet * 2));
                return;
            } else if (compSum == 21) {
                System.out.println("\nYou lose! Play again next time!");
                return;
            }
            System.out.print("\n");
        }

        if (sum > compSum) {
            System.out.println("\nYou win! You will receive double the amount you bet with!");
            player.setFunds(player.getFunds() + (bet * 2));
        } else if (sum < compSum) {
            System.out.println("\nYou lose! Play again next time!");
        } else {
            System.out.println("\nIt's a tie! You will receive the amount you bet with!");
            player.setFunds(player.getFunds() + bet);
        }
        cardValues.replace("A", 11);
    }
}