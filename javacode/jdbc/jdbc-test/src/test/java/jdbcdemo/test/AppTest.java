package jdbcdemo.test; 
import org.junit.Test;
import java.sql.SQLException;


public class AppTest 
{
    @Test
    public void testDemo() throws Exception
    {
    //   ConnectJdbc test = new ConnectJdbc("sayonara","Iloveyouxuwu@121234","jdbc:mysql://127.0.0.1:3306/kepserver");
        String sql = "select * from sp_log";
        //test.testSelect(sql);

        //String sql = "insert into sp_log (sp) values(99);";
        //test.testInsert(sql);
        ConnectJdbc test = new ConnectJdbc("./druid.properties");
        test.testDruid();
        test.testSelectByDruid(sql);
    }
}
