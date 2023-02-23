package Interface_graphique;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class QuitMenuItem extends JMenuItem implements ActionListener
{
    private final MazeApp mazeApp;

    public QuitMenuItem(MazeApp mazeApp)
    {
        super("Quit");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (mazeApp.getMazeAppModel().isModified())
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            int a = JOptionPane.showConfirmDialog(frame, "Le labyrinthe n'a pas été enregistré veux tu le faire ?", "Warning", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION)
            {
                JFileChooser choose = new JFileChooser(new File("data/"));
                int res = choose.showSaveDialog(null);

                if (res == JFileChooser.APPROVE_OPTION) 
                {
                    File file = choose.getSelectedFile();
                    try 
                    {
                        mazeApp.getMazeAppModel().saveToTextFile(file + ".txt");
                    } 
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                    }
                    JFrame frame2 = new JFrame("Successfully saved");
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setVisible(true);
                    JOptionPane.showMessageDialog(frame2, "Le labyrinthe a correctement été enregistré", "successfully saved", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                System.exit(0);
        }
    }
}