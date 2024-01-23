package opcua.demo;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import opcua.opcuasdk.*;
import opcua.opcuasdk.example.*;

//simply call examples
public class testOpcUa {
    
    private static final Logger logger = LoggerFactory.getLogger(testOpcUa.class);
    public static void test(){

        CompletableFuture.runAsync(()->{
            try {
                SubscribeStructExample example = new SubscribeStructExample();
                new ClientRunner(example,0).run();
            } catch (Exception e) {
                logger.error("error test", e);
            }
        }).thenRun(()->System.out.println("Thread finished"));

        try {
            MonitorExample example = new MonitorExample();
            new ClientRunner(example,0).run();
        } catch (Exception e) {
            logger.error("error happends when init", e.getMessage(),e);
        }
    }
}
