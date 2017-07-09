package gogol;

import gogol.backend.Controller;
import gogol.frontend.EndgameDialog;
import gogol.frontend.GameGrid;
import gogol.frontend.LifeGUI;

import java.awt.*;

/**
 * PTP 2017 project
 * Universität Hamburg
 * FB: Informatik
 * Abgabe: 13.07.2017
 *
 * Game of Game of Life:
 * A Java implementation of the Game of Life a cellular automaton developed
 * by the British mathematician John Horton Conway in 1970.
 *
 * Authors:
 * Kolja Hopfmann
 * Jonas Sander
 *
 * Hoppix/GameOfLife is licensed under the
 * GNU General Public License v3.0
 *
 * Created by hoppix on 18.05.17.
 */
public class Life
{
	/**
	 *
	 * Main method:
	 * Creates the GameGrid which does the display for the cells.
	 * LifeGUI as a Swing implemented GUI
	 * Controller which holds all the logic parts.
	 */
	public static void main(String[] args)
    {
        GameGrid grid = new GameGrid();
        LifeGUI gui = new LifeGUI(grid);
        new Controller(grid, gui);
    }
}
