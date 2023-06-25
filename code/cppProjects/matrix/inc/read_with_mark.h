#ifndef READ_WITH_MARK
#define READ_WITH_MARK
#include "read_only.h"
#include<set>
#include"matrix.h"

namespace claris{

    class comment{
    public:
        enum TYPE{BLOCK=0,LINE};
        int get_type(){return this->type;}
        virtual std::string get_start()=0;
        virtual std::string get_end(){return std::string();}
        comment()=delete;
        virtual ~comment(){};
        explicit comment(int type){this->type = type;}
    private:
        int type = LINE;
    };

    class line_comment: public comment{
    public:
        line_comment(const std::string& mark):comment(LINE){
            this->comment_mark = mark;
        }
        virtual std::string get_start() override {
            return comment_mark;
        }
    private:
        std::string comment_mark;
    };

    class block_comment: public comment{
    public:
        block_comment(const std::string& pre,const std::string& lat):comment(BLOCK){
            this->comment_mark.first = pre;
            this->comment_mark.second = lat;
        }
        virtual std::string get_start() override {
            return comment_mark.first;
        }
        std::string get_end() override {
            return comment_mark.second;
        }
    private:
        std::pair<std::string,std::string> comment_mark;
    };


    class read_with_mark: public read_only{
    public:
        read_with_mark()=delete;
        read_with_mark(const std::string& file_name,const std::string& comment_mark="");
        virtual ~read_with_mark(){
            for(auto v:comment_marks){
                delete v;
            }
        }
        bool reset(const std::string& file_name,const std::string& comment_mark="");
        bool add_comment_mark(const std::string& pre, const std::string& lat="");
        bool add_comment_mark(char);
        void rm_comment_mark(const std::string& pre, const std::string& lat="");
        void show_comment_marks(){
            std::cout<<"-----COMMENTS-----"<<std::endl;
            for(auto &v:comment_marks){
                if(v->get_type()==comment::LINE)
                    std::cout<<v->get_start()<<std::endl;
                else
                    std::cout<<v->get_start()<<" & "<<v->get_end()<<std::endl;
            }
            std::cout<<"-----COMMENTS-----"<<std::endl;
        }
        virtual void trim();
        std::vector<matrix> getMatrix(){return get_matrix(comment_pos,single_lines);}
    protected:
        virtual int check_comment(const std::string& line,const std::string& mark="");
        std::set<int> comment_pos;
        std::vector<std::string> single_lines;
    private:
        std::vector<matrix> get_matrix(const std::set<int>& comment_pos,const std::vector<std::string>& lines);
        //pointer for dynamic bind
        std::vector<comment*> comment_marks;
        static constexpr int npos = -1;

        friend std::ostream& operator<<(std::ostream& os,const read_with_mark& ro);
    };

    std::ostream& operator<<(std::ostream& os,const read_with_mark& ro);
    //even if you assign the static constexpr, declaration is needed.

}

using claris::operator<<;

#endif

