package gogol.cells;

import java.awt.*;

/**
 * Created by khopf on 08/07/2017.
 */
public class PvPCell implements Cell
{

	boolean alive;
	boolean newStatus;

	Color colorStatus;
	Color newColor;

	public PvPCell()
	{
		alive = false;
		newStatus = false;

		colorStatus = null;
		newColor = null;
	}
	/**
	 * sets the status the cell will have in the next cycle
	 *
	 * @param neighbors
	 */
	@Override
	public void setNextStatus(int neighbors)
	{
		if(neighbors < 2)
		{
			newStatus = false;
		}
		else if(neighbors > 3)
		{
			newStatus = false;
		}
		else if(neighbors == 3)
		{
			newStatus = true;
		}
		else if(neighbors == 2)
		{
			newStatus = alive;
		}
	}

	public void setNewColor(Color color)
	{
		newColor = color;
	}

	/**
	 * returns the current cell status
	 */
	@Override
	public boolean getStatus()
	{
		return alive;
	}

	/**
	 * gives the Status the cell will have next Cycle
	 *
	 * @return
	 */
	@Override
	public boolean getNextStatus()
	{
		return newStatus;
	}

	/**
	 * @return
	 */
	public Color getColorStatus()
	{
		return colorStatus;
	}

	/**
	 * @return
	 */
	public Color getNextColorStatus()
	{
		return newColor;
	}


	/**
	 * updates the current status with the status of the next cycle
	 */
	@Override
	public void updateStatus()
	{
		alive = newStatus;
		colorStatus = newColor;
	}

	/**
	 * Sets the cell status e.G. when klicked
	 */
	@Override
	public void toggleStatus()
	{
		if(alive) colorStatus = null;
		newStatus = !alive;
	}

	/**
	 * sets the color of the cell
	 */
	public void setColorStatus(Color color)
	{
		newColor = color;
	}


	/**
	 * gives the cell data as a String
	 * String Matches: alive + "," + r + "," + g + "," + b + ";"
	 */
	@Override
	public String toString()
	{
		if (colorStatus == null)
		{
			return alive + ",null";
		}
		return alive + "," + colorStatus.getRed() + "," + colorStatus.getGreen() + "," + colorStatus.getBlue();
	}
}
