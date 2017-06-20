package gogol;

import java.awt.Color;

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

		Color colorOwn = ((ColoredCell) controller.survivalMatrix[(posY)][posX])
				.getColorStatus();
		Color colorEn = null;


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
					Color colorSelect = ((ColoredCell) controller.survivalMatrix[(y + matrixY) % matrixY][(x +
							matrixX) % matrixX]).getColorStatus();
					if (colorSelect.equals(colorOwn))
					{
						countOwn++;
					}
					else
					{
						colorEn = colorSelect;
						countEn++;
					}

				}
			}
		}

		if (countOwn >= countEn)
		{
			return colorOwn;
		}
		else
		{
			return colorEn;
		}
	}
}
