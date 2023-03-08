#include<fstream>
#include<string>
#include<vector>
#include<map>

namespace claris{

class read
{
public:
	read(const std::string& name);
    read(const std::string& name, const std::string& dest);
    read(const std::string& name, const std::string& dest,const std::string& type);
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
    int checkFront(const std::string& temp);
	void push(const std::string& temp);
	void generate(int type);
	void insert(int linePos);
	void getRank();
    void initRankMax(int*);
    void getContent(int type);
private:
	std::map<int,int> rank;
	std::string contentTable;
	std::string article;
    std::string dest;
	std::ifstream ifs;
    int type;
	bool state=true;
	std::vector<dataUnit> res;
};

}
