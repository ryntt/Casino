package casino;

import java.util.HashMap;
import java.util.Scanner;

public class FoodAndDrinks {
    //allows the player to order from a menu of items until they type that they're done
    public static void order (Player player, HashMap<String, Integer> foodMap) {
        System.out.println("Please view the selections and type your choice (case-sensitive). Type \"done\" to continue.");
        Scanner scan = new Scanner (System.in);
        int money = player.getFunds();
        String foodType = scan.nextLine();
        boolean finished = false;
        //the player will keep typing menu items and their funds will deplete
        //program keeps going until player types they're done
        while (!finished) {
            if (foodType.equals("done")) {
                finished = true;
            }
            else if (!foodMap.containsKey(foodType)) {
                System.out.println("Please try again or type \"done\" to continue.");
                foodType = scan.nextLine();
            }
            else {
                if (money - foodMap.get(foodType) <= 0) {
                    System.out.println("You don't have enough money to purchase this item!" +
                            " Make another selection or type \"done\" to continue.");
                }
                else {
                    money = money - foodMap.get(foodType);
                    System.out.println("You have $" + money + " remaining.");
                }
                foodType = scan.nextLine();
            }
        }
        //this will update player funds after spending their money on food
        player.setFunds(money);
    }

    //allows the player to decide if they want to order
    //y = yes, n = no
    public static void orderChoice(Player player, HashMap<String, Integer> foodMap) {
        Scanner scan = new Scanner(System.in);
        String foodChoice = scan.nextLine();
        boolean finished = false;
        while (!finished) {
            if (foodChoice.equals("y")) {
                order(player, foodMap);
                finished = true;
            }
            else if (foodChoice.equals("n")) {
                finished = true;
            }
            else {
                System.out.println("Type \"y\" to order or \"n\" to continue.");
                foodChoice = scan.nextLine();
            }
        }
    }
}
