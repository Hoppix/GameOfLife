package gogol;

import java.awt.*;

/**
 * Created by khopf on 18/06/2017.
 */

public class ColoredCell implements Cell
{
	boolean alive;
	Color mergeStatus;
	int r;
	int g;
	int b;

	public ColoredCell()
	{
		alive = false;
		r = 255;
		g = 255;
		b = 255;
		mergeStatus = new Color(r, g, b);
	}

	/**
	 * sets the status the cell will have in the next cycle
	 *
	 * @param neighbors
	 */
	@Override
	public void setNextStatus(int neighbors)
	{

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
		return mergeStatus;
	}
	
	public void setColorStatus(Color color)
	{
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		mergeStatus = new Color(r, g, b);
	}

	/**
	 * updates the current status with the status of the next cycle
	 */
	@Override
	public void updateStatus()
	{

	}

	/**
	 * Sets the cell status e.G. when klicked
	 */
	@Override
	public void toggleStatus()
	{

	}

	/**
	 * gives the cell data as a String
	 */
	@Override
	public String cellToString()
	{
		return null;
	}
}
