package jdbcdemo.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

import java.util.Properties;
import java.io.FileInputStream;


public class ConnectJdbc{
    //for durid test
    public ConnectJdbc(String path){
        this.path = path;
    }
    public ConnectJdbc(String name, String passID, String url){
        this.passwd = passID;
        this.user = name;
        this.url = url;
    }
    private String url;
    private String user;
    private String passwd;
    private String path;
    private DataSource DSN;

    public void testInsert(String sql) throws SQLException,ClassNotFoundException
    {
        System.out.println(System.getProperty("user.dir"));
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,user,passwd);
        Statement stmt = con.createStatement();
        int count = stmt.executeUpdate(sql);
        System.out.println(count);
        stmt.close();
        con.close();
    }
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

    private static void printResultSet(ResultSet res)throws SQLException,ClassNotFoundException {
        while(res.next()){
            for(int i=1;i<4;++i){
                System.out.print(res.getString(i)+"  ");
            }
            System.out.println("");
        }
    }

    public void testSelect(String sql) throws SQLException,ClassNotFoundException
    {
        System.out.println(System.getProperty("user.dir"));
        Connection con = DriverManager.getConnection(url,user,passwd);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        printResultSet(res);
        res.close();
        stmt.close();
        con.close();
    }

}
