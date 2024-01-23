package opcua.opcuasdk;

import com.google.common.collect.ImmutableList;
// import opcua.demo.ClientExample;
// import opcua.demo.ClientExampleRunner;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
// import org.omg.CORBA.Object;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;

//Recommand to use static funcitons writByClient
//Writing by creating a new client sometimes throws some exceptions.
public class WriteToNode implements Client{
    private static final Logger logger = LoggerFactory.getLogger(WriteToNode.class);
    private NodeId id;
    private DataValue val;

    private static DataValue getDataValue(Boolean num){
        return new DataValue(new Variant(num),null,null);
    }
    WriteToNode(String id, Boolean tf){
        this.id = new NodeId(Client.nameSpaceIndex,id);
        this.val = new DataValue(new Variant(tf),null,null);
    }
    WriteToNode(NodeId id, Boolean tf){
        this.id = id;
        this.val = new DataValue(new Variant(tf),null,null);
    }
    
    private static DataValue getDataValue(Number num){
        return new DataValue(new Variant(num),null,null);
    }
    WriteToNode(String id, Number val){
        this.id = new NodeId(Client.nameSpaceIndex,id);
        this.val = getDataValue(val);
    }
    WriteToNode(NodeId id, Number val){
        this.id = id;
        this.val = getDataValue(val);
    }


    public static boolean writeByClient(OpcUaClient client, String id, Boolean dv){
        return writeByClient(client, new NodeId(Client.nameSpaceIndex,id), getDataValue(dv));
    }
    public static boolean writeByClient(OpcUaClient client, NodeId id, Boolean dv){
        return writeByClient(client, id, getDataValue(dv));
    }
    public static boolean writeByClient(OpcUaClient client, String id, Number dv){
        return writeByClient(client, new NodeId(Client.nameSpaceIndex,id), getDataValue(dv));
    }
    public static boolean writeByClient(OpcUaClient client, NodeId id, Number dv){
        return writeByClient(client, id, getDataValue(dv));
    }

    //Finally be called
    public static boolean writeByClient(OpcUaClient client, NodeId id, DataValue dv){
        try{
            StatusCode status = client.writeValue(id,dv).get();
            if(status.isGood())
                return true;
            else 
                return false;
        }catch(Exception ex){
            logger.error("An error happend when writing to a node: {}, for the reason: {}", id.toString(), ex.getMessage());
            return false;
        }
    }
    

    //The functions bellow only write once with the specified client.
    //Then the client will be released.
    //The function here is the eventually called.
    //If write failed, only error info logged without return value 
    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect().get();
        client.writeValue(id, val).get();
        future.complete(client);
    }
    
    public static <T extends Number> void writeNumber(String id, T val){
        try{
            WriteToNode example = new WriteToNode(id, val);
            new ClientRunner(example).run();
        }catch(Exception ex){
            logger.error("Error happened when writing, {}", ex.getMessage(),ex);
        }
    }

    public static void writeBoolean(String id, Boolean val){
        try{
            WriteToNode example = new WriteToNode(id, val);
            new ClientRunner(example).run();
        }catch(Exception ex){
            logger.error("Error happened when writing, {}", ex.getMessage(),ex);
        }
    }
}
