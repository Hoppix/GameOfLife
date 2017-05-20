package gogol;

public interface Cell 
{
	public void setNextStatus(int neighbors);
	
	public boolean getStatus();
	
	public void updateStatus();
}
