package opcua.opcuasdk;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.ValueConsumer;
import org.eclipse.milo.opcua.stack.core.AttributeId;
//import org.eclipse.milo.opcua.stack.core.Identifiers;
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

//This is very important because it cost me a lot of time to figure out.
// import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import java.util.function.Consumer;
import opcua.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;


//Subscription is builded with a client, and it exists until the client released.
//If you want control Subscribtion, use managed subscribtion
public class Subscription {
    private static final AtomicLong clientHandles = new AtomicLong(1L);
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionExample.class);
    private UaSubscription.ItemCreationCallback onItemCreated;
    private double timeInterval = 1000.0;
    private OpcUaClient client;
    private UaSubscription subscription = null;

    public Subscription(OpcUaClient client, double timeInterval){
        this.client = client;
        this.timeInterval = timeInterval;
    }

    public Subscription(OpcUaClient client){
        this.client = client;
    }

    //the timeInterval can override the preset and default value.
    public void addNodeIdWithHandler(String id, Consumer<DataValue> fn, double timeInterval){
        this.timeInterval = timeInterval;
        addNodeIdWithHandler(id,fn);
    }

    public void addNodeIdWithHandler(String id, ValueConsumer fn, double timeInterval){
        this.timeInterval = timeInterval;
        addNodeIdWithHandler(id,fn);
    }

    //Finally be called
    public void addNodeIdWithHandler(String id, Consumer<DataValue> fn){
        this.onItemCreated = (item,index) -> item.setValueConsumer(fn);
        subscribe(new NodeId(Client.nameSpaceIndex,id));
    }

    public void addNodeIdWithHandler(String id, ValueConsumer fn){
        this.onItemCreated = (item,index) -> item.setValueConsumer(fn);
        subscribe(new NodeId(Client.nameSpaceIndex,id));
    }

    // public void addNodeIdWithHandler(String id, ValueConsumer fn){
    //     this.nodeIdsAndHandle.add(new Pair(id,fn));
    // }

    private ReadValueId getReadValueId(NodeId id){
        ReadValueId readValueId = new ReadValueId(
            id,
            AttributeId.Value.uid(),
            null, 
            QualifiedName.NULL_VALUE
        );
        return readValueId;
    }

    //create an subscription on a node and do something with its value and your function provided.
    private void subscribe(NodeId id) {
        try{
            //Mount all item on this subscription
            if(subscription==null)this.subscription = this.client.getSubscriptionManager().createSubscription(timeInterval).get();
            ReadValueId readValueId = getReadValueId(id);
            UaMonitoredItem item = createMonitoredItem(readValueId,MonitoringMode.Reporting);
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
            } else {
                logger.error(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }catch(Exception e){
            logger.error("Create subscription failed on Nodeid: " + id.toString());
        }
    }

    private UaMonitoredItem createMonitoredItem(
        // UaSubscription subscription,
        ReadValueId readValueId,
        MonitoringMode monitoringMode
        // Consumer<DataValue> valConsumer
    ) throws ExecutionException, InterruptedException {

        // important: client handle must be unique per item
        UInteger clientHandle = uint(clientHandles.getAndIncrement());

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            timeInterval,
            null,
            uint(10),
            true
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            monitoringMode,
            parameters
        );

        //bind handle
        //onItemCreated is initalized firstly

        //send request
        //onItemCreated is initialized already
        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            this.onItemCreated
        ).get();

        return items.get(0);
    }
}
