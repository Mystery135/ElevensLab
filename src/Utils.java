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
        switch (card.suit().toLowerCase()){
            case "clubs":
                symbol.append("♣");
                break;
            case "spades":
                symbol.append("♠");
                break;
            case "hearts":
                symbol.append("❤");
                break;
            case "diamonds":
                symbol.append("♦");
                break;
            case "":
                symbol.append(" ");
        }
        return symbol.toString();
    }


    //Creates an arraylist of 52 cards
public static ArrayList<Card> createCardArrayList(){
    ArrayList<Card> cards = new ArrayList<>();
    String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
    int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
    for (int i = 0; i<suits.length; i++){
        for (int j = 0; j<pointValues.length; j++){
            cards.add(new Card(pointValues[j],  suits[i]));
        }
    }
    return cards;
}


}