#include"read_only.h"

std::ostream& claris::operator<<(std::ostream& os, const claris::read_only& ro){
    for(auto& v:ro.single_lines){
        std::cout<<v<<std::endl;
    }
    return os;
}

//This function will clear container first for the new file.
bool claris::read_only::open_file(const std::string &name){
    single_lines.clear();
    std::ifstream ifs(name);
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
    return open_file(file_name);
}

