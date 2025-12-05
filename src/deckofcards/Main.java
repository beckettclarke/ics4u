package deckofcards;

public class Main {
  public static void main (String args[]){
    Deck hand = new Deck();
    System.out.println(hand.addCard());
    System.out.println(hand.addCard());
    System.out.println(hand.addCard());
    System.out.println(hand.addCard());
    System.out.println(hand.listDeck());
    hand.shuffle();
    System.out.println("Shuffled: " + hand.listDeck());
  }
}
