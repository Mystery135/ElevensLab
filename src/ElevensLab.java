import java.util.ArrayList;

public class ElevensLab {
    public static void main(String[] args)  {
        //Creates a deck of cards & a new board
        Deck deck =new Deck(Utils.createCardArrayList());
        new ElevensBoard(deck, 0, 0);
    }
}
