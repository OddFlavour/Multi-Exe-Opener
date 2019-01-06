package ui;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI {

  public static final int DEFAULT_WIDTH = 600;
  public static final int DEFAULT_HEIGHT = 300;
  public static final int DEFAULT_X = 100;
  public static final int DEFAULT_Y = 100;

  private JFrame f;
  private JPanel p;
  private static ArrayList<JTextField> paths;
  private static ArrayList<JButton> pathButtons;

  private UI(String windowName, int width, int height, int x, int y) {
    this.f = new JFrame(windowName);

    f.setSize(width, height);
    f.setLocation(x, y);

    // Default settings
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    this.p = new JPanel();

    f.setContentPane(this.p);
  }

  public static UI createDefaultUI(String windowName) {
    UI ui = new UI(windowName, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_X, DEFAULT_Y);

    paths = new ArrayList<>();
    pathButtons = new ArrayList<>();
    paths.add(getNewPathGraphics());
    pathButtons.add(new JButton("..."));
    paths.add(getNewPathGraphics());
    pathButtons.add(new JButton("..."));

    GroupLayout layout = new GroupLayout(ui.getPanel());
    ui.getPanel().setLayout(layout);

    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    layout.setHorizontalGroup(constructHGroup(layout));
    layout.setVerticalGroup(constructVGroup(layout));

    return ui;
  }

  public static Group constructHGroup(GroupLayout layout) {
    ParallelGroup pg1 = layout.createParallelGroup();
    pg1.addComponent(paths.get(0), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
    pg1.addComponent(paths.get(1), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
    
    ParallelGroup pg2 = layout.createParallelGroup();
    pg2.addComponent(pathButtons.get(0));
    pg2.addComponent(pathButtons.get(1));
    
    return layout.createSequentialGroup()
        .addGroup(pg1)
        .addGroup(pg2)
        ;
  }

  public static Group constructVGroup(GroupLayout layout) {
    return layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup()
            .addComponent(paths.get(0), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(pathButtons.get(0)))
        .addGroup(layout.createParallelGroup()
            .addComponent(paths.get(1), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(pathButtons.get(1)))
        ;
  }

  public static JTextField getNewPathGraphics() {
    JTextField gfx = new JTextField();
    gfx.setEditable(false);
    gfx.setPreferredSize(new Dimension(500, 30));

    return gfx;
  }

  public JPanel getPanel() {
    return this.p;
  }
}
