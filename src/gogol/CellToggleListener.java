package gogol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		loller.setCell(cellX, cellY);

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
