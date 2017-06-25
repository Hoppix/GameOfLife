package gogol.listener;

import gogol.backend.Command;
import gogol.backend.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
	Command cmd;
	Controller controller;

	/**
	 * ButtonListener for the Controller which selects the Command for
	 * the Controller commandqueue.
	 * @param cmd command to be executed
	 * @param parent controller who holds the gui with the corresponding buttons.
	 */
	public ButtonListener(Command cmd, Controller parent)
	{
		this.cmd = cmd;
		this.controller = parent;
	}

	/**
	 * Invoked when an action occurs.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		controller.doCommand(cmd);
	}
	
}
