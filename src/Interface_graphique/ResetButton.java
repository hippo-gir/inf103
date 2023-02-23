package Interface_graphique;

import javax.swing.*;

import Model.MazeAppModel;

import java.awt.event.*;

public class ResetButton extends JButton implements ActionListener
{
    private MazeApp mazeApp;

    public ResetButton(MazeApp mazeApp)
    {
        super("Reset");
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
                mazeAppModel.setMboxType(j, i, "EBox");
            }
        mazeAppModel.setDepartBox(null);
        mazeAppModel.setEndBox(null);
    }
}
