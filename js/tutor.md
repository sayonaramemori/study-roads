### Comment
> The same as Cpp and Java
```javascript
/* 
more line commment
*/

//Then assign any value
//var is obsolete
```

### Declare a variable  
```JavaScript
//use let not var
let var_name;
var_name = value;
```

### DataStructure  
---
#### Basic type  
> number string boolean undefined null
```Javascript
/* using typeof to check type */
console.log(typeof variable);
//If invalid operating, return NaN, such as 
//num - "string";
//true or false
let boolean = true;
//undefine, only declaration without assignment
let nothing;
console.log(nothing);
//null, for object, the same as java
let test = null
```

#### Boolean
> ``,0,undefined,null,NaN is false. Others is true.

#### string  
> using ' or " or ` to capture a text
```javascript
//using `
let age = 18;
document.write(`I am ${age} now`);
```

#### Type Transform
> all type + string generate string
```
// The variable should start with number
Number(variable);  //return nan when invalid
parseInt(variable);
parseFloat(variable);
```
---
#### const  
> The same as top const in C++ on object
```Javascript
//Declare a const variable
const varible_name = initial_value;
```

#### Compare Operator

```javascript
//false
console.log(NaN === NaN);
```

|operator| explain|
|:---|:---|
| == |equal value|
| != | not equal value|
| === | euqal value and type|
|!== | not equal value or type|

#### Logic Operator
> The && and || only return the value, not transfer the return value to boolean
```java
//return undefined
console.log(10 && undefined);
```

---
#### Array  
> Array is an type of Object

```JavaScript
//data type can be different
let var_array = [...];

// Method
1. Type& operator[int]
2. Length push(ele...)    //add to end
3. Length unshift(ele...) //add to begin
4. LastValue pop()        //remove the last ele
5. FirstValue shift()     //remove the first el
6. void splice(begin,number) //remove n at begin, if number is not specified, remove all after begin

// Member
2. length
```


#### Function
```java
function func_name(args=default_value ...){
    ...
}
//call
func_name(args...);
//anonymous function, call after assginment
let fn = function(){}

//call when defined
(function (x,y){console.log(x+y);})();
```

#### Object
> Also a referrence the same as Java
```javascript
let obj = {
    key: value,
    func_key: function(){}
}
//add and access field
obj['new-field'] = new-value;
obj.field = new-value;
//delete field
delete obj.field;

obj.func_key();
```

#### Travel Object
```javascript
for(let key in obj){
    //key is string
    console.log(key);
    console.log(obj[key]);
}
```

## Web API

### Get DOM object
> jkjkjkjkjk






















