package gogol.listener;

import gogol.backend.Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by hoppix on 22.06.17.
 */
public class GameModeListener implements ItemListener
{
	private final Controller controller;

	/**
	 * listens to the choice selector and switches to the selected gamemode when changed
	 *
	 * @param parent
	 */
	public GameModeListener(Controller parent)
	{
		controller = parent;
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent)
	{
		String modeChange = itemEvent.getItem().toString();
		controller.changeGameMode(modeChange);
	}
}
