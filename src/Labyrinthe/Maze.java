package Labyrinthe;

import java.util.ArrayList;
import Dijkstra.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;


public class Maze implements GraphInterface
{
	private VertexInterface[][] t;
	private int width;
	private int height;

	public Maze(int width, int height, VertexInterface[][] t)
	{
		this.height = height;
		this.width = width;
		this.t = t;
	}

	public VertexInterface[][] getMaze()
	{
		return this.t;
	}
	
	public ArrayList<VertexInterface> getAllVertices()
	{
		ArrayList<VertexInterface> l = new ArrayList<VertexInterface>();
		for (int i = 0; i<height; i++)
			for (int j = 0; j<width; j++)
			if (!((MBox) t[i][j]).getType().equals("WBox"))
				l.add(t[i][j]);
		return l;
	}
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex)
	{
		ArrayList<VertexInterface> l = new ArrayList<VertexInterface>();
		MBox m = (MBox) vertex;
		int x = m.getX();
		int y = m.getY();
		ArrayList<VertexInterface> p = this.getAllVertices();
		for (VertexInterface sommet : p)
		{
			MBox s = (MBox) sommet;
			int x2 = s.getX();
			int y2 = s.getY();
			int ecartX = Math.abs(x - x2);
			int ecartY = Math.abs(y - y2);
			if ((ecartX == 1 && ecartY == 0) || (ecartX == 0 && ecartY == 1))
				l.add(sommet);
		}
		return l;
	}

	public int getWeight(VertexInterface src,VertexInterface dst)
	{
		if ((this.getSuccessors(src)).contains(dst))
			return 1;
		else 
			return 100000000;
	}

	public final void initFromTextFile(String fileName) throws Exception, IOException, MazeReadingException
	{	
		VertexInterface[][] t = new VertexInterface[height][width];
		FileReader file = null; // on doit initialiser pour ne pas avoir de problème dans le finally et être sur de fermer qqchose
		BufferedReader buff = null;
		try
		{
			file = new FileReader(fileName);
			buff = new BufferedReader(file);
			String line = buff.readLine();
			int line_number = 0;
			while (line != null)
			{
				line_number ++;
				if (line.length() != width)
				{
					throw new MazeReadingException("la ligne " + line_number + " est invalide");
				}
				else if (line_number > height)
				{
					throw new MazeReadingException("il y a trop de ligne, il ne devrait y avoir que " + height + " lignes");
				}
				for (int i = 0; i < width; i++)
				{
					char c = line.charAt(i);
					switch (c)
					{
						case 'A':
							t[line_number - 1][i] = new ABox(i, line_number - 1);
							break;
						case 'D':
							t[line_number - 1][i] = new DBox(i, line_number - 1);
							break;
						case 'E':
							t[line_number - 1][i] = new EBox(i, line_number - 1);
							break;
						case 'W':
							t[line_number - 1][i] = new WBox(i, line_number - 1);
							break;
						default:
							throw new MazeReadingException("le caractère en position " + i + 1 + " de la ligne " + line_number + " ne correspond à aucun type de case");
					}
				}
				line = buff.readLine();
			}
			if (line_number < height)
				throw new MazeReadingException("il y a " + line_number + " lignes dans le fichier alors qu'il devrait y en avoir " + height);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				buff.close();
				file.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		this.t = t;
	}

	public final void saveToTextFile(String fileName) throws Exception
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(fileName);
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
				{
					MBox s = (MBox) t[i][j];
					char type = s.getType().charAt(0);
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

	public VertexInterface getStartBox() throws Exception
	{
		int flag = 0;
		int x = 0; // équivalent de l'initialiser à nul, on sait qu'il sera soit changé, soit une exception sera levée par la suite
		int y = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (( (MBox) t[i][j] ).getType() == "DBox")
				{
					x = i;
					y = j;
					flag = flag + 1;
				}
		if (flag != 1)
			if (flag == 0)
				throw new Exception("il n'y a pas de case départ");
			else
				throw new Exception("il y a trop de cases départ");
		return t[x][y];
	}

	public VertexInterface getEndBox() throws Exception
	{
		int flag = 0;
		int x = 0; // équivalent de l'initialiser à nul, on sait qu'il sera soit changé, soit une exception sera levée par la suite
		int y = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (( (MBox) t[i][j]).getType() == "ABox")
				{
					x = i;
					y = j;
					flag = flag + 1;
				}
		if (flag != 1)
			if (flag == 0)
				throw new Exception("il n'y a pas de case d'arrivée");
			else
				throw new Exception("il y a trop de cases d'arrivée");
		return t[x][y];
	}

	public void showAndWriteSolution(String fileName) throws Exception
	{
		VertexInterface endBox = this.getEndBox();
		VertexInterface startBox = this.getStartBox();
		PreviousInterface p = Dijkstra.dijkstra(this, startBox);
		ArrayList<VertexInterface> chemin = new ArrayList<VertexInterface>();
		VertexInterface pere = p.get(endBox);
		System.out.println(pere.getLabel());
		while ((pere != null) && (!(pere.getLabel().equals(startBox.getLabel()))))
		{
			chemin.add(pere);
			pere = p.get(pere);
		}
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter("data/" + fileName + ".txt");
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
				{
					if (chemin.contains(t[i][j]))
					{
						if (j == width - 1) 
						{
							pw.println("S");
							System.out.println("S");
						}
						else
						{
							pw.print("S");
							System.out.print("S");
						}
					}
					else
					{
						MBox s = (MBox) t[i][j];
						char type = s.getType().charAt(0);
						if (j == width - 1) 
						{
							pw.println(type);
							System.out.println(type);
						}
						else
						{
							pw.print(type);
							System.out.print(type);
						}
					}
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
}
