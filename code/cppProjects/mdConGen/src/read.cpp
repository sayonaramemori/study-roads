#include<iostream>
#include<fstream>
#include<set>
#include<string>

#define POINT "."
#define MAXNU 6
#define RIGHT_S ")"
#define LEFT_S "("
#define SPACE ' '
#define TAB '	'
#define CODEBLO "```"
#define START '#'
#include "read.h"
#define UNORD "-"
#define RIGHT ']'
#define LEFT '['
#define NL "\n"
#define UPLINE ">>>>>>>>>>>>>>>>>"
#define DOLINE "<<<<<<<<<<<<<<<<<"

#define WITHNU 1
#define WITHTAB 2
#define PLAIN 3

namespace claris{

//specify name
read::read(const std::string& name)
{
	ifs.open(name);
    state = ifs.is_open();
    if(!state)
    {
        std::cout<<"error:File not exists"<<std::endl;
    }
    //default set name
    this->dest = "CopyVersion_" + name;
    //default set model
    this->type = PLAIN;
    //default set range
    setRange(0,0);
    //defult set insert pos
    insPos = 0;
}

//specify name and output
read::read(const std::string& name, const std::string& dest):read(name)
{
    if(dest.size()!=0)
    this->dest = dest;
}

//spacify name, output and type
read::read(const std::string& name, const std::string& dest,const std::string& type):read(name,dest)
{
    if(type=="-p")
    {
        //do nothing
        this->type = PLAIN;
    }
    else
    {
        if(type=="-t")
        {
            this->type = WITHTAB;
        }
        else
        {
            if(type=="-n")
            {
                this->type = WITHNU;
            }
            else
            {
                std::cout<<"No such type,please enter -p -t -n to specify the style,default -p"<<std::endl;
            }
        }
    }
}

//spacify name, output and type
read::read(const std::string& name, const std::string& dest,const std::string& type,const std::pair<int,int> ra):read(name,dest,type){
    setRange(ra.first,ra.second);
}

read::read(const std::string& name, const std::string& dest,const std::string& type,const std::pair<int,int> ra,int pos):read(name,dest,type,ra){
    this->insPos = pos;
}


void read::showParas(){
    std::cout<<"range:"<<"("<<range.first<<","<<range.second<<")"<<std::endl;
    std::cout<<"New file name:"<<dest<<std::endl;
    std::cout<<"Insert point:"<<insPos<<std::endl;
    std::cout<<"Type:"<<type<<std::endl;

}
void read::work()
{
    if(state)
    {
        getAllLine();
        workAfterRange();
		generate(this->type);
		insert();
        showParas();
    }
}

int read::checkFront(const std::string& temp)
{
	if(temp[0]!=START)return std::string::npos;
	return temp.find_last_of(START);
}

void read::getRank()
{
	std::set<int> temp;
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
		temp.insert(begin->level);
	}
    //set oringinal rank
	int index = 0;
	for(auto begin=temp.begin(),end=temp.end();begin!=end;++begin)
	{
		rank[*begin] = index;
		++index;
	}
    //fix the rank
	int last = 0;
	int curRank = 0;
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
		curRank=rank[begin->level];
		if((curRank - last)>1)rank[begin->level] = last + 1;
        last = curRank;
    }
#ifdef DBG
    std::cout<<UPLINE<<std::endl;
    std::cout<<"This is test for function getRank,to avoid this information, please modify makefile to del -D"<<std::endl;
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
        std::cout<<"L:"<<begin->level<<"---"<<"C:"<<begin->content<<"---"<<"R:"<<rank[begin->level]<<std::endl;
    }
    std::cout<<DOLINE<<std::endl;
#endif
}

std::string read::getHead(int nu)
{
	std::string temp;
	for(int i=0;i<nu;++i)temp += TAB;
    temp += UNORD;
    temp += SPACE;
	return temp;
}

void read::initRankMax(int *arr)
{
    int index = MAXNU;
    while(index--)arr[index]=0;
}

