### Dependency
```xml
<!-- MySQL的JDBC数据库驱动 添加至 pom.xml 文件中-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.19</version>
</dependency>
```

### Quick Start
```java
public class JDBCDemo {
    //1. register Driver
    Class.forName("com.mysql.jdbc.Driver");
    //2. get connection
    String url = "jdbc:mysql://127.0.0.1:3306/dbname";
    String username = "username";
    String passwd = "123456";
    Connection conn = DriverManager.getConnection(url, username, passwd);
    //3. start transaction
    Statement stmt = conn.createStatement();
    String sql_instruction = "update sheet-name set field = value;";
    stmt.executeUpdate(sql_instruction);
    //4. end transaction
    stmt.close();
    conn.close();
}
```

### API---Connection
```java
import java.sql.Connection;
public static void main(){
    String url = "jdbc:mysql://127.0.0.1:3306/dbname";
    String username = "username";
    String passwd = "123456";
    Connection conn = DriverManager.getConnection(url, username, passwd);
    try{
        //set auto_commit false
        conn.setAutoCommit(false);
        //execute sql here
        conn.commit();
    } catch(Exception throwables){
        conn.rollback();
        throwables.printStackTrace();
    }
    

}
```

### Statement

### ResultSet
```java
import java.sql.ResultSet;
```

### PreparedStatement
> Recommended
```
import java.sql.Statement;
//add parameter useServerPrepStmts=true
String url += "?useServerPrepStmts=true";
String name = value1;
String passwd = value2;
//将变量改为?
String sql = "select * from tb_user where username = ? and password = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
//设置问号的值
pstmt.setString(1,name);
pstmt.setString(2,passwd);
pstmt.executeQuery();
```

### DataSource Connection Pool
> Always used in practice, Druid for instance  
1. Import jar package.
    1. In maven add dependency.  
    2. Import to your java project.
2. Define druid.properties file and put it to workspace directory.  
3. Load property file.  
4. Get DataSource Object.
5. Get Connection Object and start sql.
```Java
private DataSource DSN;

public void testDruid() throws Exception
    {
        //1. import jar package
        //2. define properties in properties file
        
        //3. load properties file
        Properties prop = new Properties();
        prop.load(new FileInputStream(path));
        //4. get pool
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        
        //5.get connection object
        //Connection connection1 = dataSource.getConnection();
        //Connection connection2 = dataSource.getConnection();
        
        this.DSN = dataSource;
    }

private Connection getConnection()throws SQLException,ClassNotFoundException 
    {
        return DSN.getConnection();
    }

@Test
public void testSelectByDruid(String sql) throws SQLException,ClassNotFoundException 
    {
        Connection con = this.getConnection();
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        printResultSet(res);
        res.close();
        stmt.close();
        con.close();
    }
```



