package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import gogol.backend.Controller;
import gogol.backend.Referee;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;

public class RefereeTest 
{
	Referee ref;
	GameGrid grid = new GameGrid();
	LifeGUI lifegui = new LifeGUI(grid);
	Controller cont = new Controller(grid, lifegui);
	
	@Before
	public void resetReferee()
	{
		ref = new Referee(cont);
		cont.changeGameMode("PvP");
	}
	
	@Test
	public void testGetArea()
	{
		assertEquals(Color.red, ref.checkColorArea(4, 4));
		assertEquals(Color.red, ref.checkColorArea(4, 55));
		assertEquals(Color.red, ref.checkColorArea(50, 4));
		assertEquals(Color.red, ref.checkColorArea(50, 55));
		assertNull(ref.checkColorArea(3, 4));
		assertNull(ref.checkColorArea(4, 3));
		assertNull(ref.checkColorArea(4, 56));
		assertNull(ref.checkColorArea(3, 55));
		assertNull(ref.checkColorArea(51, 4));
		assertNull(ref.checkColorArea(50, 3));
		assertNull(ref.checkColorArea(50, 56));
		assertNull(ref.checkColorArea(51,55));
		assertEquals(Color.blue, ref.checkColorArea(59, 4));
		assertEquals(Color.blue, ref.checkColorArea(105, 4));
		assertEquals(Color.blue, ref.checkColorArea(59, 55));
		assertEquals(Color.blue, ref.checkColorArea(105, 55));
		assertNull(ref.checkColorArea(58, 4));
		assertNull(ref.checkColorArea(59, 3));
		assertNull(ref.checkColorArea(59, 56));
		assertNull(ref.checkColorArea(58, 55));
		assertNull(ref.checkColorArea(106, 4));
		assertNull(ref.checkColorArea(105, 3));
		assertNull(ref.checkColorArea(105, 56));
		assertNull(ref.checkColorArea(106,55));

	}
	
	@Test
	public void testInterrupt()
	{
		cont.setCell(10, 10);
		cont.setCell(11, 10);
		cont.setCell(10, 11);
		cont.setCell(11, 11);
		assertFalse(ref.interrupt(1));
		assertFalse(ref.interrupt(99));
		assertFalse(ref.interrupt(101));
		assertTrue(ref.interrupt(100));
		assertEquals(150, ref.getAvailableCellsBlue());
		assertEquals(150, ref.getAvailableCellsRed());
		assertTrue(ref.interrupt(100));
		assertEquals(200, ref.getAvailableCellsBlue());
		assertEquals(200, ref.getAvailableCellsRed());
		assertEquals(0, ref.getBluePop());
		assertEquals(4, ref.getRedPop());
	}
	
	@Test
	public void testEndGame()
	{
		cont.setCell(11, 10);
		cont.setCell(10, 11);
		cont.setCell(11, 11);
		assertFalse(ref.endGame(999));
		assertTrue(ref.endGame(1000));
		assertEquals(0, ref.getBluePop());
		assertEquals(3, ref.getRedPop());
		
	}

}
