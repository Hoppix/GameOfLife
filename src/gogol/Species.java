package gogol;

public class Species 
{
	private String name;
	private String description;
	private boolean pattern[][];
	
	public Species(String n, String d, String p)
	{
		name = n;
		description = d;
		
		String lines[] = p.split("$");
		pattern = new boolean[lines.length][getLineLength(lines[0])];
		
		for (int i = 0; i < pattern.length; i++) 
		{
			pattern[i] = convertLine(lines[i]);
		}
	}
	
	public int getSizeX()
	{
		return pattern[0].length;
	}
	
	public int getSizeY()
	{
		return pattern.length;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
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
