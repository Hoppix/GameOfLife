package gogol.backend;

import gogol.cells.Cell;

import java.awt.*;

/**
 * Created by hoppix on 29.06.17.
 */
public class Referee
{
	private final Controller controller;


	private static final int PLAYER_AREA_PADDING = 4;
	private static final int STARTING_CELLS = 0;
	private static final int CELLS_PER_CYCLE = 0;
	private static final int CYCLE_LENGTH = 0;


	private Rectangle playerRedArea;
	private Rectangle playerBlueArea;

	private int playerRedCellCount;
	private int playerBlueCellCount;

	/**
	 * NYI
	 */
	private int playerRedKillCount;
	private int playerBlueKillCount;

	public Referee(Controller parent)
	{
		controller = parent;
		playerRedArea = new Rectangle(PLAYER_AREA_PADDING,0, (controller.survivalMatrix[0].length) - PLAYER_AREA_PADDING, controller.survivalMatrix.length);
		playerBlueArea = new Rectangle(controller.survivalMatrix[0].length - PLAYER_AREA_PADDING, 0, (((controller.survivalMatrix[0].length)/2)+ PLAYER_AREA_PADDING), controller.survivalMatrix.length);
	}

	public Color checkColorArea(int posX, int posY)
	{
		//TODO IMPL
		return null;
	}
	
	public boolean interrupt(int generation)
	{
		if(generation % CYCLE_LENGTH == 0)
		{
			playerRedCellCount = playerRedCellCount + CELLS_PER_CYCLE;
			playerBlueCellCount = playerBlueCellCount + CELLS_PER_CYCLE;
			return true;
		}
		else
		{
			return false;
		}
	}
}
