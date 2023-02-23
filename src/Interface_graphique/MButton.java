package Interface_graphique;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.String;


import Model.MazeAppModel;

public class MButton extends JButton implements ActionListener
{
    protected MazeApp mazeApp;
    private String type;
    private int i;
    private int j;

    public MButton(MazeApp mazeApp, String type, int i, int j)
    {
        super();
        // this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        this.setPreferredSize(new Dimension(50, 50));
        // this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        this.mazeApp = mazeApp;
        this.type = type;
        this.i = i;
        this.j = j;
        switch (type)
        {
            case "EBox" :
                this.setBackground(new Color(98, 131, 149));
                break;
            case "ABox":
                this.setBackground(new Color(196, 32, 33));
                break;
            case "DBox":
                this.setBackground(new Color(0, 222, 118));
                break;
            case "WBox":
                this.setBackground(new Color(31, 34, 50));
                break;
            case "Sol":
                this.setBackground(new Color(255, 159, 91));
        }
        addActionListener(this);
    }

    public void setType(String type)
    {
        this.type = type;
    }


    public void paintComponent()
    {
        switch (type)
        {
            case "EBox" :
                this.setBackground(new Color(98, 131, 149));
                break;
            case "ABox":
                this.setBackground(new Color(196, 32, 33));
                break;
            case "DBox":
                this.setBackground(new Color(0, 222, 118));
                break;
            case "WBox":
                this.setBackground(new Color(31, 34, 50));
                break;
            case "Sol":
                this.setBackground(new Color(255, 159, 91));
        }
    }

    public void notifyForUpdate()
    {
        String type = mazeApp.getMazeAppModel().getMboxs()[i][j].getType();
        if (!(this.type.equals(type)))
        {
            this.setType(type);
            this.paintComponent();
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
        String s = mazeAppModel.getSelectedMenu();
        if (s.equals("DBox"))
        {
            mazeAppModel.setDepartBox(mazeAppModel.getMboxs()[i][j]);
        }
        else if (s.equals("ABox"))
        {
            mazeAppModel.setEndBox(mazeAppModel.getMboxs()[i][j]);          
        }
        mazeAppModel.setMboxType(i, j, s);
        this.setType(s); 
    } 
}
