## To find the difference from C++
### Basic Type
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


### Declaraion
```C++
//C and C++ distinguish between the declaration and definition of a variable. For example,
int i = 10;
//is a definition, whereas
extern int i;
is a declaration. In java, no declararion are separate from definitions.
```

### Constants
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

### strictfp
---
> To yield reproducible results.
---
```
cublic static strictfp void main(String args[]){}
```

### Enumerated Types
```Java
enum Size{small,medium,large};
Size s = Size.medium;
```

### Relational and boolean operators
---
> The && and || operators are evaluated in short circuit fasion. The same as C++.
---

### Comma operator
---
> Unlike C++, Java does not have a comma operator.
---

### Strings are immutable
1. The compiler can arrange that strings are shared
2. The designers of Java decided that the efficiency of sharing outweighs the inefficiency of string editing by extracting substrings and cconcatenating.
3. However, C++ strings are mutable - you can modify individual characters in a string.
4. Do not use the == operator to test whether two strings are equal. It only determines whether or not the strings are stored in the same location.

### Block scope
```Java
public static void main(String[] args){
    int n;
    {
        ink k;
        int n; //ERROR -- can't redefine n in inner block
    }
    public void anyfunc(){
        int n; //OK, shadow the instance fields with the same name
    }
}

```
---
> In C++, it is possible to redefine a variable inside a nested block. The inner definition the shadows the outer one. This can be source of programming errors; hence, Jave does not allow it.
---

### Operator Overload
---
> Unlike C++, Java has no programmable operator overloading.
---

### Auto deduce
---
> var is the same as auto keyword in C++  
> Note that the `var` keywork can only be used with *local* variables inside methods.
---

### toString Funtion
---
> define a method for a class with the name toString is the same as overloading << in C++.
---

## Arrays In Java

### Declaring Arrays
---
> The same as array in C++ of STL.
---
```Java
int[] a; //declaration
int[] a = new int[100]; //declares and initializes an array, the length should be constant.
int a[] = {1,2,3};      //not use new, so do not specify the length.
```

### Array Copying
```Java
//you can copy array variable into another, but then both variable refer to the same array
int a[] = another;
another[index] = number; //now a[index] is also number
//use copyOf method, the seconde parameter is the length of the new array.
//The additional elements are filled with 0 if out of range of another, false if boolean values.
int a[] = Arrays.copyOf(another,another.length);
```

---
> There is no pointer arithmetic - you can't increment `a` to point to the next element in the array.
---

### java.util.Arrays
```
toString(Type[]);
void sort(Type[]);
equals(a,b);
Type[] copyOfRange(Type[],int start,int end);
```

### Command-Line Parameters
```Java
/*
args[0]: parameters 1
args[1]: parameters 2
.......
*/
```

### Class
---
1. It is important to realize that an object variable doesn't actually contain an object. It only _refers_ to an object.  
2. .  
3. In Java, you must use the `*clone*` method to get a complete copy of an object.  
4. No const method in Java.
5. The name of the file must match the name of the public class. You can only have one public class in a source file, but you can have any number of nonpublic classes.  
6. You can think of the Java compiler as having the `make` functionality already built in.  
7. Object Parameters can be `null`, so ask yourself whether you really intent to model values that can be present or absent.If not, the "tough love" approach is preferred.  
8. As a rule of thumb, always use `clone` whenever you need to return a copy of a mutable field.  
9. C++ has the same rule. A method can access the private features of any object of its class, not just of the implicit parameter.
---

### Constructor
```C++
//Constructors work the same way in Java as they do in C++.
//Kepp in mind, however, that all Java objects are constructed 
//on the heap and that a constructor must be combined with `new`.
//It is a common error of C++ programmers to forget the `new` operator:
Employee number007("jesus",1990); //C++, not Java
//That works in C++ but not in Java.
```

### Final on object
---
> The same as the top const in C++.
---

### Static Fields







