package gogol.library;

import gogol.backend.Controller;

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
		
		cont.clearArea(posX, posY, preset.getSizeX(), preset.getSizeY());
		
		for(int y = 0; y < preset.getSizeY(); y++)
		{
			for(int x = 0; x < preset.getSizeX(); x++)
			{
				if(pattern[y][x])
				{
					try 
					{
						cont.setCell(x + posX % cont.survivalMatrix[0].length, y + posY % cont.survivalMatrix.length);
					} 
					catch (Exception e) 
					{
						//Ignore
					}
				}
			}
		}
	}
}
