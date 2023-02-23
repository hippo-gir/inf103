package Interface_graphique;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class SaveMenuItem extends JMenuItem implements ActionListener
{
    private MazeApp mazeApp;

    public SaveMenuItem(MazeApp mazeApp)
    {
        super("Save");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        JFileChooser choose = new JFileChooser(new File("data/"));
		int res = choose.showSaveDialog(null);

		if (res == JFileChooser.APPROVE_OPTION) 
        {
			File file = choose.getSelectedFile();
            try {
                mazeApp.getMazeAppModel().saveToTextFile(file + ".txt");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            JFrame frame = new JFrame("Successfully saved");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JOptionPane.showMessageDialog(frame, "Le labyrinthe a correctement été enregistré", "successfully saved", JOptionPane.INFORMATION_MESSAGE);

		}

    }
}

