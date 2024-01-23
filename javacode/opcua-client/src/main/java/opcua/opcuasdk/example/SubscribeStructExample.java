package opcua.opcuasdk.example;

import com.google.common.collect.ImmutableList;
import opcua.handler.*;
import opcua.opcuasdk.*;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.binaryschema.GenericBsdParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.sdk.client.dtd.DataTypeDictionarySessionInitializer;
import java.util.concurrent.CompletableFuture;

import opcua.mybatis.logger.*;

public class SubscribeStructExample implements Client {

    public static void test(String[] args){
        // CompletableFuture.runAsync(()->{
            try {
                SubscribeStructExample example = new SubscribeStructExample();
                new ClientRunner(example,0).run();
                System.out.println("Runing example over");
            } catch (Exception e) {
                logger.error("error test", e);
            }
        // }).thenRun(()->System.out.println("Thread finished"));
    }

    private static final Logger logger = LoggerFactory.getLogger(IOExample.class);

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        //add for reading struct node
        client.addSessionInitializer(new DataTypeDictionarySessionInitializer(new GenericBsdParser()));
        // synchronous connect
        client.connect().get();

        Subscription sub1 = new Subscription(client);
        // sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.actualTemperature",(dv)->System.out.println("Temp is Now:"+ dv.getValue().getValue()));

        //Mount it
        sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.STRUCT_NODE", TemperatureHandler::handleStruct);
        //This could never be called.
        //future.complete(client);
    }
}
