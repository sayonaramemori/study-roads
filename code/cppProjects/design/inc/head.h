#include<vector>
#include<string>
#include<iostream>
#include<fstream>
#include<sstream>
class read{
public:
	read(const std::string&);
	void print();
private:
	bool state = true;
	struct personInfo
	{
		std::string name;
		std::vector<std::string> number;
	};
	std::ifstream ifs;
	std::string line;
	std::string word;
	std::vector<personInfo> people;
	void getInfo();
};


