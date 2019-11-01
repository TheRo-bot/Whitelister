import java.util.*;
import java.io.*;
public class TeamMaker
{
	public static LinkedList teams;

	public static void main(String[] args)
	{
		teams = new LinkedList();


		String[] options = {"Add a new team", "Remove an existing team", "Edit an existing team", "View all current teams", "Import teams from a file"};
		int userChoice;
		boolean exit = false;
		// show a menu
		/* User can do the following
			- add a team
			- remove a team
			- edit a team
			- view teams
		*/

		System.out.println("-- Team Maker --");
		while( !exit )
		{
			if( !teams.isEmpty() )
			{
				options = new String[]{"Add a new team", "Remove an existing team", "Edit an existing team", "View all current teams","Import teams from a file","Export teams to file"};
			}
			else
			{
				options = new String[]{"Add a new team", "Remove an existing team", "Edit an existing team", "View all current teams", "Import teams from a file"};	
			}
			userChoice = UserInput.inputMenu(options,"Please select what you would like to do");
			System.out.println("\n");
			switch(userChoice)
			{
				case 0:
					// exit
					System.out.println("bye!");
					exit = true;
					break;

				case 1:
					// add a team
					addTeam();
					break;

				case 2:
					// remove a team
					removeTeam();
					break;

				case 3:
					// edit a team
					editTeam();
					break;

				case 4:
					// view teams
					viewTeams();
					break;

				case 5:
					// import teams
					importTeams();
					break;

				case 6:
					// export teams (once something has been changed)
					exportTeams();
					break;

				default:
					System.out.println("Sorry, I didn't quite understand that, can you please try again?");

			}
		}
	}

	/*
	SUBMODULE: addTeam
	PURPOSE:
		To add a new team to the teams classfield
	TO DO:
		- Prettification
		- Test
	*/
	public static void addTeam()
	{
		String teamName = "null", pname, pid, captain;
		String[] curr, options = {"Add a player", "Remove a current player", "Edit a current player", "View all players"};
		int userChoice, index;
		boolean exit = false;
		Iterator listIter;
		Team exportTeam;
		// linkedlist will store a String[3] array of [pname, pid, cap]
		LinkedList playerInformation = new LinkedList();

		do
		{
			teamName = UserInput.getString("Please enter the team's name\n(leave empty to cancel)");	
		}
		while(teams.hasEntry(teamName));
		
		System.out.println("\n");
		while( !exit && !teamName.equals(""))
		{
			userChoice = UserInput.inputSelectMenu(options,"What would you like to do?");
			switch(userChoice)
			{
				case 0:
					exportTeam = new Team(teamName);
					exportTeam.setMembersInfo(playerInformation);
					teams.insertLast(exportTeam);
					exit = true;
					break;

				case 1:
					// add a player
					System.out.println("Please enter the following:\n");

					pname = UserInput.getString("Player's name:");
					pid = UserInput.getString("Player's Steam ID");
					if( UserInput.getString("Are they a captain? [y/n]").toLowerCase().equals("y") )
					{
						curr = new String[]{pname,pid,"1"};
						playerInformation.insertLast(curr);
					}
					else
					{

						curr = new String[]{pname,pid,"0"};
						playerInformation.insertLast(curr);
					}
					System.out.println("\nCreated account '" + pname + "', their ID: '" + pid + " ");
					break;

				case 2:
					// remove a player

					index = 0;
					pname = UserInput.getString("What's the player's name?");
					if(! playerInformation.isEmpty())
					{
						listIter = playerInformation.iterator();

						while(listIter.hasNext())
						{
							curr = (String[])listIter.next();
							if( curr[0].equals(pname) )
							{
								playerInformation.removeEntryIndex(index);
								System.out.println("Removed " + pname + " from the player list!\n");
							}
							index++;
						}
					}
					else
					{
						System.out.println("Sorry! This team's player list is empty :(\n");
					}
					// reset index for later use
					
					break;

				case 3:
					// edit a player
					playerInformation = editPlayer(playerInformation);
					
				case 4:
					// show all players
					listIter = playerInformation.iterator();
					
					if( listIter.hasNext())
					{
						System.out.println("All players under " + teamName + " currently:");
						while( listIter.hasNext() )
						{
							curr = (String[])listIter.next();
							System.out.println(curr[0] + " - " + curr[1] + " - " + curr[2]);
						}
					}
					else
					{
						System.out.println("No players are under " + teamName + " currently!");
					}
					break;

				default:
					System.out.println("Sorry, I didn't quite understand that, can you please try again?");
			}

		}
	}



	/*
	SUBMODULE: removeTeam
	PURPOSE:
		To remove an existing team from the teams classfield
	*/
	public static void removeTeam()
	{
		
		Iterator teamList = teams.iterator();
		Team currTeam;
		int index = 0;
		boolean found = false;

		if( teamList.hasNext() )
		{
			String userChoice = UserInput.getString("What team would you like to remove?\n(leave blank if you wish to cancel)");

			while(teamList.hasNext())
			{
				currTeam = (Team)teamList.next();
				if(currTeam.getName().toLowerCase().equals(userChoice.toLowerCase()))
				{

					teams.removeEntryObject(currTeam);
					found = true;
					System.out.println("\nRemoving '" + currTeam.getName() + "' from my list!\n");
				}
				

				index++;
			}
			if(! found)
			{
				System.out.println("\n Sorry! I couldn't find the team you're looking for :(\n");
			}
		}
		else
		{
			System.out.println("\nSorry! I'm not tracking any teams right now!\n");
		}
	}
	


