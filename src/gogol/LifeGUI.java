package gogol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hoppix on 18.05.17.
 */
public class LifeGUI
{
    protected JFrame mainframe;
    protected JButton stepfoward;
    protected GameGrid gamegrid;

    private FlowLayout layoutManager;

    public LifeGUI(GameGrid parentGrid)
    {
        layoutManager = new FlowLayout();
        gamegrid = parentGrid;
        mainframe = new JFrame("GOGOL");
        mainframe.setVisible(true);
        mainframe.setLayout(layoutManager);
        createButtons();
        addButtons();
        mainframe.getContentPane().setBackground(Color.lightGray);
        mainframe.getContentPane().add(gamegrid);
        mainframe.pack();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layoutManager.setVgap(10);
        layoutManager.setHgap(10);
        gamegrid.setSize(600, 600);
        mainframe.getContentPane().setSize(700,700);
        mainframe.pack();
        //gamegrid.setupGrid();
    }

    private void createButtons()
    {
        stepfoward = new JButton("step foward");
        stepfoward.setSize(60,20);
        stepfoward.setVisible(true);
    }

    private void addButtons()
    {
        mainframe.add(stepfoward);
    }
}
