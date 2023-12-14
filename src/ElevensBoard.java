import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevensBoard {
    public final static int BOARD_SIZE = 9;
    public final static int BOARD_HEIGHT = 3;
    public final static int BOARD_WIDTH = 3;
    public final static Map<Integer, String> VALUE_TO_NAME = Map.of(
            1, "Ace",
            21, "Jack",
            22, "Queen",
            23, "King"
    );
    private ArrayList<Card> cardsInPlay;
    private Deck deck;
    public ElevensBoard(Deck deck){
        this.deck = deck;
        deck.shuffle();
        cardsInPlay = new ArrayList<>();
        for (int i = 0; i<BOARD_SIZE; i++){
            cardsInPlay.add(deck.deal());
        }

    }
    private void dealMyCards(){
        for (int i = 0; i<BOARD_SIZE; i++){
            cardsInPlay.add(deck.deal());
        }
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
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return false;
    }
    public boolean isLegal(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        return false;
    }
    public boolean gameIsWon() {
        if (deck.isEmpty()) {
           if (!cardsInPlay.isEmpty()){
               return false;
           }
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        int x = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<cardsInPlay.size(); i++){
//            if (valueToName.containsKey(cardsInPlay.get(i).pointValue())){
//                builder.append(valueToName.get(cardsInPlay.get(i).pointValue()));
//            }else{
//                builder.append(cardsInPlay.get(i).pointValue()+ );
//            }


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
