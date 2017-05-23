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

	private Color colorBG;
	private Color colorCell;
	private Color colorGrid;

	public GameGrid()
	{
		super();
		//default size
		sizeX = 1100;
		sizeY = 600;
		tileSize = 10;
		//default color
		colorBG = Color.black;
		colorCell = Color.green;
		colorGrid = Color.white;

		this.setSize(sizeX, sizeY);
		this.setBackground(colorBG);
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
	}

	public GameGrid(Color bg, Color cell, Color grid)
	{
		super();
		//default size
		sizeX = 600;
		sizeY = 600;
		tileSize = 20;

		colorBG = bg;
		colorCell = cell;
		colorGrid = grid;

		this.setSize(sizeX, sizeY);
		this.setBackground(colorBG);
	}


	private void setupGrid(Graphics g)
	{
		g.setColor(colorGrid);

		//draw horizontal lines
		for (int x = 0; x < sizeX; x = x + tileSize)
		{
			g.drawLine(x, 0, x, sizeY);
		}

		//draw vertical lines
		for (int y = 0; y < sizeY; y = y + tileSize)
		{
			g.drawLine(0, y, sizeX, y);

		}
	}

	public void setSize(int x, int y)
	{
		sizeY = y;
		sizeX = x;
	}

	public void setTileSize(int tile)
	{
		tileSize = tile;
	}

	public void setColorBG(Color bg)
	{
		colorBG = bg;
	}

	public void setColorCell(Color cell)
	{
		colorCell = cell;
	}

	public void setColorGrid(Color grid)
	{
		colorGrid = grid;
	}

	@Override
	public Dimension getSize()
	{
		return new Dimension(sizeX, sizeY);
	}

	public void setField(Cell cell, int x, int y)
	{
		int tileX = x * tileSize;
		int tileY = y * tileSize;

		if (cell instanceof ConwayCell)
		{
			if (cell.getStatus())
			{
				this.getGraphics().setColor(colorCell);
				this.getGraphics().drawRect(tileX, tileY, tileSize, tileSize);
			}
		}
		//TODO erweitern für andere celltypes
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
