package gogol;

import javax.swing.JPanel;

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
	 * returns the number of alive Neighbours of a cell
	 */
	protected int aliveNeighbours(int x, int y)
	{
		String mode = "Convay";
		int count = 0;
		
		switch (mode) {
		case "Convay":
			count = convayRulez(x, y);
			break;

		default:
			break;
		}
		
		return count;
	}
	
	private int convayRulez(int x, int y)
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
