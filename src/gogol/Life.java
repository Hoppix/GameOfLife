
package gogol;
/**
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
