package gogol;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by hoppix on 22.06.17.
 */
public class GameModeListener implements ItemListener
{
	private final Controller controller;

	public GameModeListener(Controller parent)
	{
		controller = parent;
	}
	@Override
	public void itemStateChanged(ItemEvent itemEvent)
	{
		String modeChange = itemEvent.getItem().toString();
		controller.gameMode = modeChange;
	}
}
