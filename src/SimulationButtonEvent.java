import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationButtonEvent implements ActionListener {
    private ElevensBoard board;
    private JButton removeCards;
    private JButton simulationButton;
    public SimulationButtonEvent(JButton simulationButton, ElevensBoard board, JButton removeCards){this.simulationButton = simulationButton; this.board = board; this.removeCards = removeCards;}

    @Override
    public void actionPerformed(ActionEvent e) {
        board.setSimulation(true);
        board.toggleSim();
        simulationButton.setText("All possible moves done!");
        removeCards.setText("Start another game");
    }
}
