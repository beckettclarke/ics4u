package deckofcards;

public class Card {
  private int suit, rank;
  // 1 = Spades, 2 = Clubs, 3 = Hearts, 4 = Diamonds
  // 1 = Ace, 2-10 = numbers, 11 = Jack, 12 = Queen, 13 = King,
  public Card(){
    // If no card details are provided, use random suit/rank
    this.suit = (int)(Math.random() * 4) + 1;
    this.rank = (int)(Math.random() * 13) + 1;
  }
  public Card(int rank, int suit){
    // Use specified suit/rank if they are provided
    this.suit = suit;
    this.rank = rank;
  }
  public String stringify(){
    // Return the card's values as a string (e.g "Three of Spades")
    String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
    String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    // Uses the rank and suit to get the item in the word array above at that index
    return ranks[this.rank - 1] + " of " + suits[this.suit - 1];
  }
  // These methods were extremely difficult to make
  public int getSuit(){
    return this.suit;
  }
  public int getRank(){
    return this.rank;
  }
}
