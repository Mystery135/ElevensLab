import com.sun.nio.sctp.PeerAddressChangeNotification;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class ElevensBoard {
    public final static int BOARD_HEIGHT = 3;
    public final static int BOARD_WIDTH = 3;
    public final static int BOARD_SIZE = BOARD_HEIGHT*BOARD_WIDTH;
    private final ArrayList<Integer> selectedCards = new ArrayList<>();

    public final static Map<Integer, String> VALUE_TO_NAME = Map.of(
            1, "A",
            21, "J",
            22, "Q",
            23, "K"
    );
    private Card[] cardsInPlay;
    private Deck deck;

    public JPanel getPanel() {
        return panel;
    }   public JFrame getFrame() {
        return frame;
    }

    private final ArrayList<JToggleButton> cardButtons = new ArrayList<>();
    private JFrame frame;
    private JPanel panel;
    JLabel cardsLeftInDeckLabel;
    public ElevensBoard(Deck deck) {


        frame = new JFrame();
        panel = new JPanel(new GridLayout(3, 3));
        JPanel infoPanel = new JPanel(new BorderLayout());
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        frame.setTitle("Elevens");


        this.deck = deck;
//        deck.perfectShuffle();
//        deck.efficientSelectionShuffle();


        ArrayList<Card> first9;
        do {
            System.out.println("SHUFFLED!");
            deck.efficientSelectionShuffle();
            first9 = new ArrayList<>();
            Deck clonedDeck = deck.clone();
            for (int i = 0; i < 9; i++) {
                first9.add(clonedDeck.deal());
            }


        } while (!hasJQK(first9.toArray(new Card[0])) && !hasPairSum11(first9.toArray(new Card[0])));


        System.out.println(deck);

        cardsInPlay = new Card[BOARD_SIZE];

        JButton button = new JButton("Remove selected cards");
        button.addActionListener(new RemoveCardEvent(this));
        button.setPreferredSize(new Dimension(100, 100));
        infoPanel.add(button, BorderLayout.NORTH);

        for (int i = 0; i < BOARD_SIZE; i++) {
            cardsInPlay[i] = deck.deal();
            JToggleButton toggleButton = new JToggleButton(Utils.getCardSymbol(cardsInPlay[i]));
            toggleButton.addActionListener(new SelectCardEvent(this, i));
            cardButtons.add(toggleButton);
            panel.add(toggleButton);
        }


        cardsLeftInDeckLabel = new JLabel("Cards left in deck: " + deck.size());
        cardsLeftInDeckLabel.setHorizontalAlignment(JLabel.CENTER);
        cardsLeftInDeckLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        infoPanel.add(cardsLeftInDeckLabel);


        frame.setSize(960, 540);

        frame.setLocationRelativeTo(null);
//        frame.pack();
        frame.setVisible(true);
    }
    private void dealMyCards(){
        for (int i = 0; i<BOARD_SIZE; i++){
            cardsInPlay[i] = deck.deal();
        }
    }
    public boolean processMove(ArrayList<Integer> cardIndexes){
        if (cardIndexes.size() > 4 || cardIndexes.size() < 2){
            return false;
        }

        if (cardIndexes.size() == 2) {
            if (cardsInPlay[cardIndexes.get(0)] == null || cardsInPlay[cardIndexes.get(1)] == null){
                return false;
            }
            if (cardsInPlay[cardIndexes.get(0)].pointValue() + cardsInPlay[cardIndexes.get(1)].pointValue() == 11) {
                cardsInPlay[cardIndexes.get(0)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(0)), cardsInPlay[cardIndexes.get(0)]);

                cardsInPlay[cardIndexes.get(1)] = deck.deal();
                setJText(cardButtons.get(cardIndexes.get(1)), cardsInPlay[cardIndexes.get(1)]);
            } else {
                return false;
            }
        }else if (cardIndexes.size() == 3){
            if (cardsInPlay[cardIndexes.get(0)] == null || cardsInPlay[cardIndexes.get(1)] == null || cardsInPlay[cardIndexes.get(2)] == null){
                return false;
            }
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

private void setJText(JToggleButton button, Card card){
    if (card == null){
        button.setText("");
    }else{
        button.setText(Utils.getCardSymbol(card));
    }
}
    public boolean anotherPlayIsPossible() {
        if (hasPairSum11(cardsInPlay)){
            return true;
        }
        if (hasJQK(cardsInPlay)){
            return true;
        }
        return false;
    }
    public boolean hasPairSum11(Card[] cards){

        for (int i = 0; i<cards.length-1; i++){
            for (int k = 0; k<cards.length-1; k++){
                if (k == i){continue;}
                    if (cards[i] != null && cards[k] != null) {
                        if (cards[i].pointValue() + cards[k].pointValue() == 11){
                            return true;
                        }
                    }
            }

        }
        return false;
    }
    public boolean hasJQK(Card[] cards){
        boolean J = false;
        boolean Q = false;
        boolean K = false;

        for (int i = 0; i<cards.length; i++){
            if (cards[i] != null) {
                if (cards[i].pointValue() == 21) {
                    J = true;
                }
                if (cards[i].pointValue() == 22) {
                    Q = true;
                }
                if (cards[i].pointValue() == 23) {
                    K = true;
                }
            }
        }
        return J && Q && K;
    }
//    public boolean isLegal(List<Integer> selectedCards) {
//        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
//        return false;
//    }
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
