package gogol;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

	private ImageIcon logoPNG;
	private ImageIcon nyiPNG;
	private ImageIcon iconICO;
	private ImageIcon toggleImage;
	private ImageIcon blockImage;
	private ImageIcon gliderImage;
	private ImageIcon blinkerImage;
	private ImageIcon carImage;
	private ImageIcon sealImage;
	private ImageIcon methImage;
	private ImageIcon fabImage;
	private ImageIcon butterImage;
	private ImageIcon ynotImage;
	private ImageIcon shipmakerImage;
	private ImageIcon glidergunImage;


	protected JButton step;
	protected JButton play;
	protected JButton pause;
	protected JButton clear;
	protected JButton random;
	protected JButton save;
	protected JButton load;

	protected JButton toggleButton;
	protected JButton blockButton;
	protected JButton gliderButton;
	protected JButton blinkerButton;
	protected JButton carButton;
	protected JButton sealButton;
	protected JButton methButton;
	protected JButton fabButton;
	protected JButton butterButton;
	protected JButton ynotButton;
	protected JButton shipmakerButton;
	protected JButton glidergunButton;

	protected JSlider speedSlider;
	protected JButton logo;
	protected Choice gametypeChooser;

	private JPanel preloadPanel;
	private JLabel gametype;
	private JLabel preload;
	private JLabel speedValue;

	protected JLabel speedNumber;
	protected JLabel preloadmode;
	protected JLabel generation;
	protected JLabel generationValue;

	private JLabel toggleLabel;
	private JLabel blockLabel;
	private JLabel gliderLabel;
	private JLabel blinkerLabel;
	private JLabel carLabel;
	private JLabel sealLabel;
	private JLabel methLabel;
	private JLabel fabLabel;
	private JLabel butterLabel;
	private JLabel glidergunLabel;
	private JLabel makerLabel;
	private JLabel ynotLabel;

	/**
	 * This is the GUI which hold all the frontend-parts
	 * @param parentGrid The GameGrid which does the cell display
	 */
	public LifeGUI(GameGrid parentGrid)
	{
		gameGrid = parentGrid;

		loadImages();
		initializeFrame();
		initializePreloadPanel();

		//Remove Java icon
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		frame.setIconImage(icon);
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

		pause = new JButton("STOP");
		pause.setBackground(tertiaryColor);
		pause.setForeground(secondaryColor);
		pause.setBounds(627, 636, 89, 23);
		frame.getContentPane().add(pause);

		
		step = new JButton("STEP");
		step.setBackground(tertiaryColor);
		step.setForeground(secondaryColor);
		step.setBounds(726, 636, 89, 23);
		frame.getContentPane().add(step);

		clear = new JButton("CLEAR");
		clear.setBackground(tertiaryColor);
		clear.setForeground(secondaryColor);
		clear.setBounds(429,636,89,23);
		frame.getContentPane().add(clear);

		random = new JButton("RANDOM");
		random.setBackground(tertiaryColor);
		random.setForeground(secondaryColor);
		random.setBounds(330,636,89,23);
		frame.getContentPane().add(random);

		speedSlider = new JSlider();
		speedSlider.setBackground(primaryColor);
		speedSlider.setForeground(secondaryColor);
		speedSlider.setUI(new ColoredThumbSliderUI(speedSlider, Color.gray));
		speedSlider.setBounds(898, 633, 200, 26);
		frame.getContentPane().add(speedSlider);


		speedValue = new JLabel("SPEED:");
		speedValue.setForeground(tertiaryColor);
		speedValue.setBounds(842, 640, 66, 14);
		frame.getContentPane().add(speedValue);

		speedNumber = new JLabel("SPS: " + 2.0 + "");
		speedNumber.setForeground(tertiaryColor);
		speedNumber.setBounds(974, 615, 86, 14);
		speedNumber.setToolTipText("Steps per Second");
		frame.getContentPane().add(speedNumber);

		generation = new JLabel("GENERATION: ");
		generation.setForeground(tertiaryColor);
		generation.setBounds(1174, 615, 96, 14);
		frame.getContentPane().add(generation);

		generationValue = new JLabel("0");
		generationValue.setForeground(tertiaryColor);
		generationValue.setBounds(1174, 635, 96, 14);
		frame.getContentPane().add(generationValue);

		play = new JButton("PLAY");
		play.setBackground(tertiaryColor);
		play.setForeground(secondaryColor);
		play.setBounds(528, 636, 89, 23);
		frame.getContentPane().add(play);

		gametype = new JLabel("TYPE:");
		gametype.setBounds(10, 11, 63, 23);
		frame.getContentPane().add(gametype);

		gametypeChooser = new Choice();
		gametypeChooser.add("Conway");
		gametypeChooser.setBackground(tertiaryColor);
		gametypeChooser.setForeground(secondaryColor);
		gametypeChooser.setBounds(83, 12, 116, 20);
		frame.getContentPane().add(gametypeChooser);

		save = new JButton("SAVE");
		save.setBackground(tertiaryColor);
		save.setForeground(secondaryColor);
		save.setBounds(10, 45, 89, 23);
		frame.getContentPane().add(save);

		load = new JButton("LOAD");
		load.setBackground(tertiaryColor);
		load.setForeground(secondaryColor);
		load.setBounds(109, 45, 89, 23);
		frame.getContentPane().add(load);


		logo = new JButton(new ImageIcon(LifeGUI.class.getResource("/design/logo.png")));
		logo.setEnabled(false);
		logo.setBounds(10, 487, 190, 190);
		frame.getContentPane().add(logo);
		frame.setVisible(true);
	}


	private void initializePreloadPanel()
	{
		preload = new JLabel("PRELOAD:");
		preload.setBounds(10, 93, 80, 14);
		frame.getContentPane().add(preload);

		preloadmode = new JLabel("Toggle");
		preloadmode.setBounds(90,93,90,16);
		frame.getContentPane().add(preloadmode);

		preloadPanel = new JPanel();
		preloadPanel.setBackground(secondaryColor);
		preloadPanel.setBounds(10, 118, 192, 358);
		frame.getContentPane().add(preloadPanel);
		preloadPanel.setLayout(null);

		toggleLabel = new JLabel("Cell");
		toggleLabel.setForeground(tertiaryColor);
		toggleLabel.setBounds(10, 11, 56, 14);
		preloadPanel.add(toggleLabel);

		blockLabel = new JLabel("Block");
		blockLabel.setForeground(tertiaryColor);
		blockLabel.setBounds(70, 11, 56, 14);
		preloadPanel.add(blockLabel);

		gliderLabel = new JLabel("Glider");
		gliderLabel.setForeground(tertiaryColor);
		gliderLabel.setBounds(130, 11, 56, 14);
		preloadPanel.add(gliderLabel);

		blinkerLabel = new JLabel("Blinker");
		blinkerLabel.setForeground(tertiaryColor);
		blinkerLabel.setBounds(10, 97, 56, 14);
		preloadPanel.add(blinkerLabel);

		carLabel = new JLabel("Car");
		carLabel.setForeground(tertiaryColor);
		carLabel.setBounds(70, 97, 56, 14);
		preloadPanel.add(carLabel);

		sealLabel = new JLabel("Seal");
		sealLabel.setForeground(tertiaryColor);
		sealLabel.setBounds(130, 97, 56, 14);
		preloadPanel.add(sealLabel);

		methLabel = new JLabel("Meth");
		methLabel.setForeground(tertiaryColor);
		methLabel.setBounds(10, 183, 56, 14);
		preloadPanel.add(methLabel);

		fabLabel = new JLabel("fab");
		fabLabel.setForeground(tertiaryColor);
		fabLabel.setBounds(70, 183, 56, 14);
		preloadPanel.add(fabLabel);

		butterLabel = new JLabel("Fly");
		butterLabel.setForeground(tertiaryColor);
		butterLabel.setBounds(130, 183, 56, 14);
		preloadPanel.add(butterLabel);

		glidergunLabel = new JLabel("Gun");
		glidergunLabel.setForeground(tertiaryColor);
		glidergunLabel.setBounds(10, 269, 56, 14);
		preloadPanel.add(glidergunLabel);

		makerLabel = new JLabel("Maker");
		makerLabel.setForeground(tertiaryColor);
		makerLabel.setBounds(70, 269, 56, 14);
		preloadPanel.add(makerLabel);

		ynotLabel = new JLabel("yNot");
		ynotLabel.setForeground(tertiaryColor);
		ynotLabel.setBounds(130, 269, 56, 14);
		preloadPanel.add(ynotLabel);

		toggleButton = new JButton(toggleImage);
		toggleButton.setBounds(10, 36, 50, 50);
		preloadPanel.add(toggleButton);

		blockButton = new JButton(blockImage);
		blockButton.setBounds(70, 36, 50, 50);
		preloadPanel.add(blockButton);

		gliderButton = new JButton(gliderImage);
		gliderButton.setBounds(130, 36, 50, 50);
		preloadPanel.add(gliderButton);

		blinkerButton = new JButton(blinkerImage);
		blinkerButton.setBounds(10, 122, 50, 50);
		preloadPanel.add(blinkerButton);

		carButton = new JButton(carImage);
		carButton.setBounds(70, 122, 50, 50);
		preloadPanel.add(carButton);

		sealButton = new JButton(sealImage);
		sealButton.setBounds(130, 122, 50, 50);
		preloadPanel.add(sealButton);

		methButton = new JButton(methImage);
		methButton.setBounds(10, 208, 50, 50);
		preloadPanel.add(methButton);

		fabButton = new JButton(fabImage);
		fabButton.setBounds(70, 208, 50, 50);
		preloadPanel.add(fabButton);

		butterButton = new JButton(butterImage);
		butterButton.setBounds(130, 208, 50, 50);
		preloadPanel.add(butterButton);

		ynotButton = new JButton(ynotImage);
		ynotButton.setBounds(130, 294, 50, 50);
		preloadPanel.add(ynotButton);

		shipmakerButton = new JButton(shipmakerImage);
		shipmakerButton.setBounds(70, 294, 50, 50);
		preloadPanel.add(shipmakerButton);

		glidergunButton = new JButton(glidergunImage);
		glidergunButton.setBounds(10, 294, 50, 50);
		preloadPanel.add(glidergunButton);

		preloadPanel.repaint();
	}

	private void loadImages()
	{
		logoPNG = new ImageIcon(LifeGUI.class.getResource("/design/logo.png"));
		nyiPNG = new ImageIcon(LifeGUI.class.getResource("/design/nyi.png"));
		iconICO = new ImageIcon(LifeGUI.class.getResource("/design/icon.ico"));
		toggleImage = new ImageIcon(LifeGUI.class.getResource("/design/toggle.png"));
		blockImage = new ImageIcon(LifeGUI.class.getResource("/design/block.png"));
		gliderImage = new ImageIcon(LifeGUI.class.getResource("/design/glider.png"));
		blinkerImage = new ImageIcon(LifeGUI.class.getResource("/design/blinker.png"));
		carImage = new ImageIcon(LifeGUI.class.getResource("/design/car.png"));
		sealImage = new ImageIcon(LifeGUI.class.getResource("/design/seal.png"));
		methImage = new ImageIcon(LifeGUI.class.getResource("/design/meth.png"));
		fabImage = new ImageIcon(LifeGUI.class.getResource("/design/fab.png"));
		butterImage = new ImageIcon(LifeGUI.class.getResource("/design/butterfly.png"));
		glidergunImage = new ImageIcon(LifeGUI.class.getResource("/design/glidergun.png"));
		shipmakerImage = new ImageIcon(LifeGUI.class.getResource("/design/fuse.png"));
		ynotImage = new ImageIcon(LifeGUI.class.getResource("/design/ynot.png"));




	}


}
