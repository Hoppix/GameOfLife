package gogol.listener;

import gogol.backend.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by khopf on 02/06/2017.
 */
public class CellToggleListener implements MouseListener
{
	Controller controller;

	/**
	 * Listener for setting structured or single cells
	 * on the GameGrid.
	 * @param parent
	 */
	public CellToggleListener(Controller parent)
	{
		controller = parent;
	}


	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 * If preloadMode is on toggle: only single cells are enabled
	 * otherwise the corresponding structure is loaded.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		int posX = e.getX();
		int posY = e.getY();

		int tile = controller.gamegrid.tileSize;

		int cellX = posX / tile;
		int cellY = posY / tile;

		if(controller.preloadMode.equals("Toggle"))
		{
			controller.setCell(cellX, cellY);
		}
		else
		{
			controller.preloader.loadPreset(cellX, cellY, controller.preloadMode);
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
