package gogol.cells;

import java.awt.*;

/**
 * Created by khopf on 08/07/2017.
 */
public class PvPCell extends ColoredCell implements Cell
{

	public PvPCell()
	{
		alive = false;
		newStatus = false;

		colorStatus = null;
		newColor = null;
	}

	/**
	 * Sets the cell status e.G. when klicked
	 */
	@Override
	public void toggleStatus()
	{
		if(alive) newColor = null;
		newStatus = !alive;
	}
}
