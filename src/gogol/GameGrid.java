package gogol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class GameGrid extends JPanel
{
	private int sizeX;
	private int sizeY;
	private int tileSize;

	private Graphics gridPainter;

	private Color colorBG;
	private Color colorCell;
	private Color colorGrid;

	public GameGrid()
	{
		super();
		//default size
		sizeX = 600;
		sizeY = 600;
		tileSize = 20;
		//default color
		colorBG = Color.black;
		colorCell = Color.green;
		colorGrid = Color.green;

		this.setSize(sizeX, sizeY);
		this.setBackground(colorBG);
		this.setLayout(new GridBagLayout());
		//gridPainter = this.getGraphics();
	}

	public GameGrid(int x, int y, int tile)
	{
		super();

		sizeX = x;
		sizeY = y;
		tileSize = tile;
		//default color
		colorBG = Color.black;
		colorCell = Color.green;
		colorGrid = Color.green;

		this.setSize(sizeX, sizeY);
		this.setBackground(colorBG);
		gridPainter = this.getGraphics();
	}


	public void setupGrid(Graphics g)
	{
		g.setColor(colorGrid);

		//draw horizontal lines
		for (int x = 0; x < sizeX; x = x + tileSize)
		{
			System.out.println("line at X " + x);
			g.drawLine(x, 0, x, sizeY);
		}

		//draw vertical lines
		for (int y = 0; y < sizeY; y = y + tileSize)
		{
			System.out.println("lineY at Y " + y);
			g.drawLine(y, 0, y, sizeX);
		}
	}

	public void setSize(int x, int y)
	{
		sizeY = y;
		sizeX = x;
	}

	public void setField(Cell cell, int x, int y)
	{
		int tileX = x * tileSize;
		int tileY = y * tileSize;

		if (cell instanceof ConwayCell)
		{
			if (cell.getStatus())
			{
				gridPainter.setColor(colorCell);
				gridPainter.drawRect(tileX, tileY, tileSize, tileSize);
			}
		}
	}


	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(sizeX, sizeY);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setupGrid(g);
	}
}
