package gogol;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

		Map<Integer, Color> colorHash = new HashMap<>();
		Color colorEn = null;
		Color colorSelect = new Color(0, 0, 0);


		int countOwn = 0;
		int countEn = 0;

		int matrixX = controller.survivalMatrix[0].length;
		int matrixY = controller.survivalMatrix.length;


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
						colorHash.put(colorSelect.hashCode(), colorSelect);
					}
				}
			}
		}

		int cr = 0;
		int cg = 0;
		int cb = 0;

		// Iterating over values only
		for (Color value : colorHash.values())
		{
			if(value.equals(Color.red))
			{
				cr++;
			}
			else if(value.equals(Color.green))
			{
				cg++;
			}
			else if(value.equals(Color.blue))
			{
				cb++;
			}
		}

		if(cr == Math.max(cr, Math.max(cg,cb)))
		{
			return Color.red;
		}
		else if(cg == Math.max(cr, Math.max(cg,cb)))
		{
			return Color.green;
		}
		else if(cb == Math.max(cr, Math.max(cg,cb)))
		{
			return Color.blue;
		}
		else
		{
			return ((ColoredCell) controller.survivalMatrix[posY][posX]).getColorStatus();
		}
	}
}
