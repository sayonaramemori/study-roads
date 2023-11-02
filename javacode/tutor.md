### 1.1 Basic Type
> No unsigned version
|type|key_word|
|:---|:----|
|int|byte|
||short|
||int|
||long|
|float|float|
||double|
|char|char|
|bool|boolean|

```java
//In C++,this is ok to compile, but not in java.
//integer experssion x=0 cannot be converted to a boolean value
if(x=0){...}
```


### 1.2 Declaraion
```C++
//C and C++ distinguish between the declaration and definition of a variable. For example,
int i = 10;
//is a definition, whereas
extern int i;
is a declaration. In java, no declararion are separate from definitions.
```

### 1.3.1 Constants
```Java
//using final
public class Constants
{
    public static final double inch = 2.54;
    public static void main(String args[])
    {
        final double pi = 3.14;
    }
}
```

### 1.3.2 strictfp
> To yield reproducible results.
```
public static strictfp void main(String args[]){}
```

### 


