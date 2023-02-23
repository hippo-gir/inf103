package Interface_graphique;

import javax.swing.JMenu;

public class EditionMenu extends JMenu
{
    private MazeApp mazeApp;
    private AddColumnMenuItem addColumnMenuItem;
    private AddLineMenuItem addLineMenuItem;
    private DelColumnMenuItem delColumnMenuItem;
    private DelLineMenuItem delLineMenuItem;

    public EditionMenu(MazeApp mazeApp)
    {
        super("Edition");
        this.mazeApp = mazeApp;
        add(addColumnMenuItem = new AddColumnMenuItem(mazeApp));
        add(addLineMenuItem = new AddLineMenuItem(mazeApp));
        add(delColumnMenuItem = new DelColumnMenuItem(mazeApp));
        add(delLineMenuItem = new DelLineMenuItem(mazeApp));
    }
}
