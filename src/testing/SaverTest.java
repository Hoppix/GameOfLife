package testing;

import org.junit.Test;

import gogol.Controller;
import gogol.GameGrid;
import gogol.LifeGUI;
import gogol.Saver;

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
