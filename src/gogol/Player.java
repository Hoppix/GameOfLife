package gogol;

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


	public Player(Controller cont)
	{
		controller = cont;
		speed = 500;
		intervall = 1000;
		running = true;
	}


	public void startLoop()
	{
		Thread thread = new Thread(() ->
		{
			running = true;
			stepLoop();
		});
		thread.start();
	}

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

	public void setSpeed(int speedV)
	{
		speed = speedV * 10;
	}


}
