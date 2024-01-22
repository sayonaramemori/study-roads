package opcua.demo;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import opcua.opcuasdk.*;

public class testOpcUa {
    
    private static final Logger logger = LoggerFactory.getLogger(testOpcUa.class);
    public static void test(){

        CompletableFuture.runAsync(()->{
            try {
                IOExample example = new IOExample();
                new ClientRunner(example,0).run();
            } catch (Exception e) {
                logger.error("error test", e);
            }
        }).thenRun(()->System.out.println("Thread finished"));

        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            logger.error("error happends when init", e.getMessage(),e);
        }
        System.out.println("OK");
    }
}
