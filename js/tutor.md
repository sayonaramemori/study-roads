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


7. array map(fn(ele,index){
    //every element changed
    return ele + 'color';
});

8. string join(separator='');
9. void forEach(func(ele,index))
10. operator[index]      //you can specify index out of range and assign to it, blank position will be empty;

// Field
2. length
```


#### Function
```javascript
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

### Get DOM object by css selector
> document.method and return an object  

|method|explain|  
|:---|:----|
|querySelector(selector)|Get the first element matched|
|querySelectorAll(selector)|Get all elements mathed, return an fake array, read only|  


### classList
```javascript
const div = document.querySelector('div');
div.classList.add('active');
div.classList.remove('active');

//if not exists, then add, or remove
div.classList.toggle('active');
```

### SetTimeEvent
```javascript
let timer = setInterval(fn,1000);
clearInterval(timer);
//可以不清除
let timeout = setTimeout(fn,1000);

```

### EventListener
> obj.addEventListener('event-type',func);   
> bind freely, no covering  
> use obj.removeEventListener('type',func-name), but anonymous func cann't be removed.

|Event|explain|
|:---|:---|
|click||
|mouseenter||
|mouseleave||
|focus|表单获得焦点|
|blur|表单失去标点|
|keydown||
|keyup||
|input|用户输入|
|load|资源已加载|
|DOMContentLoaded|HTML文档已加载|
|scroll||
|submit|表单提交事件|

```javascript
    box.addEventListener('click',function(){
        box.style.display = 'none';
    });
```

### Event Object & this
```javascript
obj.addEventListener('click',function(e){
    console.log(e);
    //this point to obj which is caller
    console.log(this);
    //prevent event bubble
    e.stopPropagation();
    //prevent default behaviour
    e.preventDefault();
});
```

### DOM Node
> result the same as querySelector
```javascript
let = obj.parentNode.parentNode...;
obj.previousElementSibling;
obj.nextElementSibling;
obj.children;  //the all 

//create node
const node = document.createElement('tag');

//add node
fatherNode.appendChild(node);
fatherNode.insertBefore(node,target-before-node);

//copy node, if bool is true copy the children together
const obj = node.cloneNode(bool=false)

//delete node by father
fatherNode.removeChild(node);

```

### BOM
```
                        window
                          |
  ------------------------------------------
  |         |         |          |         |
navigator location  document   history   screen
```



### Test mobile
```javascript
(function(){
    const userAgent = navigator.userAgent;
    const android = userAgent.match(/(Android);?[\s\/]+([\d.]+)?/);
    const iphone = userAgent.match(/(iPhone\sOS)\s([\d_]+)/);
    if (android || iphone)
        location.href =  'http://m.itcast.cn';
})();
```



### lambda 
> this in lambda point to fathere of caller
```javascript
const fn = x =>{};
const f = (x,y) => {};
const simple = x => console.log(x);
const ff = x=> x+x;

//return an object
const obj = (name) => ({username:name});
```


### Array destruct
> Quickly assgin to variables
```javascript
const arr = [1,2,3];
const [a,b,c] = arr;
console.log(a);
console.log(b);
console.log(c);
```

### Object destruct
```javascript
const {a,b} = {a:'hh',b:'gg'};
//use new_var_name to receive hh
const {a: new_var_name,b} = {a:'hh',b:'gg'};

//multiple level destruct
const pig = {
    name: "peiqi",
    family:{
        mother: "pig ma",
        father: "pig pa"
    },
    age: 6
}
const {name, family: {mother,father}} = pig;
```


### Object Constructor
```javascript
function Pig(name,age,gender){
    this.name = name;
    this.age = age;
    this.gender = gender;
}

//save memory
Pig.prototype.func = function(){
//this point to instantiation
}


const peppa = new Pig('pig',1,'male');

//static field
Pig.eye = 2;
//static method
Pig.sayHi = ()=>console.log('hi');
```

### Object static method
```javascript
array keys(target_obj);
array values(target_obj);
void assign(dest,source_obj);
```










