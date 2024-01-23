package opcua.util;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDateTime;

public class CommonUtil{
    //Parse Struct Node and Extract the name and value as key and value
    public static HashMap<String,String> parseStruct(String msg){
        int point = 0;
        int end = msg.indexOf('}', point);
        int begin = msg.substring(point, end).lastIndexOf('{', end);
        point = end + 1;

        //Extract the inner {}
        ArrayList<String> temp = newArrayList();
        while (begin!=-1) {
            temp.add(msg.substring(begin+1,end));
            end = msg.indexOf('}', point);
            begin = msg.indexOf('{',point);
            point = end + 1;
        }
        //Transform them to map
        //key - variable name in codesys
        //val - value in String format
        HashMap<String,String> map = new HashMap<>();
        for(int i=0;i<temp.size();++i){
            String item = temp.get(i);
            int start = item.indexOf(",");
            map.put(item.substring(item.indexOf("=")+1,start),item.substring(item.indexOf("=",start)+1));
        }
        // System.out.println(map);
        return map;
    }    

    //Convert Date to LocalDateTime;
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    }

}
