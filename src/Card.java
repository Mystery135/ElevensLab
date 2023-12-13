import java.util.Objects;

public record Card(int pointValue, String suit) {
    public boolean equals(Card card){
        return Objects.equals(card.suit, this.suit) && card.pointValue == this.pointValue;
    }
    @Override
    public String toString(){
        return "Value: " + pointValue + " Suit: " + suit;
    }

}
