using namespace std;
#include<iostream>
#include<set>
#include<vector>
#include<string>

class dataUnit{
public:
    bool setNumber(set<int> &ori){
        if(ori.size()==0)return false;
        preNumber = ori;
        setbe();
        write(*begin);
        return true;
    }
    bool moveForward(){
        if(++begin!=end){
            write(*begin);
            return true;
        }
        else{
            write(0);
            clear();
            return false;
        }
    }
    pair<int,int> getPos();
    int getVal();
private:
    void setbe();
    void write(int val);
    void clear(){
        setbe();
        preNumber.clear();
    }
    pair<int,int> pos;
    set<int> preNumber;
    set<int>::iterator begin;
    set<int>::iterator end;
};


class datas{
public:
typedef vector<vector<int>> chess;
datas();

private:
    int workPoint;
    int empSize;
    std::vector<dataUnit> empPos;
    std::vector<chess> answers;
    void getAnswer(const chess& ch){
        answers.push_back(ch);
    }
    void goBack(int &workPoint){
        if(workPoint==0){
            cout<<"无解"<<endl;
            exit(0);
        }
        --workPoint;
        bool Flag = empPos[workPoint].moveForward();
        if(Flag)++workPoint;
        else{
            goBack(workPoint);
        }
    }
   void InitEmpPos(vector<dataUnit> &empPos,chess&);
};

class chess{
public:
void InitChess(string input);
void setVal(pair<int,int> pos,int val);
void getPreNumber(pair<int,int> pos,set<int> &res);
void print();
string toStringChess();

private:
    void PushEmp(pair<int,int>);
    void InitCol(int raw,vector<int> col);
    void InitNine();
    void getNinePos(pair<int,int>);
    void getNine(pair<int,int> pos,set<int> &res);
    void getCross(pair<int,int> pos,set<int> &res);
        void getCol(int col,set<int> &res);
        void getRow(int row,set<int> &res);
    void diffSet(set<int> &ori,set<int> &res){
        if(ori.size()==10)return;
        auto begin = ori.begin();
        for(int i=0;i<10;++i){
            if(*begin!=i){
                for(;i<*begin;++i)res.insert(i);
            }
            ++begin;
        }

private:
    std::vector<vector<int>> workChess;
    std::vector<set<pair<int,int>>> nineGrid;
};



void work(){
    class chess(input);
    chess.InitChess();
    class datas;
    InitEmpPos();
    pair<int,int> curPos;
    set<int> preNumber;
    bool Flag;
    while(1){
        for(;workPoint<empSize;){
            curPos = empPos[workPoint].getPos();
            getPreNumber(chess,curPos,preNumber);
            Flag = empPos[workPoint].setNumber(preNumber);
            if(Flag){
                ++workPoint;
            }else{
                goBack(workPoint);
            }
        }
        getAnswer(chess);
        goBack(workPoint);
    }
}



