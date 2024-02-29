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
import opcua.opcuasdk.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.ValueConsumer;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class SubscriptionExample implements Client {

    public static void test() throws Exception{
        SubscriptionExample example = new SubscriptionExample();
        new ClientRunner(example,0).run();
    }

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionExample.class);

    public static void getSubscriptionByClient(OpcUaClient client, String id, ValueConsumer fn) throws Exception{
        getSubscriptionByClient(client, new NodeId(Client.nameSpaceIndex, id),1000.0, fn);
    }
    public static void getSubscriptionByClient(OpcUaClient client, NodeId id, ValueConsumer fn) throws Exception{
        getSubscriptionByClient(client, id,1000.0, fn);
    }
    public static void getSubscriptionByClient(OpcUaClient client, NodeId id, double timeInterval, ValueConsumer fn) throws Exception{
        UaSubscription subscription = client.getSubscriptionManager().createSubscription(timeInterval).get();
        ReadValueId readValueId = new ReadValueId(
            id,
            AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE
        );
        UInteger clientHandle = subscription.nextClientHandle();
        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            timeInterval,     // sampling interval(ms)
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        UaSubscription.ItemCreationCallback onItemCreated = 
            (item, node) -> item.setValueConsumer(fn);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
                // System.out.println("item created for nodeId="+ item.getReadValueId().getNodeId());
            } else {
                logger.error(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }
    }

    //This function is only an example.
    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        System.out.println("This Client is: "+ client.toString());
        // create a subscription @ 1000ms
        UaSubscription subscription = client.getSubscriptionManager().createSubscription(1000.0).get();

        // subscribe to the Value attribute of the server's CurrentTime node
        NodeId temp = new NodeId(4,"|var|Sinsegye-x86_64-Linux-SM-CNC.Application.PLC_PRG.actualTemperature");
        ReadValueId readValueId = new ReadValueId(
            // Identifiers.Server_ServerStatus_CurrentTime,
            temp,
            AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE
        );

        // IMPORTANT: client handle must be unique per item within the context of a subscription.
        // You are not required to use the UaSubscription's client handle sequence; it is provided as a convenience.
        // Your application is free to assign client handles by whatever means necessary.
        UInteger clientHandle = subscription.nextClientHandle();

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            1000.0,     // sampling interval
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        // when creating items in MonitoringMode.Reporting this callback is where each item needs to have its
        // value/event consumer hooked up. The alternative is to create the item in sampling mode, hook up the
        // consumer after the creation call completes, and then change the mode for all items to reporting.
        UaSubscription.ItemCreationCallback onItemCreated =
            (item, id) -> item.setValueConsumer(this::onSubscription);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
                System.out.println("item created for nodeId="+ item.getReadValueId().getNodeId());
            } else {
                logger.warn(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }

        // let the example run for 5 seconds then terminate
        Thread.sleep(5000);
        future.complete(client);
    }

    private void onSubscription(UaMonitoredItem item, DataValue value) {
        System.out.println(item.getClient());
        System.out.println(
            "subscription value received: " + item.getReadValueId().getNodeId()+",value=" +value.getValue()+",with Source time:"+value.getSourceTime());
    }

    public void onSubscriptionValue(DataValue value) {
        System.out.println(
            "value=" +value.getValue()+",with Source time:"+value.getSourceTime());
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
import opcua.opcuasdk.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.ValueConsumer;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class SubscriptionExample implements Client {

    public static void test() throws Exception{
        SubscriptionExample example = new SubscriptionExample();
        new ClientRunner(example,0).run();
    }

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionExample.class);

    public static void getSubscriptionByClient(OpcUaClient client, String id, ValueConsumer fn) throws Exception{
        getSubscriptionByClient(client, new NodeId(Client.nameSpaceIndex, id),1000.0, fn);
    }
    public static void getSubscriptionByClient(OpcUaClient client, NodeId id, ValueConsumer fn) throws Exception{
        getSubscriptionByClient(client, id,1000.0, fn);
    }
    public static void getSubscriptionByClient(OpcUaClient client, NodeId id, double timeInterval, ValueConsumer fn) throws Exception{
        UaSubscription subscription = client.getSubscriptionManager().createSubscription(timeInterval).get();
        ReadValueId readValueId = new ReadValueId(
            id,
            AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE
        );
        UInteger clientHandle = subscription.nextClientHandle();
        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            timeInterval,     // sampling interval(ms)
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        UaSubscription.ItemCreationCallback onItemCreated = 
            (item, node) -> item.setValueConsumer(fn);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
                // System.out.println("item created for nodeId="+ item.getReadValueId().getNodeId());
            } else {
                logger.error(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }
    }

    //This function is only an example.
    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        System.out.println("This Client is: "+ client.toString());
        // create a subscription @ 1000ms
        UaSubscription subscription = client.getSubscriptionManager().createSubscription(1000.0).get();

        // subscribe to the Value attribute of the server's CurrentTime node
        NodeId temp = new NodeId(4,"|var|Sinsegye-x86_64-Linux-SM-CNC.Application.PLC_PRG.actualTemperature");
        ReadValueId readValueId = new ReadValueId(
            // Identifiers.Server_ServerStatus_CurrentTime,
            temp,
            AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE
        );

        // IMPORTANT: client handle must be unique per item within the context of a subscription.
        // You are not required to use the UaSubscription's client handle sequence; it is provided as a convenience.
        // Your application is free to assign client handles by whatever means necessary.
        UInteger clientHandle = subscription.nextClientHandle();

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            1000.0,     // sampling interval
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        // when creating items in MonitoringMode.Reporting this callback is where each item needs to have its
        // value/event consumer hooked up. The alternative is to create the item in sampling mode, hook up the
        // consumer after the creation call completes, and then change the mode for all items to reporting.
        UaSubscription.ItemCreationCallback onItemCreated =
            (item, id) -> item.setValueConsumer(this::onSubscription);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
                System.out.println("item created for nodeId="+ item.getReadValueId().getNodeId());
            } else {
                logger.warn(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }

        // let the example run for 5 seconds then terminate
        Thread.sleep(5000);
        future.complete(client);
    }

    private void onSubscription(UaMonitoredItem item, DataValue value) {
        System.out.println(item.getClient());
        System.out.println(
            "subscription value received: " + item.getReadValueId().getNodeId()+",value=" +value.getValue()+",with Source time:"+value.getSourceTime());
    }

    public void onSubscriptionValue(DataValue value) {
        System.out.println(
            "value=" +value.getValue()+",with Source time:"+value.getSourceTime());
    }

}
>>>>>>> 9635c473ca24ad51e6da34a1359f80e1e58d40a7
