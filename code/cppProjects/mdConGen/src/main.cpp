#include<iostream>
#include<set>
#include<string>
#include "read.h"
#include<map>
#include<vector>

#define DEST "-o"
#define BEGIN "-f"
#define END "-t"
#define INSPOS "-i"
#define APPEND "-a"
#define TYPE "type"
#define NAME "name"
typedef std::map<std::string,std::string> stringMap;

void usage(){
    std::cout<<"Usage: prog [file-name] -o [new-file] [-opt]"<<std::endl;
    std::cout<<"       prog [file-name] -i [line] -f [begin] -t [end] [-opt]"<<std::endl;
    std::cout<<"       prog [file-name] -f [begin] -t [end] [-opt]"<<std::endl;
    std::cout<<"       prog [file-name] [-opt]"<<std::endl;
    std::cout<<"       prog [file-name] [-opt]"<<std::endl;
    std::cout<<"Opt:"<<std::endl;
    std::cout<<"-p --plain text"<<std::endl;
    std::cout<<"-t --with tab"<<std::endl;
    std::cout<<"-n --with number and tab"<<std::endl;
    std::cout<<"-a --append a ^ to every headline"<<std::endl;
    exit(-1);
}

void errMessage(const std::string& info){
    std::cout<<info<<std::endl;
    exit(-1);
}

void preckPara(int argc,char*argv[]){
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
        usage();
    }
}

void checkParas(stringMap &paras,std::set<std::string> &paraList){
    for(auto begin = paras.begin(),end = paras.end();begin!=end;++begin){
        if(paraList.find(begin->first)==paraList.end())usage();
    }
}

void initParaSet(std::set<std::string> &paras)
{
    paras.insert(DEST);
    paras.insert(BEGIN);
    paras.insert(END);
    paras.insert(INSPOS);
    paras.insert(APPEND);
    paras.insert(TYPE);
    paras.insert(NAME);
}

std::pair<int,int> getRange(int begin,int end){
    if(begin<0||end<0){
        errMessage("Bad range");
    }
    if(end>0&&begin>0){
        if(begin>end)
        errMessage("Bad range");
    }
    return std::make_pair(begin,end);
}

void showParas(stringMap &paras){
    std::cout<<"name: "<<paras[NAME]<<std::endl;
    std::cout<<"type: "<<paras[TYPE]<<std::endl;
    std::cout<<"begin: "<<paras[BEGIN]<<std::endl;
    std::cout<<"end: "<<paras[END]<<std::endl;
    std::cout<<"insPos: "<<paras[INSPOS]<<std::endl;
    std::cout<<"append: "<<paras[APPEND]<<std::endl;
}

void userInterface(int argc,char* argv[])
{
    preckPara(argc,argv);
    std::set<std::string> AllParas;
    initParaSet(AllParas);
    std::map<std::string,std::string> paras;
    paras[TYPE] = "-p";
    for(int i=2;i<argc;++i)
    {
        std::string val;
        std::string cmd = argv[i];
        if(++i<argc){
            val = argv[i];
            paras[cmd] = val;
        }else{
           if(cmd.size()!=0)paras[TYPE] = cmd;
        }
    }
    paras[NAME] = argv[1];
    int begin = 0,end = 0;
    int insPos = 0;
    int targetline = -1;
    if(paras[BEGIN].size()!=0)begin = std::stoi(paras[BEGIN]);
    if(paras[END].size()!=0)end = std::stoi(paras[END]);
    if(paras[INSPOS].size()!=0)insPos = std::stoi(paras[INSPOS]);
    if(paras[APPEND].size()!=0)targetline= std::stoi(paras[APPEND]);
    auto range = getRange(begin,end);
    checkParas(paras,AllParas);
    showParas(paras);
    claris::read temp(paras[NAME],\
                      paras[DEST],\
                      paras[TYPE],\
                      range,      \
                      insPos,     \
                      targetline);

    temp.work();
    temp.print();
}

int main(int argc,char* argv[])
{
    userInterface(argc,argv);
	return 0;
}
