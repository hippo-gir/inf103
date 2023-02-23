package Interface_graphique;

import javax.swing.JMenuItem;
import java.awt.event.*;


public class AddColumnMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public AddColumnMenuItem(MazeApp mazeApp)
    {
        super("Add Column");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        mazeApp.getMazeAppModel().addColumn();
    }
}