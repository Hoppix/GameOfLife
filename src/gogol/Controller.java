package gogol;

import javax.swing.JPanel;

/*
 * is a control freak
 */
class Controller 
{
	JPanel gamegrid;
	Cell[][] survivalMatrix;
	
	protected Controller(JPanel grid)
	{
		gamegrid = grid;
		survivalMatrix = setGridsize(42, 23);
	}
	
	/*
	 * Advance the game by one cycle
	 */
	protected void stepForward() 
	{
		
	}
	
	/*
	 * set the size of the survivalMatrix according to the grid size
	 */
	protected Cell[][] setGridsize(int x, int y)
	{
		return new Cell[x][y];
	}
	
	/*
	 * returns the number of alive Neighbors of a cell
	 */
	protected int aliveNeighbours(int x, int y)
	{
		String mode = "Conway";
		int count = 0;
		
		switch (mode) {
		case "Conway":
			count = conwayRulez(x, y);
			break;

		default:
			break;
		}
		
		return count;
	}
	
	/*
	 * count alive Neighbors of a cell with the Conway rules
	 */
	private int conwayRulez(int x, int y)
	{
		int count = 0;
		
		for (int i = x-1; i <= x+1; i++) 
		{
			for (int j = y-1; j <= y+1; j++) 
			{
				if(survivalMatrix[i][j].getStatus())
				{
					count++;
				}
			}
		}
		
		count = count - (survivalMatrix[x][y].getStatus() ? 1 : 0);
		
		return count;
	}
}
