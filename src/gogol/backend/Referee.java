package gogol.backend;

import gogol.cells.PvPCell;

import java.awt.*;
import java.awt.Color;

/**
 * Extra game managing for the pvp gamemode
 * Holds the specific rule values
 *
 * Created by hoppix on 29.06.17.
 */
public class Referee
{
	private final Controller controller;

	private static final int PLAYER_AREA_PADDING = 4;
	private static final int STARTING_CELLS = 100;
	private static final int CELLS_PER_CYCLE = 50;
	private static final int CYCLE_LENGTH = 100;
	private static final int CYCLES_PER_GAME = 1000;


	// colors are set depending in which area the cell is
	protected Rectangle playerRedArea;
	protected Rectangle playerBlueArea;

	// defines how many cells both players are allowed to place
	private int playerRedCellCount;
	private int playerBlueCellCount;

	// count for all living cells of the respective player
	private int playerRedPopCount;
	private int playerBluePopCount;

	/**
	 * initializes player area
	 * sets cellcount for the first round
	 * @param parent
	 */
	public Referee(Controller parent)
	{
		controller = parent;
		int halfsizeX =  controller.survivalMatrix[0].length / 2;
		int sizeY =  controller.survivalMatrix.length;

		playerRedCellCount = STARTING_CELLS;
		playerBlueCellCount = STARTING_CELLS;

		playerRedArea = new Rectangle(PLAYER_AREA_PADDING,PLAYER_AREA_PADDING, halfsizeX - PLAYER_AREA_PADDING*2,
				sizeY - PLAYER_AREA_PADDING*2);
		playerBlueArea = new Rectangle(halfsizeX + PLAYER_AREA_PADDING, PLAYER_AREA_PADDING, halfsizeX - PLAYER_AREA_PADDING*2, sizeY - PLAYER_AREA_PADDING*2);
	}

	/**
	 * takes in the position of a cell and returns the corresponding color depending which area contains the cell
	 * @param posX
	 * @param posY
	 * @return player color
	 */
	public Color checkColorArea(int posX, int posY)
	{
		if (playerRedArea.contains(posX,posY))
		{
			return Color.red;
		}
		else if(playerBlueArea.contains(posX,posY))
		{
			return Color.blue;
		}
		return null;
	}

	/**
	 *  returns true if one game cycle has ended
	 *  updates the cels available for the players
	 *
	 * @param generation
	 * @return
	 */
	public boolean interrupt(int generation)
	{
		if(generation % CYCLE_LENGTH == 0)
		{
			countPlayerCells();
			playerRedCellCount = playerRedCellCount + CELLS_PER_CYCLE;
			playerBlueCellCount = playerBlueCellCount + CELLS_PER_CYCLE;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * returns true if the max cycle count is reached
	 * @param generation
	 * @return
	 */
	public boolean endGame(int generation)
	{
		if(generation == CYCLES_PER_GAME)
		{
			countPlayerCells();
			return true;
		}
		
		return false;
	}

	public int getAvailableCellsRed()
	{
		return playerRedCellCount;
	}

	public int getAvailableCellsBlue()
	{
		return playerBlueCellCount;
	}

	public void spendCellsRed(int spent)
	{
		playerRedCellCount = playerRedCellCount - spent;
	}

	public void spendCellsBlue(int spent)
	{
		playerBlueCellCount = playerBlueCellCount - spent;
	}

	public int getRedPop()
	{
		return playerRedPopCount;
	}

	public int getBluePop()
	{
		return playerBluePopCount;
	}


	/**
	 * counts living cells for blue and red
	 */
	private void countPlayerCells()
	{
		playerBluePopCount = 0;
		playerRedPopCount = 0;

		for(int y = 0; y < controller.survivalMatrix.length; y++)
		{
			for(int x = 0; x < controller.survivalMatrix[0].length; x++)
			{
				Color color = ((PvPCell)controller.survivalMatrix[y][x]).getColorStatus();
				if(color == null)
				{
					//nothing to do here
				}
				else if(color.equals(Color.red))
				{
					playerRedPopCount++;
				}
				else if(color.equals(Color.blue))
				{
					playerBluePopCount++;
				}
			}
		}
	}
}
