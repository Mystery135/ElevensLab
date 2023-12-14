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

    public void shuffle() {
        for (int k = cards.size() - 1; k >= 0; k--) {
            int r = (int) (Math.random() * k);
            Card tmp = cards.get(r);
            cards.set(r, cards.get(k));
            cards.set(k, tmp);
        }
    }
//    public ArrayList<Card> perfectShuffle(){
//        ArrayList<Card> shuffled = new ArrayList<>();
//        int deckSize = cards.size();
//        for (int i = 0; i<deckSize/2; i++){
//           shuffled.add(cards.get(0));
//           cards.remove(cards.get(0));
//        }
////        method that merges two array lists, alternating elements from both array lists java
//        System.out.println("shuffledsize : " + shuffled.size());
//        System.out.println("cardsize : " + cards.size());
//        System.out.println("shuffled: " + shuffled);
//        System.out.println("cards: " + cards);
//
//        int x = 0;
//        for (int i = 0; i<cards.size(); i++){
//            x+=2;
//          shuffled.add(x, cards.get(0));
//            System.out.println("addedCard: " + cards.get(0));
//          cards.remove(0);
//
//
//
//
////                if (i % 2 == 1){
////                    shuffled.add(i, cards.get((cards.size()-1)));
////                    cards.remove(0);
////                }
//        }
//return shuffled;
//    }
    public ArrayList<Card> perfectShuffle2(){
        ArrayList<Card> otherDeck = new ArrayList<>();
        ArrayList<Card> finalCards  = new ArrayList<>();
        int deckSize = cards.size();
        for (int i = 0; i<deckSize/2; i++){//if odd, otherDeck < cards
           otherDeck.add(cards.get(0));
           cards.remove(cards.get(0));
        }
        for (int i = 0; i<deckSize; i++){
             if (i % 2 == 0){
                 finalCards.add(cards.get(0));
                 cards.remove(0);
                 continue;
             }
             finalCards.add(otherDeck.get(0));
             otherDeck.remove(0);
        }
return finalCards;
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
