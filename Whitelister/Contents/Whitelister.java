import java.io.*;
import java.util.*;

public class Whitelister
{

    private String fileName;
    // might refactor teams to be a LinkedList at some time
    private LinkedList teams;

    
    /*
    CONSTRUCTOR: Alternate
    IMPORTS: inFileName (String)
    */
    public Whitelister(String inFileName)
    {
        // set fileName
        fileName = inFileName;
        int nTeams = -1;
        
        teams = new LinkedList();

        // Attempt to import from file
        try
        {   // mutate teams
            importFromFile();
        }
        catch(IllegalArgumentException yeet)
        {
            throw new IllegalArgumentException(yeet.getMessage());
        }
        // set the rest of the classfields up
    }



    public Iterator getTeams()
    {   return teams.iterator();   }


    /*
    ACCESSOR: getNumberOfTeams
    EXPORTS: exportInt (guess xd)
    PURPOSE:
        Reads the first line of the given fileName, this *should* be the number of teams
    */
    public int getNumberOfTeams()
    {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        int exportInt;

        try
        {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            exportInt = Integer.parseInt(br.readLine());
            fis.close();
        }
        // catch that yeeted boy!
        catch(IOException yeet)
        {
            throw new IllegalArgumentException("Unexpected error. " + yeet.getMessage());
        }

        return exportInt;
    }


    /*
    MUTATOR: importFromFile
    PURPOSE:
        Does what you think it does
        it imports all it's information from a file
    */
    public void importFromFile()
    {
        // set up a buffered reader to read one line at a time
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        // make sure to declare at the beginning of the code!

        String inLine;
        Team currTeam;
        int index = 0;
        
        try
        {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            // read the first line
            inLine = br.readLine();

            while( inLine != null)
            {
                if( !inLine.substring(0,1).equals("//") )
                {
                    // make a new Team
                    currTeam = new Team();
                    // make it create itself from this line
                    currTeam.readString(inLine);
                    // add it to the Team Array
                    addTeam(currTeam);
                }
                // start over
                inLine = br.readLine();
            }
            // wow so cool!!! :ooo sugoi!
        }
        // catch one yeeted boy
        catch(IOException yeet)
        {
            throw new IllegalArgumentException("Unexcepted Error in reading from file. " + yeet.getMessage());
        }
        // catch the other yeeted boyyy
        catch(IllegalArgumentException yeet)
        {
            throw new IllegalArgumentException("Error in mass file import. " + yeet.getMessage());
        }

        
    }

    /*
    ACCESSOR: getTeamCount
    EXPORTS: nTeams (Integer)
    PURPOSE:
        goes through the teams and counts how many teams there are
    */
    public int getTeamCount()
    {
        Iterator teamIter = getTeams();
        int nTeams = 0;

        while(teamIter.hasNext())
        {
            nTeams++;
            teamIter.next();
        }


        return nTeams;

    }


    /*
    ACCESSOR: getTeam
    EXPORTS: chosenTeam (Team)
    PURPOSE:
        goes through an iterator of teams to find the chosen team
    */
    public Team getTeam(int requestTeam)
    {
        Team chosenTeam = null, currTeam;
        Iterator teamList = getTeams();
        int index = 0, teamCount = getTeamCount();
        if( 0 <= requestTeam && requestTeam < teamCount)
        {
            while(teamList.hasNext() && chosenTeam == null && index < teamCount)
            {
                currTeam = (Team)teamList.next();
                if( index == requestTeam)
                {
                    chosenTeam = currTeam;
                }
                index++;
            }
        }
        else
        {
            throw new IllegalArgumentException("requested Team is out of bounds!");
        }
        return chosenTeam;
        } 


    // you can assume this one
    public String getFileName()
    {   return fileName;   }



    /*
    MUTATOR: addTeam
    IMPROTS: newTeam (Team)
    MUTATES: teams
    PURPOSE:
        adds newTeam to the teams array
    */
    public void addTeam(Team newTeam)
    {
        teams.insertLast( newTeam );
    }



    /*
    MUTATOR: delTeam
    IMPORTS: chosenTeam (Team)
    MUTATES: teams
    PURPOSE:
        removes chosenTeam from the linkedlist
    */
    public void delTeam(Team chosenTeam)
    {
        Iterator teamsList = getTeams();
        Team currTeam;
        int index = 0;
        try
        {
            teams.removeEntryObject(chosenTeam);    
        }
        catch(NullPointerException whoops)
        {
            System.out.println("No teams!");
        }
        

    }

    /*
    MUTATOR: editTeam
    IMPORTS: editTeam (Team), newName (String), newPlayers (LinkedList)
    MUTATES: teams
    */
    public void editTeam(Team toEdit, String newName, LinkedList newPlayers)
    {
        toEdit.setTeamName(newName);
        toEdit.setPlayerList(newPlayers);
    }


    /*
    SUBMODULE: makeWhitelist
    IMPORTS: teamList (Team Array)
    PURPOSE:
        The main function of this class.
        Will output the inputted teams to a file named "whitelist.txt"
        this format is for the whitelist file for servers
    */
    public void makeWhitelist(Team[] teamList)
    {
        FileOutputStream fos;
        PrintWriter pw;
        String fileName = "whitelist.txt", exportString = "";
        Iterator thisTeam;
        String[] thisPlayer;
        int index = 0;
        try
        {
            fos = new FileOutputStream(fileName);
            pw = new PrintWriter(fos);
            // for every team
            for( int ii = 0; ii < teamList.length; ii++)
            {
                // add a title line
                if( teamList[ii] != null)
                {
                    exportString += "; ------- TEAM: '" + teamList[ii].getName() + "'";

                    thisTeam = teamList[ii].getMembersInfo().iterator();
                    
                    index = 0;
                    // while this team has players
                    while( thisTeam.hasNext())
                    {
                        thisPlayer = (String[])thisTeam.next();
                        // export a captain tag if they're a captain, otherwise a player tag
                        if( thisPlayer[2].equals("true"))
                        {
                            exportString += "\n" + thisPlayer[1] + "; " + thisPlayer[0] + " - Captain";

                        }
                        else
                        {
                            exportString += "\n" + thisPlayer[1] + "; " + thisPlayer[0] + " - Player";
                        }
                        // add currPlayer name
                        index += 1;
                    }
                    exportString += "\n\n";
                }
            }
            // output this string to the file
            pw.print(exportString);
            // close the file
            pw.close();
        }
        catch(IOException yeet)
        {
            throw new UnknownError("Unexpected error in reading from file whitelist.txt: " + yeet.getMessage());
        }
    }
    
}


