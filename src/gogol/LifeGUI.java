package gogol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class LifeGUI
{
	private JFrame frame;
	private GameGrid gameGrid;

	private static final Color primaryColor = Color.gray;
	private static final Color secondaryColor = Color.lightGray;
	private static final Color tertiaryColor = Color.darkGray;

	private ImageIcon logoPNG = new ImageIcon(getClass().getResource("../design/logo.png"));
	private ImageIcon nyiPNG = new ImageIcon(getClass().getResource("../design/nyi.png"));


	protected JButton step;
	protected JButton play;
	protected JButton pause;
	protected JButton clear;
	protected JButton random;
	protected JButton save;
	protected JButton load;

	protected JButton image1;
	protected JButton image2;
	protected JButton image3;
	protected JButton image4;
	protected JButton image5;
	protected JButton image6;
	protected JButton image7;
	protected JButton image8;
	protected JButton image9;
	protected JButton image10;
	protected JButton image11;
	protected JButton image12;

	protected JSlider speedSlider;
	protected JButton logo;
	protected Choice gametypeChooser;

	private JPanel preloadPanel;
	private JLabel gametype;
	private JLabel preload;
	private JLabel speedValue;

	private JLabel form1;
	private JLabel form2;
	private JLabel form3;
	private JLabel form4;
	private JLabel form5;
	private JLabel form6;
	private JLabel form7;
	private JLabel form8;
	private JLabel form9;
	private JLabel form10;
	private JLabel form11;
	private JLabel form12;

	public LifeGUI(GameGrid parentGrid)
	{
		gameGrid = parentGrid;

		initializeFrame();
		initializePreloadPanel();
	}

	private void initializeFrame()
	{
		frame = new JFrame("GOGOL");
		frame.getContentPane().setBackground(primaryColor);
		frame.setBounds(100, 100, 1327, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		gameGrid.setBounds(220, 0, 1100, 600);
		frame.getContentPane().add(gameGrid);

		pause = new JButton("pause");
		pause.setBackground(tertiaryColor);
		pause.setForeground(secondaryColor);
		pause.setBounds(627, 636, 89, 23);
		frame.getContentPane().add(pause);

		
		step = new JButton("step");
		step.setBackground(tertiaryColor);
		step.setForeground(secondaryColor);
		step.setBounds(726, 636, 89, 23);
		frame.getContentPane().add(step);

		clear = new JButton("clear");
		clear.setBackground(tertiaryColor);
		clear.setForeground(secondaryColor);
		clear.setBounds(429,636,89,23);
		frame.getContentPane().add(clear);

		random = new JButton("random");
		random.setBackground(tertiaryColor);
		random.setForeground(secondaryColor);
		random.setBounds(330,636,89,23);
		frame.getContentPane().add(random);

		speedSlider = new JSlider();
		speedSlider.setBackground(primaryColor);
		speedSlider.setForeground(secondaryColor);
		speedSlider.setBounds(898, 633, 200, 26);
		frame.getContentPane().add(speedSlider);


		speedValue = new JLabel("speed:");
		speedValue.setForeground(tertiaryColor);
		speedValue.setBounds(842, 640, 46, 14);
		frame.getContentPane().add(speedValue);

		play = new JButton("play");
		play.setBackground(tertiaryColor);
		play.setForeground(secondaryColor);
		play.setBounds(528, 636, 89, 23);
		frame.getContentPane().add(play);

		gametype = new JLabel("GameType:");
		gametype.setBounds(10, 11, 63, 23);
		frame.getContentPane().add(gametype);

		gametypeChooser = new Choice();
		gametypeChooser.add("Conway");
		gametypeChooser.setBackground(tertiaryColor);
		gametypeChooser.setForeground(secondaryColor);
		gametypeChooser.setBounds(83, 12, 116, 20);
		frame.getContentPane().add(gametypeChooser);

		save = new JButton("save");
		save.setBackground(tertiaryColor);
		save.setForeground(secondaryColor);
		save.setBounds(10, 45, 89, 23);
		frame.getContentPane().add(save);

		load = new JButton("load");
		load.setBackground(tertiaryColor);
		load.setForeground(secondaryColor);
		load.setBounds(109, 45, 89, 23);
		frame.getContentPane().add(load);


		logo = new JButton(logoPNG);
		logo.setEnabled(false);
		logo.setBounds(10, 487, 190, 190);
		frame.getContentPane().add(logo);
		frame.setVisible(true);
	}


	private void initializePreloadPanel()
	{
		preload = new JLabel("Preload:");
		preload.setBounds(10, 93, 50, 14);
		frame.getContentPane().add(preload);

		preloadPanel = new JPanel();
		preloadPanel.setBackground(secondaryColor);
		preloadPanel.setBounds(10, 118, 192, 358);
		frame.getContentPane().add(preloadPanel);
		preloadPanel.setLayout(null);

		form1 = new JLabel("form1");
		form1.setForeground(tertiaryColor);
		form1.setBounds(10, 11, 46, 14);
		preloadPanel.add(form1);

		form2 = new JLabel("form2");
		form2.setForeground(tertiaryColor);
		form2.setBounds(70, 11, 46, 14);
		preloadPanel.add(form2);

		form3 = new JLabel("form3");
		form3.setForeground(tertiaryColor);
		form3.setBounds(130, 11, 46, 14);
		preloadPanel.add(form3);

		form4 = new JLabel("form4");
		form4.setForeground(tertiaryColor);
		form4.setBounds(10, 97, 46, 14);
		preloadPanel.add(form4);

		form5 = new JLabel("form5");
		form5.setForeground(tertiaryColor);
		form5.setBounds(70, 97, 46, 14);
		preloadPanel.add(form5);

		form6 = new JLabel("form6");
		form6.setForeground(tertiaryColor);
		form6.setBounds(130, 97, 46, 14);
		preloadPanel.add(form6);

		form7 = new JLabel("form7");
		form7.setForeground(tertiaryColor);
		form7.setBounds(10, 183, 46, 14);
		preloadPanel.add(form7);

		form8 = new JLabel("form8");
		form8.setForeground(tertiaryColor);
		form8.setBounds(70, 183, 46, 14);
		preloadPanel.add(form8);

		form9 = new JLabel("form9");
		form9.setForeground(tertiaryColor);
		form9.setBounds(130, 183, 46, 14);
		preloadPanel.add(form9);

		form10 = new JLabel("form10");
		form10.setForeground(tertiaryColor);
		form10.setBounds(10, 269, 46, 14);
		preloadPanel.add(form10);

		form11 = new JLabel("form11");
		form11.setForeground(tertiaryColor);
		form11.setBounds(70, 269, 46, 14);
		preloadPanel.add(form11);

		form12 = new JLabel("form12");
		form12.setForeground(tertiaryColor);
		form12.setBounds(130, 269, 46, 14);
		preloadPanel.add(form12);

		image1 = new JButton(nyiPNG);
		image1.setBounds(10, 36, 50, 50);
		preloadPanel.add(image1);

		image2 = new JButton(nyiPNG);
		image2.setBounds(70, 36, 50, 50);
		preloadPanel.add(image2);

		image3 = new JButton(nyiPNG);
		image3.setBounds(130, 36, 50, 50);
		preloadPanel.add(image3);

		image4 = new JButton(nyiPNG);
		image4.setBounds(10, 122, 50, 50);
		preloadPanel.add(image4);

		image5 = new JButton(nyiPNG);
		image5.setBounds(70, 122, 50, 50);
		preloadPanel.add(image5);

		image6 = new JButton(nyiPNG);
		image6.setBounds(130, 122, 50, 50);
		preloadPanel.add(image6);

		image7 = new JButton(nyiPNG);
		image7.setBounds(10, 208, 50, 50);
		preloadPanel.add(image7);

		image8 = new JButton(nyiPNG);
		image8.setBounds(70, 208, 50, 50);
		preloadPanel.add(image8);

		image9 = new JButton(nyiPNG);
		image9.setBounds(130, 208, 50, 50);
		preloadPanel.add(image9);

		image10 = new JButton(nyiPNG);
		image10.setBounds(130, 294, 50, 50);
		preloadPanel.add(image10);

		image11 = new JButton(nyiPNG);
		image11.setBounds(70, 294, 50, 50);
		preloadPanel.add(image11);

		image12 = new JButton(nyiPNG);
		image12.setBounds(10, 294, 50, 50);
		preloadPanel.add(image12);
	}


}
