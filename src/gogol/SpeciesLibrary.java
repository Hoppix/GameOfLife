package gogol;

import java.util.HashMap;

public class SpeciesLibrary 
{
	protected HashMap<String, Species> library;

	/**
	 * Library which holds all the available Species.
	 */
	public SpeciesLibrary()
	{
		library = new HashMap<String, Species>();
		setupLib();
	}
	
	public Species getSpecies(String name)
	{
		return library.get(name);
	}
	
	private void setupLib()
	{
		library.put("Block", new Species("Block", "very edgy", "2o$2o"));
		library.put("Glider", new Species("Glider", "moving", "bob$2bo$3o"));
		library.put("Blinker", new Species("Blinker", "epilepsy inc", "o$o$o"));
	}

}
