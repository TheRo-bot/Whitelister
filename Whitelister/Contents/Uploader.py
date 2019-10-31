import ftplib

def main():

    #
    print("-- Sender --")
    # there a bunch of different types of server lists i want to use,
    # this one stores what the user will see, they're ordered by appearance on axebane's account on streamline
    userServerNames = ["45.121.208.22:27058","45.121.208.21:27058","45.121.211.30:27055","45.121.211.30:27065","45.121.210.27:27075"]
    # this one is for browsing through files if we establish an FTP connection
    fileServerNames = [None] * (len(userServerNames) - 1)
    for ii in range(len(userServerNames) - 1):
        fileServerNames[ii] = userServerNames[ii].replace(":","_")

    # this one is used for establishing the FTP connection (it's really only here cause i'm too lazy to remember what the substring method is)
    useServerNames = ["45.121.208.22","45.121.208.21","45.121.211.30","45.121.211.30","45.121.210.27"]
    # get the index of what the user chooses
    serverChoice = userMenu(userServerNames,"Please select which server you want to upload to")

    print("you chose %s" % userServerNames[serverChoice])
    # ((yeah ignore the fact that i'm storing the password in plain text lol))
    print("establishing connection between %s:%s"% (useServerNames[serverChoice], 8821))
    ftp = ftplib.FTP(host=useServerNames[serverChoice],port_=8821,user="axebane",passwd="z@uPvvq]H:S8{%#)")
    print("connection established")
    whitelistPath = "addons/sourcemod/configs/whitelist"

    # attempt to get to the whitelist file location
    try:
        installed = True
        print("going into file '%s'"%(fileServerNames[serverChoice]))
        ftp.cwd("%s/%s"%(fileServerNames[serverChoice],"addons/sourcemod/configs"))
        try:
            # a little test to see if the file whitelist exists
            ftp.cwd("whitelist")
        except:
            # we can assume that the whitelist plugin is not installed
            print("I don't have the whitelist plugin installed!")
            installed = False

        # if the whitelist plugin is installed
        if installed:
            fileName = "whitelist.txt"
            file = open(fileName, "rb")
            print("attempting to upload whitelist to server")
            ftp.storbinary("STOR " + fileName, file)
            print("success!")

    # if we fail for some reason
    except:
        print("i failed :(")
    data = []


    # ftp.retrbinary('whitelist', open('README', 'wb').write)
    print(ftp.dir())

    ftp.quit()

    
# END main


def userMenu(menuOptions = [None,None], menuPrompt = ""):


    menu = menuPrompt
    userChoice = None
    for ii in range(len(menuOptions)):
        menu += "\n%s - %s"%(ii+1, menuOptions[ii])


    while(userChoice == None):
        userChoice = input(menu + "\n")
        for ii in range(len(menuOptions)):
            if( ii == int(userChoice) - 1):
                userChoice = ii
            elif( int(userChoice) - 1 == menuOptions[ii]):
                userChoice = ii
    # END loop

    return userChoice

if( __name__ == '__main__'):
    main()