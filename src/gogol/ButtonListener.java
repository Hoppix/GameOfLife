package gogol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
	Command cmd;
	Controller lol;
	
	public ButtonListener(Command cmd, Controller idkwhattonametheseanymore)
	{
		this.cmd = cmd;
		this.lol = idkwhattonametheseanymore;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		lol.doCommand(cmd);
	}
	
}
