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
```C++
typedef char* pstring;
const pstring cstr = 0;     //cstr is const pointer pointing to char
const pstring *ps;          //ps is point pointing to a const char *
```

### auto  

