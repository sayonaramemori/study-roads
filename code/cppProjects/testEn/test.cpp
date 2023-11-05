#include<iostream>
#include<string>

class test{
public:
    test(){
        std::cout<<name<<std::endl;
    }
    std::string name = "hama";
private:
};

int main(){
    test hama;
    return 0;
}
