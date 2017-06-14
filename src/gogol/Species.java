package gogol;

public class Species 
{
	private String name;
	private String description;
	private boolean pattern[][];

	/**
	 * A Species is a special Pattern on the Grid which has unique behaviours
	 * @param n Name
	 * @param d Description
	 * @param p PatternString
	 */
	public Species(String n, String d, String p)
	{
		name = n;
		description = d;
		
		String lines[] = p.split("\\$");

		pattern = new boolean[lines.length][getLineLength(lines[0])];
		
		for (int i = 0; i < pattern.length; i++) 
		{
			pattern[i] = convertLine(lines[i]);
		}
	}

	/**
	 * returns the horizontal size of the Species
	 * @return sizeX
	 */
	public int getSizeX()
	{
		return pattern[0].length;
	}

	/**
	 * returns the vertical size of the Species
	 * @return sizeY
	 */
	public int getSizeY()
	{
		return pattern.length;
	}

	/**
	 * returns the name of the Species
	 * @return Name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * returns the description
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * pattern as boolean array
	 * @return pattern-array
	 */
	public boolean[][] getPattern()
	{
		return pattern;
	}
	
	private int getLineLength(String line)
	{
		int counter = 0;
		
		for(int i = 0; i < line.length(); i++)
		{
			if (line.charAt(i) < 65)
			{
				counter = counter + (int)(line.charAt(i) - '0');
				i++;
			}
			else
			{
				counter++;
			}
		}
		return counter;
	}
	
	private boolean[] convertLine(String line)
	{
		boolean convertedLine[];
		convertedLine = new boolean[getLineLength(line)];
		
		int prefix = 1;
		int arrayIndex = 0;
		
		for(int i = 0; i < line.length(); i++)
		{
			if (line.charAt(i) == 'o')
			{
				for(int j = 0; j <prefix; j++)
				{
					convertedLine[arrayIndex] = true;
					arrayIndex++;
				}
				prefix = 1;
			}
			else if(line.charAt(i) == 'b')
			{
				arrayIndex = arrayIndex + prefix;
				prefix = 1;
			}
			else
			{
				prefix = line.charAt(i) - '0';
			}
		}
		
		return convertedLine;
	}
	
}
