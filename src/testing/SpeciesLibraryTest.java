package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import gogol.library.Species;
import gogol.library.SpeciesLibrary;

public class SpeciesLibraryTest 
{
	SpeciesLibrary testLib = new SpeciesLibrary();
	
	@Test
	public void testGetSpecies()
	{
		boolean[][] gliderPattern = new boolean[3][3];
		gliderPattern[0][1] = true;
		gliderPattern[1][2] = true;
		gliderPattern[2][0] = true;
		gliderPattern[2][1] = true;
		gliderPattern[2][2] = true;		
		
		Species glider = testLib.getSpecies("Glider");
		assertEquals("Glider", glider.getName());
		assertEquals("moving", glider.getDescription());
		assertEquals(3, glider.getSizeX());
		assertEquals(3, glider.getSizeY());
		assertArrayEquals(gliderPattern, glider.getPattern());
	}
}
