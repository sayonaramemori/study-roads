#include"read_with_mark.h"

claris::read_with_mark::read_with_mark(const std::string& file_name,const std::string& comment_mark){
    this->reset(file_name,comment_mark);
}

bool claris::read_with_mark::reset(const std::string& file_name,const std::string& comment_mark){
    this->file_name = file_name;
    if(open_file()){
        if(!comment_mark.empty())add_comment_mark(comment_mark);
        trim();
    }
    return this->state;
}

bool claris::read_with_mark::open_file(){
    std::ifstream ifs(this->file_name);
    if(this->state = ifs.is_open()){
        std::string temp;
        while(std::getline(ifs,temp))single_lines.push_back(temp);
    }
    return this->state;
}

std::ostream& claris::operator<<(std::ostream& os,const read_with_mark& ro)
{
    for(auto &v:ro.single_lines)
    {
        os<<v<<std::endl;
    }
    return os;
}

bool claris::read_with_mark::add_comment_mark(const std::string& pre, const std::string& lat){
    if(lat.empty()){
        for(auto &v:this->comment_marks){
            if(v->get_start()==pre)
                return false;
        }
        comment_marks.push_back(new line_comment(pre));
    }else{
        for(auto &v:this->comment_marks){
            if(v->get_start()==pre||v->get_end()==lat)
                return false;
        }
        comment_marks.push_back(new block_comment(pre,lat));
    }
    return true;
}

void claris::read_with_mark::rm_comment_mark(const std::string& pre, const std::string& lat){
    if(lat.empty()){
        for(int i=0;i<comment_marks.size();++i){
            if(comment_marks[i]->get_start()==pre){
                delete comment_marks[i];
                comment_marks.erase(comment_marks.begin()+i);
                break;
            }
        }
    }
    else{
        for(int i=0;i<comment_marks.size();++i){
            if(comment_marks[i]->get_start()==pre&&comment_marks[i]->get_end()==lat){
                delete comment_marks[i];
                comment_marks.erase(comment_marks.begin()+i);
                break;
            }
        }
    }
}


