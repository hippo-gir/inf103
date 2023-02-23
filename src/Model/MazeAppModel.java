package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.util.ArrayList;

import javax.swing.event.*;

import Labyrinthe.MazeReadingException;

public class MazeAppModel 
{
    private boolean modified;
    private MBox[][] mboxs;
    private int height;
    private int width;
    private String selectedMenu;
    private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
    private MBox departBox;
    private MBox endBox;
    private boolean changeLabyPannel;

    public boolean isChangeLabyPannel() 
    {
        return changeLabyPannel;
    }

    public void setChangeLabyPannel(boolean changeLabyPannel) 
    {
        if (this.changeLabyPannel != changeLabyPannel)
        {
            this.changeLabyPannel = changeLabyPannel;
            stateChanges(); 
            modified = true;
        }
    }

    public void setMboxs(MBox[][] mboxs) 
    {
        this.mboxs = mboxs;
        stateChanges();
        modified = true;
    }

    public void setHeight(int height)
    {
        if (this.height != height)
        {
            this.height = height;
            stateChanges();
            modified = true;
        }
    }

    public void setWidth(int width) 
    {
        if (this.width != width)
        {
            this.width = width;
            stateChanges();
            modified = true;
        }
    }

    public MBox getDepartBox() 
    {
        return departBox;
    }

    public void setDepartBox(MBox box) 
    {
        if (box == null)
        {
            departBox = null;
        }
        else if (departBox == null)
        {
            departBox = box;
            box.setType("DBox");
            stateChanges();
            
        }
        else if ((departBox.getI() != box.getI() ) || (departBox.getJ() != box.getJ()))
        {
            departBox.setType("EBox");
            box.setType("DBox");
            this.departBox = box;
            stateChanges();
        }
        modified = true;
    }

    public MBox getEndBox() {
        return endBox;
    }

    public void setEndBox(MBox box) 
    {
        if (box == null)
        {
            endBox = null;
        }
        else if (endBox == null)
        {
            endBox = box;
            box.setType("ABox");
            stateChanges();
        }
        else if ((endBox.getI() != box.getI() ) || (endBox.getJ() != box.getJ()))
        {
            endBox.setType("EBox");
            box.setType("ABox");
            this.endBox = box;
            stateChanges();
        }
        modified = true;
    }

    public MazeAppModel(int height, int width)
    {
        modified = false;
        changeLabyPannel = false;
        mboxs = new MBox[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                mboxs[i][j] = new MBox(i,j,"EBox");
            }
        this.height = height;
        this.width = width;
        selectedMenu = "EBox";
    }

