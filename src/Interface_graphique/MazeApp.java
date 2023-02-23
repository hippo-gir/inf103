package Interface_graphique;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.ArrayList;


import Model.*;


public class MazeApp extends JFrame implements ChangeListener
{
    private MenuBar menuBar;
    private EditionPannel editionPannel;
    private LaunchPannel launchPannel;
    private LabyPannel labyPannel;
    private MazeAppModel mazeAppModel;
    
    public MazeAppModel getMazeAppModel() 
    {
        return mazeAppModel;
    }

    public void setMazeAppModel(MazeAppModel mazeAppModel) 
    {
        this.mazeAppModel = mazeAppModel;
    }


    public MazeApp(int height, int width)
    {
        super("MazeApp");
        mazeAppModel = new MazeAppModel(height, width);
        mazeAppModel.addObserver(this);

        // Window menu bar creation
        setJMenuBar(menuBar = new MenuBar(this));
        // Window content creation
        add(editionPannel = new EditionPannel(this), BorderLayout.WEST);
        add(launchPannel = new LaunchPannel(this), BorderLayout.SOUTH);
        add(labyPannel =  new LabyPannel(this), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        if (this.getMazeAppModel().isChangeLabyPannel()) 
        {
            this.setLabyPannel(new LabyPannel(this));
        }    
        labyPannel.notifyForUpdate();
    }

    public void setLabyPannel(LabyPannel labyPannel)
    {
        this.remove(this.labyPannel);
        this.add(labyPannel, BorderLayout.EAST);
        this.labyPannel = labyPannel;
        this.pack();
        this.repaint();
    }

}
