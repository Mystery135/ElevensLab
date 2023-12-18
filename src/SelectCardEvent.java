import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCardEvent implements ActionListener {
    private ElevensBoard board;
    private int index;
    public SelectCardEvent(ElevensBoard board, int index){
        this.board = board;
        this.index = index;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (button.isSelected()){
            board.getSelectedCards().add(index);
        }else{
            board.getSelectedCards().remove((Integer) index);//So it removes the object, not the index
        }
    }
}
