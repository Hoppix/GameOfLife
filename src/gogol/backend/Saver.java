package gogol.backend;

import gogol.backend.Controller;

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
	public final static String FILETYPE = ".life";

	/**
	 * Writes the current Gamestate to a text file
	 * first line are Settings, lines 2-n are the cells linewise.
	 * settings: Gamemode;tilesize;survivalMatrixX;surivalMatrixY;
	 */
	public void saveGamestate()
	{
		BufferedWriter writer = null;
		String saveFile = null;

		final JFileChooser fc = new JFileChooser();

		setFileChooserTheme(fc.getAccessibleContext());

		int returnVal = fc.showSaveDialog(null);

		if(returnVal != fc.APPROVE_OPTION)
		{
			return;
		}

		saveFile = fc.getSelectedFile().getAbsolutePath() + FILETYPE;

		try
		{
			writer = new BufferedWriter( new FileWriter(saveFile));
			writer.write(controller.gameMode + ";");
			writer.write(controller.gamegrid.tileSize + ";");
			writer.write(controller.survivalMatrix[0].length + ";");
			writer.write(controller.survivalMatrix.length + ";");
			writer.newLine();

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
		controller.clear();

		BufferedReader reader = null;
		String line = null;
		String[] args = null;

		String saveFile = null;

		final JFileChooser fc = new JFileChooser();

		setFileChooserTheme(fc.getAccessibleContext());

		int returnVal = fc.showOpenDialog(null);

		if(returnVal != fc.APPROVE_OPTION)
		{
			return;
		}

		saveFile = fc.getSelectedFile().getAbsolutePath();

		if(!saveFile.endsWith(FILETYPE))
		{
			return;
		}

		try
		{
			int y = 0;

			reader = new BufferedReader(new FileReader(saveFile));
			line = reader.readLine();
			args = line.split(";");

			controller.gameMode = args[0];
			controller.gamegrid.tileSize = Integer.parseInt(args[1]);
			controller.setGridsize(Integer.parseInt(args[2]),Integer.parseInt(args[3]));

			while ((line = reader.readLine()) != null)
			{
				args = line.split(";");
				for (int x = 0; x < args.length; x++)
				{
					if(args[x].equals("true"))
					{
						controller.survivalMatrix[y][x].toggleStatus();
						controller.gamegrid.setField(controller.survivalMatrix[y][x], x, y);
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
