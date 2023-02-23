package Interface_graphique;

import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.event.*;

public class WBoxRadioButton extends JRadioButton implements ActionListener
{
    private MazeApp mazeApp;

    public WBoxRadioButton(MazeApp mazeApp)
    {
        super("Case Mur");
        this.mazeApp = mazeApp;
        // this.setBackground(new Color(31, 34, 50));
        addActionListener(this);
        // this.setBackground(Color.lightGray);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (this.isSelected()) mazeApp.getMazeAppModel().setSelectedMenu("WBox");
    }

    public void notifyForUpdate() {
    }
}
