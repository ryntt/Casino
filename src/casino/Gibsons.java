package casino;

import java.util.HashMap;
import java.util.Map;

public class Gibsons extends FoodAndDrinks{

    //creates the menus for the different items
    //the four methods after these initialized HashMaps put the menu items in the menus
    public static HashMap<String, Integer> appMenu;
    public static HashMap<String, Integer> burgerMenu;
    public static HashMap<String, Integer> drinkMenu;
    public static HashMap<String, Integer> alcoholMenu;

    public static void allAppetizers() {
        appMenu = new HashMap<>();
        appMenu.put("Classic Fries", 3);
        appMenu.put("Garlic Fries", 4);
        appMenu.put("Mozzarella Sticks", 6);
        appMenu.put("Chicken Wings", 8);
        appMenu.put("Fried Pork Rinds", 8);
        appMenu.put("Truffle Fries", 9);
        appMenu.put("Calamari Rings", 10);
        appMenu.put("Scotch Eggs", 12);
        appMenu.put("Oysters Rockefeller", 20);
        appMenu.put("Jewels of the Sea", 35);
        System.out.printf("%20s%n", "Appetizers");
        for (Map.Entry<String, Integer> x : appMenu.entrySet())
        {
            System.out.printf("%-30s $%d%n", x.getKey(), x.getValue());
        }
        System.out.println("\n");
    }

    public static void allBurgers() {
        burgerMenu = new HashMap<>();
        burgerMenu.put("The OG", 11);
        burgerMenu.put("Golden OG", 14);
        burgerMenu.put("Vegan Delight", 15);
        burgerMenu.put("Gunslinger", 18);
        burgerMenu.put("Pacifica", 19);
        burgerMenu.put("Pride of New Orleans", 19);
        burgerMenu.put("Butcher's Joy", 23);
        burgerMenu.put("Johnny Cash", 23);
        burgerMenu.put("Double Truffle", 35);
        burgerMenu.put("San Fransokyo", 127);
        System.out.printf("%20s%n", "Burgers");
        for (Map.Entry<String, Integer> x : burgerMenu.entrySet())
        {
            System.out.printf("%-30s $%d%n",  x.getKey(), x.getValue());
        }
        System.out.println("\n");
    }

    public static void allNonAlcoholicDrinks() {
        drinkMenu = new HashMap<>();
        drinkMenu.put("Water Bottle", 3);
        drinkMenu.put("Can of Soda", 4);
        drinkMenu.put("Soda Freeze", 6);
        drinkMenu.put("Italian Soda", 7);
        drinkMenu.put("Earl Grey Milk Tea w/ Boba", 7);
        drinkMenu.put("DIY Milkshake", 8);
        drinkMenu.put("Arnold Palmer", 10);
        drinkMenu.put("Virgin Pina Colada", 10);
        drinkMenu.put("Virgin Margarita", 10);
        drinkMenu.put("Shirley Temple", 12);
        System.out.printf("%27s%n", "Non-Alcoholic Drinks");
        for (Map.Entry<String, Integer> x : drinkMenu.entrySet())
        {
            System.out.printf("%-30s $%d%n", x.getKey(), x.getValue());
        }
        System.out.println("\n");
    }

    public static void allAlcoholicDrinks() {
        alcoholMenu = new HashMap<>();
        alcoholMenu.put("Bottled Beer", 3);
        alcoholMenu.put("Draft Beer", 5);
        alcoholMenu.put("Moscow Mule", 9);
        alcoholMenu.put("Glass of Wine", 9);
        alcoholMenu.put("Pina Colada", 10);
        alcoholMenu.put("Margarita", 12);
        alcoholMenu.put("Shot Line-Up", 12);
        alcoholMenu.put("Champagne Glass", 14);
        alcoholMenu.put("Bottle of Wine", 30);
        alcoholMenu.put("Champagne Bottle", 62);
        System.out.printf("%24s%n", "Alcoholic Drinks");
        for (Map.Entry<String, Integer> x : alcoholMenu.entrySet())
        {
            System.out.printf("%-30s $%d%n", x.getKey(), x.getValue());
        }
        System.out.println("\n");
    }

    //allows the player to decide if they want to place orders for specific items
    public static void placeOrder(Player player)
    {
        System.out.println("Would you like any non-alcoholic drinks? Type \"y\" to order or \"n\" to continue.");
        allNonAlcoholicDrinks();
        orderChoice(player, drinkMenu);
        System.out.println("Would you like any alcoholic drinks? Type \"y\" to order or \"n\" to continue.");
        allAlcoholicDrinks();
        orderChoice(player, alcoholMenu);
        System.out.println("Would you like any appetizers to start off with? Type \"y\" to order or \"n\" to continue.");
        allAppetizers();
        orderChoice(player, appMenu);
        System.out.println("Would you like any burgers? Type \"y\" to order or \"n\" to continue.");
        allBurgers();
        orderChoice(player, burgerMenu);
    }

    //executes the actual restaurant program
    public static void runRestaurant(Player player)
    {
        System.out.println("\nWelcome to Gibson's!");
        placeOrder(player);
        System.out.println("Thank you for visiting! Please come again!");
        System.out.println("You have $" + player.getFunds() + " remaining.");
    }
}
