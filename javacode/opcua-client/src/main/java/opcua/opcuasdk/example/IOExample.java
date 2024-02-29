<<<<<<< HEAD
/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */


package opcua.opcuasdk.example;

import com.google.common.collect.ImmutableList;
import opcua.handler.*;
import opcua.opcuasdk.*;

// import opcua.demo.ClientExample;
// import opcua.demo.ClientExampleRunner;
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

public class IOExample implements Client {

    public static void test(String[] args){

        // CompletableFuture.runAsync(()->{
            try {
                IOExample example = new IOExample();
                new ClientRunner(example,0).run();
                System.out.println("Runing example over");
            } catch (Exception e) {
                logger.error("error test", e);
            }
        // }).thenRun(()->System.out.println("Thread finished"));

    }

    private static final Logger logger = LoggerFactory.getLogger(IOExample.class);
    private final String identifier = "|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL";

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        //add for reading struct node
        client.addSessionInitializer(new DataTypeDictionarySessionInitializer(new GenericBsdParser()));
        // synchronous connect
        client.connect().get();

        Subscription sub1 = new Subscription(client);
        // sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.actualTemperature",(dv)->System.out.println("Temp is Now:"+ dv.getValue().getValue()));
        sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.STRUCT_NODE", TemperatureHandler::handleStruct);

        //Identifiers.DataTypesFolder
        // NodeId nodeIds = new NodeId(nameSpaceIndex,"|var|Sinsegye-x86_64-Linux-SM-CNC.Application.PLC_PRG");
        // List<? extends UaNode> nodesOfplc = client.getAddressSpace().browseNodes(nodeIds);
        // for(UaNode node:nodesOfplc){
        //     NodeId nodeId2 = node.getNodeId();
        //     System.out.println("--------------" + nodeId2.getIdentifier().toString() + node.getNodeClass().toString());
        //     if(node.getNodeClass() == NodeClass.Variable){
        //         DataValue value2 = client.readValue(0.0, TimestampsToReturn.Both, nodeId2).get();
        //         String index = nodeId2.getIdentifier().toString();
        //         System.out.println(index + ": "+ value2.getValue().getValue());
        //     }
        // }

        // // synchronous read request via VariableNode
        // UaVariableNode node = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus_StartTime);
        // DataValue value = node.readValue();
        // logger.info("StartTime={}", value.getValue().getValue());

        // // asynchronous read request
        // readServerStateAndTime(client).thenAccept(values -> {
        //     DataValue v0 = values.get(0);
        //     DataValue v1 = values.get(1);

        //     logger.info("State={}", ServerState.from((Integer) v0.getValue().getValue()));
        //     logger.info("CurrentTime={}", v1.getValue().getValue());

        //     System.out.println(v1.getValue().getValue());
        //     future.complete(client);
        // });
    }

    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
            Identifiers.Server_ServerStatus_State,
            Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
=======
/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */


package opcua.opcuasdk.example;

import com.google.common.collect.ImmutableList;
import opcua.handler.*;
import opcua.opcuasdk.*;

// import opcua.demo.ClientExample;
// import opcua.demo.ClientExampleRunner;
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

public class IOExample implements Client {

    public static void test(String[] args){

        // CompletableFuture.runAsync(()->{
            try {
                IOExample example = new IOExample();
                new ClientRunner(example,0).run();
                System.out.println("Runing example over");
            } catch (Exception e) {
                logger.error("error test", e);
            }
        // }).thenRun(()->System.out.println("Thread finished"));

    }

    private static final Logger logger = LoggerFactory.getLogger(IOExample.class);
    private final String identifier = "|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL";

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        //add for reading struct node
        client.addSessionInitializer(new DataTypeDictionarySessionInitializer(new GenericBsdParser()));
        // synchronous connect
        client.connect().get();

        Subscription sub1 = new Subscription(client);
        // sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.actualTemperature",(dv)->System.out.println("Temp is Now:"+ dv.getValue().getValue()));
        sub1.addNodeIdWithHandler("|var|Sinsegye-x86_64-Linux-SM-CNC.Application.GVL.STRUCT_NODE", TemperatureHandler::handleStruct);

        //Identifiers.DataTypesFolder
        // NodeId nodeIds = new NodeId(nameSpaceIndex,"|var|Sinsegye-x86_64-Linux-SM-CNC.Application.PLC_PRG");
        // List<? extends UaNode> nodesOfplc = client.getAddressSpace().browseNodes(nodeIds);
        // for(UaNode node:nodesOfplc){
        //     NodeId nodeId2 = node.getNodeId();
        //     System.out.println("--------------" + nodeId2.getIdentifier().toString() + node.getNodeClass().toString());
        //     if(node.getNodeClass() == NodeClass.Variable){
        //         DataValue value2 = client.readValue(0.0, TimestampsToReturn.Both, nodeId2).get();
        //         String index = nodeId2.getIdentifier().toString();
        //         System.out.println(index + ": "+ value2.getValue().getValue());
        //     }
        // }

        // // synchronous read request via VariableNode
        // UaVariableNode node = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus_StartTime);
        // DataValue value = node.readValue();
        // logger.info("StartTime={}", value.getValue().getValue());

        // // asynchronous read request
        // readServerStateAndTime(client).thenAccept(values -> {
        //     DataValue v0 = values.get(0);
        //     DataValue v1 = values.get(1);

        //     logger.info("State={}", ServerState.from((Integer) v0.getValue().getValue()));
        //     logger.info("CurrentTime={}", v1.getValue().getValue());

        //     System.out.println(v1.getValue().getValue());
        //     future.complete(client);
        // });
    }

    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
            Identifiers.Server_ServerStatus_State,
            Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
>>>>>>> 9635c473ca24ad51e6da34a1359f80e1e58d40a7
