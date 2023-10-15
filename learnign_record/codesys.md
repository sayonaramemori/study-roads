### *Structure Text -- A script language like Pascal*
#### *Operating Signal*

|操作符|符号|
|:--:|:--:|
|括号|()|
|函数调用|Func(parameter list)|
|幂|EXPT|
|加减乘除|+ - \* /|
|取模|MOD|
|比较|\<,\>,\>=,\<=,=,\<\>|
|逻辑|AND,XOR,OR,NOT|
|沿逻辑|R\_TRIG,F\_TRIG|
> 优先级从上往下递减

#### 声明和赋值  
```codesys
//DECLARATION
VAR
    [NAME]:[TYPE];
    [NAME_ONE]:[TYPE]:=VALUE;
END_VAR

//ASSIGNMENT
[NAME]:=VALUE;
```

#### 条件语句  
```
IF <BOOL TEST> THEN
    *statements*
ELSIF <BOOL TEST> THEN
    *statements*
ELSE
    *statements*
END_IF
```


#### 分支语句
```
CASE VARIABLE OF
//match only one
N1:
    STATEMENTS;
//or
N2,N8:
    STATEMENTS;
//[begin,end]
N8..Nn:
    STATEMENTS;
//default
ELSE:
    STATEMENTS;
END_CASE;
```

#### 循环语句
```
FOR VARIABLE:=INITIAL_VALUE TO TARGAT [BY STEP] DO
    STATEMENTS;
END_FOR;

WHILE <BOOL TEST>
    STATEMENTS;
END_WHILE;

REPEAT
    STATEMENTS;
UNTIL
//The condition to stop
    <BOOL TEST>
END_REPEAT;

//Control statements
//Equal to break
EXIT; 
CONTINUE;
```

#### 常用功能块
##### 沿检测指令
```
(* Example declaration *)
RTRIGInst : R_TRIG ;
FTRIGInst : F_TRIG ;

(* Example in ST *)
RTRIGInst(CLK := VarBOOL1);
VarBOOL2 := RTRIGInst.Q;
```

##### 定时器
```
(* Example declaration *)
TONInst : TON ;

(* Example in ST *)
TONInst(IN := VarBOOL1, PT:= T#5s);
VarBOOL2 := TONInst.Q;
```


