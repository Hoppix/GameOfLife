package gogol.backend;


import gogol.cells.Cell;
import gogol.cells.ColoredCell;
import gogol.cells.ConwayCell;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;
import gogol.library.PreLoader;
import gogol.listener.*;

import java.awt.*;

/**
 * Holds the Cell array and gives Cells data about their Neighbors
 * Holds other logic as Classes
 */
public class Controller
{
	public GameGrid gamegrid;
	public LifeGUI lifegui;
	public Cell[][] survivalMatrix; //Coords are Cell[y][x]
	public String gameMode;
	public String preloadMode;

	private Player player;
	private Saver saver;
	public PreLoader preloader;
	private Ruler ruler;

	private int generation;

	public Controller(GameGrid grid, LifeGUI gui)
	{
		gamegrid = grid;
		lifegui = gui;
		
		gameMode = "Conway";
		preloadMode = "Toggle";
		generation = 0;

		int gridX = gamegrid.sizeX / gamegrid.tileSize;
		int gridY = gamegrid.sizeY / gamegrid.tileSize;
		setGridsize(gridX, gridY);

		player = new Player(this);
		saver = new Saver(this);
		preloader = new PreLoader(this);
		ruler = new Ruler(this);
		
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
		for (int y = 0; y < survivalMatrix.length; y++)
		{
			for (int x = 0; x < survivalMatrix[0].length; x++)
			{
				int rnd = (int) (0 + (Math.random() * (3)));
				if (rnd == 1)
				{
					survivalMatrix[y][x].toggleStatus();
					if(!(gameMode.equals("Conway")) && survivalMatrix[y][x].getStatus())
					{
						for(int i = 0; i < (int) (0 + (Math.random() * (3))); i++)
						{
								survivalMatrix[y][x].toggleStatus();
						}
					}
					gamegrid.setField(survivalMatrix[y][x], x, y);
				}

			}
		}
	}


	/**
	 * Advance the game by one cycle
	 */
	public void stepForward()
	{
		for (int y = 0; y < survivalMatrix.length; y++)
		{
			for (int x = 0; x < survivalMatrix[0].length; x++)
			{
				survivalMatrix[y][x].setNextStatus(aliveNeighbours(x, y));
				if (gameMode.equals("ColorMerge"))
				{
					((ColoredCell)survivalMatrix[y][x]).setColorStatus(ruler.colorMerging(x, y));
					if(!survivalMatrix[y][x].getNextStatus())
					{
						((ColoredCell)survivalMatrix[y][x]).setColorStatus(null);
					}
				}
				else if(gameMode.equals("ColorWar"))
				{
					((ColoredCell)survivalMatrix[y][x]).setColorStatus(ruler.colorWarRules(x,y));
					if(!survivalMatrix[y][x].getNextStatus())
					{
						((ColoredCell)survivalMatrix[y][x]).setColorStatus(null);
					}
				}
			}
		}

		for (int y = 0; y < survivalMatrix.length; y++)
		{
			for (int x = 0; x < survivalMatrix[0].length; x++)
			{
				survivalMatrix[y][x].updateStatus();		
				if(!gameMode.equals("Conway"))
				{
					suizideAlbinos((ColoredCell)survivalMatrix[y][x]);
				}
				gamegrid.setField(survivalMatrix[y][x], x, y);
			}
		}
		//temporary
		generation++;
		lifegui.generationValue.setText(generation + "");

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
		for (int y = 0; y < survivalMatrix.length; y++)
		{
			for (int x = 0; x < survivalMatrix[0].length; x++)
			{
				survivalMatrix[y][x] = createCell();
				gamegrid.setField(survivalMatrix[y][x], x,y);
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
		for (int y = 0; y < lengthY; y++)
		{
			for (int x = 0; x < lengthX; x++)
			{
				int matX = x + startX;
				int matY = y + startY;
				try 
				{
					survivalMatrix[matY][matX] = new ConwayCell();
					gamegrid.setField(survivalMatrix[matY][matX], matX,matY);				
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
		}
	}

	/**
	 * returns the number of alive Neighbors of a cell
	 */
	public int aliveNeighbours(int x, int y)
	{
		int count = 0;
		count = ruler.conwayRulez(x, y);
		return count;
	}

	/**
	 * Retruns the Cell at the specified position
	 */
	protected Cell giveCellAtPosition(int x, int y)
	{
		return survivalMatrix[y][x];
	}
	
	public void changeGameMode(String mode)
	{
		gameMode = mode;

		switch (mode)
		{
			case "Conway":
				survivalMatrix = new ConwayCell[survivalMatrix.length][survivalMatrix[0].length];
				break;
			case "ColorWar":
			case "ColorMerge":
				survivalMatrix = new ColoredCell[survivalMatrix.length][survivalMatrix[0].length];
				break;
			default:
				break;
		}
		clear();
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

		lifegui.toggleButton.addActionListener(new PreloadListener("Toggle", this));
		lifegui.blockButton.addActionListener(new PreloadListener("Block", this));
		lifegui.gliderButton.addActionListener(new PreloadListener("Glider", this));
		lifegui.blinkerButton.addActionListener(new PreloadListener("Blinker", this));
		lifegui.carButton.addActionListener(new PreloadListener("Car", this));
		lifegui.sealButton.addActionListener(new PreloadListener("Seal", this));
		lifegui.methButton.addActionListener(new PreloadListener("Meth", this));
		lifegui.fabButton.addActionListener(new PreloadListener("Foreandback", this));
		lifegui.butterButton.addActionListener(new PreloadListener("Butterfly", this));
		lifegui.ynotButton.addActionListener(new PreloadListener("Whynot", this));
		lifegui.shipmakerButton.addActionListener(new PreloadListener("Shipmaker", this));
		lifegui.glidergunButton.addActionListener(new PreloadListener("Glidergun", this));


		lifegui.speedSlider.addChangeListener(new SpeedChangerListener(this, player));
		lifegui.gametypeChooser.addItemListener(new GameModeListener(this));
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
				generation = 0;
				lifegui.generationValue.setText(generation + "");
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
			default:
		}
	}

	private Cell createCell()
	{
		switch(gameMode)
		{
			case "Conway": return new ConwayCell();
			case "ColorWar":
			case "ColorMerge":	return new ColoredCell();
			default: return null;
		}
	}
	
	private void suizideAlbinos(ColoredCell cell) 
	{
		if(cell.getStatus() && cell.getColorStatus() == null)
		{
			cell.setNextStatus(0);
			cell.setColorStatus(null);
			cell.updateStatus();
		}	
	}
}
