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
    //For unique handler id
    private static final AtomicLong clientHandles = new AtomicLong(1L);
    private static final Logger logger = LoggerFactory.getLogger(Subscription.class);
    //Bind to handler
    private UaSubscription.ItemCreationCallback onItemCreated;
    //Reporting interval for every monitor item, default 1 second
    private double timeInterval = 1000.0;
    //This subscription is affiliated to this client
    private OpcUaClient client;
    //The true subscription described in bottom
    private UaSubscription subscription = null;

    //set a new default time interval for reporting
    public Subscription(OpcUaClient client, double timeInterval){
        this.client = client;
        this.timeInterval = timeInterval;
    }

    public Subscription(OpcUaClient client){
        this.client = client;
    }

    //the time interval can override the preset and default value.
    public void addNodeIdWithHandler(String id, Consumer<DataValue> fn, double timeInterval){
        this.timeInterval = timeInterval;
        addNodeIdWithHandler(id,fn);
    }

    public void addNodeIdWithHandler(String id, ValueConsumer fn, double timeInterval){
        this.timeInterval = timeInterval;
        addNodeIdWithHandler(id,fn);
    }

    //Finally be called
    //Another type of handlers can be added on your own.
    public void addNodeIdWithHandler(String id, Consumer<DataValue> fn){
        this.onItemCreated = (item,index) -> item.setValueConsumer(fn);
        subscribe(new NodeId(Client.nameSpaceIndex,id));
    }

    public void addNodeIdWithHandler(String id, ValueConsumer fn){
        this.onItemCreated = (item,index) -> item.setValueConsumer(fn);
        subscribe(new NodeId(Client.nameSpaceIndex,id));
    }

    //Simply box a id
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
            //Exception thrown here
            //Init subscription first
            if(subscription==null)this.subscription = this.client.getSubscriptionManager().createSubscription(timeInterval).get();
            ReadValueId readValueId = getReadValueId(id);

            //Exception thrown here
            //Mount an item on this subscription and automatically start.
            UaMonitoredItem item = createMonitoredItem(readValueId,MonitoringMode.Reporting);

            //Check whether the item be mounted well
            if (item.getStatusCode().isGood()) {
                logger.info("Monitor item was mounted for nodeId={}", item.getReadValueId().getNodeId());
            } else {
                logger.error("Failed to mount item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }catch(Exception e){
            logger.error("Create subscription or mount node failed for: {}" , e.getMessage());
        }
    }

    //Parameter Mode can be omitted too.
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
