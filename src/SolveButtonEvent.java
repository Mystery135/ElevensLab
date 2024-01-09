import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Called when the "solve this for me" button is clicked.
public class SolveButtonEvent implements ActionListener {
    private ElevensBoard board;
    private JButton removeCards;
    private JButton simulationButton;
    public SolveButtonEvent(JButton simulationButton, ElevensBoard board, JButton removeCards){this.simulationButton = simulationButton; this.board = board; this.removeCards = removeCards;}


    //Solves the board
    @Override
    public void actionPerformed(ActionEvent e) {
        board.setSolve(true);
        board.solve();
        simulationButton.setText("All possible moves done!");
        removeCards.setText("Start another game");
    }
}
