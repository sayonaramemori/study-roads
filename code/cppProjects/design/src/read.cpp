#include<vector>
#include<string>
#include<iostream>
#include<fstream>
#include<sstream>
#include"head.h"

read::read(const std::string& name)
{
	ifs.open(name);
	if((state = ifs.is_open())==true)
	{
		getInfo();
		print();
	}
}
void read::print()
{
	if(state==false)return;
	for(auto begin=people.begin(),end = people.end();begin!=end;++begin)
		std::cout<<"Name:"<<begin->name<<"---"<<"Phone:"<<begin->number.front()<<std::endl;
}
void read::getInfo()
{
		while(std::getline(ifs,line))
		{
			personInfo info;
			std::istringstream record(line);
			record >> info.name;
			while(record >> word)
				info.number.push_back(word);
			people.push_back(info);
		}
}
	

	

