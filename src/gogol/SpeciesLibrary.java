package gogol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;

public class SpeciesLibrary 
{
	protected HashMap<String, Species> library;

	/**
	 * Library which holds all the available Species.
	 */
	public SpeciesLibrary()
	{
		library = new HashMap<String, Species>();
		//setupLib();
		dummySetup();
		System.out.println("Lib size: " + library.size());
	}
	
	public Species getSpecies(String name)
	{
		return library.get(name);
	}
	
	private void dummySetup()
	{
		//dummies
		library.put("Test_Block", new Species("Test_Block", "very edgy", "2o$2o"));
		library.put("Test_Glider", new Species("Test_Glider", "moving", "bob$2bo$3o"));
		library.put("Test_Blinker", new Species("Test_Blinker", "epilepsy inc", "o$o$o"));
	}
	
	private void setupLib()
	{		
		//webscraping
		String baseUrl = "http://conwaylife.appspot.com/";
		String categories[] = new String[12];
		categories[0] = "abc";
		categories[1] = "def";
		categories[2] = "ghi";
		categories[3] = "jkl";
		categories[4] = "mno";
		categories[5] = "pqr";
		categories[6] = "stu";
		categories[7] = "vwx";
		categories[8] = "yz";
		categories[9] = "123";
		categories[10] = "456";
		categories[11] = "789";
		
		List<String> patternNames = new ArrayList<>();
		
		for(int i = 0; i < 12; i++)
		{
			System.out.println("iteration: " + i);
			patternNames = scrapeNames(baseUrl + "library/" + categories[i]);
			for (Iterator iterator = patternNames.iterator(); iterator.hasNext();) 
			{
				String string = (String) iterator.next();
				Species species = scrapePattern(baseUrl + "pattern/" + string);
				library.put(species.getName(), species);
			}
		}	
	}
	
	private Species scrapePattern(String patternName)
	{
		System.out.println("Scraping " + patternName);
		Species species;
		String name = "";
		String description = "";
		String preset = "";
		
		String source = getSource(patternName);
		
		Pattern pattern = Pattern.compile("<title>The Game of Life | (.*?)</title>");
		Matcher matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		pattern = Pattern.compile("content=\"(.*?)\">");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		pattern = Pattern.compile("\"data\": \"(.*?)!\"");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		species = new Species(name, description, preset);
		return species;		
	}
	
	private List<String> scrapeNames(String category)
	{
		String source = getSource(category);
		List<String> nameList = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("\"/pattern/(.*?)\"");
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
		{
			nameList.add(matcher.group(1));
		}
		
		return nameList;
	}
	
	private String getSource(String inputUrl)
	{
		URL url;
		String source = null;
		
		try 
		{
			url = new URL(inputUrl);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
			  response.append(inputLine + "\n");
			}

			in.close();
			
			source = response.toString();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source;
	}

}
