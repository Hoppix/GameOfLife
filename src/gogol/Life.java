
package gogol;
/**
 * Created by hoppix on 18.05.17.
 */
public class Life
{
    public static void main(String[] args)
    {
        GameGrid grid = new GameGrid();
        new LifeGUI(grid);
        new Controller(grid);

    }
}
