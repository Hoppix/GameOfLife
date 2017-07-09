package gogol.cells;


import java.awt.*;

/**
 * the colored cell is a variant of the conway cell
 * it still follows the conway rules but in addition it holds a color
 *
 * Created by khopf on 18/06/2017.
 */

public class ColoredCell implements Cell
{
	boolean alive;
	Color colorStatus;

	boolean newStatus;
	Color newColor;

	public ColoredCell()
	{
		alive = false;
		colorStatus = null;

		newStatus = false;
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
		if (neighbors < 2)
		{
			newStatus = false;
		}
		else if (neighbors > 3)
		{
			newStatus = false;
		}
		else if (neighbors == 3)
		{
			newStatus = true;
		}
		else if (neighbors == 2)
		{
			newStatus = alive;
		}
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
	 * returns the actual color
	 *
	 * @return color status
	 */
	public Color getColorStatus()
	{
		return colorStatus;
	}

	/**
	 * sets the color of the cell
	 */
	public void setColorStatus(Color color)
	{
		newColor = color;
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
		if (alive)
		{
			if (colorStatus == null)
			{
				colorStatus = Color.red;
			}
			else if (colorStatus.equals(Color.red))
			{
				colorStatus = Color.green;
			}
			else if (colorStatus.equals(Color.green))
			{
				colorStatus = Color.blue;
			}
			else if (colorStatus.equals(Color.blue))
			{
				colorStatus = null;
				alive = !alive;
			}
			else
			{
				colorStatus = Color.red;
			}
		}
		else
		{
			alive = !alive;
			colorStatus = Color.red;
		}
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

	@Override
	public boolean getNextStatus() 
	{
		return newStatus;
	}
}
