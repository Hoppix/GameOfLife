package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;

import gogol.cells.ColoredCell;

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
		cCell.updateStatus();
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(1);
		cCell.updateStatus();
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(2);
		cCell.updateStatus();
		assertFalse(cCell.getStatus());
		cCell.setNextStatus(3);
		cCell.updateStatus();
		assertTrue(cCell.getStatus());
		cCell.setNextStatus(2);
		cCell.updateStatus();
		assertTrue(cCell.getStatus());
		cCell.setNextStatus(4);
		cCell.updateStatus();
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
		cCell.toggleStatus();
		assertEquals(Color.red, cCell.getColorStatus());
		assertEquals(true, cCell.getStatus());

		cCell.toggleStatus();
		assertEquals(Color.green, cCell.getColorStatus());
		assertEquals(true, cCell.getStatus());

		cCell.toggleStatus();
		assertEquals(Color.blue, cCell.getColorStatus());
		assertEquals(true, cCell.getStatus());

		cCell.toggleStatus();
		assertEquals(null, cCell.getColorStatus());
		assertEquals(false, cCell.getStatus());

		cCell.toggleStatus();
		cCell.toggleStatus();
		cCell.toggleStatus();
		cCell.toggleStatus();
		assertEquals(null, cCell.getColorStatus());
		assertEquals(false, cCell.getStatus());

		cCell.toggleStatus();
		cCell.toggleStatus();
		cCell.toggleStatus();
		assertEquals(Color.blue, cCell.getColorStatus());
		assertEquals(true, cCell.getStatus());

		cCell.toggleStatus();
		cCell.toggleStatus();
		assertEquals(Color.red, cCell.getColorStatus());
		assertEquals(true, cCell.getStatus());
	}
	
	@Test
	public void testCellToString()
	{
		System.out.println(Color.red);
		cCell.setNextStatus(0);
		cCell.setColorStatus(null);
		cCell.updateStatus();
		assertEquals("false,null;", cCell.toString());
		cCell.setNextStatus(3);
		cCell.setColorStatus(Color.red);
		cCell.updateStatus();
		assertEquals("true,255,0,0;", cCell.toString());
		cCell.setNextStatus(2);
		cCell.setColorStatus(Color.blue);
		cCell.updateStatus();
		assertEquals("true,0,0,255;", cCell.toString());
	}
}
