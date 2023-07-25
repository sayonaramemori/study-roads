#include"read_only.h"

#ifndef READ_FOR_MD
#define READ_FOR_MD
const std::string PREFIX = "copy_version_"; 

namespace claris{
    class add_content: public read_only{
        public:
            add_content()=delete;
            add_content(const std::string &name):read_only(name){
                this->level = 1;
                this->insert_line = 0;
                this->output = PREFIX + name;
            }
            void generate();
            void set_output(const std::string &name){this->output = name;}
            void set_insert_line(int nu){this->insert_line = nu;}
            void set_level(int level){this->level = level;}

        private:
            int level;
            int insert_line;
            std::string output;
            bool compare(const std::string &line, const std::string &f);
            std::vector<int> get_content_line_pos(int level);
            std::string get_content_line(const std::string &line);
            static constexpr char head = '#';

    };
}


#endif



namespace claris{
    bool add_content::compare(const std::string &line, const std::string &f){
        auto res = line.find(f);
        if(res==std::string::npos)return false;
        return true;
    }

    std::vector<int> add_content::get_content_line_pos(int level){
        const auto &ori = get_origin();
        const std::string search_str(level,this->head);
        std::vector<int> res;
        int index = 0;
        for(auto &v:ori){
            if(compare(v,search_str))res.push_back(index);
            ++index;
        }
        return res;
    }

    std::string add_content::get_content_line(const std::string &line){

    }













}
