#include<iostream>
#include<string>
#include "read.h"
#include<map>
#include<vector>

#define DEST "-o"

void initParas(std::map<std::string,std::string> &paras)
{
    paras[DEST];
}

void userInterface(int argc,char* argv[])
{
    if(argc == 1)
    {
        std::cout<<"Please enter a file name"<<std::endl;
        std::cout<<"Usage: prog [file-name] -o [new-file]"<<std::endl;
        std::cout<<"prog --help to get more info"<<std::endl;
        exit(-1);
    }
    std::string secondPara = argv[1];
    if(secondPara=="--help")
    {
        std::cout<<"Usage: prog [file-name] -o [new-file] [-opt]"<<std::endl;
        std::cout<<"       prog [file-name] [-opt]"<<std::endl;
        std::cout<<"Opt:"<<std::endl;
        std::cout<<"-p --plain text"<<std::endl;
        std::cout<<"-t --with tab"<<std::endl;
        std::cout<<"-n --with number and tab"<<std::endl;
        exit(-1);
    }

    std::map<std::string,std::string> paras;
    initParas(paras);
    for(int i=2;i<argc;++i)
    {
        if(++i<argc)
        paras[std::string(argv[i-1])] = std::string(argv[i]);
    }
    std::string lastPara = argv[argc-1];
    std::string name = argv[1];
    if(lastPara[0]=='-')
    {
        claris::read temp(name,paras[DEST],lastPara);
        temp.work();
        temp.print();
    }
    else
    {
        claris::read temp(name,paras[DEST]);
        temp.work();
        temp.print();
    }
}

int main(int argc,char* argv[])
{
    userInterface(argc,argv);
	return 0;
}
