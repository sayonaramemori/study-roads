#include"read_only.h"

class read_for_matrix : public claris::read_only{
public:
    read_for_matrix()=delete;
    read_for_matrix(const std::string& file_name,const std::string& comment_mark=""):read_only(file_name,comment_mark){}
    virtual void trim() override;
private:
    std::vector<std::string> single_line_slim;
    virtual int check_comment(const std::string& line,const std::string& mark="") override;
    friend std::ostream& operator<<(std::ostream& os,const read_for_matrix& rfm);
};

std::ostream& operator<<(std::ostream& os,const read_for_matrix& rfm);

