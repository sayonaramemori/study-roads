### 9x9

> 数独是个益智游戏，该程序输入为9x9个数，判断该数独是否有解，解是否唯一，并给出解。

### Input\_system  
> 输入由文本给出，要求如下：
1. 一行九个数，由空格隔开，空格个数不限制
2. 空格子用0代替

### Data\_Structure
```C++
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

typedef std::vector<vector<int>> answer;
std::vector<answer> answers;
std::vector<vector<int>> chess;
std::vector<dataUnit> empPos;
int empSize;
int workPoint;
chess.size() == 10;
//functions:
void InitChess(string input);
    void InitCol(int raw,vector<int> col);
        void InsertEmp(pair<int,int>);

```

### API
```C++
void setVal(pair<int,int> pos,int val);
void InitEmpPos(vector<dataUnit> &empPos,std::vector<vector<int>> &chess);\

void goBack(int &workPoint){
    if(workPoint==0){
        cout<<"无解"<<endl;
        exit(0);
    }
    --workPoint;
    Flag = empPos[workPoint].moveForward();
    if(Flag)++workPoint;
    else{
        goBack(workPoint);
    }
}

void getAnswer(chess){
    answers.push_back(chess);
}
void 
void work(){
    InitChess();
    InitEmpPos();
    empSize;
    workPoint = 0;
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

void getPreNumber(vector<vector<int>> chess, pair<int,int> pos,set<int> &res);
    void diffSet(set<int> &ori,set<int> &res){
        if(ori.size()==10)return;
        auto begin = ori.begin();
        for(int i=0;i<10;++i){
            if(*begin!=i){
                for(;i<*begin;++i)res.insert(i);
            }
            ++begin;
        }
    }
    void getCross(vector<vector<int>> chess, pair<int,int> pos,set<int> &res);
        void getCol(vector<vector<int>> chess,int col,set<int> &res);
        void getRow(vector<vector<int>> chess,int row,set<int> &res);
```



