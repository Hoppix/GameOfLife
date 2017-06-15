package gogol;


/**
 * Holds the Cell array and gives Cells data about their Neighbors
 * Holds other logic as Classes
 */
public class Controller
{
	GameGrid gamegrid;
	LifeGUI lifegui;
	public Cell[][] survivalMatrix; //Coords are Cell[y][x]
	public String gameMode;
	public String preloadMode;

	private Player player;
	private Saver saver;
	protected PreLoader preloader;

	public Controller(GameGrid grid, LifeGUI gui)
	{
		gamegrid = grid;
		lifegui = gui;
		
		gameMode = "Conway";
		preloadMode = "toggle";

		int gridX = gamegrid.sizeX / gamegrid.tileSize;
		int gridY = gamegrid.sizeY / gamegrid.tileSize;
		setGridsize(gridX, gridY);

		player = new Player(this);
		saver = new Saver(this);
		preloader = new PreLoader(this);
		
		addListeners();
	}


	/**
	 * Sets a cell on the matrix and on the Gamegrid
	 * @param x X-location
	 * @param y Y-location
	 */
	public void setCell(int x, int y)
	{
		survivalMatrix[y][x].toggleStatus();
		gamegrid.setField(survivalMatrix[y][x], x,y);
	}

	/**
	 * initializes the GameGrid by random values
	 */
	private void initializeRandom()
	{
		for (int i = 0; i < survivalMatrix.length; i++)
		{
			for (int j = 0; j < survivalMatrix[0].length; j++)
			{
				int rnd = (int) (0 + (Math.random() * (3)));
				if (rnd == 1)
				{
					survivalMatrix[i][j].toggleStatus();
					gamegrid.setField(survivalMatrix[i][j], j, i);
				}

			}
		}
	}


	/**
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

	/**
	 * set the size of the survivalMatrix according to the grid size
	 * Relative to 11:6
	 */
	public void setGridsize(int x, int y)
	{
		if (x >= 3 || y >= 3)
		{
			survivalMatrix = new Cell[y][x];
			clear();
			gamegrid.tileSize = gamegrid.sizeX / x;
		}
	}

	/**
	 * sets all the cells to dead
	 */
	public void clear()
	{
		for (int i = 0; i < survivalMatrix.length; i++)
		{
			for (int j = 0; j < survivalMatrix[0].length; j++)
			{
				survivalMatrix[i][j] = new ConwayCell();
				gamegrid.setField(survivalMatrix[i][j], j,i);
			}
		}
	}

	/**
	 * sets all the cells to dead on a specified area
	 * @param startX beginning X value
	 * @param startY beginning Y value
	 * @param lengthX horizontal length
	 * @param lengthY vertical length
	 */
	public void clearArea(int startX, int startY, int lengthX, int lengthY)
	{
		for (int i = 0; i < lengthY; i++)
		{
			for (int j = 0; j < lengthX; j++)
			{
				int matX = j + startX;
				int matY = i + startY;
				survivalMatrix[matY][matX] = new ConwayCell();
				gamegrid.setField(survivalMatrix[matY][matX], matX,matY);
			}
		}
	}

	/**
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

	/**
	 * Retruns the Cell at the specified position
	 */
	protected Cell giveCellAtPosition(int x, int y)
	{
		return survivalMatrix[y][x];
	}

	/**
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


	/**
	 * adds all the required listeners for the LifeGUI
	 */
	private void addListeners()
	{
		lifegui.step.addActionListener(new ButtonListener(Command.STEPFOWARD, this));
		lifegui.clear.addActionListener(new ButtonListener(Command.CLEAR, this));
		lifegui.random.addActionListener(new ButtonListener(Command.RANDOMIZE, this));
		lifegui.play.addActionListener(new ButtonListener(Command.PLAY,this));
		lifegui.pause.addActionListener(new ButtonListener(Command.PAUSE,this));
		lifegui.save.addActionListener(new ButtonListener(Command.SAVE, this));
		lifegui.load.addActionListener(new ButtonListener(Command.LOAD, this));
		lifegui.toggleButton.addActionListener(new ButtonListener(Command.TOGGLEMODE, this));
		lifegui.blockButton.addActionListener(new ButtonListener(Command.BLOCKMODE, this));
		lifegui.gliderButton.addActionListener(new ButtonListener(Command.GLIDERMODE, this));
		lifegui.carButton.addActionListener(new ButtonListener(Command.CARMODE, this));
		lifegui.speedSlider.addChangeListener(new SpeedChangerListener(this, player));
		gamegrid.addMouseListener(new CellToggleListener(this));
	}

	/**
	 * command handling
	 * @param cmd command
	 */
	public void doCommand(Command cmd)
	{
		switch (cmd)
		{
			case STEPFOWARD:
				this.stepForward();
				break;
			case CLEAR:
				this.clear();
				break;
			case RANDOMIZE:
				this.initializeRandom();
				break;
			case PLAY:
				player.startLoop();
				lifegui.step.setEnabled(false);
				lifegui.play.setEnabled(false);
				break;
			case PAUSE:
				player.stopLoop();
				lifegui.step.setEnabled(true);
				lifegui.play.setEnabled(true);
				break;
			case SAVE:
				saver.saveGamestate();
				break;
			case LOAD:
				saver.loadGamestate();
				break;
			case TOGGLEMODE:
				this.preloadMode = "toggle";
				break;
			case BLOCKMODE:
				this.preloadMode = "Block";
				break;
			case GLIDERMODE:
				this.preloadMode = "Glider";
				break;
			case CARMODE:
				this.preloadMode = "Car";
			default:
		}
	}
	

}
