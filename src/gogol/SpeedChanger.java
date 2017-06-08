package gogol;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by khopf on 07/06/2017.
 */
public class SpeedChanger implements ChangeListener
{

	private final Controller controller;
	private final Player player;

	public SpeedChanger(Controller cont, Player play)
	{
		controller = cont;
		player = play;
	}

	/**
	 * Invoked when the target of the listener has changed its state.
	 *
	 * @param e a ChangeEvent object
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();

		int fps = source.getValue();
		controller.lifegui.speedNumber.setText("SPS: " + player.getStepsPerSecond());
		player.setSpeed(fps);

	}
}
