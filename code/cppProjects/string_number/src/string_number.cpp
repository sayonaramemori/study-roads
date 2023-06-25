#include"string_number.h"

namespace claris{
    string_number::string_number(double val){
        auto temp = std::to_string(val);
        this->assign(temp);
    }
    string_number::string_number(int val){
        auto temp = std::to_string(val);
        this->assign(temp);
    }
    string_number::string_number(const std::string& val){
        this->assign(val);
    }


    void string_number::assign(const std::string& val){
        this->significant_figures = val;
#ifdef DEBUG
        std::cout<<"string_formate_number: "<<significant_figures<<std::endl;
#endif
        return;
    }






}
