package gogol;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by hoppix on 18.05.17.
 */
public class GameGrid extends JPanel
{
	protected int sizeX;
	protected int sizeY;
	protected int tileSize;

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
		colorBG = Color.darkGray;
		colorCell = Color.white;
		colorGrid = Color.gray;

		this.setSize(sizeX, sizeY);
		this.setBackground(colorBG);

		setupPaintImage();
	}

	private void setupPaintImage()
	{
		PaintImage.drawnImage = new BufferedImage(sizeX, sizeY,
				BufferedImage.TYPE_INT_ARGB);

		Graphics initG = PaintImage.drawnImage.getGraphics();
		initG.create();
		initG.setColor(colorBG);
		initG.fillRect(0,0,sizeX,sizeY);
		repaint();
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


	@Override
	public Dimension getSize()
	{
		return new Dimension(sizeX, sizeY);
	}

	public void setField(Cell cell, int x, int y)
	{
		int tileX = x * tileSize;
		int tileY = y * tileSize;

		Graphics g = PaintImage.drawnImage.getGraphics();

		if (cell instanceof ConwayCell)
		{
			//Graphics g = this.getGraphics();
			if (cell.getStatus())
			{				
				g.setColor(colorCell);
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
			else
			{
				g.setColor(colorBG);
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
		}
		repaint();
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
		g.drawImage(PaintImage.drawnImage,0,0, null);
		setupGrid(g);
	}
}
