//alert("This is the my first js");
console.log("test Js");
document.write("I am div");
document.write("<h1>Hello Js</h1>");
let num = 2 - "hhh";
let str = "str";
//document.write(num);
//document.write(Number(str));
//document.write(parseInt('888$jkjk'));

let arr = [];
for(let i=1;i<=9;i++){
    for(let j=1;j<=i;j++){
        let val = i*j;
        if((val%3)===0)arr.push(val);
        document.write(j + "x" + i + " = " + val +"   ");
    }
    document.write("<br>");
}
console.log(arr);
console.log(undefined || null);
console.log('str' || null);

let myobj = {
    name: "java",
    cp: "cpp",
    gender: "female"
}
myobj.hh = "hhh";
delete myobj.hh;
myobj['tea'] = "pot-tea";
console.log(myobj);
let hacker = myobj;
hacker["hacker"] = 999;

for(let key in myobj){
    //key is string
    console.log(key);
    console.log(myobj[key]);
}

let div = document.querySelectorAll('div');
console.dir(div);

for(let i=0;i<div.length;++i){
    div[i].style.color = 'red';
}

const btn = document.querySelector('.btn');
let i = 10;

let timer = setInterval(function(){
    btn.innerHTML = "agree(" + --i +"s)";
    if(i===0){
        btn.disabled = false;
        clearInterval(timer);
        btn.innerHTML = "agree";
    }
},1000);












