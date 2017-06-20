package gogol;

import java.awt.*;

/**
 * Created by khopf on 18/06/2017.
 */

public class ColoredCell implements Cell
{
	boolean alive;
	Color colorStatus;
	int r;
	int g;
	int b;
	
	boolean newStatus;
	Color newColor;

	public ColoredCell()
	{
		alive = false;
		colorStatus = null;
		r = 0;
		g = 0;
		b = 0;
		
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

	/**
	 * returns the current cell status
	 */
	@Override
	public boolean getStatus()
	{
		return alive;
	}

	/**
	 * returns the merging color
	 * @return merge status
	 */
	public Color getColorStatus()
	{
		return colorStatus;
	}
	
	public void setColorStatus(Color color)
	{
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		newColor = new Color(r, g, b);
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
		if(alive)
		{
			alive = !alive;
			colorStatus = null;
			r = 0;
			g = 0;
			b = 0;
		}
		else
		{
			alive = !alive;
		}
	}

	/**
	 * gives the cell data as a String
	 */
	@Override
	public String cellToString()
	{
		//TODO: impl
		return null;
	}
}
