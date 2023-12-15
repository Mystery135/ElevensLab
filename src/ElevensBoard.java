import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevensBoard {
    public final static int BOARD_HEIGHT = 3;
    public final static int BOARD_WIDTH = 3;
    public final static int BOARD_SIZE = BOARD_HEIGHT*BOARD_WIDTH;

    public final static Map<Integer, String> VALUE_TO_NAME = Map.of(
            1, "A",
            21, "J",
            22, "Q",
            23, "K"
    );
    private Card[] cardsInPlay;
    private Deck deck;
    public ElevensBoard(Deck deck){
        this.deck = deck;
        deck.shuffle();
        cardsInPlay = new Card[BOARD_SIZE];
        for (int i = 0; i<BOARD_SIZE; i++){
            cardsInPlay[i] = deck.deal();
        }

    }
    private void dealMyCards(){
        for (int i = 0; i<BOARD_SIZE; i++){
            cardsInPlay[i] = deck.deal();
        }
    }
    public boolean processMove(ArrayList<Integer> cards){
        if (cards.size() > 4 || cards.size() < 2){
            return false;
        }

        if (cards.size() == 2) {
            if (cardsInPlay[cards.get(0) - 1].pointValue() + cardsInPlay[cards.get(1) - 1].pointValue() == 11) {
                cardsInPlay[cards.get(0) - 1] = deck.deal();
                cardsInPlay[cards.get(1) - 1] = deck.deal();
            } else {
                return false;
            }
        }else if (cards.size() == 3){
            if (cardsInPlay[cards.get(0) - 1].pointValue() + cardsInPlay[cards.get(1) - 1].pointValue() + cardsInPlay[cards.get(2) - 1].pointValue() == 11) {
                cardsInPlay[cards.get(0) - 1] = deck.deal();
                cardsInPlay[cards.get(1) - 1] = deck.deal();
                cardsInPlay[cards.get(2) - 1] = deck.deal();
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean containsJQK(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return false;
    }
    private boolean containsPairSum11(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return false;
    }
    public boolean anotherPlayIsPossible() {
        if (hasPairSum11(cardsInPlay)){
            return true;
        }
        if (hasJQK(cardsInPlay)){
            return true;
        }
        return false;
    }
    public boolean hasPairSum11(Card[] cards){
        for (int i = 0; i<cards.length; i++){
            if (cards[i].pointValue() + cards[i+1].pointValue() == 11){
                return true;
            }
        }
        return false;
    }
    public boolean hasJQK(Card[] cards){
        boolean J = false;
        boolean Q = false;
        boolean K = false;

        for (int i = 0; i<cards.length; i++){
            if (cards[i].pointValue() == 21){
                J = true;
            }         if (cards[i].pointValue() == 22){
                Q = true;
            }         if (cards[i].pointValue() == 22){
                K = true;
            }
        }
        return J && Q && K;
    }
    public boolean isLegal(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return false;
    }
    public boolean gameIsWon() {
//        if (deck.isEmpty()) {
//           if (!cardsInPlay.isEmpty()){
//               return false;
//           }
//            return true;
//        }
        return false;
    }
    @Override
    public String toString(){
        int x = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<cardsInPlay.length; i++){
            builder.append(Utils.getCardSymbol(cardsInPlay[i]));
            x++;
            if (x == BOARD_WIDTH){
                x = 0;
                builder.append("\n");
            }else{
                builder.append(" ");
            }
        }
        return builder.toString();
    }



}
