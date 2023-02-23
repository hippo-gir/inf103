package Interface_graphique;

import javax.swing.JButton;
import java.awt.event.*;
import Model.*;

public class ResetCheminButton extends JButton implements ActionListener
{
    private MazeApp mazeApp;

    public ResetCheminButton(MazeApp mazeApp)
    {
        super("Reset Chemin");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) 
    {
        int width = mazeApp.getMazeAppModel().getWidth();
        int height = mazeApp.getMazeAppModel().getHeight();
        MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                if (mazeAppModel.getMboxs()[i][j].getType().equals("Sol")) mazeAppModel.setMboxType(i, j, "EBox");
            }
    }
}