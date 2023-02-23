package Interface_graphique;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class ABoxRadioButton extends JRadioButton implements ActionListener
{
    private MazeApp mazeApp;

    public ABoxRadioButton(MazeApp mazeApp)
    {
        super("Case Arrivée");
        this.mazeApp = mazeApp;
        addActionListener(this);
        // this.setBackground(new Color(31, 34, 50));
        // this.setBackground(Color.lightGray);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        if (this.isSelected()) mazeApp.getMazeAppModel().setSelectedMenu("ABox");
    }
}