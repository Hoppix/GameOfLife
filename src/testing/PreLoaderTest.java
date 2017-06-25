package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import gogol.backend.Controller;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;
import gogol.library.PreLoader;

public class PreLoaderTest 
{
	GameGrid grid = new GameGrid();
	Controller cont = new Controller(grid, new LifeGUI(grid));
	PreLoader testPreLoad = new PreLoader(cont);
	
	@Test
	public void testLoadPreset()
	{
		testPreLoad.loadPreset(1, 1, "Block");
		testPreLoad.loadPreset(10, 10, "Block");
		
		for(int y = 0; y < cont.survivalMatrix.length; y++)
		{
			for(int x = 0; x < cont.survivalMatrix[0].length; x++)
			{
				if(((x == 1 || x == 2 )&& (y == 1 || y == 2)) || ((x == 10 || x == 11 )&& (y == 10 || y == 11)))
				{
					assertTrue(cont.survivalMatrix[y][x].getStatus());
				}
				else
				{
					assertFalse(cont.survivalMatrix[y][x].getStatus());
				}
			}
		}
	}
}
