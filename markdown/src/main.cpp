#include<iostream>
#include<fstream>
#include<string>
#include<vector>

class reader;
static void msg(const std::string& str){
    std::cout<<str<<std::endl;
    exit(-1);
}
class reader{
public:
    int size()const{return content.size();}
    const std::string& operator[](int i)const{return content[i];}
    reader(const std::string &name){
        this->file_name = name;
        initContent(name);
    }
private:
    void initContent(const std::string& file_name){
        std::ifstream ifs;
        ifs.open(file_name);
        if(ifs.is_open()){
            std::string line;
            while(std::getline(ifs,line))content.push_back(line);
        }else{
            msg("Cannot open the specific file");
        }
    }
    std::string file_name;
    std::vector<std::string> content;
};

std::ostream& operator<<(std::ostream& os,const reader& body){
    for(int i=0;i<body.size();++i){
        os<<body[i]<<std::endl;
    }
    return os;
}


int main(int argc, char * argv[]){
    if(argc<2)msg("Please offer a file");
    reader test(argv[1]);
    std::cout<<test<<std::endl;
    return 0;
}
