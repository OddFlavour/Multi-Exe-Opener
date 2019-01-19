package ui;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UI {

  public static final int DEFAULT_WIDTH = 600;
  public static final int DEFAULT_HEIGHT = 300;
  public static final int DEFAULT_X = 100;
  public static final int DEFAULT_Y = 100;

  private JFrame f;
  private JPanel p;

  private static ArrayList<JTextField> paths;
  private static ArrayList<JButton> pathButtons;
  
  // TODO: this is not a good way to initialize it, in fact we don't even need a reference
  private static JButton startButton = new JButton("Start");

  public static GroupLayout layout;
  
  private UI(String windowName, int width, int height, int x, int y) {
    this.f = new JFrame(windowName);

    f.setSize(width, height);
    f.setLocation(x, y);

    // Default settings
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Used so that frame will only be visible after everything is set up
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        f.setVisible(true);
      }
    });

    this.p = new JPanel();

    f.setContentPane(new JScrollPane(this.p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
  }

  public static UI createDefaultUI(String windowName) {
    UI ui = new UI(windowName, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_X, DEFAULT_Y);

    paths = new ArrayList<>();
    pathButtons = new ArrayList<>();

    startButton.addActionListener(new StartListener());
    addNewEmptyPath();

    layout = new GroupLayout(ui.getPanel());
    ui.getPanel().setLayout(layout);

    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    constructLayout();

    return ui;
  }

  public static void constructLayout() {
    layout.setHorizontalGroup(constructHGroup());
    layout.setVerticalGroup(constructVGroup());
  }

  public static Group constructHGroup() {

    ParallelGroup pg1 = layout.createParallelGroup(); // Path text fields
    ParallelGroup pg2 = layout.createParallelGroup(); // Path buttons

    pg1.addComponent(startButton);
    
    for (int i=0; i<paths.size(); i++) {
      pg1.addComponent(paths.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
      pg2.addComponent(pathButtons.get(i));
    }

    Group sg = layout.createSequentialGroup();
    sg.addGroup(pg1);
    sg.addGroup(pg2);

    return sg;
  }

  public static Group constructVGroup() {
    ArrayList<ParallelGroup> pgList = new ArrayList<>();
    for (int i=0; i<paths.size(); i++) {
      ParallelGroup tempGroup = layout.createParallelGroup();
      tempGroup.addComponent(paths.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
      tempGroup.addComponent(pathButtons.get(i));

      pgList.add(tempGroup);
    }

    Group sg = layout.createSequentialGroup();
    sg.addComponent(startButton);
    for (ParallelGroup pg : pgList) {
      sg.addGroup(pg);
    }

    return sg;
  }

  public static JTextField getNewPathGfx() {
    JTextField gfx = new JTextField();
    gfx.setEditable(false);
    gfx.setPreferredSize(new Dimension(500, 30));
    
    gfx.setName(String.valueOf(paths.size()));

    return gfx;
  }

  public static JButton getNewPathButtonGfx() {
    JButton gfx = new JButton("...");
    gfx.addActionListener(new ExplorerListener());
    
    gfx.setName(String.valueOf(pathButtons.size()));

    return gfx;
  }

  public static void addNewEmptyPath() {
    paths.add(getNewPathGfx());
    pathButtons.add(getNewPathButtonGfx());
  }
  
  public static ArrayList<JTextField> getPaths() {
	  return paths;
  }
  
  public static ArrayList<JButton> getPathButtons() {
	  return pathButtons;
  }

  public JPanel getPanel() {
    return this.p;
  }
}
