#!/bin/bash
cd ~/../../Applications/Flaktest/Whitelister/
cd Contents
echo Compiling program... 
javac *.java
echo Compilation complete, copying program files...
cd "Distribution_Files"
pwd
gcc TeamMaker.c -o TeamMaker
gcc Uploader.c -o Uploader
gcc Whitelist.c -o Whitelist
mv TeamMaker ../../
mv Uploader ../../
mv Whitelist ../../

echo Finished! You can close the program now.
