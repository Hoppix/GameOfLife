package gogol;

public class PreLoader 
{
	Controller cont;
	SpeciesLibrary lib;
	
	public PreLoader(Controller parent)
	{
		cont = parent;
		lib = new SpeciesLibrary();
	}
	
	public void loadPreset(int posX, int posY, String name)
	{
		Species preset = lib.getSpecies(name);
		boolean[][] pattern = preset.getPattern();
		
		cont.clearArea(posX, posY, preset.getSizeX(), preset.getSizeY());
		
		for(int y = 0; y < preset.getSizeX(); y++)
		{
			for(int x = 0; x < preset.getSizeY(); x++)
			{
				if(pattern[y][x])
				{
					cont.setCell(x + posX, y + posY);
				}
			}
		}
	}
}
