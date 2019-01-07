package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class ExplorerListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    System.out.println(
        ((JButton) arg0.getSource()).getName()
        );
    
    UI.addNewEmptyPath();
    UI.constructLayout();
  }

}
