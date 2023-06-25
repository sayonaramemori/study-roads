#include<iostream>
#include<string>

namespace claris{

    class string_number{
        public:
            string_number(double val);
            string_number(int val);
            string_number(const std::string& val);
            string_number(const string_number& val);
            string_number operator+(const string_number& val);
            string_number operator-(const string_number& val);
            string_number operator*(const string_number& val);
            string_number operator/(const string_number& val);
            string_number operator=(const string_number& val);
            std::ostream& operator<<(const string_number& val);
        private:
            void assign(const std::string& val);
            int max_figure;
            int float_point;
            std::string significant_figures;

    };
}


