#include <stdio.h>
#include <stdlib.h>

#define SHELLSCRIPT "\
#!/bin/bash \n\
cd ~/../../Applications \n\
cd Flaktest/Whitelister/Contents \n\
pwd \n\
ls \n\
java TeamMaker \n\
"


int main(void)
{
	printf("-- starting script --\n\n");
	system(SHELLSCRIPT);
   	printf("\n\n-- done --\n"); 


}
