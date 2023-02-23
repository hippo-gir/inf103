package Interface_graphique;
import javax.swing.*;

public class MenuBar extends JMenuBar
{   
    private final MazeApp mazeApp;
    private FileMenu fileMenu;
    private EditionMenu editionMenu;

    public MenuBar(MazeApp mazeApp)
    {
        super();
        this.mazeApp = mazeApp;
        add(fileMenu = new FileMenu(mazeApp));
        add(editionMenu = new EditionMenu(mazeApp));
    }
}
