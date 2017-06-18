package gogol;

/**
 * Created by khopf on 18/06/2017.
 */
public class ColorWarCell implements Cell
{

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
		return false;
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
