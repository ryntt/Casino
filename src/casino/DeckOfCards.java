package casino;

import java.util.ArrayList;

public class DeckOfCards
{
    public String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
    public ArrayList<Card> og_deck = new ArrayList<>();
    public ArrayList<Card> shuffled_deck = new ArrayList<>();

    //makes an automatically shuffled deck of cards
    public DeckOfCards()
    {
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                og_deck.add(new Card(ranks[j], suits[i]));
            }
        }
        //shuffle mechanism
        //from https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java
        //an ordered deck would be identical to this class without a shuffled mechanism
        while (og_deck.size() > 0) {
            int shuffled_index = (int) (Math.random() * og_deck.size());
            shuffled_deck.add(og_deck.remove(shuffled_index));
        }
    }

    //deals a hand of cards
    public ArrayList<Card> deal_up (int num_cards) {
        ArrayList<Card> hand = new ArrayList<>();
        int count = 0;
        while (count != num_cards) {
            hand.add(shuffled_deck.get(0));
            shuffled_deck.remove(0);
            count++;
        }
        for (int i = 0; i < hand.size() - 1; i++) {
            System.out.print("" + hand.get(i).getRank() + " - " + hand.get(i).getSuit() + ", ");
        }
        System.out.print(hand.get(hand.size()-1).getRank() + " - " + hand.get(hand.size()-1).getSuit());
        return hand;
    }

    //adds a card to the hand of cards
    public void add_card (ArrayList<Card> hand) {
        hand.add(shuffled_deck.get(0));
        shuffled_deck.remove(0);
        for (int i = 0; i < hand.size() - 1; i++) {
            System.out.print("" + hand.get(i).getRank() + " - " + hand.get(i).getSuit() + ", ");
        }
        System.out.print(hand.get(hand.size()-1).getRank() + " - " + hand.get(hand.size()-1).getSuit());
    }

}
