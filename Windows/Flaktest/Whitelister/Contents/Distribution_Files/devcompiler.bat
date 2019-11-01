@ECHO OFF
cd ..
javac *.java

ECHO Compiled Successfully, Copying programs to file...
cd ..


copy Contents\Distribution_Files\TeamMaker.lnk .

copy Contents\Distribution_Files\Whitelist.lnk .

copy Contents\Distribution_Files\Uploader.lnk .

ECHO Completed. You can close the program now!

PAUSE
