#include<fstream>
#include<string>
#include<vector>
#include<map>
#include<set>

namespace claris{

class read
{
public:
	read(const std::string& name);
    read(const std::string& name, const std::string& dest);
    read(const std::string& name, const std::string& dest,const std::string& type);
    read(const std::string& name, const std::string& dest,const std::string& type,const std::pair<int,int>);
    read(const std::string& name, const std::string& dest,const std::string& type,const std::pair<int,int>,int pos);
read(const std::string& name, const std::string& dest,const std::string& type,const std::pair<int,int> ra,int pos,int targetLine);
	void print();
    void work();
private:
	typedef struct
	{
		std::string content;
		int level;
	}dataUnit;
    typedef std::vector<dataUnit>::iterator vecit;
private:
	std::string getHead(int level);
    std::string getTail(const std::string& content);
    std::string getBody(std::vector<dataUnit>::iterator it,int* arr=nullptr);
    std::string getNumber(int* arr, int level);
	void getAllLine();
    void append();
    int checkFront(const std::string& temp);
	bool push(const std::string& temp);
	void generate(int type);
	void insert();
	void getRank();
    void initRankMax(int*);
    void getContent(int type);
    void setRange(int lhv,int rhv);
    void workAfterRange();
    void showParas();
private:
    std::string img = "^";
    int insPos = 0;
    int targetLine = -1;
    std::set<int> headLine;
    std::pair<int,int> range = {0,0};
    std::vector<std::string> lines;
	std::map<int,int> rank;
	std::string contentTable;
    std::string dest;
	std::ifstream ifs;
    int type;
	bool state=true;
	std::vector<dataUnit> res;
};

}
