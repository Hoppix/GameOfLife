package gogol;

/*
 * Represents a cell for Conways original Game of Life
 */
public class ConwayCell implements Cell {

	boolean alive;
	boolean newStatus;
	
	public ConwayCell()
	{
		alive = false;
		newStatus = false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gogol.Cell#setNextStatus(int)
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
	}
	
	/*
	 * (non-Javadoc)
	 * @see gogol.Cell#getStatus()
	 */
	@Override
	public boolean getStatus() 
	{
		return alive;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gogol.Cell#updateStatus()
	 */
	@Override
	public void updateStatus() 
	{
		alive = newStatus;
	}

	@Override
	public void toggleStatus() 
	{
		alive = !alive;
	}
	
}
