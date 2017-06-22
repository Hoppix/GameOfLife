package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import gogol.ColoredCell;
import gogol.Controller;
import gogol.GameGrid;
import gogol.LifeGUI;
import gogol.Ruler;

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
		setGridColored();
		assertEquals(ruler.colorMerging(10, 10), new Color(255,0,0));
		assertEquals(ruler.colorMerging(11, 10), new Color(127,0,127));
		assertEquals(ruler.colorMerging(12, 10), new Color(127,0,127));
		assertEquals(ruler.colorMerging(10, 11), new Color(255,0,0));
		assertEquals(ruler.colorMerging(11, 11), new Color(255*3/5,0,255*2/5));
		assertEquals(ruler.colorMerging(12, 11), new Color(127,0,127));
		assertEquals(ruler.colorMerging(10, 12), new Color(255,0,0));
		assertEquals(ruler.colorMerging(11, 12), new Color(255*2/3,0,255/3));
		assertEquals(ruler.colorMerging(12, 12), new Color(127,0,127));
	}
	
	@Test
	public void testColorWarRulesStep()
	{
		setGridColored();
		assertEquals(ruler.colorWarRules(10, 10), Color.red);
		assertEquals(ruler.colorWarRules(11, 10), Color.green);
		assertEquals(ruler.colorWarRules(12, 10), Color.red);
		assertEquals(ruler.colorWarRules(10, 11), Color.red);
		assertEquals(ruler.colorWarRules(11, 11), Color.red);
		assertEquals(ruler.colorWarRules(12, 11), Color.red);
		assertEquals(ruler.colorWarRules(10, 12), Color.red);
		assertEquals(ruler.colorWarRules(11, 12), Color.red);
		assertEquals(ruler.colorWarRules(12, 12), null);
	}
	
	private void setGridColored()
	{
		cont.changeGameMode("ColorWar");	
		System.out.println("set mode done");
		System.out.println(cont.survivalMatrix[0][0]);
		cont.setCell(11, 10);
		((ColoredCell)cont.survivalMatrix[10][11]).setColorStatus(Color.red);
		cont.setCell(12, 10);
		((ColoredCell)cont.survivalMatrix[10][12]).setColorStatus(Color.green);
		cont.setCell(11, 11);
		((ColoredCell)cont.survivalMatrix[11][11]).setColorStatus(Color.red);
		cont.setCell(12, 11);
		((ColoredCell)cont.survivalMatrix[11][12]).setColorStatus(Color.green);
		cont.setCell(10, 12);
		((ColoredCell)cont.survivalMatrix[12][10]).setColorStatus(Color.red);
	}
}
