import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ElevensBoard {
    public final static int BOARD_HEIGHT = 3;
    public final static int BOARD_WIDTH = 3;
    public final static int BOARD_SIZE = BOARD_HEIGHT*BOARD_WIDTH;
    private final ArrayList<Integer> selectedCards = new ArrayList<>();
    private boolean solve = true;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public boolean isSolve() {
        return solve;
    }
    public void setSolve(boolean solve) {
        this.solve = solve;
    }
    public int getGamesWon() {
        return gamesWon;
    }

    private final int gamesPlayed;
    private final int gamesWon;
    private final Card[] cardsInPlay;
    private final Deck deck;
    private final ArrayList<JToggleButton> cardButtons = new ArrayList<>();
    private final JFrame frame;
    private final JPanel panel;
    JLabel cardsLeftInDeckLabel;
    JButton solveButton;

    public JPanel getPanel() {
        return panel;
    }   public JFrame getFrame() {
        return frame;
    }

    public void solve(){//If solve is true, then do moves until there is none to make.
        if (isSolve()){
            while (hasPairSum11(cardsInPlay, true) != null || hasJQK(cardsInPlay, true).size() == 3){
                if (hasPairSum11(cardsInPlay, true) != null){
                    processMove(hasPairSum11(cardsInPlay, true));
                }else if (hasJQK(cardsInPlay, true).size() == 3){
                    processMove(hasJQK(cardsInPlay, true));
                }
            }
        }
    }
    //Initializes the game window &b adds buttons
    public ElevensBoard(Deck deck, int gamesPlayed, int gamesWon)  {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        frame = new JFrame();
        panel = new JPanel(new GridLayout(3, 3));
        JPanel infoPanel = new JPanel(new BorderLayout());
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.setTitle("Elevens");
        this.deck = deck;

        //Makes sure there is always a playable move on the first turn
        ArrayList<Card> first9;
        do {
            deck.efficientSelectionShuffle();
            first9 = new ArrayList<>();
            Deck clonedDeck = deck.clone();
            for (int i = 0; i < 9; i++) {
                first9.add(clonedDeck.deal());
            }


        } while (!hasJQK(first9.toArray(new Card[0])) && !hasPairSum11(first9.toArray(new Card[0])));

        //Adds buttons to the window
        cardsInPlay = new Card[BOARD_SIZE];
        JButton button = new JButton("Remove selected cards");
        button.addActionListener(new RemoveCardEvent(this));
        button.setPreferredSize(new Dimension(100, 100));
        JPanel buttonPane = new JPanel(new FlowLayout());
        solveButton = new JButton("Solve this for me");
        solveButton.setPreferredSize(new Dimension(300, 30));
        solveButton.addActionListener(new SimulationButtonEvent(solveButton, this, button));
        buttonPane.add(solveButton);
        infoPanel.add(buttonPane, BorderLayout.CENTER);
        infoPanel.add(button, BorderLayout.NORTH);

        //Adds the buttons that represent cards to the window
        for (int i = 0; i < BOARD_SIZE; i++) {
            cardsInPlay[i] = deck.deal();
            JToggleButton toggleButton = new JToggleButton(Utils.getCardSymbol(cardsInPlay[i]));
            toggleButton.addActionListener(new SelectCardEvent(this, i));
            cardButtons.add(toggleButton);
            panel.add(toggleButton);
        }

        //JLabels for statistics
        cardsLeftInDeckLabel = new JLabel("Cards left in deck: " + deck.size());
        JLabel gamesWonLabel = new JLabel("Games won: "+ gamesWon + "/" + gamesPlayed + " (" + Math.round((float)gamesWon/gamesPlayed*10000)/100.0f + "%)");

        //Sets up the alignment for the JComponents & modifies window settings.
        gamesWonLabel.setHorizontalAlignment(JLabel.CENTER);
        cardsLeftInDeckLabel.setHorizontalAlignment(JLabel.CENTER);
        cardsLeftInDeckLabel.setBorder(new EmptyBorder(20, 40, 0, 40));
        gamesWonLabel.setBorder(new EmptyBorder(20, 40, 0, 40));
        infoPanel.add(gamesWonLabel, BorderLayout.EAST);
        infoPanel.add(cardsLeftInDeckLabel, BorderLayout.WEST);
        frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //Processes a game move given in an arraylist format.
    public boolean processMove(ArrayList<Integer> cardIndexes){
        //Move has involve either 2 or 3 cards
        if (cardIndexes.size() > 3 || cardIndexes.size() < 2){
            return false;
        }


        if (cardIndexes.size() == 2) {
            if (cardsInPlay[cardIndexes.get(0)] == null || cardsInPlay[cardIndexes.get(1)] == null){
                return false;
            }

            //Checks if the 2 cards' values add to 11. If they do, those cards are replaced with new ones.
            if (cardsInPlay[cardIndexes.get(0)].pointValue() + cardsInPlay[cardIndexes.get(1)].pointValue() == 11) {
                cardsInPlay[cardIndexes.get(0)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(0)), cardsInPlay[cardIndexes.get(0)]);

                cardsInPlay[cardIndexes.get(1)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(1)), cardsInPlay[cardIndexes.get(1)]);
            } else {
                return false;
            }

        }else {//if cardIndexes.size() == 3
            if (cardsInPlay[cardIndexes.get(0)] == null || cardsInPlay[cardIndexes.get(1)] == null || cardsInPlay[cardIndexes.get(2)] == null){
                return false;
            }

            //Checks if the three cards selected are J, Q, and K. If they are, those cards are replaced with new ones.
            if (hasJQK(new Card[]{cardsInPlay[cardIndexes.get(0)], cardsInPlay[cardIndexes.get(1)], cardsInPlay[cardIndexes.get(2)]})) {
                cardsInPlay[cardIndexes.get(0)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(0)), cardsInPlay[cardIndexes.get(0)]);

                cardsInPlay[cardIndexes.get(1)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(1)), cardsInPlay[cardIndexes.get(1)]);

                cardsInPlay[cardIndexes.get(2)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(2)), cardsInPlay[cardIndexes.get(2)]);

            }else{
                return false;
            }
        }
        cardsLeftInDeckLabel.setText("Cards left in deck: " + deck.size());
        return true;
    }

    //Sets the JToggleButton's text to the card's value and suit.
