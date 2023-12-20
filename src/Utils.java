import java.util.*;

public class Utils {
    public static String getCardSymbol(Card card){
        Map<Integer, String > values = ElevensBoard.VALUE_TO_NAME;
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

//return card.
        return symbol.toString();
    }
    public static int getInt(Scanner scanner, int lowerBound, int upperBound) {//Gets an int higher than the lowerbound from the user
        while (true) {
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i > lowerBound && i < upperBound) {
                    return i;
                }
            }
            System.out.println("Input an int above " + lowerBound + " and below " + upperBound + "!");
            scanner.nextLine();
        }
    }
    public static String formatText(String s){return s.replaceAll("\\s", "").toUpperCase();}

    public static ArrayList<Integer> getInts(Scanner scanner, int lowerBound, int upperBound) {
        ArrayList<Integer> ints = new ArrayList<>();
        String input = scanner.nextLine();
        ArrayList<String> stringInts = new ArrayList<>(List.of(input.split(",")));

        for (String str : stringInts) {
            str = Utils.formatText(str).trim();
            try {
                int i = Integer.parseInt(str);
                if (i > lowerBound && i < upperBound) {
                    ints.add(i);
                } else {
                    System.out.println("Invalid number: " + i + ". Please enter integers above " + lowerBound + " and below " + upperBound + " separated by a comma! (ex. 1, 5)");
                    return getInts(scanner, lowerBound, upperBound);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter integers above " + lowerBound + " and below " + upperBound + " separated by a comma! (ex. 1, 5)");
                return getInts(scanner, lowerBound, upperBound);//recursion if user types something that is not accepted
            }
        }
        return ints;
    }
public static ArrayList<Card> createRandCardArrayList(){
    ArrayList<Card> cards = new ArrayList<>();
    String[] suits = new String[]{"Hearts", "Diamonds", "Spades", "Diamonds"};
    int[] pointValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 22, 23};
//        Random random = new Random();
    //i<2, j<2
    //i<suits.length, j<pointValues.length
    for (int i = 0; i<suits.length; i++){
        for (int j = 0; j<pointValues.length; j++){
            cards.add(new Card(pointValues[j],  suits[i]));
        }
    }
    return cards;
}


}