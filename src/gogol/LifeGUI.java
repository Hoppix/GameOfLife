package gogol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class LifeGUI
{

	public LifeGUI(GameGrid parentGrid)
	{
		JFrame frame = new JFrame("GGOL");
		JButton stepfoward = new JButton("stepfoward");
		JButton stepfoward1 = new JButton("stepfoward");
		JButton stepfoward2 = new JButton("stepfoward");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		frame.add(stepfoward);
		frame.add(stepfoward1);
		frame.add(stepfoward2);
		frame.getContentPane().setSize(1280,720);
		frame.getContentPane().add(parentGrid);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}


}
