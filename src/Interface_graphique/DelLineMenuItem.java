package Interface_graphique;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.*;

public class DelLineMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public DelLineMenuItem(MazeApp mazeApp)
    {
        super("Del Line");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0) 
    {
        int height = mazeApp.getMazeAppModel().getHeight();
        if (height == 1)
        {
            JFrame frame = new JFrame("Erreur");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JOptionPane.showMessageDialog(frame, "Tu ne peux pas supprimer d'autre ligne", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            mazeApp.getMazeAppModel().deleteLine();
        }
    }
    
}
