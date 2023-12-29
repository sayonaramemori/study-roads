### TypeScript Basic
> Addtional part -- Type system
```TypeScript
let age:number = 18;
let arr: string[] = ['a'];
let arr: Array<boolean> = [true,false];
```
### Union type
```TypeScript
//This array can contain two types
let arr: (number | string)[] = [1,2,3,'4'];
let num: number | null = 99;
```

### TypeDefine
```ts
type MyType = (number | string);
let vari: MyType[] = [];
```

### Function
```ts
function add(a:number,b:number):number{
    return a+b;
}
//the parenthesis can not be omited
const sub = (a:number,b:number):number => {return a-b;}

type Functype = (a:number,b:number) => number;
const sub: Functype = (a,b) => {return a-b;}

//if no return value, then return type is void

//selectable parameters, the same as default parameters in cpp
const print = (name?: string, gender?: string): void =>{
    if(name && gender){
        console.log(name,gender);
    }
}
```

### object type
```ts
let obj:{name:string,age:number} = {name:'hh',age:18}
type objType = {
    name: string,
    age: 18,
    saHi: (content:string) => void,
    greet(name: string): void,
    girlfriend?: string
}
//? express the selectable field

let obj:objType={...}

//access ? field;
obj.grilfriend?.concat();
obj.girlfriend && obj.grilfriend.concat();
```

### interface
```ts
//omit semicolon is ok
interface IPerson{
    name: string
    age: number
    sayhi: () => void
}
//interface only works on OBJ, but type works on any

//inheritance
interface IStudent extends IPerson{
    new-field: type
}
```

### Tuple
```ts
let position: [number,number] = [114,115];
```

### Type auto deduce
```ts
//when init;
let a = 1;

//function return value;
function add(a:number,b:number){
    return a + b;
}
```

### literal type & enum type
```ts
type Gender = 'male' | 'female' | 'ghost';
function changeGender(gender: Gender){...}

enum direction{up, down, left, right}
```

### Generic Type
```
function add<T extends interface_abc>(val: T){
    return val;
}
const res = add<number>(1);
```



### setup
```js
<script lang="ts">
    export default{

    }
<script>
//auto return
<script setup lang="ts>
</script>
```


### computed
```js
let a = computed({
get(){
    //some ref statements
},
set(val){}
});
//call set(val)
a.value = value
```

### watch
```js
import {watch} from 'vue'
//1.watch basic type
//sum is the watched, without sum.value
//oldvalue can be omitted
const stop = watch(sum,(newValue,oldValue)=>{
    if(condition)stop();
})
//2.watch obj type, if only modify field, then new === old
//originally watch the location for an obj.
watch(()=>person,(new,old){},{deep:true})

//3.watch specific field with basic type
watch(()=>person.name,(new,old){});

//4.watch obj in obj
watch(()=>person.car,(new,old){},{deep:true})

//5.multiple watch
watch([()=>person.car,()=>person.name],(new,old){},{deep:true});
```

### tag ref
```js
import {ref} from 'vue'
//id can be used in template for ref
//<input ref="id">
let id = ref();

//An element of dom
id.value
```

### life stage
```js
import {onBeforeCreate,onCreated,onBeforeMount,onMounted,onBeforeUpdate,onUpdated}
onBeforeMount(()=>{});
```















