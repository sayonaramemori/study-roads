#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include<utility>

#ifndef READ_ONLY
#define READ_ONLY

namespace claris{
    class read_only{
    public:
        read_only()=delete;
        read_only(const std::string& name){this->reset(name);}
        bool get_state()const{return this->state;}
        std::string get_file_name()const{return this->file_name;}
        bool reset(const std::string& file_name);
    private:
        std::string file_name;
        std::vector<std::string> single_lines;
        bool state=false;
        bool open_file(const std::string &name);
        friend std::ostream& operator<<(std::ostream& os, const read_only& ro);
    protected:
        const std::vector<std::string>& get_origin()const{return this->single_lines;}
        void clear_origin(){single_lines.clear();}
    };
        std::ostream& operator<<(std::ostream& os, const read_only& ro);
}

using claris::operator<<;

#endif
