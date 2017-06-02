package testing;

import gogol.GameGrid;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class GameGridTest
{
	GameGrid testGrid;

	@org.junit.Before
	public void setUp() throws Exception
	{
		testGrid = new GameGrid();
	}

	@org.junit.Test
	public void getPreferredSize() throws Exception
	{
		Dimension testMe = new Dimension(1100, 600);
		assertEquals(testMe, testGrid.getSize());
	}

	@org.junit.Test
	public void testSetupGrid()
	{
		//TODO test
	}

	@org.junit.Test
	public void testSetField()
	{
		//TODO test
	}

}