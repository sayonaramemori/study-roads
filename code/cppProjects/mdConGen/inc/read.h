#include<fstream>
#include<string>
#include<vector>
#include<map>

namespace claris{

class read
{
public:
	read(const std::string& name);
    void work();
	void print();
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
	void insert(int);
	void getRank();
    void initRankMax(int*);
    void getContent(int type);
private:
	std::map<int,int> rank;
	std::string contentTable;
	std::string article;
	std::ifstream ifs;
	bool state=true;
	std::vector<dataUnit> res;
};

}
