package Interface_graphique;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;


public class EBoxRadioButton extends JRadioButton implements ActionListener
{
    private MazeApp mazeApp;

    public EBoxRadioButton(MazeApp mazeApp)
    {
        super("Gomme");
        this.mazeApp = mazeApp;
        // this.setBackground(new Color(31, 34, 50));
        addActionListener(this);
        // this.setBackground(Color.DARK_GRAY);
    }   

    public void actionPerformed(ActionEvent evt)
    {
        if (this.isSelected()) mazeApp.getMazeAppModel().setSelectedMenu("EBox");
    }
}
