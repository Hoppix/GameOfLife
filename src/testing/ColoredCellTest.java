package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;

import gogol.ColoredCell;

public class ColoredCellTest 
{
	ColoredCell cCell;
	
	@Before
	public void resetCell()
	{
		cCell = new ColoredCell();
	}
	
	@Test
	public void testSetNextStatus()
	{
		cCell.setNextStatus(0);
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(1);
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(2);
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(3);
		assertTrue(cCell.getStatus());
		cCell.setNextStatus(2);
		assertTrue(cCell.getStatus());
		cCell.setNextStatus(4);
		assertFalse(cCell.getStatus());
	}
	
	@Test
	public void testUpdateStatus()
	{
		cCell.setNextStatus(0);
		cCell.setColorStatus(null);
		cCell.updateStatus();
		assertFalse(cCell.getStatus());
		assertNull(cCell.getColorStatus());
		cCell.setNextStatus(3);
		cCell.setColorStatus(Color.blue);
		cCell.updateStatus();
		assertTrue(cCell.getStatus());
		assertEquals(Color.blue, cCell.getColorStatus());
	}
	
	@Test
	public void testToggleStatus()
	{
		//TODO: impl
	}
	
	@Test
	public void testCellToString()
	{
		//TODO: impl
	}
}
