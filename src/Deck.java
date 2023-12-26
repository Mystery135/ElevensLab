import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> discardedCards;

    public Deck(ArrayList<Card> cards){
        this.discardedCards = new ArrayList<>();
        this.cards = cards;
    }
    @Override
    public String toString(){
        return cards.toString();
    }

    public void efficientSelectionShuffle() {
        for (int k = cards.size() - 1; k >= 0; k--) {
            int r = (int) (Math.random() * k);
            Card tmp = cards.get(r);
            cards.set(r, cards.get(k));
            cards.set(k, tmp);
        }
    }
    public void perfectShuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        int deckSize = cards.size();
        for (int i = 0; i<deckSize/2; i++){
            shuffled.add(cards.get(0));
            cards.remove(cards.get(0));
        }
        int shuffledSize = shuffled.size();
        int x = 0;
        for (int i = 0; i<shuffledSize; i++){
            cards.add(x, shuffled.get(0));
            shuffled.remove(0);
            x+=2;
        }
    }
        public void efficientSelectionShuffle1(){
        int deckSize = cards.size();
        for (int i = deckSize-1; i>0; i--){
            int rand = (int) (Math.random()*i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
    }
    public Card deal(){
        if (isEmpty()){
            return null;
        }
        discardedCards.add(cards.get(cards.size()-1));
        cards.remove(cards.size()-1);
        return discardedCards.get(discardedCards.size()-1);
    }

    public void selectionShuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        int deckSize = cards.size();
        for (int k = 0; k<deckSize; k++){
            int rand = (int) (Math.random()*cards.size());
            shuffled.add(cards.get(rand));
            cards.remove(rand);
        }
        cards = shuffled;
    }
    public int size(){
        return cards.size();
    }
    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Deck clone(){
        ArrayList<Card> deckCards = new ArrayList<>(cards);
        return new Deck(deckCards);
    }
}
