package Interface_graphique;

import javax.swing.JMenuItem;
import java.awt.event.*;

public class AddLineMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public AddLineMenuItem(MazeApp mazeApp)
    {
        super("Add Line");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0) 
    {
        mazeApp.getMazeAppModel().addLine();
    }
}
