package Interface_graphique;

import javax.swing.*;
import java.awt.*;


public class EditionPannel extends JPanel
{
    private final MazeApp mazeApp;
    private DBoxRadioButton dBoxRadioButton;
    private ABoxRadioButton aBoxRadioButton;
    private WBoxRadioButton wBoxRadioButton;
    private EBoxRadioButton eBoxRadioButton;

    public EditionPannel(MazeApp mazeApp)
    {
        super();
        this.mazeApp = mazeApp;
        ButtonGroup bg = new ButtonGroup();
        DBoxRadioButton dBoxRadioButton = new DBoxRadioButton(mazeApp);
        ABoxRadioButton aBoxRadioButton = new ABoxRadioButton(mazeApp);
        WBoxRadioButton wBoxRadioButton = new WBoxRadioButton(mazeApp);
        EBoxRadioButton eBoxRadioButton = new EBoxRadioButton(mazeApp);
        bg.add(dBoxRadioButton);
        bg.add(aBoxRadioButton);
        bg.add(wBoxRadioButton);
        bg.add(eBoxRadioButton);
        this.add(dBoxRadioButton);
        this.add(aBoxRadioButton);
        this.add(wBoxRadioButton);
        this.add(eBoxRadioButton);
        // add(dBoxRadioButton = new DBoxRadioButton(mazeApp));
        // add(aBoxRadioButton = new ABoxRadioButton(mazeApp));
        // add(wBoxRadioButton = new WBoxRadioButton(mazeApp));
        // add(eBoxRadioButton = new EBoxRadioButton(mazeApp));


        setLayout(new GridLayout(4,1)); //on peut rajouter deux autres arguments pour le gap horizontal et vertical 
        // setBackground(Color.gray);
    }
}