    public void stateChanges()
    {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners)
            listener.stateChanged(evt);
    }

    public void addObserver(ChangeListener listener)
    {
        listeners.add(listener);
    }

    public void addColumn()
    {
        MBox[][] mboxes = this.mboxsInitialisation(height, width + 1);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                mboxes[i][j].setType(this.mboxs[i][j].getType());
                // if (mboxes[i][j].getType() == "DBox") this.departBox = mboxes[i][j]; // on ne veut pas de state change avant de changer l observer ! (cf remarque dans initFromTextFile)
                // if (mboxes[i][j].getType() == "ABox") this.endBox = mboxes[i][j];
            }
        this.width = width + 1;
        this.mboxs = mboxes;
        modified = true;
        this.setChangeLabyPannel(true);
        this.setChangeLabyPannel(false);
    }

    public void addLine()
    {
        MBox[][] mboxes = this.mboxsInitialisation(height + 1, width);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                mboxes[i][j].setType(this.mboxs[i][j].getType());
            }
        this.height = height + 1;
        this.mboxs = mboxes; // même problème que dans initFromTextFile on ne peut pas utiliser de setter sinon le statechanged mène à un indexOutOfBound
        modified = true;
        this.setChangeLabyPannel(true);
        this.setChangeLabyPannel(false);
    }

    public MBox[][] mboxsInitialisation(int height, int width)
    {
        MBox[][] mboxes = new MBox[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                mboxes[i][j] = new MBox(i, j, "EBox");
            }
        return mboxes;
    }

    public void deleteColumn()
    {
        MBox[][] mboxes = this.mboxsInitialisation(height, width - 1);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width - 1; j++)
            {
                mboxes[i][j].setType(this.mboxs[i][j].getType());
            }
        this.width = width - 1;
        this.mboxs = mboxes;
        modified = true;
        this.setChangeLabyPannel(true);
        this.setChangeLabyPannel(false);
    }

    public void deleteLine()
    {
        MBox[][] mboxes = this.mboxsInitialisation(height - 1, width);
        for (int i = 0; i < height - 1; i++)
            for (int j = 0; j < width; j++)
            {
                mboxes[i][j].setType(this.mboxs[i][j].getType());
            }
        this.height = height - 1;
        this.mboxs = mboxes;
        modified = true;
        this.setChangeLabyPannel(true);
        this.setChangeLabyPannel(false); 
    }

    public boolean isModified() 
    {
        return modified;
    }

    public void setModified(boolean modified) 
    {
        this.modified = modified;
    }

    public MBox[][] getMboxs() 
    {
        return mboxs;
    }

    public void setMboxType(int i, int j, String type) 
    {
        if (!((((this.mboxs)[i][j]).getType()).equals(type)))
                {
                    (this.mboxs)[i][j].setType(type);;
                    modified = true;
                    if (type.equals("DBox")) this.setDepartBox((this.mboxs)[i][j]);
                    else if (type.equals("ABox")) this.setEndBox((this.mboxs)[i][j]);
                    stateChanges();
                }
    }

    public int getHeight() 
    {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) 
    {
        if (!(this.selectedMenu.equals(selectedMenu)))
        {
            this.selectedMenu = selectedMenu;
            modified = true;
            stateChanges();
        }
    }

    public final void initFromTextFile(String fileName) throws Exception, IOException, MazeReadingException
	{	

        FileReader file2 = null; // on doit initialiser pour ne pas avoir de problème dans le finally et être sur de fermer qqchose
		BufferedReader buff2 = null;
        int hauteur = 0;
        int largeur = 0;
        try
		{
			file2 = new FileReader(fileName);
			buff2 = new BufferedReader(file2);
			String line = buff2.readLine();
			largeur = line.length();
			while (line != null)
			{
				hauteur ++;
				line = buff2.readLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
        finally
        {
            try
			{
				buff2.close();
				file2.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
        }
        this.height = hauteur; // c'est important de ne pas faire appel au setter, on ne veut pas de statechanged pour le moment sinon il y aura un indexOutOfBound dans notifyForUpdate de LabyPannel
        this.width = largeur; // (suite) à l'inverse si on commence par changer le pannel, il utilisera les mauvaises hauteur et largeurs
        MBox[][] newMboxes = this.mboxsInitialisation(hauteur, largeur);
        this.mboxs = newMboxes;
        this.modified = false;
        this.selectedMenu = "EBox";
        this.setChangeLabyPannel(true);
        this.setChangeLabyPannel(false);
		MBox[][] t = new MBox[hauteur][largeur];
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
				if (line.length() != this.width)
				{
					throw new MazeReadingException("la ligne " + line_number + " est invalide");
				}
				else if (line_number > this.height)
				{
					throw new MazeReadingException("il y a trop de ligne, il ne devrait y avoir que " + height + " lignes");
				}
				for (int j = 0; j < this.width; j++)
				{
					char c = line.charAt(j);
					switch (c)
					{
						case 'A':
							t[line_number - 1][j] = new MBox(line_number - 1, j, "ABox");
                            this.setEndBox(t[line_number - 1][j]);
							break;
						case 'D':
							t[line_number - 1][j] = new MBox(line_number - 1, j, "DBox");
                            this.setDepartBox(t[line_number - 1][j]);
							break;
						case 'E':
							t[line_number - 1][j] = new MBox(line_number - 1, j, "EBox");
							break;
						case 'W':
							t[line_number - 1][j] = new MBox(line_number - 1, j, "WBox");
							break;
                        case 'S':
							t[line_number - 1][j] = new MBox(line_number - 1, j, "Sol");
							break;
						default:
							throw new MazeReadingException("le caractère en position " + j + 1 + " de la ligne " + line_number + " ne correspond à aucun type de case");
					}
				}
				line = buff.readLine();
			}
			if (line_number < this.height)
				throw new MazeReadingException("il y a " + line_number + " lignes dans le fichier alors qu'il devrait y en avoir " + this.height);
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
        // for (int j = 0; j < width; j++) System.out.print(t[0][j]);
		this.setMboxs(t);
        modified = false;
        stateChanges();
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
					MBox s = (MBox) mboxs[i][j];
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
        modified = false;
	}
}
