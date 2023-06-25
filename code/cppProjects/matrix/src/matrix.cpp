#include"matrix.h"

namespace claris{
    matrix& matrix::operator=(const matrix& mat){
        //totally copy
        this->data = mat.data;
        this->size_out = mat.size_out;
        this->size_in = mat.size_in;
        return *this;
    }

    matrix matrix::operator-(const matrix& mat)const{
        if(!same_shape(mat)){
            std::cout<<"Two matrix with different shape, - operation failed"<<std::endl;
            abort();
        }
        matrix temp = *this;
        for(int i=0;i<size_out;++i)
            for(int j=0;j<size_in;++j){
                temp.data[i][j] -= mat.data[i][j];
            }
        return temp;
    }


    matrix matrix::operator+(const matrix& mat)const{
        if(!same_shape(mat)){
            std::cout<<"Two matrix with different shape, + operation failed"<<std::endl;
            abort();
        }
        matrix temp = *this;
        for(int i=0;i<size_out;++i)
            for(int j=0;j<size_in;++j){
                temp.data[i][j] += mat.data[i][j];
            }
        return temp;
    }

    matrix matrix::operator*(const matrix& mat)const{
        if(this->size_out!=mat.size_in){
            std::cout<<"Two matrix with different column & row, * operation failed"<<std::endl;
            abort();
        }
        matrix temp(std::vector<std::vector<double>> (mat.size_out,std::vector<double>(this->size_in,0.0)));
        for(int row=0;row<temp.size_in;++row)
            for(int col=0;col<temp.size_out;++col){
                double res = 0;
                for(int i=0;i<this->size_out;++i){
                    res += mat.data[col][i]*locate(row,i);
                }
                temp.data[row][col] = res;
            }
        return temp;
    }

    matrix matrix::operator*(double k)const{
        auto temp = *this;
        for(int i=0;i<size_out;++i)
            for(int j=0;j<size_in;++j){
                temp.data[i][j] *= k;
            }
        return temp;
    }

    double matrix::det(){
        if(is_square())return laplace(data);
        MATRIX temp;
        temp = data;
        if(size_out>size_in){
            temp.resize(size_in);
        }else{
            for(auto&v:temp){
                v.resize(size_out);
            }
        }
        return laplace(temp);
    }


    // Function to calculate the determinant of a square matrix
    double matrix::laplace(const std::vector<std::vector<double>> &val) {
        int n = val.size();
        double det = 0.0;
        if (n == 1) {
            return val[0][0];
        }
        else if (n == 2) {
            return (val[0][0] * val[1][1]) - (val[0][1] * val[1][0]);
        }
        else {
            for (int k = 0; k < n; k++) {
                std::vector<std::vector<double>> subval(n - 1, std::vector<double>(n - 1));

                for (int i = 1; i < n; i++) {
                    int j = 0;
                    for (int l = 0; l < n; l++) {
                        if (l != k) {
                            subval[i - 1][j] = val[i][l];
                            j++;
                        }
                    }
                }
                det += std::pow(-1.0, k) * val[0][k] * laplace(subval);
            }
        }
        return det;
    }

    //if no root, return empty;
    std::vector<double> matrix::get_root(){
        if(size_out - 1 != size_in)return {};
        auto last = data.back();
        auto D = data;
        D.pop_back();
        double det_D = laplace(D);
        if(det_D==0)return {};
        std::vector<double> det_DN;
        for(int i=0;i<D.size();++i){
            auto tmp = D;
            tmp[i] = last;
            double res = laplace(tmp);
            det_DN.push_back(res);
        }
        std::for_each(det_DN.begin(),det_DN.end(),[&](double &val){
                val = val/det_D;
        });
        return det_DN;
    }


    void matrix::transform(){
        MATRIX tmp(size_in,std::vector<double>(size_out));
        for(int i=0;i<size_out;++i){
            for(int j=0;j<size_in;++j){
                tmp[j][i] = data[i][j];
            }
        }
        double sizeTmp = size_in;
        size_in = size_out;
        size_out = sizeTmp;
        data = tmp;
    }

    matrix::matrix(std::vector<std::vector<double>> val,int type){
        size_out = val.size();
        size_in = val[0].size();
        this->assign(val);
        if(type==ROW)transform();
    }

    void matrix::assign(const MATRIX& mat){
        this->data = std::vector<std::vector<double>>(size_out,std::vector<double>(size_in,0));
        for(int i=0;i<size_out;++i){
            int size = mat[i].size();
            int index_min = (size_in>size)?size:size_in;
            for(int j=0;j<index_min;++j){
                data[i][j] = mat[i][j];
            }
        }
        return;
    }


    std::ostream& operator<<(std::ostream& os,const matrix& ma)
    {
        int index = 0;
        int end = ma.size_in;
        int last = end - 1;
        std::cout<<std::fixed<<std::setprecision(2);
        for(;index<end;++index){
            //head
            if(index==0){
                std::cout<<"/ ";
            }else{
                if(index==last)std::cout<<"\\ ";
                else std::cout<<"| ";
            }
            //body
            std::for_each(ma.data.begin(),ma.data.end(),[&](std::vector<double> val){
                    std::cout<<val[index]<<" ";
            });
            //tail
            if(index==0){
                std::cout<<"\\";
            }else{
                if(index==last)std::cout<<"/";
                else std::cout<<"|";
            }
            std::cout<<std::endl;
        }
        return os;
    }

}
