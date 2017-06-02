package testing;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

import javax.swing.JPanel;

import gogol.Controller;
import gogol.GameGrid;
import gogol.LifeGUI;

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
	public void testSetGridsize() 
	{
		bigBrother.setGridsize(666, 1337);
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
		assertTrue(bigBrother.aliveNeighbours(0, 1) == 0);
		assertTrue(bigBrother.aliveNeighbours(0, 2) == 3);
		assertTrue(bigBrother.aliveNeighbours(0, 3) == 3);
		assertTrue(bigBrother.aliveNeighbours(0, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(1, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(1, 1) == 2);
		assertTrue(bigBrother.aliveNeighbours(1, 2) == 2);
		assertTrue(bigBrother.aliveNeighbours(1, 3) == 2);
		assertTrue(bigBrother.aliveNeighbours(1, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(2, 0) == 0);
		assertTrue(bigBrother.aliveNeighbours(2, 1) == 1);
		assertTrue(bigBrother.aliveNeighbours(2, 2) == 0);
		assertTrue(bigBrother.aliveNeighbours(2, 3) == 0);
		assertTrue(bigBrother.aliveNeighbours(2, 4) == 1);
		assertTrue(bigBrother.aliveNeighbours(3, 0) == 2);
		assertTrue(bigBrother.aliveNeighbours(3, 1) == 1);
		assertTrue(bigBrother.aliveNeighbours(3, 2) == 1);
		assertTrue(bigBrother.aliveNeighbours(3, 3) == 2);
		assertTrue(bigBrother.aliveNeighbours(3, 4) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 0) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 1) == 1);
		assertTrue(bigBrother.aliveNeighbours(4, 2) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 3) == 3);
		assertTrue(bigBrother.aliveNeighbours(4, 4) == 3);
	}
	
	@Test
	public void testStepForward()
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
