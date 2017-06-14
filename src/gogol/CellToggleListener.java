package gogol;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by khopf on 02/06/2017.
 */
public class CellToggleListener implements MouseListener
{
	Controller loller;

	public CellToggleListener(Controller that)
	{
		loller = that;
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		int posX = e.getX();
		int posY = e.getY();

		int tile = loller.gamegrid.tileSize;

		int cellX = posX / tile;
		int cellY = posY / tile;

		if(loller.preloadMode.equals("toggle"))
		{
			loller.setCell(cellX, cellY);
		}
		else
		{
			loller.preloader.loadPreset(cellX, cellY, loller.preloadMode); //debugging
		}
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{

		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseEntered(MouseEvent e)
	{

		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseExited(MouseEvent e)
	{
		/**
		 * IGNORE
		 */
	}
}
