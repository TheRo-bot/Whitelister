#!/bin/bash
cd ~/../../Applications/Flaktest/Whitelister/
cd Contents

javac *.java
cd "Distribution_Files"
pwd
gcc TeamMaker.c -o TeamMaker
gcc Uploader.c -o Uploader
gcc Whitelist.c -o Whitelist
mv TeamMaker ../../
mv Uploader ../../
mv Whitelist ../../

