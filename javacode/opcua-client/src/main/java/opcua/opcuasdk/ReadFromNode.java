<<<<<<< HEAD
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

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ReadFromNode {
    private static final Logger logger = LoggerFactory.getLogger(ReadFromNode.class);
    NodeId id;
    ReadFromNode(String id){
        this.id = new NodeId(Client.nameSpaceIndex,id);
    }
    public static DataValue readByClient(OpcUaClient client, String id){
        try {
            return client.readValue(0, TimestampsToReturn.Both, new NodeId(Client.nameSpaceIndex,id)).get();
        } catch (Exception e) {
            logger.error("Error happened when read node {}", id, e);
            return null;
        }
    }
}
=======
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

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ReadFromNode {
    private static final Logger logger = LoggerFactory.getLogger(ReadFromNode.class);
    NodeId id;
    ReadFromNode(String id){
        this.id = new NodeId(Client.nameSpaceIndex,id);
    }
    public static DataValue readByClient(OpcUaClient client, String id){
        try {
            return client.readValue(0, TimestampsToReturn.Both, new NodeId(Client.nameSpaceIndex,id)).get();
        } catch (Exception e) {
            logger.error("Error happened when read node {}", id, e);
            return null;
        }
    }
}
>>>>>>> 9635c473ca24ad51e6da34a1359f80e1e58d40a7
