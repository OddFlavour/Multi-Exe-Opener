package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ExplorerListener implements ActionListener {

  private boolean explored = false;

  @Override
  public void actionPerformed(ActionEvent action) {
    // TODO Auto-generated method stub
    System.out.println(
        ((JButton) action.getSource()).getName()
        );

    if (!explored) {
      UI.addNewEmptyPath();
      UI.constructLayout();
      explored = true;
    }
  }

}
