#include"read_with_mark.h"
using std::cout;
using std::endl;

int main(int argc,char* argv[]){
    if(argc!=2){
        cout<<"please proffer a file"<<endl;
        return 0;
    }
    claris::read_with_mark test(argv[1]);
    cout<<test<<endl;
    test.add_comment_mark('#');
    test.add_comment_mark("/*","*/");
    test.trim();
    cout<<test<<endl;
    return 0;
}
