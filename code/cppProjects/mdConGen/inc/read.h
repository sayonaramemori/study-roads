#include<fstream>
#include<string>
#include<vector>
#include<map>

namespace claris{

class read
{
public:
	read(const std::string& name);
private:
	void getAllLine();
	std::string contentTable;
	std::string article;
    int checkFront(const std::string& temp);
	void push(const std::string& temp);
	std::string getTab(int nu);
	void plain();
	void withTab();
	void withNumber();
	void print();
	void generate(int type);
	void insert(int);
	std::map<int,int> rank;
	void getRank();
	std::ifstream ifs;
	bool state=true;
	typedef struct
	{
		std::string content;
		int level;
	}dataUnit;
	std::vector<dataUnit> res;
};

}
