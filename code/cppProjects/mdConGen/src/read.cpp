#include<iostream>
#define RIGHT_S ")"
#include<fstream>
#define LEFT_S "("
#include<set>
#define SPACE ' '
#define TAB '	'
#include<string>
#define CODEBLO "```"
#define START '#'
#include "read.h"
#define UNORD "-"
#define RIGHT ']'
#define LEFT '['

#define WITHNU 1
#define WITHTAB 2
#define PLAIN 3

namespace claris{

read::read(const std::string& name)
{
	ifs.open(name);
	if(state = ifs.is_open())getAllLine();
	generate(PLAIN);
	insert(0);
	print();
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
	int index = 0;
	for(auto begin=temp.begin(),end=temp.end();begin!=end;++begin)
	{
		rank[*begin] = index;
		++index;
	}
}


std::string read::getTab(int nu)
{
	std::string temp;
	for(int i=0;i<nu;++i)temp += TAB;
	return temp;
}

void read::plain()
{
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
		std::string head = UNORD;
		head += SPACE;
		std::string body = LEFT + begin->content + RIGHT;
		std::string tail = LEFT_S;
		tail += START + begin->content + RIGHT_S;
		std::string newline = head + body + tail + '\n';
		this->contentTable.append(newline);
	}

}

void read::withNumber()
{
	
}


void read::withTab()
{
	int last = 0;
	int curlevel = 0;
	for(auto begin=res.begin(),end=res.end();begin!=end;++begin)
	{
		curlevel=rank[begin->level];
		if((curlevel - last)>1)rank[begin->level] = last + 1;

		std::string head = getTab(rank[begin->level]) + UNORD + SPACE;
		std::string body = LEFT + begin->content + RIGHT;
		std::string tail = LEFT_S;
		tail += START + begin->content + RIGHT_S;
		std::string newline = head + body + tail + '\n';
		this->contentTable.append(newline);
	}
}

void read::generate(int type)
{
	getRank();
	switch(type)
	{
		case WITHNU:
			withNumber();
			break;	
		case WITHTAB:
			withTab();
			break;	
		case PLAIN:
			plain();
			break;
	}
}

void read::insert(int lineNu=0)
{
	ifs.close();
	std::string name = "result.md";
	std::ofstream ofs(name);
	ofs << contentTable << article;
	ofs.close();
	return;

}

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
	
void read::getAllLine()
{
	std::string line;
	bool mark=false;
	while(std::getline(ifs,line))
	{
		article += line + '\n';
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
