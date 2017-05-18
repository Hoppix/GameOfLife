package gogol;

public class ConvayCell implements Cell {

	boolean alive;
	
	protected ConvayCell()
	{
		alive = false;
	}

	@Override
	public void setNextStatus() 
	{
		alive = !alive;
	}

	@Override
	public boolean getStatus() 
	{
		return alive;
	}
	
}
