package testing;

import static org.junit.Assert.*;

import gogol.ConwayCell;
import org.junit.Before;
import org.junit.Test;

public class ConwayCellTest 
{
	ConwayCell testCell;
	
	@Before
	public void reset()
	{
		testCell = new ConwayCell();
	}
	
	@Test
	public void testSetNextStatus() 
	{	
		assertFalse(testCell.getStatus());
		testCell.setNextStatus(3);
		assertFalse(testCell.getStatus());
		testCell.updateStatus();
		testCell.setNextStatus(1);
		testCell.updateStatus();
		assertFalse(testCell.getStatus());
		testCell.setNextStatus(2);
		testCell.updateStatus();
		assertFalse(testCell.getStatus());
		testCell.setNextStatus(3);
		testCell.updateStatus();
		assertTrue(testCell.getStatus());
		testCell.setNextStatus(2);
		testCell.updateStatus();
		assertTrue(testCell.getStatus());
		testCell.setNextStatus(4);
		testCell.updateStatus();
		assertFalse(testCell.getStatus());
	}
	
	@Test
	public void testToggleStatus()
	{
		testCell.toggleStatus();
		assertTrue(testCell.getStatus());
		testCell.toggleStatus();
		assertFalse(testCell.getStatus());
	}
	
	public void testCellToString()
	{
		assertEquals("false", testCell.toString());
		testCell.toggleStatus();
		assertEquals("true", testCell.toString());
	}

}
