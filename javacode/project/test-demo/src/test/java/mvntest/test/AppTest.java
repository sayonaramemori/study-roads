package mvntest.test;
import org.junit.Test;

public class AppTest 
{
    @Test
    public static void suite()
    {
        System.out.println("TESTOK >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    public AppTest(){
        System.out.println(msg);
    }
    private String msg = "SBMVN";
}
