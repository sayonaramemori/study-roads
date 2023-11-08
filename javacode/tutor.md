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
//is a declaration. In java, no declararion are separate from definitions.
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
2. Every class can have a `main` method. That is a handy trick for unit testing of classes.     
3. In Java, you must use the `*clone*` method to get a complete copy of an object.  
4. No const method in Java.
5. The name of the file must match the name of the public class. You can only have one public class in a source file, but you can have any number of nonpublic classes.  
6. You can think of the Java compiler as having the `make` functionality already built in.  
7. Object Parameters can be `null`, so ask yourself whether you really intent to model values that can be present or absent.If not, the "tough love" approach is preferred.  
8. As a rule of thumb, always use `clone` whenever you need to return a copy of a mutable field.  
9. C++ has the same rule. A method can access the private features of any object of its class, not just of the implicit parameter.
---

### Constructor
1. If you don't set a field explicitly in a constructor, it is automatically set to default value: numbers to 0, boolean values to false, and object references to null.  
2. C++ uses special initializer list syntax to call field constructors. In java there is no need for that because objects have no subobjects, only pointers to other objects.  
3. If the first statement of a constructor has the form `this(...)`, then the constructor calls another constructor of the same class. The same as the work of delegating constructor in C++.

```C++
//Constructors work the same way in Java as they do in C++.
//Kepp in mind, however, that all Java objects are constructed 
//on the heap and that a constructor must be combined with `new`.
//It is a common error of C++ programmers to forget the `new` operator:
Employee number007("jesus",1990); //C++, not Java
//That works in C++ but not in Java.
```

#### Initialization Blocks
> There is a third mechanism in Java. Class declarations can contain arbitrary blocks of code. These blocks are executed firstly whenever an object of that class is constructed.
```java
class Employee{
    private int id;
    private String name;
    private double salary;
    //object initialization blocks
    {
        salary=1000;
    }
}
```

#### Orders of the construction
1. If the first line of constructor calls a second constructor, then the second constructor executes with the provided arguments.  
2. Otherwise,  
    1. All data fields are initialized to their default values.(0,false or null).
    2. All field initializers and initialization blocks are executed, in the order in which they occur in the class declaration.  
3. The body of the constructor is executed.

#### Specially for static filed
> To initialize a static field, either supply an inital value or use a static initialization block. 
```
//Static initialization occurs when the class is first loaded.
public static int nextid;
static
{
    var generator = new Random();
    nextid = generator.nextInt(1000);
}
```

### Static Fields
> We recommend that you use class names, not objects, to invoke static methods. Static fields and methods have the same functionality in Java and C++. However, the syntax is slightly different. In C++, you use the :: operator to access a staitc field or method outside its scope, such as Math::PI.


### Final on object
---
> The same as the top const in C++.
---

### Method Parameters
> Primitive types are passed by value.  
> Object references are passed by value.  
1. A method cannot modify a parameter of a primitive type(that is, numbers or boolean values).
2. A method can change the state of an object parameter.
3. A method cannot make an object parameter refer to a new object.

### Package
> Its works similarly to namespace in C++.
> `import` directive is the same as `using` in C++.

#### Addition of a class into a Package
> Put the name of the package at the top of your source file, before the code that defines the classes int the package.
> You should compile and execute at project directory and specify the package name which corresponds to their subdirectory.

#### Package Access  
|Key word|Scope|
|:---|:----|
|public|Any class|
|nothing|Package|
|private|Class defining them|


#### Class Importation
```java
java.time.LocalDate today = java.time.LocalDate.now();
import java.time.*;
//Then you can use
LocalDate today = LocalDate.now();
//However, note that you can only use the * notation to import a single package. You cannot use import java.* or import java.*.* to import all packages with the java prefix.
//Suppose you write a program that imports both packages.
import java.util.*;
import java.sql.*;
//If you now use the Date class, you get a compile-time error:
Date today; //Error  -- java.util.Date or java.sql.Date?
//You can solve this problem by adding a specific import statement, or use the full package name with every class name.
import java.util.*;
import java.sql.*;
import java.util.Date;
var deadline = new java.util.Date();
var today = new java.sql.Date(...);
```

#### Static Imports
> A form of the `import` statement permits the importing of static methods and fields, not just classes.
```java
import static java.lang.System.*;
out.println("Hello world");
```

---
#### jar
---

---
### Comments for documents
---



### Inheritance
1. Inheritance is similar in Java and C++. Java uses the extends keyword instead of the : token. All inheritance in Java is public inheritance;
2. there is no analog to the C++ features of private and protected inheritance.
3. Every method except constructor can be overrided, but using virtual keyword in C++ to override.
4. Overloading functions is be inherited and overriding is unlike C++ which shadows the all functions with the same name in C++.

```java
public class Manager extends Employee{
    @override
    private bonus;
    public double getSalary(){
        //use super to call the superclass method in overriding functions while C++ uses the :: operator.
        double baseSalary = super.getSalary();  
        return baseSalary + bonus;
    }
}
```

#### Subclass Constructors
```java
//If the subclass constructor does not call a superclass constructor explicitly, the no-argument constructor of the superclass is invoked. If the superclass does not have a no-argument constructor
//and the subclass constructor does not call another superclass constructor explicitly, the Java compiler reports an error.
public Manager(String name, double salary){
    //A bit of difference from C++
    //Must be the first statement.
    super(name,salary);
    bonus = 0;
}

//It is legal to convert this array to an Employee[] array:
Manager managers[] = new Manager[10];
Employee staff[] = managers; //Ok;
```

#### Override in Inheritance
> When you override a method, the subclass method must be at least as visible as the superclass method the same as C++.
> If the superclass is public, the subclass method must also be declared public.


#### Prevent Inheritance
```
public final class Executive extends Manager{
...
}
```






















