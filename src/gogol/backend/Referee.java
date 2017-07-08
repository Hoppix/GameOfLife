package gogol.backend;


import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;

/**
 * Created by hoppix on 29.06.17.
 */
public class Referee
{
	private final Controller controller;


	private static final int PLAYER_AREA_PADDING = 4;
	private static final int STARTING_CELLS = 1;
	private static final int CELLS_PER_CYCLE = 1;
	private static final int CYCLE_LENGTH = 1;
	private static final int CYCLES_PER_GAME = Integer.MAX_VALUE;


	protected Rectangle playerRedArea;
	protected Rectangle playerBlueArea;

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
		int halfsizeX =  controller.survivalMatrix[0].length / 2;
		int sizeY =  controller.survivalMatrix.length;

		playerRedArea = new Rectangle(PLAYER_AREA_PADDING,PLAYER_AREA_PADDING, halfsizeX - PLAYER_AREA_PADDING*2,
				sizeY - PLAYER_AREA_PADDING*2);
		playerBlueArea = new Rectangle(halfsizeX + PLAYER_AREA_PADDING, PLAYER_AREA_PADDING, halfsizeX - PLAYER_AREA_PADDING*2, sizeY - PLAYER_AREA_PADDING*2);
	}

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
	
	public boolean endGame(int generation)
	{
		if(generation == CYCLES_PER_GAME)
		{
			return true;
		}
		
		return false;
	}
}
