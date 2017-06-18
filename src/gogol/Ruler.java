package gogol;

import java.awt.Color;

public class Ruler 
{
	Controller cont;
	public Ruler(Controller parentController)
	{
		cont = parentController;
	}
	
	/**
	 * count alive Neighbors of a cell with the Conway rules
	 */
	protected int conwayRulez(int posX, int posY)
	{
		int count = 0;
		int matrixX = cont.survivalMatrix[0].length;
		int matrixY = cont.survivalMatrix.length;

		for (int y = posY - 1; y <= posY + 1; y++)
		{
			for (int x = posX - 1; x <= posX + 1; x++)
			{
				if (cont.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].getStatus())
				{
					count++;
				}
			}
		}

		count = count - (cont.survivalMatrix[posY][posX].getStatus() ? 1 : 0);

		return count;
	}
	
	protected Color colorMerging(int posX, int posY)
	{	
		int count = 0;
		
		int colorR = 0;
		int colorG = 0;
		int colorB = 0;
		
		int matrixX = cont.survivalMatrix[0].length;
		int matrixY = cont.survivalMatrix.length;

		for (int y = posY - 1; y <= posY + 1; y++)
		{
			for (int x = posX - 1; x <= posX + 1; x++)
			{
				if (cont.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].getStatus())
				{
					Color colorRGB = ((ColoredCell)cont.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX]).getColorStatus();
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
	
	protected int[] colorWarRules(int posX, int posY)
	{
		int response[] = new int[4];
		
		//TODO: impl
		
		return response;
	}
}
