import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RemoveCardEvent implements ActionListener {
    private ElevensBoard board;
    public RemoveCardEvent(ElevensBoard board){
        this.board = board;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(board.getSelectedCards());

        if (!board.processMove(board.getSelectedCards())){
            System.out.println("INVALID move!");
            JLabel message = new JLabel("Invalid Move!", JLabel.CENTER);
            JOptionPane.showMessageDialog(board.getFrame(), message, "Invalid Move", JOptionPane.INFORMATION_MESSAGE);

        }else{
            System.out.println(board);
            for (Component component : board.getPanel().getComponents()){
                if (component instanceof JToggleButton){
                    ((JToggleButton) component).setSelected(false);
                    board.getSelectedCards().clear();
                }
            }

        }

        if (board.gameIsWon()){
            System.out.println("You won! All cards gone!");
            board.getPanel().setVisible(false);
            JLabel message = new JLabel("You won! All cards gone!", JLabel.CENTER);
            JOptionPane.showMessageDialog(board.getFrame(), message, "Game Won", JOptionPane.INFORMATION_MESSAGE);

        }else if (!board.anotherPlayIsPossible()){
            System.out.println("You lost! No more moves available.");
            board.getPanel().setVisible(false);
            JLabel message = new JLabel("You lost! No more moves available.", JLabel.CENTER);
            JOptionPane.showMessageDialog(board.getFrame(), message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
