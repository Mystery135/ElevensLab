import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ElevensLab {
    public static void main(String[] args) {
        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
        int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
//        Random random = new Random();
        for (int i = 0; i<4; i++){
            for (int j = 0; j<2; j++){
                cards.add(new Card(2,  suits[i]));
                cards.add(new Card(9,  suits[i]));
//                cards.add(new Card(pointValues[j],  suits[i]));
            }
        }


//
//        for (int i = 0; i<52; i++){
//            int randPoint = random.nextInt(pointValues.length);
//            int randSuit = random.nextInt(suits.length);
//            cards.add(new Card(pointValues[randPoint], suits[randSuit]));
//        }





    Deck deck =new Deck(cards);

        ElevensBoard game = new ElevensBoard(deck);
        Scanner scanner = new Scanner(System.in);
        while (!game.gameIsWon()){
            System.out.println(game);
            System.out.println("What cards do you want to remove? Separate the indexes with a comma.");
            if (!game.processMove(Objects.requireNonNull(Utils.getInts(scanner, 0, 10)))){
                System.out.println("Invalid move! Try again!");
            }
        }
        System.out.println("You WON!");
    }
}
