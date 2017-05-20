package gogol;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest 
{
	Controller bigBrother;
	
	@Before
	public void reset()
	{
		bigBrother = new Controller(new JPanel());
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
