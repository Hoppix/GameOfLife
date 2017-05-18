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
    protected int tileSize;
    protected Graphics gridPainter;

    public GameGrid()
    {

        sizeX = 600;
        sizeY = 600;
        tileSize = 20;
        this.setSize(sizeX,sizeY);
        this.setBackground(Color.black);       

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(sizeX,sizeY);
    }

    public void setupGrid()
    {
        gridPainter = this.getGraphics();
        gridPainter.setColor(Color.black);

        //draw horizontal lines
        for(int x = 0; x < sizeX; x = x + tileSize)
        {
            System.out.println("line at X " + x);
            gridPainter.drawLine(x, 0, x, sizeY);
        }

        //draw vertical lines
        for(int y = 0; y < sizeY; y = y + tileSize)
        {
            System.out.println("lineY at Y " + y);
            gridPainter.drawLine(y, 0, y, sizeX);
        }
        gridPainter.drawRect(0,0,50,50);
        gridPainter.drawOval(0,0,40,40);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setupGrid();
    }
}
