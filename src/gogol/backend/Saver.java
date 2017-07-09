package gogol.backend;

import gogol.cells.ColoredCell;
import gogol.cells.PvPCell;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by khopf on 13/06/2017.
 */
public class Saver
{
	private final Controller controller;

	/**
	 * handles the saving and loading called by the GUI
	 * @param parent Controller
	 */
	public Saver(Controller parent)
	{
		controller = parent;
	}

	public final static String FILETYPE = ".life"; // own file type

	/**
	 * Writes the current Gamestate to a text file
	 * first line are Settings, lines 2-n are the cells linewise.
	 * settings: Gamemode;tilesize;survivalMatrixX;surivalMatrixY;
	 */
	public void saveGamestate()
	{
		BufferedWriter writer = null;
		String saveFile = null;

		//create filechooser
		final JFileChooser fc = new JFileChooser();

		setFileChooserTheme(fc.getAccessibleContext());

		int returnVal = fc.showSaveDialog(null);

		//return when approve button was not pressed
		if(returnVal != fc.APPROVE_OPTION)
		{
			return;
		}

		// get file path from the selected file
		saveFile = fc.getSelectedFile().getAbsolutePath() + FILETYPE;


		try
		{
			// write game information into the *.life file
			writer = new BufferedWriter( new FileWriter(saveFile));
			writer.write(controller.gameMode + ";");
			writer.write(controller.gamegrid.tileSize + ";");
			writer.write(controller.survivalMatrix[0].length + ";");
			writer.write(controller.survivalMatrix.length + ";");
			writer.newLine();

			// write string from the cells overwritten toString method
			for (int y = 0; y < controller.survivalMatrix.length; y++)
			{
				for (int x = 0; x < controller.survivalMatrix[y].length; x++)
				{
					writer.write(controller.survivalMatrix[y][x].toString() + ";");
				}
				writer.newLine();
			}
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * loads a previously saved game
	 */
	public void loadGamestate()
	{

		// clean SVM + grid
		controller.clear();

		// declare reader variables
		BufferedReader reader = null;
		String line = null;
		String[] args = null;
		String[] cellArgs = null;

		String saveFile = null;

		// create filechooser
		final JFileChooser fc = new JFileChooser();

		setFileChooserTheme(fc.getAccessibleContext());

		int returnVal = fc.showOpenDialog(null);

		//return when approve button was not pressed
		if(returnVal != fc.APPROVE_OPTION)
		{
			return;
		}

		saveFile = fc.getSelectedFile().getAbsolutePath();

		// return when selected file is not of the .life filetype
		if(!saveFile.endsWith(FILETYPE))
		{
			return;
		}

		try
		{
			int y = 0;

			// initialize reader for the selected file
			reader = new BufferedReader(new FileReader(saveFile));
			line = reader.readLine();
			args = line.split(";");
			// save values into an array

			int rulezConway = 0;

			// switch so the gamemode directed by the loaded file
			controller.lifegui.gametypeChooser.select(args[0]);
			controller.changeGameMode(args[0]);
			controller.gamegrid.tileSize = Integer.parseInt(args[1]);
			controller.setGridsize(Integer.parseInt(args[2]),Integer.parseInt(args[3]));

			if(args[0].equals("Conway"))
			{
				rulezConway = 0;
			}
			else if(args[0].equals("PvP"))
			{
				rulezConway = 2;
			}
			else
			{
					rulezConway = 1;
			}

			// read through all lines of the file beginning with the first line
			while ((line = reader.readLine()) != null)
			{
				//split args per line
				args = line.split(";");
				for (int x = 0; x < args.length; x++)
				{
					// in conway mode we just set the SVM at the given position to the boolean string we read
					if(rulezConway == 0)
					{
						if(args[x].equals("true"))
						{
							controller.survivalMatrix[y][x].toggleStatus();
							controller.gamegrid.setField(controller.survivalMatrix[y][x], x, y);
						}
					}

					// for the colored cell variants we extract the color which comes after the boolean string
					else if(rulezConway == 1)
					{
						cellArgs = args[x].split(",");
						if(cellArgs[0].equals("true"))
						{
							controller.survivalMatrix[y][x].setNextStatus(3);
							((ColoredCell)controller.survivalMatrix[y][x]).setColorStatus(new Color(Integer.parseInt
									(cellArgs[1]),
									Integer.parseInt(cellArgs[2]),Integer.parseInt(cellArgs[3])));
							controller.survivalMatrix[y][x].updateStatus();
							controller.gamegrid.setField(controller.survivalMatrix[y][x], x, y);
						}
					}

					// in pvp mode we just adjust to the two given player colors
					else if(rulezConway == 2)
					{
						cellArgs = args[x].split(",");
						if(cellArgs[0].equals("true"))
						{
							controller.survivalMatrix[y][x].setNextStatus(3);
							((PvPCell)controller.survivalMatrix[y][x]).setColorStatus(new Color(Integer.parseInt
									(cellArgs[1]),
									Integer.parseInt(cellArgs[2]),Integer.parseInt(cellArgs[3])));
							controller.survivalMatrix[y][x].updateStatus();
							controller.gamegrid.setField(controller.survivalMatrix[y][x], x, y);
						}
					}

				}
				y++;
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * applies dark theme to the filechooser
	 * @param ac
	 */
	private static void setFileChooserTheme(AccessibleContext ac)
	{
		Color prim = Color.GRAY;
		Color sec = Color.LIGHT_GRAY;
		Color tert = Color.DARK_GRAY;

		if(ac.getAccessibleComponent().getClass().getName().equals("javax.swing.JPanel$AccessibleJPanel"))
		{
			ac.getAccessibleComponent().setForeground(sec);
			ac.getAccessibleComponent().setBackground(prim);
		}
		else if(ac.getAccessibleComponent().getClass().getName().equals("javax.swing.JButton$AccessibleJButton"))
		{
			ac.getAccessibleComponent().setForeground(sec);
			ac.getAccessibleComponent().setBackground(tert);
		}
		else
		{
			ac.getAccessibleComponent().setForeground(tert);
			ac.getAccessibleComponent().setBackground(prim);
		}
		int n = ac.getAccessibleChildrenCount();
		for (int i=0; i<n; i++) {
			setFileChooserTheme(ac.getAccessibleChild(i).getAccessibleContext());
		}
	}
}
