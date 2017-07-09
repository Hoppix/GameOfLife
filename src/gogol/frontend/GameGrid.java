package gogol.frontend;

import gogol.cells.Cell;
import gogol.cells.ColoredCell;
import gogol.cells.ConwayCell;
import gogol.cells.PvPCell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * the gamegrid transforms the information of the SVM into visible information on a grid
 *
 * Created by hoppix on 18.05.17.
 */
public class GameGrid extends JPanel
{
	public int sizeX;
	public int sizeY;
	public int tileSize;

	private boolean paintPVP;
	private Rectangle playerRedArea;
	private Rectangle playerBlueArea;
	
	private Color colorBG;
	private Color colorCell;
	private Color colorGrid;

	/**
	 * Grid for displaying the Cell-Array on the GUI-surface.
	 */
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

	/**
	 * configure the image where all the painting happens
	 */
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

	/**
	 * paint lines for the grid
	 * @param g
	 */
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

	@Override
	public void setSize(int x, int y)
	{
		sizeY = y;
		sizeX = x;
	}

	/**
	 * takes in arguments from the controller for displaying pvp mode
	 * @param setter
	 * @param red
	 * @param blue
	 */
	public void setPaintPVP(boolean setter, Rectangle red, Rectangle blue)
	{
		paintPVP = setter;
		playerRedArea = red;
		playerBlueArea = blue;
	}

	/**
	 * when pvp mode is activated colored rectangles are painted to make the starting areas visible
	 *
	 * @param g
	 */
	private void paintPlayerAreas(Graphics g)
	{
		if(!paintPVP) return;

		int x = playerRedArea.x * tileSize;
		int y = playerRedArea.y * tileSize;

		int width = playerRedArea.width * tileSize;
		int height = playerRedArea.height * tileSize;

		g.setColor(Color.red);
		g.drawRect(x, y, width, height);

		x = playerBlueArea.x * tileSize;
		y = playerBlueArea.y * tileSize;

		width = playerBlueArea.width * tileSize;
		height = playerBlueArea.height * tileSize;

		g.setColor(Color.blue);
		g.drawRect(x, y, width, height);

		repaint();
	}


	@Override
	public Dimension getSize()
	{
		return new Dimension(sizeX, sizeY);
	}

	/**
	 * paints a small rectangle on the grid which represents a cell
	 * @param cell for receiving needed information
	 * @param x location of the cell
	 * @param y location of the cell
	 */
	public void setField(Cell cell, int x, int y)
	{
		// calculate the actual position on the panel
		int tileX = x * tileSize;
		int tileY = y * tileSize;

		// get graphic context of the image
		Graphics g = PaintImage.drawnImage.getGraphics();

		// different painting behaviours for different cell types
		if (cell instanceof ConwayCell)
		{
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
		if(cell instanceof ColoredCell)
		{
			if(cell.getStatus())
			{
				g.setColor(((ColoredCell) cell).getColorStatus());
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
			else
			{
				g.setColor(colorBG);
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
		}
		if(cell instanceof PvPCell)
		{
			if(cell.getStatus())
			{
				g.setColor(((PvPCell) cell).getColorStatus());
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
			else
			{
				g.setColor(colorBG);
				g.fillRect(tileX, tileY, tileSize, tileSize);
			}
		}
		// paint the image on the panel
		repaint();
	}


	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(sizeX, sizeY);
	}

	/**
	 * this method applies the paintimage to the actual panel
	 * and paints additional components
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(PaintImage.drawnImage,0,0, null);
		setupGrid(g);
		paintPlayerAreas(g);
	}
}
