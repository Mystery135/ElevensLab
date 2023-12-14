import java.util.ArrayList;
import java.util.Random;

public class ElevensLab {
    public static void main(String[] args) {
        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
        int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
        Random random = new Random();
        for (int i = 0; i<52; i++){
            int randPoint = random.nextInt(pointValues.length);
            int randSuit = random.nextInt(suits.length);
            cards.add(new Card(pointValues[randPoint], suits[randSuit]));
        }




    Deck deck =new Deck(cards);
//        System.out.println(deck);
//        System.out.println(deck.size());

//        System.out.println(deck);
        ElevensBoard game = new ElevensBoard(deck);
        System.out.println(game);


//        ArrayList<Card> perf = deck.perfectShuffle2();
//        System.out.println(perf);
//        System.out.println(perf.size());





    }
}
