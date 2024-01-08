import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class RemoveCardEvent implements ActionListener {
    private ElevensBoard board;
    public RemoveCardEvent(ElevensBoard board){
        this.board = board;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
        if (board.gameIsWon()){
            board.getPanel().setVisible(false);
            JLabel message = new JLabel("You won! All cards gone! Do you want to play again?", JLabel.CENTER);
            int choice = JOptionPane.showConfirmDialog(board.getFrame(), message, "Game Over!", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                board.getFrame().dispatchEvent(new WindowEvent(board.getFrame(), WindowEvent.WINDOW_CLOSING));
                Deck deck = new Deck(Utils.createRandCardArrayList());
                    new ElevensBoard(deck, board.getGamesPlayed()+1, board.getGamesWon()+1);

            }else if (choice == JOptionPane.NO_OPTION){
                board.getFrame().dispatchEvent(new WindowEvent(board.getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }else if (!board.anotherPlayIsPossible()){

            board.getPanel().setVisible(false);
            JLabel message = new JLabel("You lost! No more moves available. Do you want to play again?", JLabel.CENTER);
            int choice;
              choice   = JOptionPane.showConfirmDialog(board.getFrame(), message, "Game Over!", JOptionPane.YES_NO_OPTION);
              if (choice == JOptionPane.YES_OPTION){
                board.getFrame().dispatchEvent(new WindowEvent(board.getFrame(), WindowEvent.WINDOW_CLOSING));
                Deck deck = new Deck(Utils.createRandCardArrayList());
                    new ElevensBoard(deck, board.getGamesPlayed()+1, board.getGamesWon());
            }else if (choice == JOptionPane.NO_OPTION){
                board.getFrame().dispatchEvent(new WindowEvent(board.getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
