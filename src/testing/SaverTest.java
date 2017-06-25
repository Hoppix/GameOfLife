package testing;

import org.junit.Test;

import gogol.backend.Controller;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;
import gogol.backend.Saver;

public class SaverTest 
{
	GameGrid grid = new GameGrid();
	Controller cont = new Controller(grid, new LifeGUI(grid));
	Saver saver = new Saver(cont);
	
	@Test
	public void testSaveLoad()
	{
		//TODO how to test?
	}
}
