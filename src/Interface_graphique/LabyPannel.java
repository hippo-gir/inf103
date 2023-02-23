package Interface_graphique;

import javax.swing.*;
import java.awt.*;

public class LabyPannel extends JPanel
{
    private final MazeApp mazeApp;
    private MButton[][] table;

    public LabyPannel(MazeApp mazeApp)
    {
        super();
        int height = mazeApp.getMazeAppModel().getHeight();
        int width = mazeApp.getMazeAppModel().getWidth();
        this.mazeApp = mazeApp;
        MButton[][] table = new MButton[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                table[i][j] = new MButton(mazeApp, "EBox",i, j);
            }
        this.table = table;
        setLayout(new GridLayout(height, width));
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                add(table[i][j]);
            }
    }
    
    public void notifyForUpdate()
    {
        int height = mazeApp.getMazeAppModel().getHeight();
        int width = mazeApp.getMazeAppModel().getWidth();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                (table[i][j]).notifyForUpdate();
            }
    }
}