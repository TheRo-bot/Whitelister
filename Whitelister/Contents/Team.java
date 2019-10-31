import java.io.*;
import java.util.*;

 /*
    ----------------------------------
    CLASS: Team
    CONTAINS: teamName (String), players (LinkedList)
    PURPOSE:
        To store all Player objects. Can read/write from file input/output and serialization
    */
    public class Team
    {
        

        /*
        Continue creation of class Team 
        --------------------------------------
        */

        private String teamName;
        private LinkedList players;


        /*
        CONSTRUCTOR: Default
        */
        public Team()
        {
            setTeamName("null");
            players = new LinkedList();
        }



        /*
        CONSTRUCTOR: Alternate
        IMPORTS; inTeamName (String), inPLayerList (LinkedList)
        */
        public Team(String inTeamName, LinkedList inPlayerList)
        {
            setTeamName(inTeamName);
            setPlayerList(inPlayerList);
        }

        /*
        CONSTRUCTOR: Alternate
        IMPORTS: inputString (String)
        PURPOSE:
            A secondary alternate constructor for creating a team object from a formatted line
        */
        public Team(String inputString)
        {
            players = new LinkedList();
            try
            {
                readString(inputString);
            }
            catch(IllegalArgumentException yeet)
            {
                throw new IllegalArgumentException("Invalid imports for Team. " + yeet.getMessage());
            }
        }

        /*
        ACCESSOR: getName
        EXPORTS: teamName (classfield)
        */
        public String getName()
        {   return teamName;   }

        /*
        ACCESSOR: getTeamMembers
        EXPORTS: exported Iterator
        PURPOSE:
            super useful iterator accessor so it doesn't get confusing
        */
        public Iterator getTeamMembers()
        {   return players.iterator();   }


        /*
        ACCESSOR: getPlayerList
        EXPORTS: players
        PURPOSE:
            to obtain the actual linked list to do operations on it externally
        */
        public LinkedList getPlayerList()
        {   return players;   }


        /*
        MUTATOR: setPlayerList
        IMPORTS: inPlayers (LinkedList)
        MUTATES: players
        */
        public void setPlayerList(LinkedList inPlayers)
        {   players = inPlayers;   }
        /*
        MUTATOR: setTeamName
        IMPORTS: inTeamName (String)
        MUTATES: teamName
        */
        public void setTeamName(String inTeamName)
        {   teamName = inTeamName;   }


        /*
        ACCESSOR: getMembersInfo
        EXPORTS: exportList (LinkedList)
        PURPOSE:
            To convert Player info into something more usable outside this class
            Turns into a 3 long Array of [name, id, isCaptain]
        */
        public LinkedList getMembersInfo()
        {

            // will return a linked list of String arrays [name, id, captain]

            Iterator playerInfo = getTeamMembers();
            LinkedList exportList = new LinkedList();
            Player currPlayer;
            String[] currString;

            // go through every player and make the three length string
            while(playerInfo.hasNext())
            {
                currString = new String[3];
                currPlayer = (Player)playerInfo.next();

                currString[0] = currPlayer.getPlayerName();
                currString[1] = currPlayer.getSteamID();
                currString[2] = String.valueOf(currPlayer.isCaptain());

  
                exportList.insertLast( currString );


            }
            // yeET IT
            return exportList;

        }

        public void setMembersInfo(LinkedList importList)
        {
            Iterator playerInfo = importList.iterator();
            String[] currPlayer;
            LinkedList newList = new LinkedList();
            Player exportPlayer;

            // for all the importList players
            while(playerInfo.hasNext())
            {
                exportPlayer = null;
                // make a Player for them
                currPlayer = (String[])playerInfo.next();
                if( currPlayer.length == 3)
                {
                    exportPlayer = new Player(currPlayer[0],currPlayer[1], currPlayer[2].equals("1"));
                }
                else if( currPlayer.length == 2)
                {
                    exportPlayer = new Player(currPlayer[0],currPlayer[1],false);
                }
                newList.insertLast(exportPlayer);
            }

            setPlayerList(newList);
        }



        /*
        MUTATOR: addPlayer
        IMPORTS: newPlayerName (String), newPlayerID (String), isCaptain (boolean)
        MUTATES: players
        PURPOSE:
            Adds a new Player object to the players LinkedList
        */
        public void addPlayer(String newPlayerName, String newPlayerID, int playerState)
        {

            try
            {
                players.insertLast(new Player(newPlayerName,newPlayerID, playerState));
            }
            catch(IllegalArgumentException woops)
            {
                throw new IllegalArgumentException("Invalid Member Information. " + woops.getMessage());
            }
        } 



        /*
        MUTATOR: setTeam
        IMPORTS: playerNames (String Array), steamIDs (String Array), isCaptain (int)
        MUTATES: players
        PURPOSE
            To easily set the entire team up. Mainly used for file input
        */
        public void setTeam(String[] playerNames, String[] steamIDs, int isCaptain)
        {

            LinkedList newPlayers = new LinkedList();

            if( playerNames.length != steamIDs.length)
            {
                throw new IllegalArgumentException("Too many of Player names / IDs!");
            }

            for( int ii = 0; ii < playerNames.length; ii++)
            {
                // insert all the new players into the linkedlist
                newPlayers.insertLast(new Player( playerNames[ii], steamIDs[ii], (isCaptain - 1 == ii) ) );
            }

            players = newPlayers;
        }


        /*
        ACCESSOR: equals
        IMPORTS: inObject (Object)
        EXPORTS: isEqual
        */
        public boolean equals(Object inObject)
        {
            Team inTeam = null;
            boolean isEqual = false;
            if( inObject instanceof Team )
            {
                inTeam = (Team)inObject;
                Iterator playerList = getTeamMembers();
                Iterator inPlayerList = inTeam.getTeamMembers();
                if( inTeam.getName().equals(getName()) )
                {
                    boolean equalPlayers = true;

                    while(playerList.hasNext() && equalPlayers)
                    {
                        // if player (x) != inPlayer (x)
                        if(!((Player)playerList.next()).equals( (Player)inPlayerList.next() ) )
                        {
                            equalPlayers = false;
                        }
                    }
                    if( equalPlayers)
                    {
                        isEqual = true;
                    }
                }
            }
            return isEqual;
        }




        /*
        ACCESSOR: toString
        EXPORTS: exportString
        */
        public String toFileString()
        {
            /* exporting format goes like:
            team / pname - pid - pcap / pname - pid - pcap / etc.
            */

            // this is all self explanatory really
            String exportString = "";
            Iterator iter = players.iterator();

            // add the team name to the first part of the String
            exportString += getName();
            Player currPlayer;
            // add each player and their information in the format "name - id"
            while(iter.hasNext())
            {
                currPlayer = (Player)iter.next();
                
                exportString += " / " + currPlayer.toString();
            }

            return exportString;
        }


        /*
        SUBMODULE: toFile
        IMPORTS: fileName (String)
        PURPOSE:
            To export the current Team object to file
        */
        public void toFile(String fileName)
        {
            // create necessary print writer setup
            FileOutputStream fos;
            PrintWriter pw;
            
            try
            {
                fos = new FileOutputStream(fileName);
                pw = new PrintWriter(fos);
                // call toString because we already managed formatting
                pw.print(toString());


                fos.close();
            }  
            // handle that yeeted exception!
            catch(IOException yeet)
            {
                try
                {
                    throw new IOException("Exception in file IO. " + yeet.getMessage());
                }
                catch(IOException skeet) {}
            }

        }

        /*
        MUTATOR: readFile
        IMPORTS: fileName (String)
        PURPOSE:
            To import file information to a team object
            refer to toString for importing format
        */
        public void readFile(String fileName)
        {
            // set up a buffered reader
            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader br;

            String[] importString;
            try
            {
                fis = new FileInputStream(fileName);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);

                readString(br.readLine());


            }
            // catch that yeeted dude!
            catch(IOException yeet)
            {
                throw new UnknownError("Unexpected IO Exception. " + yeet.getMessage());
            }
        }



        /*
        MUTATOR: readString
        IMPORTS: importString (Try guess this one ;) )
        PURPOSE:
            to mutate classfields based off of an imported string
        */
        public void readString(String importString)
        {
            /* String format looks like
            teamname / pname - pid - captain / pname - pid - captain / etc.
            */
            // break up the string by it's main components
            String[] brokenString = importString.split(" / ");

            // create a string array for all the player based components
            String[] playerString;
            // ii = 1 because we're ignoring the first element (teamname)
            // for each player, essentially
            for( int ii = 1; ii < brokenString.length; ii++)
            {
                // split the player components up
                playerString = brokenString[ii].split(" - ");
                // if there's 3 elements (most likely a captain)
                // (this is mainly done to avoid a NPE, as if there's only two elements, you can't check if there's a third (duh))
                if( playerString.length == 3)
                {
                    // confirm that the third entry is "1" (indicating they're a captain)
                    try
                    {   

                        addPlayer(playerString[0], playerString[1], (int)playerString[2].charAt(0));
                    }
                    catch(NumberFormatException whoops)
                    {
                        throw new IllegalArgumentException("Invalid number '" +  playerString[2] + "'!");
                    }
                }
                else if( playerString.length == 2)
                {
                    // otherwise just pass through a false
                    addPlayer(playerString[0], playerString[1], 1);
                }
                else
                {   // we throw if the user entered >3 or <2 arguments, because that don't make sense
                    throw new IllegalArgumentException("importString is invalid!");
                }
            }
            // set the team name if everything's gone well
            setTeamName(brokenString[0]);

        }

    }