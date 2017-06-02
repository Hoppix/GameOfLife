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
		//TODO @Jonas fix
		assertTrue(testCell.getStatus());
		testCell.setNextStatus(1);
		assertFalse(testCell.getStatus());
		testCell.setNextStatus(2);
		assertFalse(testCell.getStatus());
		testCell.setNextStatus(3);
		assertTrue(testCell.getStatus());
		testCell.setNextStatus(2);
		assertTrue(testCell.getStatus());
		testCell.setNextStatus(4);
		assertFalse(testCell.getStatus());
	}

}
