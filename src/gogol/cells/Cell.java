package gogol.cells;

public interface Cell 
{
	/**
	 * sets the status the cell will have in the next cycle
	 */
	public void setNextStatus(int neighbors);
	
	/**
	 * returns the current cell status
	 */
	public boolean getStatus();
	
	/**
	 * updates the current status with the status of the next cycle
	 */
	public void updateStatus();
	
	/**
	 * Sets the cell status e.G. when klicked
	 */
	public void toggleStatus();
	
	/**
	 * gives the cell data as a String
	 */
	public String toString();
	
	/**
	 * gives the Status the cell will have next Cycle
	 * @return
	 */
	public boolean getNextStatus();
}