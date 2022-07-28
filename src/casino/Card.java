package casino;

public class Card
{
    public String rank;
    public String suit;

    public Card (String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank()
    {
        return rank;
    }

    public void setRank(String rank)
    {
        this.rank = rank;
    }

    public String getSuit()
    {
        return suit;
    }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }
}
