package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;

import gogol.cells.PvPCell;

public class PvPCellTest 
{
	PvPCell cell;
	
	@Before
	public void resetCell()
	{
		cell = new PvPCell();
	}
	
	@Test
	public void testToggle()
	{
		cell.toggleStatus();
		cell.setColorStatus(Color.pink);
		cell.updateStatus();
		assertTrue(cell.getStatus());
		assertEquals(Color.pink, cell.getColorStatus());
		cell.toggleStatus();
		cell.updateStatus();
		assertFalse(cell.getStatus());
		assertNull(cell.getColorStatus());
	}
}
