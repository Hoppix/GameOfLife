package testing;

import gogol.Controller;
import gogol.GameGrid;
import gogol.LifeGUI;
import gogol.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by khopf on 15/06/2017.
 */
public class PlayerTest
{
	private Controller bigBrother;
	private Player testMe;

	@Before
	public void reset()
	{
		GameGrid grid = new GameGrid();
		LifeGUI gui = new LifeGUI(grid);
		bigBrother = new Controller(grid, gui);

		testMe = new Player(bigBrother);
	}

	@Test
	public void startLoop() throws Exception
	{
	}

	@Test
	public void stopLoop() throws Exception
	{
	}

	@Test
	public void setSpeed() throws Exception
	{
		testMe.setSpeed(0);
		assertEquals(0, testMe.speed);

		testMe.setSpeed(10);
		assertEquals(100, testMe.speed);

		testMe.setSpeed(100);
		assertEquals(1000, testMe.speed);

		testMe.setSpeed(-1);
		assertEquals(1000,testMe.speed);

		testMe.setSpeed(101);
		assertEquals(1000, testMe.speed);
	}

	@Test
	public void getStepsPerSecond() throws Exception
	{
		testMe.setSpeed(100);
		assertEquals("MAX", testMe.getStepsPerSecond());

		testMe.setSpeed(0);
		assertEquals(1.0 +"", testMe.getStepsPerSecond());

		testMe.setSpeed(50);
		assertEquals(2.0 +"", testMe.getStepsPerSecond());

		for(int i = 1; i < 99; i++)
		{
			testMe.setSpeed(i);
			float  number = (float)1000 / (1000 - i) ;
			assertEquals(Math.round(number*100.0)/100.0 +"" , testMe.getStepsPerSecond());
		}
	}

}