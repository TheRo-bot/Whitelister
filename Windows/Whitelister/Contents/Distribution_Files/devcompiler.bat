@ECHO OFF
cd ..
javac *.java
ECHO Program Compiled, copying batch files over..
cd ..

copy Contents\Distribution_Files\TeamMaker.bat .
copy Contents\Distribution_Files\Whitelist.bat .
copy Contents\Distribution_Files\Uploader.bat .

ECHO Done! You can close this window now!

PAUSE
