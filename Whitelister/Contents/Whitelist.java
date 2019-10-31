import java.util.*;


public class Whitelist
{
    /*
    MAIN PROGRAM
    does things lmao
    i really just wanted a submodule header here tbh
    */
    public static void main(String[] args)
    {
        // make a new Whitelister that reads teams.txt

        System.out.println("--Whitelisting program --");
        Whitelister wlister = new Whitelister(UserInput.getFileName("Please select which team storing file to use:","teams.txt"));
        // this stores all the team names
        String[] teamNames;
        // used for confirming the user only enters one of each team
        boolean inArray;
        // v both used for inputMenu v  v used for ensuring the user doesn't enter 2 of one team 
        int userChoice = -1, index = 0, teamsNum = wlister.getTeamCount();
        Team[] chosenTeams = new Team[teamsNum];
        Iterator teams;

        // get wLister teams
        teams = wlister.getTeams();
        // get all the team names
        teamNames = new String[wlister.getTeamCount()];

        while(teams.hasNext())
        {
            teamNames[index] = ((Team)teams.next()).getName();
            index++;
        }
        index = 0;
        // :ooo the first thing the user sees! so ominous~~
        System.out.println("-- TEAM WHITELIST SELECTOR --");

        do
        {
            // call inputMenu to get the user to choose a team
            userChoice = UserInput.inputMenu(teamNames, "Please select a team or exit to continue");
            // if they didn't exit
            if( userChoice != 0 )
            {
                // assume that the current team isn't in the array
                inArray = false;
                // for every team that's been chosen already
                for( int ii = 0; ii < chosenTeams.length; ii++)
                {
                    // check if it's been chosen
                    if( chosenTeams[ii] != null)
                    {
                        // if we haven't already figured out it's in the array, AND the names match
                        if( !inArray && chosenTeams[ii].getName().equals(wlister.getTeam(userChoice - 1).getName()))
                        {
                            // we found a match
                            inArray = true;
                            // tell the user they're dumb and idiots
                            System.out.println("You've already added " + wlister.getTeam(userChoice - 1).getName() + " to the whitelist!\n");
                        }
                    }

                }
                // if the current team is not in the chosenTeams array already
                if( !inArray)
                {
                    // spill some tea with the user about what we're doing
                    System.out.println("Adding " + wlister.getTeam(userChoice - 1).getName() + " to the whitelist!\n");
                    // and what we're doing is adding the current team to the chosenTeams array
                    chosenTeams[index] = wlister.getTeam(userChoice - 1);
                    index++;
                }
            }

        }
        // while we don't have one of each team and the user didn't exit
        while(( index < teamsNum && userChoice != 0));
        
        if( chosenTeams[0] != null)
        {
            System.out.println("\n- Chosen teams -");
            // make the whitelist
            wlister.makeWhitelist(chosenTeams);
            // export the team names to confirm to the user what we're doing
            for( int ii = 0; ii < index; ii++)
            {
                System.out.println("Team " + (ii + 1) + ": " + chosenTeams[ii].getName());
            }
            
        }
        else
        {
            System.out.println("User exited, bye bye :(");
        }
        // output info lol
    }



  
}