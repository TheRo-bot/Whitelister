public class SteamID
{

	private String id3;
	private int[] steamID;

	public SteamID(String inID3)
	{
		steamID = null;
		setID3(inID3);
	}

	public SteamID(int steamIDp1, int steamIDp2)
	{
		id3 = null;
		setSteamID(steamIDp1, steamIDp2);

	}



	public String toString()
	{
		String exportString = "";
		if( steamID == null)
		{
			// nothing has been set (somehow)
			if( id3.equals(null) )
			{
				exportString = "<none>";
			}
			// only id3 exists
			else
			{
				exportString = id3;
			}


		}
		// only a steamID exists
		else if( id3.equals(null))
		{
			exportString = "STEAM_1:" + steamID[0] + ":" + steamID[1];
		}


		return exportString;

	}


	public void setID3(String inString)
	{
		if( inString.charAt(0) == '[' && inString.charAt(inString.length() - 1) == ']')
		{
			inString = inString.substring(1,inString.length() - 2);
			String[] splitString = inString.split(":");
		}
	}

	public void setSteamID(int partOne, int partTwo)
	{
		if( partOne == 1 || partOne == 2 )
		{
			if( partTwo >= 0)
			{
				steamID = new int[]{partOne, partTwo};
			}
		}
	}

}