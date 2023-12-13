import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> discardedCards;

    public Deck(ArrayList<Card> cards){
        this.cards = cards;
    }
    @Override
    public String toString(){
        return cards.toString();
    }
    public void shuffle(){

    }
    public ArrayList<Card> perfectShuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        for (int i = 0; i<cards.size()/2; i++){
           shuffled.add(cards.get(0));
           cards.remove(cards.get(0));
        }
        for (int i = shuffled.size(); i>0; i--){
                if (i % 2 == 0){
                    shuffled.add(i, cards.get(0));
                    cards.remove(0);
                }
        }
return shuffled;
    }
    public void efficientShuffle(){

    }
    public Card deal(){
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
}
