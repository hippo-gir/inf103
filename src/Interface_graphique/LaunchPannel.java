package Interface_graphique;

import javax.swing.*;
import java.awt.*;


public class LaunchPannel extends JPanel
{
    private final MazeApp mazeApp;
    private StartButton startButton;
    private ResetButton resetButton;
    private ResetCheminButton resetCheminButton;

    public LaunchPannel(MazeApp mazeApp)
    {
        super();
        this.mazeApp = mazeApp;
        add(resetButton = new ResetButton(mazeApp));
        add(resetCheminButton = new ResetCheminButton(mazeApp));
        add(startButton = new StartButton(mazeApp));
        

        setLayout(new GridLayout(1,2));
        setPreferredSize(new Dimension(256, 60));
        // setBackground(Color.gray);
    }

   /* public void notifyForUpdate()
    {
        startButton.notifyForUpdate();
        resetButton.notifyForUpdate();
    }*/
}
