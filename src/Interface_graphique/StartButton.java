package Interface_graphique;

import javax.swing.*;
import java.awt.event.*;
import java.io.PrintWriter;

import Labyrinthe.*;
import Model.MBox;
import Dijkstra.*;

public class StartButton extends JButton implements ActionListener
{
    private final MazeApp mazeApp;

    public StartButton(MazeApp mazeApp)
    {
        super("Start");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public final void saveToTextFile(String fileName) 
	{
        int height = mazeApp.getMazeAppModel().getHeight();
        int width = mazeApp.getMazeAppModel().getWidth();
        MBox[][] laby = mazeApp.getMazeAppModel().getMboxs();

		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(fileName);
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
				{
					MBox box = laby[i][j];
					char type = box.getType().charAt(0);
					if (j == width - 1) 
						pw.println(type);
					else
						pw.print(type);
				}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally	
		{
			try
			{
				pw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

    public void actionPerformed(ActionEvent e)
    {
        if (mazeApp.getMazeAppModel().getDepartBox() == null)
        {
            JFrame frame = new JFrame("Erreur");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JOptionPane.showMessageDialog(frame, "Il n'y a pas de case départ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (mazeApp.getMazeAppModel().getEndBox() == null)
        {
            JFrame frame = new JFrame("Erreur");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JOptionPane.showMessageDialog(frame, "Il n'y a pas de case d'arrivée", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int height = mazeApp.getMazeAppModel().getHeight();
            int width = mazeApp.getMazeAppModel().getWidth();
            VertexInterface[][] laby = new VertexInterface[height][width];
            for (int i = 0; i  < height; i++)
                for (int j = 0; j < width; j++)
                {
                    switch (mazeApp.getMazeAppModel().getMboxs()[i][j].getType())
                    {
                        case "EBox" :
                            laby[i][j] = new EBox(j, i);
                            break;
                        case "ABox" :
                            laby[i][j] = new ABox(j, i);
                            break;
                        case "DBox" :
                            laby[i][j] = new DBox(j, i);
                            break;
                        case "WBox" :
                            laby[i][j] = new WBox(j, i);
                            break;
                        case "Sol" :
                            laby[i][j] = new EBox(j, i);
                            break;                    }
                }
            Maze maze = new Maze(width, height, laby);
            VertexInterface r;
            try 
            {
                r = maze.getStartBox();
                PreviousInterface p = Dijkstra.dijkstra(maze, r);
                VertexInterface endCase = maze.getEndBox();
                VertexInterface pere = p.get(endCase);
                if (pere == null)
                {
                    JFrame frame = new JFrame("Erreur");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setVisible(true);
                    JOptionPane.showMessageDialog(frame, "Il n'y a pas de solution", "Erreur", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    while (pere != null)
                    {
                        Labyrinthe.MBox perebis = (Labyrinthe.MBox) pere;
                        int x = perebis.getX();
                        int y = perebis.getY();
                        if (!(mazeApp.getMazeAppModel().getMboxs()[y][x].getType().equals("DBox")))
                            mazeApp.getMazeAppModel().setMboxType(y, x, "Sol");
                        pere = p.get(pere);
                    }
                }
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
            
        }
    }
}
