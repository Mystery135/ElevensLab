import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ElevensLab {
    public static void main(String[] args)  {


        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
        int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
//        Random random = new Random();
        //i<2, j<2
        //i<suits.length, j<pointValues.length
        for (int i = 0; i<suits.length; i++){
            for (int j = 0; j<pointValues.length; j++){

                //Shuffle testing

//                cards.add(new Card(6,  suits[i]));
//                cards.add(new Card(7,  suits[i]));
//                cards.add(new Card(1,  suits[i]));
//                cards.add(new Card(2,  suits[i]));
//                cards.add(new Card(3,  suits[i]));
//                cards.add(new Card(4,  suits[i]));
//                cards.add(new Card(5,  suits[i]));
//                cards.add(new Card(1,  suits[i]));
//                cards.add(new Card(2,  suits[i]));
//                cards.add(new Card(3,  suits[i]));
//                cards.add(new Card(4,  suits[i]));

                //Face cards testing
//                cards.add(new Card(21,  suits[i]));
//                cards.add(new Card(22,  suits[i]));
//                cards.add(new Card(23,  suits[i]));



                //Normal
                cards.add(new Card(pointValues[j],  suits[i]));
            }
        }





//        cards.add(new Card(23,  suits[2]));
        Deck deck =new Deck(cards);

        ElevensBoard game = new ElevensBoard(deck, 0, 0);
        Scanner scanner = new Scanner(System.in);
        while (!game.gameIsWon() && game.anotherPlayIsPossible()){
            System.out.println(game);
            System.out.println("What cards do you want to remove? Separate the indexes with a comma.");
            if (!game.processMove(Objects.requireNonNull(Utils.getInts(scanner, -1, 9)))){
                System.out.println("Invalid move! Try again!");
            }
        }
        if (game.gameIsWon()){
            System.out.println("You won! All cards gone!");
        }else if (!game.anotherPlayIsPossible()){
            System.out.println("You lost! No more moves available.");
        }
    }
}
