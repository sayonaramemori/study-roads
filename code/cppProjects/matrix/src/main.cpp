#include"matrix.h"
#include"read_for_matrix.h"

using std::cout;
using std::endl;

int main(int argc,char* argv[]){
    if(argc!=2){
        cout<<"please proffer a file"<<endl;
        return 0;
    }
    read_for_matrix test(argv[1]);
    /*
    std::vector<std::vector<double>> data={
        {1,2,3,1},
        {4,5,6,1},
        {7,8,9,1}
    };
    */
    cout<<test<<endl;
    test.add_comment_mark("/*","*/");
    test.trim();
    cout<<test<<endl;
    return 0;
}
