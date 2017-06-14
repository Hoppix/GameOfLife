package gogol;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by khopf on 07/06/2017.
 */
public class SpeedChangerListener implements ChangeListener
{

	private final Controller controller;
	private final Player player;

	public SpeedChangerListener(Controller parent, Player setSpeed)
	{
		controller = parent;
		player = setSpeed;
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
