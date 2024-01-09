import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

//Called when the "remove selected cards" button is clicked.
public class RemoveCardEvent implements ActionListener {
    private ElevensBoard board;
    public RemoveCardEvent(ElevensBoard board){
        this.board = board;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Checks if the move is invalid.
        // Creates a dialogue box if the move is invalid and clears the selected cards if not.
        if (!board.processMove(board.getSelectedCards()) && board.anotherPlayIsPossible()){
            JLabel message = new JLabel("Invalid Move!", JLabel.CENTER);
            JOptionPane.showMessageDialog(board.getFrame(), message, "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
        }else{
            for (Component component : board.getPanel().getComponents()){
                if (component instanceof JToggleButton){
                    ((JToggleButton) component).setSelected(false);
                    board.getSelectedCards().clear();
                }
            }
        }


        //TODO: optimize
        //Creates a dialogue box if the game is won/lost, then updates the statistics.

        if (board.gameIsWon() || !board.anotherPlayIsPossible()){
            JLabel message;
            if (board.gameIsWon()){
                message = new JLabel("You won! All cards gone! Do you want to play again?", JLabel.CENTER);
            }else {//game is lost
                message = new JLabel("You lost! No more moves available. Do you want to play again?", JLabel.CENTER);
            }
            int choice = JOptionPane.showConfirmDialog(board.getFrame(), message, "Game Over!", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                board.getFrame().dispatchEvent(new WindowEvent(board.getFrame(), WindowEvent.WINDOW_CLOSING));
                Deck deck = new Deck(Utils.createCardArrayList());
                new ElevensBoard(deck, board.getGamesPlayed()+1, board.getGamesWon()  + (board.gameIsWon() ? 1 : 0));
            }
        }


    }
}
