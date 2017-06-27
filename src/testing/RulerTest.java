package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import gogol.backend.Controller;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;
import gogol.backend.Ruler;

public class RulerTest 
{
	GameGrid grid = new GameGrid();
	LifeGUI lifegui = new LifeGUI(grid);
	Controller cont = new Controller(grid, lifegui);
	Ruler ruler = new Ruler(cont);
	
	@Before
	public void doStuff()
	{
		//maybe stuff
	}
	
	@Test
	public void testColorMergeRulesStep()
	{
		setGridMerge();
		assertEquals(new Color(255,0,0), ruler.colorMerging(10, 10));			
		assertEquals(new Color(127,0,127), ruler.colorMerging(11, 10));
		assertEquals(new Color(127,0,127), ruler.colorMerging(12, 10));
		assertEquals(new Color(255,0,0), ruler.colorMerging(10, 11));
		assertEquals(new Color(255*3/5,0,255*2/5), ruler.colorMerging(11, 11));
		assertEquals(new Color(127,0,127), ruler.colorMerging(12, 11));
		assertEquals(new Color(255,0,0), ruler.colorMerging(10, 12));
		assertEquals(new Color(255*2/3,0,255/3), ruler.colorMerging(11, 12));
		assertEquals(new Color(127,0,127), ruler.colorMerging(12, 12));
	}
	
	@Test
	public void testColorWarRulesStep()
	{
		setGridWar();
		System.out.println(ruler.colorWarRules(10, 10));	
		assertEquals(ruler.colorWarRules(10, 10), null);
		assertEquals(ruler.colorWarRules(11, 10), Color.red);
		assertEquals(ruler.colorWarRules(12, 10), Color.blue);
		assertEquals(ruler.colorWarRules(10, 11), Color.red);
		assertEquals(ruler.colorWarRules(11, 11), null);
		assertEquals(ruler.colorWarRules(12, 11), Color.blue);
		assertEquals(ruler.colorWarRules(10, 12), null);
		assertEquals(ruler.colorWarRules(11, 12), Color.red);
		assertEquals(ruler.colorWarRules(12, 12), null);
	}
	
	private void setGridWar()
	{
		cont.changeGameMode("ColorWar");

		//red
		cont.setCell(11, 10);

		//blue
		cont.setCell(12, 10);
		cont.setCell(12, 10);
		cont.setCell(12, 10);

		//red
		cont.setCell(11, 11);

		//blue
		cont.setCell(12, 11);
		cont.setCell(12, 11);
		cont.setCell(12, 11);

		//red
		cont.setCell(10, 12);
		
		//setNextStatus for cells
		for(int y = 10; y <= 12; y++)
		{
			for(int x = 10; x <= 12; x++)
			{
				cont.survivalMatrix[y][x].setNextStatus(cont.aliveNeighbours(x, y));
			}
		}
	}
	
	private void setGridMerge()
	{
		cont.changeGameMode("ColorMerge");	
		cont.setCell(11, 10);
		cont.setCell(12, 10);
		cont.setCell(12, 10);
		cont.setCell(12, 10);
		cont.setCell(11, 11);
		cont.setCell(12, 11);
		cont.setCell(12, 11);
		cont.setCell(12, 11);
		cont.setCell(10, 12);
	}
}