	/*
	SUBMODULE: editTeam
	PURPOSE:
		To edit an existing team's information
	TO DO:
		- Submodule split up?
	*/
	public static void editTeam()
	{
		String input, pname, pid, editTeam;
		String[] curr, currPlayer, options = {"Change team name","Add a player","Edit the players in this team","Show team info"};
		Iterator teamList = teams.iterator(), playerList;
		Team currentTeam;

		int userChoice = -1, index = 0, pstate;
		boolean exit, teamNotFound = true;
		// ask the user what team they would like to edit
		if(teamList.hasNext())
		{
			editTeam = UserInput.getString("What team would you like to edit?");

			while(teamList.hasNext() && teamNotFound)
			{
				currentTeam = (Team)teamList.next();
				// if we've found it
				if( currentTeam.getName().equals(editTeam) )
				{
					teamNotFound = false;
					exit = false;
					while( !exit )
					{
						System.out.println();
						userChoice = UserInput.inputSelectMenu(options,"What would you like to edit?");
						System.out.println(userChoice + "\n");
						switch(userChoice)
						{
							case 0:
								exit = true;
								break;

							case 1:
								// Change team name
								System.out.println("Current team name is: " + currentTeam.getName());
								input = UserInput.getString("Would you like to edit?\n(Leave blank if you wish to cancel)");
								if( !input.equals("") )
								{
									currentTeam.setTeamName(input);
									System.out.println("New team name set!\n");
								}
								break;

							case 2:
								System.out.println("Please enter the following:\n");

								pname = UserInput.getString("Player's name ");
								pid = UserInput.getString("Player's Steam ID");
								pstate = UserInput.getNumber("What's their player state? [0 = sub, 1 = player, 2 = captain]",0,2);

								currentTeam.addPlayer(pname, pid, pstate);
								
								break;


							case 3:
								// Change players
								currentTeam.setMembersInfo(editPlayer(currentTeam.getMembersInfo()));
								
								break;

							case 4:
								System.out.println("Team name: " + currentTeam.getName());
								playerList = currentTeam.getMembersInfo().iterator();
								while(playerList.hasNext())
								{
									currPlayer = (String[])playerList.next();
									System.out.println(currPlayer[0] + " - " + currPlayer[1] + " - " + currPlayer[2]);
								}

								break;
							default:
								System.out.println("Sorry, I didn't quite understand that, can you please try again?\n");
						}
					}
				}


				index++;
			}
			if(teamNotFound)
			{
				System.out.println("Sorry! I couldn't find that team :(\n");
			}
		}
		else
		{
			System.out.println("\nSorry! I'm not tracking any teams right now!\n");
		}
		// find that team

		/* menu:
		- edit team name
		- edit players
		*/
	}



	/*
	SUBMODULE: editPlayer
	IMPORTS: playerInformation (LinkedList)
	EXPORTS: playerInformation (Updated version)
	PURPOSE:
		Helped method for editTeam, edits an individual player's information
	TO DO:
		- Done
	*/
	public static LinkedList editPlayer(LinkedList playerInformation)
	{
		//     v mutating player info v  v used for finding the player
		String newName, newID, pname = UserInput.getString("What's the player's name?");

		//       v current player 							   v used for changing captain state,
		String[] curr, options = {"Name","SteamID","Captain"}, captainLevels = {"Player","Captain","Sub"};
		//             ^ for the info mutator menu
		//  v output of menu
		int userChoice, index = 0, newCPLVL;
		// 				^ used for finding players inside their linked list
		// 		 v to actually have everything xd
		Iterator listIter = playerInformation.iterator();
		//      v exit condition for menu
		boolean exit = false;

		//If there's actually players in here
		if(! playerInformation.isEmpty())
		{
			
			// for every player
			while(listIter.hasNext())
			{
				// (the current player)
				curr = (String[])listIter.next();
				// if this is the player we're looking for
				if( curr[0].equals(pname) )
				{
					// show a menu for what they can edit
					while( !exit )
					{
						// ask what they want
						userChoice = UserInput.inputMenu(options,"What would you like to edit?");
						switch(userChoice)
						{
							case 0:
								// exit condition
								exit = true;
								break;

							case 1:
								// Name

								// tell the user what they're editing
								System.out.println("Player's current name: " + curr[0]);
								newName = UserInput.getString("What do you want to change it to?\n(leave blank if you wish to cancel)");
								// if they actually entered something
								if(!newName.equals("") )
								{	// edit it and update it
									curr[0] = newName;
									playerInformation.editEntry(index, curr);
								}
								break;
								
							case 2:
								// Steam ID
								// tell the user what they're editing
								System.out.println("Player's current ID: " + curr[1]);
								newID = UserInput.getString("What do you want to change it to?\n(leave blank if you wish to cancel)");
								// if they actually entered something 
								if( !newID.equals("") )
								{
									curr[1] = newID;
									playerInformation.editEntry(index, curr);
								}
								break;
							case 3:
								// Captain
								// the only difference compared to the others is the user output

								if( curr[2].equals("1"))
								{
									System.out.println("This player is currently a captain");
								}
								else
								{
									System.out.println("This player is currently a player");
								}

								// this is literally just the same
								newCPLVL = UserInput.inputSelectMenu(captainLevels,"What do you want to change it to?");

								playerInformation.editEntry(index, newCPLVL);

								break;

							default:
								System.out.println("Sorry, I didn't quite understand that, can you please try again?");

						}
					}
				}
				index++;
			}
		}
		else
		{
			System.out.println("Sorry! This team's player list is empty :(\n");
		}
		return playerInformation;
	}



