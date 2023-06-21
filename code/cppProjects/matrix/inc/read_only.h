#ifndef READ_ONLY
#define READ_ONLY
#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include<utility>


namespace claris{

    class comment{
    public:
        enum TYPE{BLOCK=0,LINE};
        int get_type(){return this->type;}
        virtual std::string get_start()=0;
        virtual std::string get_end(){return std::string();}
        comment()=default;
        virtual ~comment(){}
        comment(int type){this->type = type;}
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


    class read_with_mark{
    public:
        read_with_mark()=delete;
        virtual ~read_with_mark(){
            for(auto v:comment_marks){
                delete v;
            }
        }
        read_with_mark(const std::string& file_name,const std::string& comment_mark="");
        bool reset(const std::string& file_name,const std::string& comment_mark="");
        bool add_comment_mark(const std::string& pre, const std::string& lat="");
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
        static constexpr int npos = -1;
    private:
        std::vector<std::string> single_lines;
        virtual int check_comment(const std::string& line,const std::string& mark="")=0;
        virtual void trim(){}

        std::vector<comment*> comment_marks;
        friend std::ostream& operator<<(std::ostream& os,const read_with_mark& ro);
    };

    std::ostream& operator<<(std::ostream& os,const read_with_mark& ro);
    constexpr int read_with_mark::npos;

}
using claris::operator<<;

namespace claris{
    class read_only{
    public:
        read_only()=delete;
        read_only(const std::string& name){

        }
        bool get_state(){return this->state;}
        std::string get_file_name(){return this->file_name;}
        bool reset(const std::string& file_name);
    private:
        std::string file_name;
        bool state=false;
        bool open_file();
    protected:

    };
}

#endif

/*pure virtual base class*
 *
 * constructor:
 *          read_with_mark(const string& file_name);
 *          read_with_mark(const string& file_name, const string& single_comment_mark);
 * interface:
 *          add_comment_mark(const string&);
 *          add_comment_mark(const string&,const string&);     //for couple comment mark
 *          rm_comment_mark(const string&);
 *          rm_comment_mark(const string&,const string&);
 *          reset ~ constructor                                //reset to a new file, but comment marks are kept.
 *
 */



