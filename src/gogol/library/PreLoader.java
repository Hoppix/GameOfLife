package gogol.library;

import gogol.backend.Controller;
import gogol.cells.Cell;
import gogol.cells.ColoredCell;


/**
 *
 * Created by 3sander on 14.06.17.
 */
public class PreLoader
{
	Controller cont;
	SpeciesLibrary lib;

	/**
	 * The PreLoader loads all predefined structures via the SpeciesLibrary
	 * @param parent Controller
	 */
	public PreLoader(Controller parent)
	{
		cont = parent;
		lib = new SpeciesLibrary();
	}

	/**
	 * loads a structure on the GameGrid
	 * @param posX X-location
	 * @param posY Y-location
	 * @param name Name of the structure
	 */
	public void loadPreset(int posX, int posY, String name)
	{

		Species preset = lib.getSpecies(name);
		boolean[][] pattern = preset.getPattern();
		
		for(int y = 0; y < preset.getSizeY(); y++)
		{
			for(int x = 0; x < preset.getSizeX(); x++)
			{
				if(pattern[y][x])
				{
					cont.setCell((x + posX) % cont.survivalMatrix[0].length, (y + posY) % cont.survivalMatrix.length);
				}
				else
				{
					Cell cellAtPos = cont.survivalMatrix[(y + posY) % cont.survivalMatrix.length][(x + posX) % cont.survivalMatrix[0].length];
					cellAtPos.setNextStatus(0);
					if(cellAtPos instanceof ColoredCell)
					{
						((ColoredCell) cellAtPos).setColorStatus(null);
					}
					cellAtPos.updateStatus();
				}
			}
		}
	}

}
