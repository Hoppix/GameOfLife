package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import gogol.backend.Controller;
import gogol.cells.*;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest 
{
	Controller bigBrother;
	
	@Before
	public void reset()
	{
		GameGrid grid = new GameGrid();
        LifeGUI gui = new LifeGUI(grid);
		bigBrother = new Controller(grid, gui);
	}
	
	@Test
	public void testSetCell()
	{
		bigBrother.setCell(0, 0);
		assertTrue(bigBrother.survivalMatrix[0][0].getStatus());
		bigBrother.setCell(0, 0);
		assertFalse(bigBrother.survivalMatrix[0][0].getStatus());
		bigBrother.changeGameMode("PvP");
		bigBrother.setCell(3, 3);
		assertFalse(bigBrother.survivalMatrix[3][3].getStatus());
		bigBrother.setCell(5, 5);
		assertTrue(bigBrother.survivalMatrix[5][5].getStatus());
	}
	
	@Test
	public void testSetGridsize() 
	{
		bigBrother.setGridsize(1337, 666);
		assertTrue(bigBrother.survivalMatrix.length == 666);
		assertTrue(bigBrother.survivalMatrix[0].length == 1337);
		bigBrother.setGridsize(1, 2);
		assertTrue(bigBrother.survivalMatrix.length == 666);
		assertTrue(bigBrother.survivalMatrix[0].length == 1337);
	}
	
	@Test
	public void testAliveNeighbors()
	{
		setTestingGrid();
		assertTrue(bigBrother.aliveNeighbours(0, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(1, 0) == 0);
		assertTrue(bigBrother.aliveNeighbours(2, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(3, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(0, 1) == 3);
		assertTrue(bigBrother.aliveNeighbours(1, 1) == 2);
		assertTrue(bigBrother.aliveNeighbours(2, 1) == 2);
		assertTrue(bigBrother.aliveNeighbours(3, 1) == 2);
		assertTrue(bigBrother.aliveNeighbours(4, 1) == 3);
		assertTrue(bigBrother.aliveNeighbours(0, 2) == 0);
		assertTrue(bigBrother.aliveNeighbours(1, 2) == 1);
		assertTrue(bigBrother.aliveNeighbours(2, 2) == 0);
		assertTrue(bigBrother.aliveNeighbours(3, 2) == 0);
		assertTrue(bigBrother.aliveNeighbours(4, 2) == 1);
		assertTrue(bigBrother.aliveNeighbours(0, 3) == 2);
		assertTrue(bigBrother.aliveNeighbours(1, 3) == 1);
		assertTrue(bigBrother.aliveNeighbours(2, 3) == 1);
		assertTrue(bigBrother.aliveNeighbours(3, 3) == 2);
		assertTrue(bigBrother.aliveNeighbours(4, 3) == 3);
		assertTrue(bigBrother.aliveNeighbours(0, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(1, 4) == 1);
		assertTrue(bigBrother.aliveNeighbours(2, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(3, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 4) == 3);
	}
	
	@Test
	public void testStepForwardConway()
	{
		setTestingGrid();
		bigBrother.stepForward();
		assertTrue(bigBrother.survivalMatrix[0][0].getStatus());
		assertFalse(bigBrother.survivalMatrix[0][1].getStatus());
		assertTrue(bigBrother.survivalMatrix[0][2].getStatus());
		assertTrue(bigBrother.survivalMatrix[0][3].getStatus());
		assertTrue(bigBrother.survivalMatrix[0][4].getStatus());
		assertTrue(bigBrother.survivalMatrix[1][0].getStatus());
		assertFalse(bigBrother.survivalMatrix[1][1].getStatus());
		assertFalse(bigBrother.survivalMatrix[1][2].getStatus());
		assertFalse(bigBrother.survivalMatrix[1][3].getStatus());
		assertTrue(bigBrother.survivalMatrix[1][4].getStatus());
		assertFalse(bigBrother.survivalMatrix[2][0].getStatus());
		assertFalse(bigBrother.survivalMatrix[2][1].getStatus());
		assertFalse(bigBrother.survivalMatrix[2][2].getStatus());
		assertFalse(bigBrother.survivalMatrix[2][3].getStatus());
		assertFalse(bigBrother.survivalMatrix[2][4].getStatus());
		assertFalse(bigBrother.survivalMatrix[3][0].getStatus());
		assertFalse(bigBrother.survivalMatrix[3][1].getStatus());
		assertFalse(bigBrother.survivalMatrix[3][2].getStatus());
		assertFalse(bigBrother.survivalMatrix[3][3].getStatus());
		assertTrue(bigBrother.survivalMatrix[3][4].getStatus());
		assertTrue(bigBrother.survivalMatrix[4][0].getStatus());
		assertFalse(bigBrother.survivalMatrix[4][1].getStatus());
		assertTrue(bigBrother.survivalMatrix[4][2].getStatus());
		assertTrue(bigBrother.survivalMatrix[4][3].getStatus());
		assertTrue(bigBrother.survivalMatrix[4][4].getStatus());
	}
	
	@Test
	public void testStepForwardColorWar()
	{
		bigBrother.changeGameMode("ColorWar");
		
		//red cells
		bigBrother.setCell(10, 10);
		bigBrother.setCell(11, 10);
		bigBrother.setCell(10, 11);
		bigBrother.setCell(11, 11);
		
		//green cells
		bigBrother.setCell(12, 12);
		bigBrother.setCell(12, 12);
		bigBrother.setCell(14, 14);
		bigBrother.setCell(14, 14);
		bigBrother.setCell(13, 14);
		bigBrother.setCell(13, 14);
		bigBrother.setCell(14, 13);
		bigBrother.setCell(14, 13);
		
		//blue cells
		bigBrother.setCell(13, 13);
		bigBrother.setCell(13, 13);
		bigBrother.setCell(13, 13);
		bigBrother.setCell(12, 13);
		bigBrother.setCell(12, 13);
		bigBrother.setCell(12, 13);
		bigBrother.setCell(13, 12);
		bigBrother.setCell(13, 12);
		bigBrother.setCell(13, 12);
		
		bigBrother.stepForward();
		
		assertTrue(bigBrother.survivalMatrix[10][10].getStatus());
		assertEquals(Color.red, ((ColoredCell)bigBrother.survivalMatrix[10][10]).getColorStatus());
		assertTrue(bigBrother.survivalMatrix[10][11].getStatus());
		assertEquals(Color.red, ((ColoredCell)bigBrother.survivalMatrix[10][11]).getColorStatus());
		assertFalse(bigBrother.survivalMatrix[10][12].getStatus());
		assertFalse(bigBrother.survivalMatrix[10][13].getStatus());
		assertFalse(bigBrother.survivalMatrix[10][14].getStatus());
		
		assertTrue(bigBrother.survivalMatrix[11][10].getStatus());
		assertEquals(Color.red, ((ColoredCell)bigBrother.survivalMatrix[11][10]).getColorStatus());
		assertFalse(bigBrother.survivalMatrix[11][11].getStatus());
		assertFalse(bigBrother.survivalMatrix[11][12].getStatus());
		assertFalse(bigBrother.survivalMatrix[11][13].getStatus());
		assertFalse(bigBrother.survivalMatrix[11][14].getStatus());
		
		assertFalse(bigBrother.survivalMatrix[12][10].getStatus());
		assertFalse(bigBrother.survivalMatrix[12][11].getStatus());
		assertFalse(bigBrother.survivalMatrix[12][12].getStatus());
		assertFalse(bigBrother.survivalMatrix[12][13].getStatus());
		assertTrue(bigBrother.survivalMatrix[12][14].getStatus());
		assertEquals(Color.blue, ((ColoredCell)bigBrother.survivalMatrix[12][14]).getColorStatus());
		
		assertFalse(bigBrother.survivalMatrix[13][10].getStatus());
		assertFalse(bigBrother.survivalMatrix[13][11].getStatus());
		assertFalse(bigBrother.survivalMatrix[13][12].getStatus());
		assertFalse(bigBrother.survivalMatrix[13][13].getStatus());
		assertFalse(bigBrother.survivalMatrix[13][14].getStatus());
		
		assertFalse(bigBrother.survivalMatrix[14][10].getStatus());
		assertFalse(bigBrother.survivalMatrix[14][11].getStatus());
		assertTrue(bigBrother.survivalMatrix[14][12].getStatus());
		assertEquals(Color.blue, ((ColoredCell)bigBrother.survivalMatrix[14][12]).getColorStatus());
		assertFalse(bigBrother.survivalMatrix[14][13].getStatus());
		assertTrue(bigBrother.survivalMatrix[14][14].getStatus());
		assertEquals(Color.green, ((ColoredCell)bigBrother.survivalMatrix[14][14]).getColorStatus());
	}
	
	@Test
	public void testClear()
	{
		setTestingGrid();
		bigBrother.clear();
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				assertFalse(bigBrother.survivalMatrix[y][x].getStatus());
			}
		}
	}
	
	@Test
	public void testClearArea()
	{
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				bigBrother.survivalMatrix[y][x].toggleStatus();
			}
		}
		
		bigBrother.clearArea(1, 2, 3, 2);
		
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				if(y > 1 && y < 4 && x > 0 && x < 4 )
				{
					assertFalse(bigBrother.survivalMatrix[y][x].getStatus());
				}
				else
				{
					assertTrue(bigBrother.survivalMatrix[y][x].getStatus());
				}
			}
		}
	}
	
	@Test
	public void testChangeGameMode()
	{
		bigBrother.changeGameMode("ColorMerge");
		assertEquals("ColorMerge", bigBrother.gameMode);
		assertTrue(bigBrother.survivalMatrix[0][0] instanceof ColoredCell);
		bigBrother.changeGameMode("Conway");
		assertEquals("Conway", bigBrother.gameMode);
		assertTrue(bigBrother.survivalMatrix[0][0] instanceof ConwayCell);
		bigBrother.changeGameMode("ColorWar");
		assertEquals("ColorWar", bigBrother.gameMode);
		assertTrue(bigBrother.survivalMatrix[0][0] instanceof ColoredCell);
		bigBrother.changeGameMode("PvP");
		assertEquals("PvP", bigBrother.gameMode);
		assertTrue(bigBrother.survivalMatrix[0][0] instanceof PvPCell);
	}
	
	private void setTestingGrid()
	{
		bigBrother.setGridsize(5, 5);
		bigBrother.survivalMatrix[0][1].toggleStatus();
		bigBrother.survivalMatrix[0][3].toggleStatus();
		bigBrother.survivalMatrix[0][4].toggleStatus();
		bigBrother.survivalMatrix[2][0].toggleStatus();
		bigBrother.survivalMatrix[4][3].toggleStatus();
		bigBrother.survivalMatrix[4][4].toggleStatus();
	}
}
