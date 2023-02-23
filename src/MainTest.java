
import java.io.IOException;
import java.lang.String;

import javax.swing.*;

import java.awt.*;

import Interface_graphique.*;
import Labyrinthe.*;

public class MainTest 
{
	public static void main(String[] args) throws IOException, MazeReadingException, Exception 
	{
		// Résolution sans interface graphique //

		/* VertexInterface[][] t = new VertexInterface[20][20];
		Maze maze = new Maze(20, 20, t);
		maze.initFromTextFile("data/labyGrand.txt");
		maze.showAndWriteSolution("labyGrandSol"); */

		// Résolution avec interface graphique //

		JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 2, 2));

		JComboBox<Integer> myComboBox1 = new JComboBox<Integer>();				
		for( int i = 1; i<21; i++) myComboBox1.addItem(i);	

		JComboBox<Integer> myComboBox2 = new JComboBox<Integer>();				
		for( int i = 1; i<21; i++) myComboBox2.addItem(i);	


        panel.add(new JLabel("Hauteur :"));
        panel.add(myComboBox1);

        panel.add(new JLabel("Largeur :"));
        panel.add(myComboBox2);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Dimensions du Labyrinthe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) 
		{
            int height = (Integer) myComboBox1.getSelectedItem();
			int width = (Integer) myComboBox2.getSelectedItem();
			new MazeApp(width, height);    
        }
		else
		{
			System.exit(0);
		}
	}
}
