#include"read_for_matrix.h"

//The line should not be empty.
int read_for_matrix::check_comment(const std::string& line,const std::string& specific_mark){
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
void read_for_matrix::trim(){
    //no comment mark, then do nothing
    if(comment_marks.empty())return;
    for(int i=0;i<this->single_lines.size();++i){
        std::string line = single_lines[i];
        //check empty line
        if(line.empty())continue;
        int res = this->check_comment(line);
        //check comment line
        if(res==npos){
            this->single_line_slim.push_back(line);
            continue;
        }
        if(comment_marks[res]->get_type()==claris::comment::BLOCK){
            auto end = comment_marks[res]->get_end();
            for(++i;i<this->single_lines.size();++i){
                res = this->check_comment(line,end);
                if(res!=npos)break;
            }
        }
    }
}

//output all the lines after trim()
std::ostream& operator<<(std::ostream& os,const read_for_matrix& rfm){
    if(rfm.single_line_slim.empty())
        for(auto &v:rfm.single_lines){
            os<<v<<std::endl;
        }
    else
        for(auto &v:rfm.single_line_slim){
            os<<v<<std::endl;
        }
    return os;
}
