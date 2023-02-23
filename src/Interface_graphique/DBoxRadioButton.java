package Interface_graphique;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class DBoxRadioButton extends JRadioButton implements ActionListener
{
    private MazeApp mazeApp;

    public DBoxRadioButton(MazeApp mazeApp)
    {
        super("Case Départ");
        this.mazeApp = mazeApp;
        // this.setBackground(new Color(31, 34, 50));
        addActionListener(this);

        // this.setBackground(Color.DARK_GRAY);
    }

    public void actionPerformed(ActionEvent evt)
    {
       if (this.isSelected()) mazeApp.getMazeAppModel().setSelectedMenu("DBox");
    }
}
