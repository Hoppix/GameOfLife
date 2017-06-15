package gogol;

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
				System.out.println("x: " + x + "; y: " + y);
				System.out.println("Y size: " + pattern[y].length);
				if(pattern[y][x])
				{
					cont.setCell(x + posX, y + posY);
				}
			}
		}
	}
}
