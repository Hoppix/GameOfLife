package gogol;

import java.io.*;

import javax.swing.JPanel;

/*
 * Holds the Cell array and gives Cells data about their Neighbors
 */
public class Controller
{
	GameGrid gamegrid;
	LifeGUI lifegui;
	public Cell[][] survivalMatrix; //Coords are Cell[y][x]
	public String gameMode;

	private Player player;

	public Controller(GameGrid grid, LifeGUI gui)
	{
		gamegrid = grid;
		lifegui = gui;
		
		gameMode = "Conway";

		int gridX = gamegrid.sizeX / gamegrid.tileSize;
		int gridY = gamegrid.sizeY / gamegrid.tileSize;
		setGridsize(gridX, gridY);

		player = new Player(this);
		addListeners();
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
				int rnd = (int) (0 + (Math.random() * (3)));
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
			survivalMatrix = new Cell[y][x];
			clear();
		}
	}

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
	
	/*
	 * Writes the current Gamestate to a text file
	 * first line are Settings, lines 2-n are the cells linewise.
	 * settings: Gamemode;tilesize;survivalMatrixX;surivalMatrixY;
	 */
	public void saveGamestate()
	{
		BufferedWriter writer = null;
		
		try 
		{
			writer = new BufferedWriter( new FileWriter("saves/lastSave.txt"));
		    writer.write(gameMode + ";");
			writer.write(gamegrid.tileSize + ";");
		    writer.write(survivalMatrix[0].length + ";");
		    writer.write(survivalMatrix.length + ";");
		    writer.newLine();
		    
		    for (int y = 0; y < survivalMatrix.length; y++) 
		    {
				for (int x = 0; x < survivalMatrix[y].length; x++) 
				{
					writer.write(survivalMatrix[y][x].cellToString() + ";");
				}
				writer.newLine();
			}
			writer.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * loads a previously saved game
	 */
	public void loadGameState()
	{
		clear();
		
		BufferedReader reader = null;
		String line = null;
		String[] args = null;
		
		try
		{
			int y = 0;
			
			reader = new BufferedReader(new FileReader("saves/lastSave.txt"));
			line = reader.readLine();
			args = line.split(";");
			
			gameMode = args[0];
			gamegrid.tileSize = Integer.parseInt(args[1]);
			setGridsize(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
			
			while ((line = reader.readLine()) != null) 
			{
				args = line.split(";");
				for (int x = 0; x < args.length; x++) 
				{
					if(args[x].equals("true"))
					{
						survivalMatrix[y][x].toggleStatus();
						gamegrid.setField(survivalMatrix[y][x], x, y);
					}					
				}
				y++;
			}
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	private void addListeners()
	{
		lifegui.step.addActionListener(new ButtonListener(Command.STEPFOWARD, this));
		lifegui.clear.addActionListener(new ButtonListener(Command.CLEAR, this));
		lifegui.random.addActionListener(new ButtonListener(Command.RANDOMIZE, this));
		lifegui.play.addActionListener(new ButtonListener(Command.PLAY,this));
		lifegui.pause.addActionListener(new ButtonListener(Command.PAUSE,this));
		lifegui.save.addActionListener(new ButtonListener(Command.SAVE, this));
		lifegui.load.addActionListener(new ButtonListener(Command.LOAD, this));
		lifegui.speedSlider.addChangeListener(new SpeedChanger(this, player));
		gamegrid.addMouseListener(new CellToggleListener(this));
	}
	
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
				this.saveGamestate();
				break;
			case LOAD:
				this.loadGameState();
				break;
			default:
		}
	}
}
