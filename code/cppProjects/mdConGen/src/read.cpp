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

read::read(const std::string& name)
{
	ifs.open(name);
	if(state = ifs.is_open())getAllLine();
	generate(WITHNU);
	insert(0);
	print();
}


//check the line front
int read::checkFront(const std::string& temp)
{
	if(temp[0]!=START)return std::string::npos;
	return temp.find_last_of(START);
}


//This function is designed to figure out the prior for every headline.
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
	int curlevel = 0;
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
		curlevel=rank[begin->level];
		if((curlevel - last)>1)rank[begin->level] = last + 1;
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


//generate tab block 
std::string read::getHead(int nu)
{
	std::string temp;
	for(int i=0;i<nu;++i)temp += TAB;
    temp += UNORD;
    temp += SPACE;
	return temp;
}

//init maxNuber
void read::initRankMax(int *arr)
{
    int index = MAXNU;
    while(index--)arr[index]=0;
}

//key func 
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
            body = getBody(begin,rankMaxNu);
            tail = getTail(begin->content);
            newline = body + tail + NL;
            this->contentTable.append(newline);
        }
            break;
        case WITHTAB:
		for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
        {
            head = getHead(begin->level);
            body = getBody(begin);
            tail = getTail(begin->content);
            newline = head + body + tail + NL;
            this->contentTable.append(newline);
        }
        break;
        case WITHNU:
		for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
        {
            head = getHead(begin->level);
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

//main func 
void read::generate(int type)
{
	getRank();
    getContent(type);
}

//create a new file to store
void read::insert(int lineNu=0)
{
	ifs.close();
	std::string name = "result.md";
	std::ofstream ofs(name);
	ofs << contentTable << article;
	ofs.close();
	return;
}

//interrim 
void read::print()
{
	std::cout<<this->contentTable<<std::endl;
}

void read::push(const std::string& temp)
{
	//if not start with #, then return;
	int pos = checkFront(temp);
	if(pos==std::string::npos)return;
	
	dataUnit data;
	data.level = ++pos;

	//delete space 
	auto index = temp.find_first_not_of(SPACE,pos);
	if(index==std::string::npos)
		data.content = temp.substr(pos);
	else
		data.content = temp.substr(index);

	this->res.push_back(data);
	return;
}

//This func is designed to get the all page and headline.
void read::getAllLine()
{
	std::string line;
	bool mark=false;
	while(std::getline(ifs,line))
	{
        //record the content of the file
		article += line + '\n';
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
				continue;
			}
		}else
		{
			if(mark)continue;
			else this->push(line);
		}
	}
}


	
}
