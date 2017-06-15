package gogol;

import java.math.BigDecimal;

import static java.lang.Thread.sleep;

/**
 * Created by khopf on 07/06/2017.
 */
public class Player
{
	private final Controller controller;
	private final int intervall;
	protected int speed;
	protected boolean running;



	/**
	 * The Player is used for threading the lifecycle of the cell-array
	 * @param parent Controller
	 */
	public Player(Controller parent)
	{
		controller = parent;
		speed = 500;
		intervall = 1000;
		running = false;
	}


	/**
	 * Starts a new thread which begins the loop for playing.
	 */
	public void startLoop()
	{
		Thread thread = new Thread(() ->
		{
			running = true;
			stepLoop();
		});
		thread.start();
	}

	/**
	 * stops the playing loop.
	 */
	public void stopLoop()
	{

		running = false;
	}


	private void stepLoop()
	{
		while (running)
		{
			controller.stepForward();
			try
			{
				sleep(intervall - speed);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * sets the speed for the loop
	 * @param speedV given speed value
	 */
	public void setSpeed(int speedV)
	{
		speed = speedV * 10;
	}

	/**
	 * returns the SPS for displaying on the GUI
	 * @return LabelString
	 */
	public String getStepsPerSecond()
	{
		if (intervall - speed == 0)
		{
			return "MAX";
		}
		float r = (float) intervall / ((float) intervall - (float) speed);
		return String.valueOf(round3(r, 2));
	}

	private static float round3(float d, int decimalPlace)
	{
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}


}
