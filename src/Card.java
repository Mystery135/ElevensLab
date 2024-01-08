//Card contains just a point value and suit. It is also an immutable data carrier, so a record can be used.
public record Card(int pointValue, String suit) {
    @Override
    public String toString(){
        return "Value: " + pointValue + " Suit: " + suit;
    }
}
