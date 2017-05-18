package gogol;

public class ConwayCell implements Cell {

	boolean alive;
	
	protected ConwayCell()
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
