package opcua.handler;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import opcua.mybatis.pojo.*;
import opcua.util.*;
import opcua.mybatis.logger.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.DoubleSupplier;
import opcua.opcuasdk.WriteToNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import opcua.opcuasdk.Client;

public class MonitorSp {

    private double currentSp = 0;
    private DoubleSupplier source;
    //some bugs here, if you ignore, then input the same value twice will be lost.
    private Boolean ignoreInitial = false;
    private static Logger logger = LoggerFactory.getLogger(MonitorSp.class);
    public MonitorSp(DoubleSupplier ms, Boolean ignoreInitial){
        this.source = ms;
        this.ignoreInitial = ignoreInitial;
    }
    public MonitorSp(DoubleSupplier ms){
        this.source = ms;
    }
    public void start(OpcUaClient client){
        NodeId id = new NodeId(Client.nameSpaceIndex,"|var|Sinsegye-x86_64-Linux-SM-CNC.Application.PLC_PRG.setPoint");
        currentSp = this.source.getAsDouble();
        if(!ignoreInitial){
            WriteToNode.writeByClient(client,id,(float)currentSp);
        }
        double temp = 0;
        while(true){
            try{
                Thread.sleep(1000);
                temp = this.source.getAsDouble();
                if(temp!=currentSp){
                    currentSp = temp;
                    WriteToNode.writeByClient(client,id,(float)currentSp);
                }
            }catch(Exception ex){
                logger.error("Monitor interval happens error when sleep for 300ms");
            }
        }
    }
}
