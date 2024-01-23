package opcua.opcuasdk;
import opcua.opcuasdk.example.*;
import opcua.demo.*;
import org.junit.Test;


public class AppTest 
{
    @Test 
    public void testDemo() {
        try {
            // UnifiedAutomationReadCustomDataTypeExample.test();
        //    testMybatis.test();
            testOpcUa.test();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
