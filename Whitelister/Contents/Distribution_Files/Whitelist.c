#include <stdio.h>
#include <stdlib.h>

#define SHELLSCRIPT "\
#!/bin/bash \n\
cd ~/../../Applications \n\
cd Flaktest/Whitelister/Contents \n\
java Whitelist \n\
"

int main(void)
{
	system(SHELLSCRIPT);
	return 0;
}