void read::getContent(int type)
{
	std::string body;
    std::string head;
    std::string tail;
    std::string newline;
    int rankMaxNu[MAXNU];
    initRankMax(rankMaxNu);
    switch(type)
    {
        case PLAIN:
		for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
        {
            body = getBody(begin);
            tail = getTail(begin->content);
            newline = body + tail + NL;
            this->contentTable.append(newline);
        }
            break;
        case WITHTAB:
		for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
        {
            head = getHead(rank[begin->level]);
            body = getBody(begin);
            tail = getTail(begin->content);
            newline = head + body + tail + NL;
            this->contentTable.append(newline);
        }
        break;
        case WITHNU:
		for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
        {
            head = getHead(rank[begin->level]);
            body = getBody(begin,rankMaxNu);
            tail = getTail(begin->content);
            newline = head + body + tail + NL;
            this->contentTable.append(newline);
        }
        break;
    }
}

std::string read::getNumber(int* arr, int level)
{
    std::string res;
    if(arr[level]!=0&&arr[level+1]!=0)
        for(int i=level+1;i<MAXNU;++i)
        {
            arr[i] = 0;
        }
    ++arr[level];
    if(level!=0)
    {
        for(int i=0;i<level;++i)
        {
            res += std::to_string(arr[i]);
            res += POINT;
        }
    }
    res += std::to_string(arr[level]);
    if(res.size()==1)res += POINT;
    else res += SPACE;
    return res; 
}

//Only get the body of a content line
std::string read::getBody(std::vector<dataUnit>::iterator it,int* arr)
{
    std::string body;
    if(arr==nullptr)
    {
        body = LEFT + it->content + RIGHT;
    }
    else
    {
        std::string number = getNumber(arr,rank[it->level]);
        body = LEFT + number + it->content + RIGHT;
    }
    return body;
}

std::string read::getTail(const std::string& content)
{
    std::string tail = LEFT_S; 
    tail += START;
    tail += content;
    tail += RIGHT_S;
    return tail;
}

void read::generate(int type)
{
	getRank();
    getContent(type);
}

//create a new file to store
void read::insert()
{
	ifs.close();
	std::string name = this->dest;
	std::ofstream ofs(name);
    std::string line;
    auto size = this->lines.size();
    if(this->insPos<=size){
        --insPos;
        for(int index=0;index<size;++index){
            line = lines[index] + NL;
            if(index==this->insPos){
                ofs << contentTable << line;
            }else
                ofs << line;
        }
    }
    else{
        ofs << contentTable;
        for(int index=0;index<size;++index){
            line = lines[index] + NL;
            ofs << line;
        }
    }
    ofs.close();
	return;
}

//interrim 
void read::print()
{
	std::cout<<this->contentTable<<std::endl;
}

bool read::push(const std::string& temp)
{
	//if not start with #, then return;
	int pos = checkFront(temp);
	if(pos==std::string::npos)return false;
	dataUnit data;
	data.level = ++pos;
	//delete space 
	auto index = temp.find_first_not_of(SPACE,pos);
	if(index==std::string::npos)
		data.content = temp.substr(pos);
	else
		data.content = temp.substr(index);
	this->res.push_back(data);
	return true;
}

//nu should be positive
void read::setRange(int begin,int end){
    range.first = begin;
    range.second = end;
}


void read::workAfterRange(){
    std::string line;
	bool mark=false;
    bool headFlag;
    int begin=range.first;
    int end=range.second;
    if(end==0)end = lines.size();
    for(;begin<end;++begin){
        line = lines[begin];
        //This judge is designed to avoid the # in code block
        if(line.substr(0,3)==CODEBLO)
		{
			//First come to code block
			if(mark)
			{
				//only mark is set and line is equal code block
				mark = false;
				continue;
			}else
			{
				//set mark, to indicate the field of code;
				mark = true;
				//end this loop;
            }
        }else
		{
			if(mark)continue;
			else{
                headFlag = this->push(line);
                if(headFlag)headLine.push_back(begin);
            }
		}
    }
}

void read::getAllLine()
{
	std::string line;
	while(std::getline(ifs,line))
	{
        //store every line
        this->lines.push_back(line);
    }
}


	
}
