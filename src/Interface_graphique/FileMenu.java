package Interface_graphique;

import javax.swing.*;

public class FileMenu extends JMenu
{
    private final MazeApp mazeApp;
    private QuitMenuItem quitMenuItem;
    private SaveMenuItem saveMenuItem;
    private OpenMenuItem openMenuItem;

    public FileMenu(MazeApp mazeApp)
    {
        super("Menu");
        this.mazeApp = mazeApp;
        add(quitMenuItem = new QuitMenuItem(mazeApp));
        add(saveMenuItem = new SaveMenuItem(mazeApp));
        add(openMenuItem = new OpenMenuItem(mazeApp));
    }
}
