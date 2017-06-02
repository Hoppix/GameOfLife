package gogol;

import javax.swing.JPanel;

/*
 * Holds the Cell array and gives Cells data about their Neighbors
 */
public class Controller
{
	GameGrid gamegrid;
	LifeGUI lifegui;
	public Cell[][] survivalMatrix; //Coords are Cell[y][x]

	public Controller(GameGrid grid, LifeGUI gui)
	{
		gamegrid = grid;
		lifegui = gui;

		int gridX = gamegrid.sizeX / gamegrid.tileSize;
		int gridY = gamegrid.sizeY / gamegrid.tileSize;
		setGridsize(gridY, gridX);
		addListeners();
		//initializeRandom();
	}


	public void setCell(int x, int y)
	{
		survivalMatrix[y][x].toggleStatus();
		gamegrid.setField(survivalMatrix[y][x], x,y);
	}

	private void initializeRandom()
	{
		//debugging method
		for (int i = 0; i < survivalMatrix.length; i++)
		{
			for (int j = 0; j < survivalMatrix[0].length; j++)
			{
				int rnd = (int) (0 + (Math.random() * (2)));
				if (rnd == 1)
				{
					survivalMatrix[i][j].toggleStatus();
					gamegrid.setField(survivalMatrix[i][j], j, i);
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
				gamegrid.setField(survivalMatrix[i][j], j, i);
			}
		}

	}

	/*
	 * set the size of the survivalMatrix according to the grid size
	 */
	public void setGridsize(int x, int y)
	{
		if (x >= 3 || y >= 3)
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

		switch (mode)
		{
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

		for (int i = x - 1; i <= x + 1; i++)
		{
			for (int j = y - 1; j <= y + 1; j++)
			{
				if (survivalMatrix[(i + matrixY) % matrixY][(j + matrixX) % matrixX].getStatus())
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
		lifegui.step.addActionListener(new ButtonListener(Command.STEPFOWARD, this));
		gamegrid.addMouseListener(new CellToggleListener(this));
	}

	public void doCommand(Command cmd)
	{
		switch (cmd)
		{
			case STEPFOWARD:
				this.stepForward();
				break;
			default:
		}

	}
}
