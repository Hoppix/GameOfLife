package gogol.backend;

import gogol.backend.Controller;
import gogol.cells.ColoredCell;

import java.awt.Color;
import java.util.*;

public class Ruler
{
	Controller controller;

	public Ruler(Controller parentController)
	{
		controller = parentController;
	}

	/**
	 * count alive Neighbors of a cell with the Conway rules
	 */
	public int conwayRulez(int posX, int posY)
	{
		int count = 0;
		int matrixX = controller.survivalMatrix[0].length;
		int matrixY = controller.survivalMatrix.length;

		for (int y = posY - 1; y <= posY + 1; y++)
		{
			for (int x = posX - 1; x <= posX + 1; x++)
			{
				if (controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].getStatus())
				{
					count++;
				}
			}
		}

		count = count - (controller.survivalMatrix[posY][posX].getStatus() ? 1 : 0);

		return count;
	}

	public Color colorMerging(int posX, int posY)
	{
		int count = 0;

		int colorR = 0;
		int colorG = 0;
		int colorB = 0;

		int matrixX = controller.survivalMatrix[0].length;
		int matrixY = controller.survivalMatrix.length;

		for (int y = posY - 1; y <= posY + 1; y++)
		{
			for (int x = posX - 1; x <= posX + 1; x++)
			{
				if (controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].getStatus())
				{
					Color colorRGB = ((ColoredCell) controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX]).getColorStatus();
					colorR = colorR + colorRGB.getRed();
					colorG = colorG + colorRGB.getGreen();
					colorB = colorB + colorRGB.getBlue();
					count++;
				}
			}
		}

		if (count == 0)
		{
			return null;
		}
		colorR = colorR / count;
		colorG = colorG / count;
		colorB = colorB / count;

		return new Color(colorR, colorG, colorB);
	}

	/**
	 * returns major Color of the Cells neighbors
	 * if two top Colors are equally numbers returns previous Color of the specified Cell
	 * new Cells are only born if there is a majority
	 *
	 * @param posX
	 * @param posY
	 * @return New Color of the Cell at input Position
	 */
	public Color colorWarRules(int posX, int posY)
	{
		List<Color> colorList = new LinkedList<>();
		Color colorSelect;
		Color colorOwn;

		int matrixX = controller.survivalMatrix[0].length;
		int matrixY = controller.survivalMatrix.length;

		/**
		 * cell alive in next cycle?
		 */
		boolean alive = controller.survivalMatrix[posY][posX].getNextStatus();

		/**
		 * own color counts into calculation
		 */
		colorOwn = ((ColoredCell) controller.survivalMatrix[posY][posX]).getColorStatus();


		/**
		 * Get all the surounding colors
		 * and add them to the list
		 */
		for (int y = posY - 1; y <= posY + 1; y++)
		{
			for (int x = posX - 1; x <= posX + 1; x++)
			{
				if (controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].getStatus())
				{
					colorSelect = ((ColoredCell) controller.survivalMatrix[(y + matrixY) % matrixY][(x +
							matrixX) % matrixX]).getColorStatus();

					if (colorSelect != null)
					{
						colorList.add(colorSelect);
					}
				}
			}
		}

		/**
		 * return color of the highest count
		 * when cell is dead we dont set a new color
		 */
		if (alive)
		{
			return countFreqColor(colorList, colorOwn);
		}
		return null;
	}


	/**
	 * helper method
	 *
	 * @param list
	 * @param own
	 * @return freq color
	 */
	private Color countFreqColor(List<Color> list, Color own)
	{
		if (list.size() == 0)
		{
			return null;
		}

		int countR = 0;
		int countG = 0;
		int countB = 0;

		for (Color color : list)
		{
			if (color.equals(Color.red))
			{
				countR++;
			}
			else if (color.equals(Color.green))
			{
				countG++;
			}
			else if (color.equals(Color.blue))
			{
				countB++;
			}
			else
			{
				/**
				 * dead end
				 */
			}
		}

		int maxValue = Integer.max(countR, Integer.max(countG, countB)); //fixed: inner max(b, b)
		
		//fixed: check if equal values are max
		if (countR == countB && countR == maxValue)
		{
			return own;
		}
		if (countB == countG && countB == maxValue)
		{
			return own;
		}
		if (countR == countG && countR ==maxValue)
		{
			return own;
		}

		if (countR == maxValue)
		{
			return Color.red;
		}
		else if (countB == maxValue)
		{
			return Color.blue;
		}
		else if (countG == maxValue)
		{
			return Color.green;
		}
		
		//should never be reached!
		System.out.println("I failed");
		return null;
	}

}
