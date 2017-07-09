package gogol.backend;


import gogol.cells.Cell;
import gogol.cells.ColoredCell;
import gogol.cells.ConwayCell;
import gogol.cells.PvPCell;
import gogol.frontend.EndgameDialog;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;
import gogol.library.PreLoader;
import gogol.listener.*;

import java.awt.*;

/**
 * Holds the Cell array and gives Cells data about their Neighbors
 * Holds other logic as Classes
 * Holds the SurvivalMatrix (SVM) which contains references from the Cell interface
 *
 * Created by 3sander on 18.05.17.
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
	private Referee referee;
	public PreLoader preloader;
	public Ruler ruler;

	protected int generation;

	/**
	 * the constructor initializes the modes and components
	 * @param grid the gamegrid for translating the game logic to the frontend
	 * @param gui the full gui for controlling certain components and transmitting the information
	 */
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
		referee = new Referee(this);
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
		if(gameMode.equals("PvP"))
		{
			if(referee.checkColorArea(x,y) == null) return;
			if(referee.checkColorArea(x,y).equals(Color.red) && referee.getAvailableCellsRed() > 0)
			{
				referee.spendCellsRed(1);
			}
			else if(referee.checkColorArea(x,y).equals(Color.blue) && referee.getAvailableCellsBlue() > 0)
			{
				referee.spendCellsBlue(1);
			}
			else
			{
				return;
			}
			survivalMatrix[y][x].toggleStatus();
			((PvPCell)survivalMatrix[y][x]).setColorStatus(referee.checkColorArea(x,y));
			survivalMatrix[y][x].updateStatus();
			this.suizideAlbinos(survivalMatrix[y][x]);
		}
		else
		{
			survivalMatrix[y][x].toggleStatus();
		}
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
		// Set the SVM to the Values for the next Generation
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
				else if(gameMode.equals("PvP"))
				{
					((PvPCell)survivalMatrix[y][x]).setColorStatus(ruler.colorWarRules(x,y));
					if(!survivalMatrix[y][x].getNextStatus())
					{
						((PvPCell)survivalMatrix[y][x]).setColorStatus(null);
					}
				}
			}
		}

		// Update all Cells and paint them on the gamegrid
		for (int y = 0; y < survivalMatrix.length; y++)
		{
			for (int x = 0; x < survivalMatrix[0].length; x++)
			{
				survivalMatrix[y][x].updateStatus();		
				if(!gameMode.equals("Conway"))
				{
					suizideAlbinos(survivalMatrix[y][x]);
				}
				gamegrid.setField(survivalMatrix[y][x], x, y);
			}
		}

		// progress the generation counter and update the label
		generation++;
		lifegui.generationValue.setText(generation + "");

		// when pvp mode is active: interrupt the game after certain amount of steps
		if(gameMode == "PvP" && referee.interrupt(generation))
		{
			doCommand(Command.PAUSE);
			lifegui.step.setEnabled(false);
			if(referee.endGame(generation))
			{
				// show endgame frame when game is over
				// optional: disable background
				new EndgameDialog(generation,referee.getRedPop(),referee.getBluePop());
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
					survivalMatrix[matY][matX] =createCell();
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
		int count = ruler.conwayRulez(x, y);
		return count;
	}


	/**
	 * changes the gamemode:
	 * reinitializes the SVM with the corresponding cell-type
	 * and other various settings for the specific gametype
	 *
	 * @param mode
	 */
	public void changeGameMode(String mode)
	{
		// reenable the buttons in case they were disabled previously due to pvp mode
		lifegui.random.setEnabled(true);
		lifegui.step.setEnabled(true);
		lifegui.pause.setEnabled(true);

		// sets the field variable gamemode for the environment
		gameMode = mode;

		switch (mode)
		{
			case "Conway":
				gamegrid.setPaintPVP(false, null,null);
				survivalMatrix = new ConwayCell[survivalMatrix.length][survivalMatrix[0].length];
				break;

			// paint the color areas, disable certain buttons for pvp mode
			case "PvP":
				gamegrid.setPaintPVP(true, referee.playerRedArea, referee.playerBlueArea);
				survivalMatrix = new PvPCell[survivalMatrix.length][survivalMatrix[0].length];
				lifegui.random.setEnabled(false);
				lifegui.step.setEnabled(false);
				lifegui.pause.setEnabled(false);
				break;
			case "ColorWar":
			case "ColorMerge":
				gamegrid.setPaintPVP(false, null,null);
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
		// listeners for the main commands for controlling the grid
		lifegui.step.addActionListener(new ButtonListener(Command.STEPFOWARD, this));
		lifegui.clear.addActionListener(new ButtonListener(Command.CLEAR, this));
		lifegui.random.addActionListener(new ButtonListener(Command.RANDOMIZE, this));
		lifegui.play.addActionListener(new ButtonListener(Command.PLAY,this));
		lifegui.pause.addActionListener(new ButtonListener(Command.PAUSE,this));
		lifegui.save.addActionListener(new ButtonListener(Command.SAVE, this));
		lifegui.load.addActionListener(new ButtonListener(Command.LOAD, this));

		// preloads for loading certain creatures on the grid
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


		// misc.
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
				referee = new Referee(this);
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

	/**
	 * helper method which returns a new cell-type given by the current gamemode
	 * @return
	 */
	private Cell createCell()
	{
		switch(gameMode)
		{
			case "Conway": return new ConwayCell();
			case "PvP": return new PvPCell();
			case "ColorWar":				
			case "ColorMerge":	return new ColoredCell();
			default: return null;
		}
	}

	/**
	 * helper method for handling colored cells which are alive but have no color
	 * @param cell
	 */
	private void suizideAlbinos(Cell cell)
	{
		ColoredCell cCell;
		PvPCell pCell;
		if(gameMode.equals("PvP"))
		{
			pCell = (PvPCell)cell;
			if(pCell.getStatus() && pCell.getColorStatus()==null)
			{
				pCell.setNextStatus(0);
				pCell.setColorStatus(null);
				pCell.updateStatus();
			}
		}
		else
		{
			cCell = (ColoredCell)cell;
			if(cCell.getStatus() && cCell.getColorStatus()==null)
			{
				cCell.setNextStatus(0);
				cCell.setColorStatus(null);
				cCell.updateStatus();
			}
		}
	}

}