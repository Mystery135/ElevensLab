import java.util.*;

public class Utils {


    //Converts a cards' instance variables into more readable symbols.
    public static String getCardSymbol(Card card){
        Map<Integer, String > values = Constants.VALUE_TO_NAME;
        StringBuilder symbol = new StringBuilder();
        if (card == null){
            symbol.append("  ");
            return symbol.toString();
        }
        if (values.containsKey(card.pointValue())){
            symbol.append(values.get(card.pointValue()));
        }else if (card.pointValue() == 0){
            symbol.append(" ");
        } else{
            symbol.append(card.pointValue());
        }
        switch (card.suit().toLowerCase()) {
            case "clubs" -> symbol.append("♣");
            case "spades" -> symbol.append("♠");
            case "hearts" -> symbol.append("❤");
            case "diamonds" -> symbol.append("♦");
            case "" -> symbol.append(" ");
        }
        return symbol.toString();
    }


    //Creates an arraylist of 52 cards
public static ArrayList<Card> createCardArrayList(){
    ArrayList<Card> cards = new ArrayList<>();
    String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
    int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
    for (String suit : suits) {
        for (int pointValue : pointValues) {
            cards.add(new Card(pointValue, suit));
        }
    }
    return cards;
}


}