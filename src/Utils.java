import java.util.*;

public class Utils {
public static String getCardSymbol(Card card){
    Map<Integer, String > values = ElevensBoard.VALUE_TO_NAME;
    StringBuilder symbol = new StringBuilder();
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
         symbol.append("♥");
         break;
     case "diamonds":
         symbol.append("♦");
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

    public static ArrayList<Integer> getInts(Scanner scanner, int lowerBound, int upperBound) {//Gets an int higher than the lowerbound from the user
                try {
                    String input = scanner.nextLine();
                    ArrayList<String> stringInts = new ArrayList<>(List.of((input.split(","))));
                    ArrayList<Integer> ints = new ArrayList<>();

                    for (String str : stringInts){
                        str = Utils.formatText(str);
                        int i = Integer.parseInt(str);
                        if (i > lowerBound && i < upperBound) {
                            ints.add(Integer.parseInt(str));
                        }else{
                            throw new Exception();
                        }

                    }
                    return ints;

                }catch (Exception e){
                    System.out.println("Input ints above " + lowerBound + " and below " + upperBound + " separated by a comma! (ex. 1, 5)");
                    scanner.nextLine();

                }
return null;
}

}
