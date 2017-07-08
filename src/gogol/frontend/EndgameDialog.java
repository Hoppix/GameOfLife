package gogol.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by khopf on 08/07/2017.
 */
public class EndgameDialog extends JFrame
{
	private static final int WIDTH = 300;
	private static final int HEIGHT = 120;

	Color winnerColor;
	int turns;
	int redCount;
	int blueCount;


	public EndgameDialog( int t, int r, int b)
	{
		super();

		turns = t;
		redCount = r;
		blueCount = b;
		winnerColor = (r>b) ? Color.red : Color.blue;

		initialize();
		addComponents();

		setLocationRelativeTo(null);
		setVisible(true);
	}


	private void initialize()
	{
		getContentPane().setBackground(LifeGUI.primaryColor);
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);

		setDefaultCloseOperation(1);
		setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setFocusable(true);
		requestFocus();
		pack();
	}

	private void addComponents()
	{
		JLabel winnerLabel = new JLabel("WINNER:",0);
		winnerLabel.setBounds(WIDTH / 2 - 35 , 0, 70, 30);
		getContentPane().add(winnerLabel);


		JLabel blueLabel = new JLabel("Blue:",0);
		JLabel redLabel = new JLabel("Red:", 0);
		JLabel blueCountLabel = new JLabel("" + blueCount ,0);
		JLabel redCountLabel = new JLabel("" + redCount,0);

		blueLabel.setBounds(10,20,70,30);
		blueCountLabel.setBounds(10,40,70,30);
		redLabel.setBounds(210,20,70,30);
		redCountLabel.setBounds(210,40,70,30);

		getContentPane().add(redLabel);
		getContentPane().add(redCountLabel);
		getContentPane().add(blueLabel);
		getContentPane().add(blueCountLabel);



		JLabel colorWinLabel;
		if(winnerColor.equals(Color.red))
		{
			colorWinLabel = new JLabel("red",0);

		}
		else if(winnerColor.equals(Color.blue))
		{
			colorWinLabel = new JLabel("blue",0);
		}
		else
		{
			colorWinLabel = new JLabel("dont call me",0);
		}
		colorWinLabel.setForeground(winnerColor);
		colorWinLabel.setBounds(WIDTH / 2 -35, 20,70,30);
		getContentPane().add(colorWinLabel);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(WIDTH, HEIGHT);
	}
}