private void setJText(JToggleButton button, Card card){
    if (card == null){
        button.setText("");
    }else{
        button.setText(Utils.getCardSymbol(card));
    }
}

//Checks if another play is possible using hasPairSum11 & hasJQK
    public boolean anotherPlayIsPossible() {
        if (hasPairSum11(cardsInPlay)){
            return true;
        }
        if (hasJQK(cardsInPlay)){
            return true;
        }
        return false;
    }


    //
    public boolean hasPairSum11(Card[] cards) {
        for (int i = 0; i < cards.length - 1; i++) {
            for (int k = i + 1; k < cards.length; k++) {
                if (cards[i] != null && cards[k] != null) {
                    if (cards[i].pointValue() + cards[k].pointValue() == 11) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> hasPairSum11(Card[] cards, boolean simulation){

        for (int i = 0; i<cards.length; i++){
            for (int k = 0; k<cards.length; k++){
                if (k == i){continue;}
                if (cards[i] != null && cards[k] != null) {
                    if (cards[i].pointValue() + cards[k].pointValue() == 11){
                        return new ArrayList<>(List.of(i, k));
                    }
                }
            }

        }
        return null;
    }
    public ArrayList<Integer> hasJQK(Card[] cards, boolean simulation){
        boolean J = false;
        boolean Q = false;
        boolean K = false;
        ArrayList<Integer> JKQIndexes = new ArrayList<>();


        for (int i = 0; i<cards.length; i++){
            if (cards[i] != null) {
                if (cards[i].pointValue() == 21) {
                    if (simulation && !J){
                        JKQIndexes.add(i);
                    }
                    J = true;
                }
                if (cards[i].pointValue() == 22) {
                    if (simulation && !Q){
                        JKQIndexes.add(i);
                    }
                    Q = true;
                }
                if (cards[i].pointValue() == 23) {
                    if (simulation && !K){
                        JKQIndexes.add(i);
                    }
                    K = true;
                }
            }
        }
        return JKQIndexes;
    }
    ArrayList<Integer> JKQIndexes;
    public boolean hasJQK(Card[] cards) {
        boolean J = false;
        boolean Q = false;
        boolean K = false;
        JKQIndexes = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                if (cards[i].pointValue() == 21) {
                    JKQIndexes.add(i);
                    J = true;
                } else if (cards[i].pointValue() == 22) {
                    JKQIndexes.add(i);
                    Q = true;
                } else if (cards[i].pointValue() == 23) {
                    JKQIndexes.add(i);
                    K = true;
                }
            }
        }

        return J && Q && K;
    }
    public boolean gameIsWon() {
        if (deck.isEmpty()) {
            for (Object obj : cardsInPlay){
                if (obj != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        int x = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<cardsInPlay.length; i++){
            builder.append(Utils.getCardSymbol(cardsInPlay[i]));
            x++;
            if (x == BOARD_WIDTH){
                x = 0;
                builder.append("\n");
            }else{
                builder.append(" ");
            }
        }
        return builder.toString();
    }



    public ArrayList<Integer> getSelectedCards() {
        return selectedCards;
    }
}
