#include<iostream>

inline void test(){
    std::cout<<"other file"<<std::endl;
}

void get(){
    test();
    return;
}
