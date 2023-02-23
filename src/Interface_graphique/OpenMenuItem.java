package Interface_graphique;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import Labyrinthe.MazeReadingException;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class OpenMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public OpenMenuItem(MazeApp mazeApp)
    {
        super("Open");
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
                    JOptionPane.showMessageDialog(frame2, "Le labyrinthe a correctement été enregistré, tu peux désormais choisir quel fichier ouvrir", "successfully saved", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        }
        JFileChooser choose = new JFileChooser(new File("data/"));
		int res = choose.showOpenDialog(null);

		if (res == JFileChooser.APPROVE_OPTION) 
        {
			File file = choose.getSelectedFile();
            try {
                mazeApp.getMazeAppModel().initFromTextFile(file.getAbsolutePath());
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (MazeReadingException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
		}
    }
}


