package opcua.opcuasdk;
import opcua.demo.*;
import opcua.opcuasdk.*;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import opcua.demo.testMybatis;
import opcua.opcuasdk.SubscriptionExample;

public class AppTest 
{
    @Test 
    public void testDemo() {
        try {
            // UnifiedAutomationReadCustomDataTypeExample.test();
            new Thread(()->IOExample.test(null)).start();
            Thread.sleep(20000);
            MonitorExample.test();
            // SubscriptionExample.test();
        //    testMybatis.test();
        //    testOpcUa.test();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
