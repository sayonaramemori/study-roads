#include"read_only.h"

std::ostream& claris::operator<<(std::ostream& os, const claris::read_only& ro){
    for(auto& v:ro.single_lines){
        std::cout<<v<<std::endl;
    }
    return os;
}

bool claris::read_only::open_file(){
    std::ifstream ifs(this->file_name);
    single_lines.clear();
    if(this->state = ifs.is_open()){
        std::string temp;
        while(std::getline(ifs,temp))single_lines.push_back(temp);
    }
    ifs.close();
    return this->state;
}

bool claris::read_only::reset(const std::string& file_name){
    this->file_name = file_name;
    return open_file();
}

