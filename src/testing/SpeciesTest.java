package testing;

import static org.junit.Assert.*;
import org.junit.*;
import gogol.Species;

public class SpeciesTest 
{
	Species testMonkey = null;
	
	@Before
	public void resetMonkey()
	{
		testMonkey = new Species("Glider", "moving", "bo2b$2bob$3ob", 4, 3);
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Glider", testMonkey.getName());
	}
	
	@Test
	public void testGetDescription()
	{
		assertEquals("moving", testMonkey.getDescription());
	}
	
	@Test
	public void testGetSizeX()
	{
		assertEquals(4, testMonkey.getSizeX());
	}
	
	@Test
	public void testGetSizeY()
	{
		assertEquals(3, testMonkey.getSizeY());
	}
	
	@Test
	public void testGetPattern()
	{
		boolean[][] testPattern = testMonkey.getPattern();
		assertFalse(testPattern[0][0]);
		assertTrue(testPattern[0][1]);
		assertFalse(testPattern[0][2]);
		assertFalse(testPattern[0][3]);
		assertFalse(testPattern[1][0]);
		assertFalse(testPattern[1][1]);
		assertTrue(testPattern[1][2]);
		assertFalse(testPattern[1][3]);
		assertTrue(testPattern[2][0]);
		assertTrue(testPattern[2][1]);
		assertTrue(testPattern[2][2]);
		assertFalse(testPattern[2][3]);
	}
}
