package gogol;

import javax.swing.JPanel;

/*
 * Holds the Cell array and gives Cells data about their Neighbors
 */
public class Controller
{
	JPanel gamegrid;
	public Cell[][] survivalMatrix; //Coords are Cell[y][x]
	public Controller(GameGrid grid, LifeGUI gui)
	
	{
		gamegrid = grid;
		setGridsize(42, 23);
		addListeners();
		initializeRandom();
	}
	

	private void initializeRandom() 
	{
		for (int i = 0; i < survivalMatrix.length; i++) 
		{
			for (int j = 0; j < survivalMatrix[0].length; j++) 
			{
				int rnd = (int) (0 + (Math.random() * (2 - 0)));
				if(rnd == 1)
				{
					survivalMatrix[i][j].toggleStatus();
				}

			}		
		}
	}


	/*
	 * Advance the game by one cycle
	 */
	public void stepForward()
	{		
		for (int i = 0; i < survivalMatrix.length; i++) 
		{
			for (int j = 0; j < survivalMatrix[0].length; j++) 
			{
				survivalMatrix[i][j].setNextStatus(aliveNeighbours(i, j));
			}		
		}
		
		for (int i = 0; i < survivalMatrix.length; i++) 
		{
			for (int j = 0; j < survivalMatrix[0].length; j++) 
			{
				survivalMatrix[i][j].updateStatus();
			}		
		}
	}
	
	/*
	 * set the size of the survivalMatrix according to the grid size
	 */
	public void setGridsize(int x, int y)
	{
		if(x >= 3 || y >= 3)
		{
			survivalMatrix = new Cell[x][y];
			for (int i = 0; i < survivalMatrix.length; i++) 
			{
				for (int j = 0; j < survivalMatrix[0].length; j++) 
				{
					survivalMatrix[i][j] = new ConwayCell();
				}		
			}
		}
	}
	
	/*
	 * returns the number of alive Neighbors of a cell
	 */
	public int aliveNeighbours(int x, int y)
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
	 * Retruns the Cell at the specified position
	 */
	protected Cell giveCellAtPosition(int x, int y)
	{
		return survivalMatrix[y][x];
	}
	
	/*
	 * count alive Neighbors of a cell with the Conway rules
	 */
	private int conwayRulez(int x, int y)
	{
		int count = 0;
		int matrixX = survivalMatrix[0].length;
		int matrixY = survivalMatrix.length;
		
		for (int i = x-1; i <= x+1; i++) 
		{
			for (int j = y-1; j <= y+1; j++) 
			{
				if(survivalMatrix[(i + matrixY) % matrixY][(j + matrixX) % matrixX].getStatus())
				{
					count++;
				}
			}
		}
		
		count = count - (survivalMatrix[x][y].getStatus() ? 1 : 0);
		
		return count;
	}
	
	private void addListeners() 
	{
		
	}

	public void doCommand(Command cmd) 
	{
		switch (cmd)
		{
			case STEPFOWARD: this.stepForward();
			break;
			default:
		}
		
	}
}
