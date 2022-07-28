package casino;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BauCuaCaCop extends GameMechanics implements GameRequirements{
    private static final String[] picList = {"Shrimp", "Crab", "Fish", "Rooster",
            "Gourd", "Deer"};

    public static HashMap<Integer, String> picMap = new HashMap<>();
    public static HashMap<String, Integer> betMap = new HashMap<>();

    private static void createMaps() {
        for (int i = 0; i < picList.length; i++) {
            picMap.put((i + 1), picList[i]);
        }

        for (int i = 0; i < picList.length; i++) {
            betMap.put(picList[i], 0);
        }
    }
    public void runGame(Player player) {
        createMaps();
        System.out.println("\nWelcome to Bau Cua Ca Cop!");
        System.out.println("For those of you unfamiliar with the game, here are the rules:\n");
        System.out.println("1. You will place your bets by putting as much money as \nyou want " +
                "on any of the six pictures. You may put money on multiple pictures.\n\n2. When " +
                "you're done placing your bets, three dice will be rolled.\n\n3. The pictures " +
                "that show up on the dice will determine how much money you will receive, \n" +
                "if any. For instance, if you put two dollars on crab and one crab is rolled \n" +
                "from the three dice, you will get your money back + 1x your bet. \n" +
                "If two crabs are rolled, you get your money back + 2x your bet. Tbe \n" +
                "same applies for three crabs rolled. This also applies if you put money on \n" +
                "two or more different pictures and two/three pictures on the dice match. \n\n" +
                "4. If none of the dice match with the pictures you bet on, you don't win anything.\n");
        System.out.println("Ready to play? y/n");
        //if the person types y, they play
        //otherwise, they don't play
        String choice = makeChoice();
        if (choice.equals("y")) {
            playGame(player);
        } else {
            System.out.println("Come again next time!");
        }
    }

    public void playGame(Player player) {
        //wrote a different bet mechanism because this game is different
        Scanner scan = new Scanner(System.in);
        System.out.println("\nLet's play! To put money on a picture, enter its corresponding number." +
                "\nYou can also change your bet on a picture by typing the picture's number again \n" +
                "and entering a new amount. Type 0 to stop placing bets.\n\n");
        int choice;
        do {
            for (Map.Entry<String, Integer> x : betMap.entrySet()) {
                if (x.getValue() != 0) {
                    System.out.println("" + x.getKey() + ": " + x.getValue());
                }
            }
            System.out.println("0. Stop placing bets\n1. Place a bet on shrimp\n2. Place a bet on crab\n" +
                    "3. Place a bet on fish\n4. Place a bet on rooster\n5. Place a bet on gourd\n6. " +
                    "Place a bet on deer");
            choice = scan.nextInt();
            if (choice >= 1 && choice <= 6) {
                System.out.print("\n");
                int bet = placeBet(player);
                betMap.replace(picMap.get(choice), bet);
                System.out.print("\n");
            } else if (choice != 0) {
                System.out.println("Choice invalid. Try again\n");
            }
        } while (choice != 0);
        System.out.println("The dice are rolling...\n");
        String firstDie = picList[(int) (Math.random() * 6)];
        String secondDie = picList[(int) (Math.random() * 6)];
        String thirdDie = picList[(int) (Math.random() * 6)];
        System.out.printf("%s  ||  %s  ||  %s\n", firstDie, secondDie, thirdDie);
        for (Map.Entry<String, Integer> x : betMap.entrySet())
        {
            if (x.getValue() != 0)
            {
                if (x.getKey().equals(firstDie) && x.getKey().equals(secondDie) && x.getKey().equals(thirdDie))
                {
                    System.out.println("All three dice are " + x.getKey() + "! " +
                            "You will get your money back + 3x your bet.");
                    player.setFunds(player.getFunds() + (4 * x.getValue()));
                } else if ((x.getKey().equals(firstDie) && x.getKey().equals(secondDie)) ||
                        (x.getKey().equals(secondDie) && x.getKey().equals(thirdDie)) ||
                        (x.getKey().equals(firstDie) && x.getKey().equals(thirdDie)))
                {
                    System.out.println("Two of the dice are " + x.getKey() + "! You will get your money back + " +
                            "2x your bet.");
                    player.setFunds(player.getFunds() + (3 * x.getValue()));
                } else if (x.getKey().equals(firstDie) || x.getKey().equals(secondDie) || x.getKey().equals(thirdDie))
                {
                    System.out.println("One die is a " + x.getKey() + "! " +
                            "You will get your money back + 1x your bet.");
                    player.setFunds(player.getFunds() + (2 * x.getValue()));
                } else
                {
                    System.out.println("You didn't roll a " + x.getKey() + ". You won't receive anything.");
                }
            }
        }
    }

}
