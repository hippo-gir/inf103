package Interface_graphique;
import javax.swing.*;
import java.awt.event.*;

public class DelColumnMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public DelColumnMenuItem(MazeApp mazeApp)
    {
        super("Del Column");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        int width = mazeApp.getMazeAppModel().getWidth();
        if (width == 1)
        {
            JFrame frame = new JFrame("Erreur");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JOptionPane.showMessageDialog(frame, "Tu ne peux pas supprimer d'autre colonne", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
        else 
        {
            mazeApp.getMazeAppModel().deleteColumn();
        }
    }
}