package gogol;

public class ConwayCell implements Cell {

	boolean alive;
	boolean newStatus;
	
	protected ConwayCell()
	{
		alive = false;
		newStatus = false;
	}

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
	
	@Override
	public boolean getStatus() 
	{
		return alive;
	}

	@Override
	public void updateStatus() 
	{
		alive = newStatus;
	}
	
}
