import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Called when a card is clicked.
public class SelectCardEvent implements ActionListener {
    private final ElevensBoard board;
    private final int index;
    public SelectCardEvent(ElevensBoard board, int index){
        this.board = board;
        this.index = index;
    }

    //Adds/removes the card depending on if it was already selected.
    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (button.isSelected()){
            board.getSelectedCards().add(index);
        }else{
            board.getSelectedCards().remove((Integer) index);//So it removes the value, not the index
        }
    }
}
