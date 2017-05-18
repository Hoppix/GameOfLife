package gogol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class GameGrid extends JPanel
{
    protected int sizeX;
    protected int sizeY;

    public GameGrid()
    {
        super();
        sizeX = 600;
        sizeY = 600;
        this.setSize(sizeX,sizeY);
        this.setBackground(Color.black);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(sizeX,sizeY);
    }
}
