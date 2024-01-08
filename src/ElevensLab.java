import java.util.ArrayList;

public class ElevensLab {
    public static void main(String[] args)  {
        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
        int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
        for (int i = 0; i< suits.length; i++){
            for (int j = 0; j<pointValues.length; j++){
                cards.add(new Card(pointValues[j],  suits[i]));
            }
        }
        Deck deck =new Deck(cards);
        new ElevensBoard(deck, 0, 0);
    }
}
