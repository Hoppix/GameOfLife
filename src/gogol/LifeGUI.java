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
	
	protected JButton step;

	public LifeGUI(GameGrid parentGrid)
	{
		gameGrid = parentGrid;
		initialize();
	}

	private void initialize()
	{
		System.out.println("start init");
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);


		gameGrid.setBounds(220, 0, 1100, 600);
		frame.getContentPane().add(gameGrid);

		JButton btnPause = new JButton("pause");
		btnPause.setBounds(627, 636, 89, 23);
		frame.getContentPane().add(btnPause);

		//JButton btnStep = new JButton("step");
		//btnStep.setBounds(726, 636, 89, 23);
		
		step = new JButton("step");
		step.setBounds(726, 636, 89, 23);
		frame.getContentPane().add(step);

		JSlider slider = new JSlider();
		slider.setBounds(898, 633, 200, 26);
		frame.getContentPane().add(slider);

		JLabel lblSpeed = new JLabel("speed:");
		lblSpeed.setBounds(842, 640, 46, 14);
		frame.getContentPane().add(lblSpeed);

		JButton btnPlay = new JButton("play");
		btnPlay.setBounds(528, 636, 89, 23);
		frame.getContentPane().add(btnPlay);

		JLabel lblGametype = new JLabel("GameType:");
		lblGametype.setBounds(10, 11, 63, 23);
		frame.getContentPane().add(lblGametype);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(83, 12, 116, 20);
		frame.getContentPane().add(comboBox);

		JButton btnSave = new JButton("save");
		btnSave.setBounds(10, 45, 89, 23);
		frame.getContentPane().add(btnSave);

		JButton btnLoad = new JButton("load");
		btnLoad.setBounds(109, 45, 89, 23);
		frame.getContentPane().add(btnLoad);

		JLabel lblPreload = new JLabel("Preload:");
		lblPreload.setBounds(10, 93, 46, 14);
		frame.getContentPane().add(lblPreload);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 118, 192, 358);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblForm = new JLabel("form1");
		lblForm.setBounds(10, 11, 46, 14);
		panel_1.add(lblForm);

		JLabel lblForm_1 = new JLabel("form2");
		lblForm_1.setBounds(70, 11, 46, 14);
		panel_1.add(lblForm_1);

		JLabel lblForm_2 = new JLabel("form3");
		lblForm_2.setBounds(130, 11, 46, 14);
		panel_1.add(lblForm_2);

		JLabel label = new JLabel("form1");
		label.setBounds(10, 97, 46, 14);
		panel_1.add(label);

		JLabel label_1 = new JLabel("form1");
		label_1.setBounds(70, 97, 46, 14);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("form1");
		label_2.setBounds(130, 97, 46, 14);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("form1");
		label_3.setBounds(10, 183, 46, 14);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("form1");
		label_4.setBounds(70, 183, 46, 14);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("form1");
		label_5.setBounds(130, 183, 46, 14);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("form1");
		label_6.setBounds(10, 269, 46, 14);
		panel_1.add(label_6);

		JLabel label_7 = new JLabel("form1");
		label_7.setBounds(70, 269, 46, 14);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("form1");
		label_8.setBounds(130, 269, 46, 14);
		panel_1.add(label_8);

		JButton btnNewButton_1 = new JButton("IMAGE");
		btnNewButton_1.setToolTipText("sample");
		btnNewButton_1.setBounds(10, 36, 50, 50);
		panel_1.add(btnNewButton_1);

		JButton button = new JButton("IMAGE");
		button.setBounds(70, 36, 50, 50);
		panel_1.add(button);

		JButton button_1 = new JButton("IMAGE");
		button_1.setBounds(130, 36, 50, 50);
		panel_1.add(button_1);

		JButton button_2 = new JButton("IMAGE");
		button_2.setBounds(10, 122, 50, 50);
		panel_1.add(button_2);

		JButton button_3 = new JButton("IMAGE");
		button_3.setBounds(70, 122, 50, 50);
		panel_1.add(button_3);

		JButton button_4 = new JButton("IMAGE");
		button_4.setBounds(130, 122, 50, 50);
		panel_1.add(button_4);

		JButton button_5 = new JButton("IMAGE");
		button_5.setBounds(10, 208, 50, 50);
		panel_1.add(button_5);

		JButton button_6 = new JButton("IMAGE");
		button_6.setBounds(70, 208, 50, 50);
		panel_1.add(button_6);

		JButton button_7 = new JButton("IMAGE");
		button_7.setBounds(130, 208, 50, 50);
		panel_1.add(button_7);

		JButton button_8 = new JButton("IMAGE");
		button_8.setBounds(130, 294, 50, 50);
		panel_1.add(button_8);

		JButton button_9 = new JButton("IMAGE");
		button_9.setBounds(70, 294, 50, 50);
		panel_1.add(button_9);

		JButton button_10 = new JButton("IMAGE");
		button_10.setBounds(10, 294, 50, 50);
		panel_1.add(button_10);

		JButton btnNewButton = new JButton("LOGO");
		btnNewButton.setEnabled(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(10, 487, 189, 193);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);

		System.out.println("end init");
	}


}
