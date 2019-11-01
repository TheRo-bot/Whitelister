 /*
AUTHOR: Robot 
DATE: 31st October, 2019s
PURPOSE:
    To store all the information that a team player will have.
    steam
*/
public class Player
{
    private String playerName;
    private String steamID;
    private int playerState;
    private boolean captain;

    /*
    CONSTRUCTOR: Alternate
    IMPORTS: inPlayerName (String), inSteamID (String), inCaptain (boolean)
    */
    public Player(String inPlayerName, String inSteamID, boolean inCaptain)
    {

        setPlayerName(inPlayerName);
        setSteamID(inSteamID);
        setCaptain(inCaptain);

    }


    public Player(String inPlayerName, String inSteamID, int inPlayerState)
    {
        setPlayerName(inPlayerName);
        setSteamID(inSteamID);
        setPlayerState(inPlayerState);
    }
    /*
    ACCESSOR: getPlayerName
    EXPORTS: playerName
    */
    public String getPlayerName()
    {   return playerName;   }

    /*
    ACCESSOR: getSteamID
    EXPORTS: steamID
    */
    public String getSteamID()
    {   return steamID;   }


    public int getPlayerState()
    {   return playerState;   }


    /*
    ACCESSOR: toString
    EXPORTS: exportString (String)
    PURPOSE:
        Gives information about this object
    */
    public String toString()
    {
        String exportString;
        exportString = getPlayerName() + " - " + getSteamID() + " - " + getPlayerState();

        return exportString;
    }



    /*
    MUTATOR: setPlayerName
    IMPORTS: inPlayerName (String)
    */
    public void setPlayerName(String inPlayerName)
    {   playerName = inPlayerName;   }

    /*
    MUTATOR: setSteamID 
    IMPORTS: inID (String)
    */
    public void setSteamID(String inID)
    {   steamID = inID;   }

    /*
    MUTATOR: setCaptain
    EXPORTS: captain
    */
    public void setCaptain(boolean newCaptain)
    {   captain = newCaptain;   }

    public void setPlayerState(int inState)
    {   playerState = inState;   }

} // END Player