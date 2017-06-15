package gogol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		library.put("Block", new Species("Block", "very edgy", "2o$2o", 2, 2));
		library.put("Glider", new Species("Glider", "moving", "bob$2bo$3o", 3, 3));
		library.put("Blinker", new Species("Blinker", "epilepsy inc", "o$o$o", 1, 3));
		library.put("Car", new Species("Car", "Vroom!", "bo6b$o5bob$o5bob$5obob2$8b$4b2o2b$2bo4bo$bo6b$bo5bo$b6o", 8, 10)); //8b is not from original code
		library.put("Seal", new Species("Seal", "A cute giant seal", "2b2o30b$b3ob3o26b$3bo3b2o25b$2ob2o2bo2bobo21b$o6bob4o21b$6b2obo2bo21b$o4b2o3bob2o20b$2o8bobo2bo18b$o2b5ob3o4bo17b$bo9bo4bo17b$b2obo2bo3bo3bo18b$2bo9b2o20b$9b2o2b2o4bo14b$2bo3b2obo4b3o2bo14b$3b2obob5o5bob2o12b$7b2ob2ob2ob2o2b3o11b$17bobo4bo9b$8b2o4bobo3b6o8b$13bo7b2o11b$13bo3bo16b$13bo4bo3b3o6bo2b$14b5o3b2ob2o2b2ob2o$17b2obo2bob2o2bo4b$16b2o5bo4bo2b3o$15b3obobobobo8b$22bo2bo8b$18bobo13b$19bo14b2$34b$21b3o10b$21b3o10b2$34b$20b2o12b$20b2o12b$21bo", 34 ,35));
		library.put("Meth", new Species("Meth", "a methuselah", "o10bo$b4o6bo$2b2o7bo$10bob$8bobo", 12 , 5));
		//library.put("Mirrorshield", new Species("Mirrorshield", "Activate when an opponentMonster is Attacking. Destoy all opponent Monsters in Attack position", "33bob$32bo2b$32b3o6$26bo8b$24b2o9b$25b2o8b9$7b2obo2bob2o18b$2o4bo2bo4bo2bo4b2o11b$2o5bobo4bobo5b2o11b$8bo6bo19b6$8bo6bo19b$2o5bobo4bobo5b2o11b$2o4bo2bo4bo2bo4b2o11b$7b2obo2bob2o", 35, 32));
		//library.put("Butterfly", new Species("Butterfly", "Fabulous horizontal spaceship", "5o13b$o4bo7b2o3b$o11b2ob3o$bo9b2ob4o$3b2o3b2ob2o2b2ob$5bo4bo2bo4b$6bobobobo5b$7bo10b$7bo10b$6bobobobo5b$5bo4bo2bo4b$3b2o3b2ob2o2b2ob$bo9b2ob4o$o11b2ob3o$o4bo7b2o3b$5o", 18, 16));
		//library.put("Spacefiller", new Species("Spacefiller", "fills space (NO SHIT!)", "5bobo49bobo5b$4bo2bo49bo2bo4b$3b2o55b2o3b$2bo59bo2b$b4o55b4ob$o4bo53bo4bo$o2bo24b3o3b3o24bo2bo$o2bo24bo2bobo2bo24bo2bo$bo26bo7bo26bob$2b4obo20bo7bo20bob4o2b$3bo3bo21bobobobo21bo3bo3b$4bo24bobobobo24bo4b$4bobo23bo3bo23bobo4b$29b3ob3o29b$3b3o26bo26b3o3b$3b2o23b9o23b2o3b$3b3o21bo9bo21b3o3b$26b13o26b$4bobo18bo13bo18bobo4b$4bo19b17o19bo4b$3bo3bo15bo17bo15bo3bo3b$2b4obo14b21o14bob4o2b$bo19bo21bo19bob$o2bo16b25o16bo2bo$o2bo15bo25bo15bo2bo$o4bo12b29o12bo4bo$b4o12bo29bo12b4ob$2bo13b33o13bo2b$3b2o10bo33bo10b2o3b$4bo2bobo4b37o4bobo2bo4b$5bobo2bo2bo37bo2bo2bobo5b$8bo3b20ob20o3bo8b$9bo21bobo21bo9b$10b21o3b21o10b2$8b21o3bo3b21o8b$7bo21bobobobo21bo7b$6bo3b2o2bob2ob2ob2ob5obobob5ob2ob2ob2obo2b2o3bo6b$6bo4bobo2b2o4b2o7bobo7b2o4b2o2bobo4bo6b$6bo8bo5bo5b3obobob3o5bo5bo8bo6b$7b3o20b2ob2o20b3o7b$9bo17b2o3bo3b2o17bo9b$6b2o2bo3b2o11b4obob4o11b2o3bo2b2o6b$4bo5b2obo17bobo17bob2o5bo4b$3bo6bo18bobobobo18bo6bo3b$3bo4b2obo2bo10b2o2b3ob3o2b2o10bo2bob2o4bo3b$3b5o3b2o5bo6b2o2b2o3b2o2b2o6bo5b2o3b5o3b$13b2o4bo25bo4b2o13b$10b2o3b2ob2obo7b3ob3o7bob2ob2o3b2o10b$13bo4bobo23bobo4bo13b$9bo4b5obo4bo3b3ob3o3bo4bob5o4bo9b$8b2o2b2o4bo3b3o3bo7bo3b3o3bo4b2o2b2o8b$9b2o3bo4b2o7b4ob4o7b2o4bo3b2o9b$10b2o8bo10bobo10bo8b2o10b$30b2ob2o30b$22bo3bob2obobob2obo3bo22b$21bobobobob2o3b2obobobobo21b$21bobobobo9bobobobo21b$22bobob3o7b3obobo22b$24bobo3b5o3bobo24b$24b2o6bo6b2o24b2$27bo2bobobo2bo27b$27b11o27b2$29bo5bo29b$28bobo3bobo28b$28bob2ob2obo28b$26bobob2ob2obob2o25b$25bobo9bobo25b$25bo2b3o3b3o28b$26b2o37b$29bo2bo2b2o28b$26b3obobobo2bo27b$26bo3bobobob2o27b$27bobo4bo30b$28b2obo2bob2o27b$30bobobobo28b$30bobobobo28b$31b2ob2o",48 ,48));
		//library.put("Shipmaker", new Species("Shipmaker", "Makes Ships while burning", "39b2o$38bobo$37bo3b$36bo4b$35bo5b$34bo6b$33bo7b$32bo8b$31bo9b$30bo10b$29bo11b$28bo12b$27bo13b$26bo14b$25bo15b$24bo16b$23bo17b$22bo18b$21bo19b$20bo20b$19bo21b$18bo22b$17bo23b$16bo24b$15bo25b$14bo26b$13bo27b$12bo28b$11bo29b$10bo30b$9bo31b$8bo32b$7bo33b$6bo34b$6o35b$5bo35b$5bo35b$5bo35b$5bo35b$5bo", 41, 40));
		//library.put("Glidergun", new Species("Glidergun", "GG", "24bo11b$22bobo11b$12b2o6b2o12b2o$11bo3bo4b2o12b2o$2o8bo5bo3b2o14b$2o8bo3bob2o4bobo11b$10bo5bo7bo11b$11bo3bo20b$12b2o", 36, 9));
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

		String previous = "initial";
		
		for(int i = 0; i < 12; i++)
		{
			System.out.println("iteration: " + i);
			patternNames = scrapeNames(baseUrl + "library/" + categories[i]);
			for (Iterator iterator = patternNames.iterator(); iterator.hasNext();) 
			{
				String string = (String) iterator.next();
				Species species = scrapePattern(baseUrl + "pattern/" + string);
				library.put(species.getName(), species);
				if(string.contains(previous))
				{
					break;
				}
				if(library.size() > 100)
				{
					//temporary breakout
					break;
				}
				previous = (String) iterator.next();
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
		String sizeXY = "";
		String[] dimensions;
		
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
		
		pattern = Pattern.compile("\"size\": \"(.*?)\"};");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			sizeXY = matcher.group(1);
		}
		
		dimensions = sizeXY.split("x");
		species = new Species(name, description, preset, Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
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
