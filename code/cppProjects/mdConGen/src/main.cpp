#include<iostream>
#include<string>
#include "read.h"


int main(int argc,char* argv[])
{
    if(argc == 1)
    {
        std::cout<<"Please enter a file name"<<std::endl;
        std::cout<<"Usage: prog [file-name] -o [new-file]"<<std::endl;
        exit(-1);
    }
	std::string name = argv[1];
	claris::read temp = name;
    temp.work();
    temp.print();
	return 0;
}
