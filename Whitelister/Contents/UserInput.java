/*
Author: The Robot (7th September 2019)
Contact: the.robot00@gmail.com
Purpose:
	Handles all user input
*/
import java.util.*;
import java.io.*;

public class UserInput
{


	/*
	SUBMODULE: getString
	IMPORTS: promptMessage (String)
	EXPORTS: exportString (String)
	PURPOSE:
		Will get the user to put in a string
	*/
	public static String getString(String promptMessage)
	{
		Scanner sc = new Scanner(System.in);
		String userInput, exportString = "\0";

		do
		{
			try
			{
				System.out.println(promptMessage);
				userInput = sc.nextLine();
				exportString = userInput;
			}
			catch(UnknownError yeet)
			{
				System.out.println("\nunexpected error occurred. Please try again! \n");
			}

		}
		while(exportString.equals("\0"));
		return exportString;
	}


    /*
    SUBMODULE: inputMenu
    IMPORTS: menuOptions (String array), promptMessage (String)
    EXPORTS: userChoice (int)
    PURPOSE:
        to make the user get one option out of menuOptions (including exit)
        exports the number in the array in which they chose, (exit = 0, menuOptions[0] = 1)
    */
    public static int inputMenu(String[] menuOptions, String promptMessage)
    {

        Scanner sc = new Scanner(System.in);
        String userInput = "", menu = promptMessage;
        String[] finalMenu = new String[menuOptions.length + 1];
        int userChoice = -1;

        finalMenu[0] = "Exit";
        for( int ii = 0; ii < menuOptions.length; ii++)
        {
            finalMenu[ii + 1] = menuOptions[ii];
        }

        for( int ii = 0; ii < finalMenu.length; ii++)
        {
            menu += "\n" + (ii + 1) + " - " + finalMenu[ii];
        }


        do
        {
            System.out.println(menu);
            userInput = sc.nextLine();

            for( int ii = 0; ii < finalMenu.length; ii++)
            {
                // if the user wrote a menu item
                if( finalMenu[ii].toLowerCase().equals(userInput.toLowerCase()))
                {
                    userChoice = ii;
                }
                // if the user chose the number of a menu item
                else if( String.valueOf(ii + 1).equals(userInput))
                {
                    userChoice = ii;
                }
                
                

            }
            if( userChoice == -1)
            {
                System.out.println("ERROR:\nPlease enter a menu item.\n\n");
            }

        }
        while(userChoice == -1);
        
        return userChoice;
    }

 /*
    SUBMODULE: inputSelectMenu
    IMPORTS: menuOptions (String array), promptMessage (String)
    EXPORTS: userChoice (int)
    PURPOSE:
        to make the user get one option out of menuOptions
        The user can enter nothing, which means they cancelled
        exports menuOptions item, not index
    */
    public static int inputSelectMenu(String[] menuOptions, String promptMessage)
    {

        Scanner sc = new Scanner(System.in);
        String userInput = "", menu = promptMessage;
        int userChoice = -1;


        for( int ii = 0; ii < menuOptions.length; ii++)
        {
            menu += "\n" + (ii + 1) + " - " + menuOptions[ii];
        }


        do
        {
            System.out.println(menu + "\n(Press <ENTER> to cancel)");
            userInput = sc.nextLine();

            for( int ii = 0; ii < menuOptions.length; ii++)
            {

                if( userInput.equals(""))
                {
                    userChoice = 0;
                }
                // if the user wrote a menu item
                else if( menuOptions[ii].toLowerCase().equals(userInput.toLowerCase()))
                {
                    userChoice = ii + 1;
                }
                // if the user chose the number of a menu item
                else if( String.valueOf(ii + 1).equals(userInput))
                {
                    userChoice = ii + 1;
                }
                
                

            }
            if( userChoice == -1 )
            {
                System.out.println("ERROR:\nPlease enter a menu item.\n\n");
            }

        }
        while(userChoice == -1);
        
        return userChoice;
    }

public static String getFileNameOverwrite(String promptMessage, String directory)
    {
        String fileName = "\0";
        String userChoice = null;
        String exportString = "";
        while( fileName.equals("\0") )
        {

            fileName = UserInput.getString(promptMessage + "\n(Press <ENTER> to cancel)");
            if( fileName.equals("") )
            {
                fileName = "";
            }
            if(!( new File(directory + System.getProperty("file.separator") + fileName).isFile() ) )
            {
                userChoice = UserInput.getString("That file exists! Would you like to overwrite?\nPress <ENTER> to cancel");

                if(! userChoice.toLowerCase().equals("y") )
                {
                    fileName = "\0";    
                }
                else if( userChoice.toLowerCase().equals("") )
                {
                    fileName = null;
                }
                
            }
        }
        if(! fileName.equals("") )
        {
            exportString = directory + System.getProperty("file.separator") + fileName;
        }
        return exportString;
    }


    public static String getFileName(String promptMessage, String directory)
    {
        String fileName = "\0";
        String userChoice = "";
        String exportString = "";
        while( fileName.equals("\0") )
        {

            fileName = UserInput.getString(promptMessage + "\n(Press <ENTER> to cancel)");
            if( fileName.equals("") )
            {
                System.out.println("Cancelled!\n");
                fileName = "";
            }
            else if(!( new File(directory + System.getProperty("file.separator") + fileName).isFile() ) )
            {
                System.out.println("ERROR: Please enter a valid file!\n\n");
                fileName = "\0";
            }
        }
        if(!fileName.equals(""))
        {
            exportString = directory + System.getProperty("file.separator") + fileName;
        }
        return exportString;
    }


    public static int getNumber(String promptMessage)
    {
        return getNumber(promptMessage, -999999, 999999 );
    }

    public static int getNumber(String promptMessage, int lowerBound, int upperBound)
    {
        Scanner sc = new Scanner(System.in);
        int exportInt = lowerBound - 1;

        do
        {
            System.out.println(promptMessage + "\nWithin " + lowerBound + " and " + upperBound);
            exportInt = sc.nextInt();
            if(! (exportInt >= lowerBound && exportInt <= upperBound) )
            {
                System.out.println("Please enter a number within the range!");
                exportInt = lowerBound - 1;
            }
        }
        while(exportInt == lowerBound - 1);

        return exportInt;
    }
}













