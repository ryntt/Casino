package casino;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlotMachine extends GameMechanics implements GameRequirements{

    //this array is all the options for the slot machine's symbols
    private static final String[] slotList = {"Lightning", "Trident", "Helm", "Arrow",
            "Owl", "Perfume", "Wild", "Golden Fleece"};
    public static HashMap<String, Integer> slotPayouts = new HashMap<>();

    //lists the payouts from each slot machine symbol
    private static void slotPayouts()
    {
        slotPayouts.put("Lightning", 200);
        slotPayouts.put("Trident", 150);
        slotPayouts.put("Helm", 100);
        slotPayouts.put("Arrow", 70);
        slotPayouts.put("Owl", 60);
        slotPayouts.put("Perfume", 50);
        slotPayouts.put("Golden Fleece", 500);
        for (Map.Entry<String, Integer> x : slotPayouts.entrySet())
        {
            System.out.printf("%-25s %dx%n",  x.getKey(), x.getValue());
        }
        System.out.println("1 Wild = 10x\n2 Wilds = payout of the other symbol\n3 Wilds = 1000x\n");
    }

    //displays the layout of the slot machine
    //gives players the choice to play
    public void runGame(Player player)
    {
        System.out.println("\nWelcome to the Pillars of Mount Olympus!");
        System.out.println("The slot payouts are as follows:\n");
        slotPayouts();
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

    //executes the actual slot machine game
    public void playGame(Player player)
    {
        int bet = placeBet(player);
        //these three strings represent the three outputted symbols of a slot machine
        String firstCol = slotList[(int) (Math.random() * 8)];
        String secondCol = slotList[(int) (Math.random() * 8)];
        String thirdCol = slotList[(int) (Math.random() * 8)];
        System.out.printf("%s  ||  %s  ||  %s\n", firstCol, secondCol, thirdCol);
        String[] slotOutput = {firstCol, secondCol, thirdCol};
        int wildCount = 0;
        for (int i = 0; i < slotOutput.length; i++) {
            if (Objects.equals(slotOutput[i], "Wild")) {
                wildCount++;
            }
        }
        if (wildCount == 1) {
            System.out.println("You pulled one Wild and instantly won $" + (bet * 10) + "!");
            player.setFunds(player.getFunds() + (bet * 10));
        } else if (wildCount == 2) {
            String outlier = "";
            for (int i = 0; i < slotOutput.length; i++) {
                if (!Objects.equals(slotOutput[i], "Wild")) {
                    outlier += slotOutput[i];
                    break;
                }
            }
            System.out.println("You pulled two Wilds and one " + outlier + "! You've won $" +
                    (bet * slotPayouts.get(outlier)) + "!");
            player.setFunds(player.getFunds() + (bet * slotPayouts.get(outlier)));
        } else if (wildCount == 3) {
            System.out.println("You pulled all three Wilds! You've won the max payout of $" + (bet * 1000) + "!!!");
            player.setFunds(player.getFunds() + (bet * 1000));
        } else if (firstCol.equals(secondCol) && secondCol.equals(thirdCol)) {
            System.out.println("You pulled three " + firstCol + "s! You've won $" + (bet * slotPayouts.get(firstCol)) +
                    "!");
            player.setFunds(player.getFunds() + (bet * slotPayouts.get(firstCol)));
        } else {
            System.out.println("Better luck next time!");
        }
    }
}
