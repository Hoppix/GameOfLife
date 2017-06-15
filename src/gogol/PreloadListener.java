package gogol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hoppix on 15.06.17.
 */
public class PreloadListener implements ActionListener
{
	private final Controller controller;
	private String newPreload;

	public PreloadListener(String change, Controller parent)
	{
		controller = parent;
		newPreload = change;
	}

	/**
	 * Changes the Preload mode on button press
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		controller.preloadMode = newPreload;
		controller.lifegui.preloadmode.setText(controller.preloadMode);
	}
}
