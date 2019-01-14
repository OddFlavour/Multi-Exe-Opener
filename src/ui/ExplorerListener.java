package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExplorerListener implements ActionListener {

  private boolean explored = false;

  @Override
  public void actionPerformed(ActionEvent action) {
    // TODO Auto-generated method stub
    System.out.println(
        ((JButton) action.getSource()).getName()
        );

    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Executables", "exe");
    chooser.setFileFilter(filter);
    
    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    if (!explored) {
	      UI.addNewEmptyPath();
	      UI.constructLayout();
	      explored = true;
	    }
	    
	    int index = Integer.parseInt(((JButton) action.getSource()).getName());
	    UI.getPaths().get(index).setText(chooser.getSelectedFile().getAbsolutePath());
    }
  }

}
