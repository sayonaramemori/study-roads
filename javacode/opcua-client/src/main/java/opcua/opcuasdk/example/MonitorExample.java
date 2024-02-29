<<<<<<< HEAD
package opcua.opcuasdk.example;

import com.google.common.collect.ImmutableList;

import opcua.opcuasdk.*;
import opcua.util.*;
import opcua.handler.*;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.sdk.client.dtd.DataTypeDictionarySessionInitializer;
import org.eclipse.milo.opcua.binaryschema.GenericBsdParser;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import opcua.mybatis.logger.*;

public class MonitorExample implements Client{

    
    private static final Logger logger = LoggerFactory.getLogger(IOExample.class);
    public static void test(){
        // CompletableFuture.runAsync(()->{
            try {
                MonitorExample example = new MonitorExample();
                new ClientRunner(example,0).run();
                System.out.println("Runing example over");
            } catch (Exception e) {
                logger.error("error test", e);
            }
        // }).thenRun(()->System.out.println("Thread finished")); 
    }
   @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception { 
        client.connect().get();

        MonitorSp sp = new MonitorSp(readInstruction::readSp,false);
        System.out.println("Start monitoring");
        sp.start(client);
    }
}
=======
package opcua.opcuasdk.example;

import com.google.common.collect.ImmutableList;

import opcua.opcuasdk.*;
import opcua.util.*;
import opcua.handler.*;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.sdk.client.dtd.DataTypeDictionarySessionInitializer;
import org.eclipse.milo.opcua.binaryschema.GenericBsdParser;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import opcua.mybatis.logger.*;

public class MonitorExample implements Client{

    
    private static final Logger logger = LoggerFactory.getLogger(IOExample.class);
    public static void test(){
        // CompletableFuture.runAsync(()->{
            try {
                MonitorExample example = new MonitorExample();
                new ClientRunner(example,0).run();
                System.out.println("Runing example over");
            } catch (Exception e) {
                logger.error("error test", e);
            }
        // }).thenRun(()->System.out.println("Thread finished")); 
    }
   @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception { 
        client.connect().get();

        MonitorSp sp = new MonitorSp(readInstruction::readSp,false);
        System.out.println("Start monitoring");
        sp.start(client);
    }
}
>>>>>>> 9635c473ca24ad51e6da34a1359f80e1e58d40a7
