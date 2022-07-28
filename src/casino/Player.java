package casino;

public class Player {
    private String name;
    private int age;
    public int funds;

    /**
     * Initializes the player and their attributes
     * (name, age, money)
     *
     * @param name  name of player
     * @param age   age of player (21+)
     * @param funds amount of money player has
     */
    public Player(String name, int age, int funds) {
        this.name = name;
        this.age = age;
        this.funds = funds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public boolean hasCash() {
        return getFunds() > 0;
    }

    public String toString() {
        String nameText = "Player name: " + name;
        String ageText = "Player age: " + age;
        String fundsText;
        if (hasCash()) {
            fundsText = "Player has $" + funds;
        }
        else {
            fundsText = "Player has no money.";
        }
        return " Player Details\n" + nameText +"\n"+ageText+"\n"+fundsText;
    }

    public int hashCode() {
        return age+funds;
    }

    public int compareTo(Object o) {
        Player that = (Player)o;
        return this.hashCode() - that.hashCode();
    }

    public boolean equals(Object o) {
        Player that = (Player) o;
        return this.name.equals(that.name) && this.compareTo(that) == 0;
    }

}
