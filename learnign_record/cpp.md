# C++ Primer  

### const top-level and low-level  
> low-level const refers to the pointer or reference
```C++
int i=0;
int *const p1 = &i;         //top-level
const int *p=nullptr;       //low-level
const int *const p2 = &i;   //low and top level
```

### const expression
> const expression refers to the data whose value stays const and be comfirmed when compiling.

### typedef  
> type alias
```C++
typedef char* pstring;
const pstring cstr = 0;     //cstr is const pointer pointing to char
const pstring *ps;          //ps is point pointing to a const char *
```

### auto  
> automatically deduce the type of an expression on the left
>> auto often ignores the top-level const, but low-level const will be kept.
```C++
auto it = val + val_1;      //variable defined with auto should be initialized.
auto i = 42;                //error
const auto &j = 42          //correct
```

### decltype
> return a type of a given expression, include the top-level const and reference.
```C++
double x=5.0;
double &y=x;
const double *pd = x;
decltype(x) w;              //w is type double
decltype(y) u = x;          //u is type double &
decltype(pd) v;             //v is type const double *
long indeed(int);
decltype (indeed(3)) m;     //m is type int. It not actually call this function.
decltype ((x)) r = x;       //r is type double &, only for a variable
decltyps(x+y) j;            //j is type double

/* decltype for template*/
template<typename T2,typename T2>
auto gt(T1 x, T2 y)-> decltype(x+y)
{
    ...
}

//if exist function func, use decltype(func)* to get the same type function pointer
decltype(func)* temp = func;
```



