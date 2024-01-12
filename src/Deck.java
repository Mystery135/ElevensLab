import java.util.ArrayList;
//Represents a real life deck. Can deal & shuffle.
public class Deck {
    private final ArrayList<Card> cards;
    private final ArrayList<Card> discardedCards;

    public Deck(ArrayList<Card> cards){
        this.discardedCards = new ArrayList<>();
        this.cards = cards;
    }
    @Override
    public String toString(){
        return cards.toString();
    }


    public void efficientSelectionShuffle(){
        //Switches a card at a random location with the card an index i. Goes from cards.size to 0.
        for (int i = cards.size()-1; i>=0; i--){
            int rand = (int) (Math.random()*i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
    }
    public Card deal(){
        //Adds cards to the discardedCards arraylist and removes cards from the cards arraylist.
        if (isEmpty()){
            return null;
        }
        discardedCards.add(cards.get(cards.size()-1));
        cards.remove(cards.size()-1);
        return discardedCards.get(discardedCards.size()-1);
    }

    public int size(){
        return cards.size();
    }
    public boolean isEmpty(){
        return cards.isEmpty();
    }


    @Override
    public Deck clone(){
        //makes a clone of this deck.
        ArrayList<Card> deckCards = new ArrayList<>(cards);
        return new Deck(deckCards);
    }
}
