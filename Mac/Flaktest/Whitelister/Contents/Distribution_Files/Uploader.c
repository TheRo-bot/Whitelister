#include <stdio.h>
#include <stdlib.h>

#define SHELLSCRIPT "\
#!/bin/bash \n\
cd ~/../../Applications \n\
cd Flaktest/Whitelister/Contents \n\
python3 Uploader.py \n\
"

int main(void)
{
	int success = system(SHELLSCRIPT);
	char exit;
	if( success != 0)
	{
		if( success == 256 )
		{
			
			printf("---\n\n\nuhhh did you close the program?\nPress enter to close the program\n");
			scanf("%c", &exit);

		}
		else
		{
			printf("YO THIS SHIT BROKE, TELL RAMAR ABOUT THIS >:(((");
		}

	}
	else
	{
		printf("Press enter to close the program\n");
		scanf("%c", &exit);
	}

	return 0;
}

