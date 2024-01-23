### The code structure
```
                                                              --> Datas Record
                                                             /
        (Example)                                           /
        Subscribe ---> handler ----> logger ----> DataBase--        
         /                A                                 \
        /                 |                                  \---> Specific Instructions <-------- Front End
        V                 |                                  /
OpcUa== *               utils                           <----
        A                 |                            /
        \                 |                           /
         \                V                          /
        Write<----Monitor(handler)<---Querylogger <--
        (Example)


```

### How to use
1. Implement Client and put your object to ClientRunner then calling 'run' method in 'example' directory.
2. Freely use my sdk.
3. Run `mvn test` to test.

### Something vulnerable
1. The url is hard code in Client.
2. Exception handling is not robust.
3. In current code terminating the application is always by hand without calling complete and keeping blocking.


