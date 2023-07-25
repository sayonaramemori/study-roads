#include"read_only.h"
#include<iostream>

int main(int argc, char *argv[]){
    if(argc!=2){
        std::cout<<"Please offer a input file"<<std::endl;
        std::cerr<<"no input, stop"<<std::endl;
        abort();
    }
    claris::read_only test(argv[1]);
    if(test.get_state()){
        std::cout<<test<<std::endl;
    }
    return 0;
}
