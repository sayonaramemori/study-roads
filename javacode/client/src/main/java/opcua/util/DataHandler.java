package opcua.util;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.dtd.DataTypeDictionarySessionInitializer;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import java.util.Map;
import java.util.HashMap;
import opcua.mybatis.pojo.*;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import opcua.mybatis.logger.*;


public class DataHandler {
    private static String valName = "temp";
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    }

    public static void handleStruct(UaMonitoredItem item,DataValue dv){
        ExtensionObject xo= (ExtensionObject) dv.getValue().getValue();
        Object value = xo.decode(item.getClient().getDynamicSerializationContext());
        HashMap<String,String> resultSet = ParseStruct.parse(value.toString());

        int quality = 0;
        if(dv.getStatusCode().isGood())quality = 192;
        else quality = 32;
        // res.put("quality",quality.toString());
        // res.put("Temperature",res.get("temp"));
        // res.put("insert_time",dv.getSourceTime().toString());

        DataRecord res = new DataRecord();
        LocalDateTime time = convertToLocalDateTimeViaInstant(dv.getSourceTime().getJavaDate());
        res.setInsert_time(time);
        res.setQuality(quality);
        res.setTemperature(Double.parseDouble(resultSet.get(valName)));
        System.out.println(res);
        logTemperature.log(res);
    }
}