	/*
	SUBMODULE: viewTeams
	PURPOSE:
		to show all teams under the teams classfield
	TO DO:
		- Done
	*/
	public static void viewTeams()
	{
		
		Team currTeam;
		if(! teams.isEmpty())
		{
			System.out.println("\nCurrent teams:\n");
			for( Object o : teams )
			{
				currTeam = (Team)o;

				System.out.println(currTeam.getName());
				
			}	
			System.out.print("\n");
		}
		else
		{
			System.out.println("\nSorry! I'm not tracking any teams right now!\n");
		}
	}


	public static String getTeamDir()
	{
		String separator = System.getProperty("file.separator");
		String dir = System.getProperty("user.dir") + separator + "Team_Files";	
		return dir;
	}



	public static void showTeamFiles()
	{
		showTeamFiles(getTeamDir());
	}

	public static void showTeamFiles(String dir)
	{
		File teamFile = new File(dir);
		System.out.println("Team Files:");
		for( String s : teamFile.list() )
		{
			if( s.charAt(0) != '.' )
			System.out.println( " - " + s);
		}
	}


	/*
	SUBMODULE: importTeams
	PURPOSE:
		To import valid teams from a file to the teams classfield
	TO DO:
		- Done
	*/
	public static void importTeams()
	{
		String userChoice, line, fileName;
		Team currTeam;
		int index = 0;

		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;

		String dir = getTeamDir();

		showTeamFiles(dir);
		System.out.println();
		fileName = UserInput.getFileName("Please enter the file you'd like to import from",dir);
		if(! fileName.equals("") )
		{
			try
			{
				fis = new FileInputStream(fileName);
				isr = new InputStreamReader(fis);
				br = new BufferedReader(isr);

				line = br.readLine();

				while(line != null)
				{
					currTeam = new Team(line);
					teams.insertLast(currTeam);

					line = br.readLine();
					index++;
				}

				System.out.println("Imported " + index + " teams from '" + fileName.substring(dir.length() + 1, fileName.length()) + "'.\n\n");


			}
			catch(IOException yeet)
			{
				System.out.println("Sorry! An unexpected Error occurred, please try again :( +\n 		(" + yeet.getMessage() + ")");
			}
		}
	}



	/*
	SUBMODULE: exportTeams
	PURPOSE:
		To export all teams under the teams classfield to a valid file
		(option to override or add teams to the given file)
	TO DO:
		- Done
	*/
	public static void exportTeams()
	{
		String userChoice, line, fileName;
		FileOutputStream fos;
		PrintWriter pw;

		int index = 0;
		Iterator teamList;
		String exportString = "";

		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;


		String separator = System.getProperty("file.separator");
		String dir = System.getProperty("user.dir") + separator + "Team_Files";
		// obtain the fileName from UserInput
		fileName = UserInput.getFileNameOverwrite("Please enter a filename to output to:",dir);

		if(! fileName.equals("") )
		{
			try
			{
				fos = new FileOutputStream(fileName);
				pw = new PrintWriter(fos);

				teamList = teams.iterator();

				while( teamList.hasNext() )
				{
					exportString += ((Team)teamList.next()).toFileString() + "\n";

				}
				pw.print(exportString);
				pw.close();
			}
			catch(FileNotFoundException yeet)
			{
				System.out.println("Somehow that's not a file, please try again!");
			}
			
		}
		else
		{
			try
			{

				fis = new FileInputStream(fileName);
				isr = new InputStreamReader(fis);
				br = new BufferedReader(isr);

				index = 0;
				line = br.readLine();
				while(line != null)
				{
					exportString += line + "\n";

					line = br.readLine();
					index++;
				}
				System.out.println("Loaded " + index + " Teams from " + fileName);

				teamList = teams.iterator();
				fos = new FileOutputStream(fileName);
				pw = new PrintWriter(fos);

				while( teamList.hasNext() )
				{
					exportString += ((Team)teamList.next()).toString() + "\n";
				}
				pw.print(exportString);
				pw.close();

			}
			catch(FileNotFoundException yeet)
			{}
			catch(IOException yeet)
			{
				System.out.println("Unexpected Error!");
			}
		}

	}


}