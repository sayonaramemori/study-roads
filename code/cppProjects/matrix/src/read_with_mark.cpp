#include"read_with_mark.h"

claris::read_with_mark::read_with_mark(const std::string& file_name,const std::string& comment_mark):read_only(file_name){
    this->reset(file_name,comment_mark);
}

bool claris::read_with_mark::reset(const std::string& file_name,const std::string& comment_mark){
    if(!comment_mark.empty())add_comment_mark(comment_mark);
    return read_only::reset(file_name);
}

std::ostream& claris::operator<<(std::ostream& os,const read_with_mark& ro)
{
    if(ro.single_lines.empty()){
        const auto &val = ro.get_origin();
        for(const auto &v:val){
            os<<v<<std::endl;
        }
    }
    else
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

bool claris::read_with_mark::add_comment_mark(char alpha){
    std::string temp(1,alpha);
    for(auto &v:this->comment_marks){
            if(v->get_start()==temp)
                return false;
    }
    comment_marks.push_back(new line_comment(temp));
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


//The line should not be empty.
int claris::read_with_mark::check_comment(const std::string& line,const std::string& specific_mark){
    if(specific_mark.empty())
        for(int i=0;i<comment_marks.size();++i){
            if(line.find(comment_marks[i]->get_start())!=std::string::npos)
                return i;
        }
    else{
        if(line.find(specific_mark)!=std::string::npos)return (npos+1);
    }
    return npos;
}

//delete the comment line
void claris::read_with_mark::trim(){
    //no comment mark, then do nothing
    if(comment_marks.empty())return;
    const auto &origin = get_origin();
    for(int i=0;i<origin.size();++i){
        std::string line = origin[i];
        //check empty line
        if(line.empty())continue;
        int res = this->check_comment(line);
        //check comment line
        if(res==npos){
            this->single_lines.push_back(line);
            continue;
        }
        //block comment check
        if(comment_marks[res]->get_type()==claris::comment::BLOCK){
            auto end = comment_marks[res]->get_end();
            for(++i;i<origin.size();++i){
                line = origin[i];
                res = this->check_comment(line,end);
                if(res!=npos)break;
            }
        }
    }
}


