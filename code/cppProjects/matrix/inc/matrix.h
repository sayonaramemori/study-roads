#ifndef CLARIS_MATRIX
#define CLARIS_MATRIX

#include<iostream>
#include<cmath>
#include<vector>
#include<iomanip>
#include<algorithm>

namespace claris{

    class matrix{
    public:
        using MATRIX = std::vector<std::vector<double>>;
        enum {ROW=0,COL=1};
        ~matrix()=default;
        matrix(std::vector<std::vector<double>>,int type=COL);
        std::vector<double> get_root();
        void transform();
        int rank();
        double det();
        bool is_square(){return size_out==size_in;}
        bool empty(){return size_out==0;}
        matrix& operator=(const matrix&);
        matrix operator-(const matrix&);
        matrix operator+(const matrix&);
        matrix operator*(const matrix&);
        matrix operator*(double);

    private:
        double laplace(const std::vector<std::vector<double>> &val);
        //to make sure the correctly initailization
        void matrix::assign(const MATRIX& mat);
        bool same_shape(const matrix& mat);

    private:
        MATRIX data;
        size_t size_out;
        size_t size_in;

    friend std::ostream& operator<<(std::ostream& os,const matrix& ma);

    };

    std::ostream& operator<<(std::ostream& os,const matrix& ma);
    matrix operator*(int k, const matrix&);

}

//This is a unsolved problem that put this function to globle scope
//I just make it in this way----put it inside claris, and use using.
using claris::operator<<;

#endif

