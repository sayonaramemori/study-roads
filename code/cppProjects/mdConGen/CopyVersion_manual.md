- [1.read func](#read func)
	- [1.1 getAllLine](#getAllLine)
		- [1.1.1 pushLine](#pushLine)
			- [1.1.1.1 checkFront](#checkFront)
	- [1.2 generate](#generate)
		- [1.2.1 getContent](#getContent)
			- [1.2.1.1 getRank](#getRank)
			- [1.2.1.2 getBody](#getBody)
				- [1.2.1.2.1 getNumber](#getNumber)
			- [1.2.1.3 getHead](#getHead)
			- [1.2.1.4 getTail](#getTail)
- [2.read data](#read data)
# read func<a href="#getAllLine">^</a>
## getAllLine<a href="#getAllLine">^</a>
```C++
/*
obtain the line of content table and the full message.
*/
void getAlline();
```
### pushLine<a href="#getAllLine">^</a>
```
/*
push a line to the DATA 'res'
it judges a line whether to push to res.
*/
void pushLine(const string& line);
```
#### checkFront<a href="#getAllLine">^</a>
```C++
/*
check the head of a line, starting with '#',return the number of '#',if not, return std::string::npos;
*/
int checkFront(const string& line);
```

## generate<a href="#getAllLine">^</a>
```C++
/*
generate the content table according to the DATA 'res',with three type
*/
void generate(int type);
```

### getContent<a href="#getAllLine">^</a>
```C++
/*
get the plain text of content table, without any prefix
*/
void getContent(int type);
```

#### getRank<a href="#getAllLine">^</a>
```C++
/*
sort the content line by the number of "#"
*/
void getRank();
```

#### getBody<a href="#getAllLine">^</a>
```C++
/*
get the body of a content line;
for example, [some texts] of "^I^I-[some texts](#anchor name)
*/
string getBody(vector<dataUnit>::iterator it,int* arr);
```
##### getNumber<a href="#getAllLine">^</a>
```C++
/*
get the number of a line
*/
string getNumber(int* arr,int level);
```

#### getHead<a href="#getAllLine">^</a>
```C++
/*
get the head of a content line;
for example, ^I^I- of "^I^I-[some texts](#anchor name)
cnly type withtab and withnumber are needing this func.
*/
string getHead(int level);
```

#### getTail<a href="#getAllLine">^</a>
```C++
/*
get the tail of a content line;
for example, (#anchor name) of "^I^I-[some texts](#anchor name)
*/
string getTail(const string& content);
```

# read data<a href="#getAllLine">^</a>
