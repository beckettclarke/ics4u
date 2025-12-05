package deckofcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private List<Card> cards = new ArrayList<>();

  public String addCard(){
    // Add a random card when no specifications are provided, and return it
    Card adding = new Card();
    cards.add(adding);
    return adding.stringify();
  }
  public String addCard(int a, int b){
    // Add a card with provided suit and rank, and return it
    Card adding = new Card(a, b);
    cards.add(adding);
    return adding.stringify();
  }
  public void shuffle(){
    // Randomize the collection of cards in the deck
    Collections.shuffle(cards);
  }
  public String dealCard(){
    // Gets the first card from the deck and saves it to a variable (So it can be returned after removal)
    Card deal = cards.get(0);
    // Removes it from the deck
    cards.remove(0);
    // Returns the drawn card
    return deal.stringify();
  }
  public String listDeck(){
    // Creates new string fot the deck to be stored in
    String decklist = "";
    // For each card, add its stringified name to the deck
    for (int i = 0; i < cards.size(); i++){
      decklist += cards.get(i).stringify() + ", ";
    }
    return decklist;
  }
}
