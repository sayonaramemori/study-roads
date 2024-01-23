package opcua.mybatis.pojo;

import java.time.LocalDateTime;
import java.util.Date;

public class DataRecord {
    public long id;
    public double temperature;
    public LocalDateTime insert_time;
    public int quality;
    public DataRecord(){}
    public DataRecord(double temperature, LocalDateTime time,int quality){
        this.temperature = temperature;
        this.insert_time = time;
        this.quality = quality;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInsert_time(LocalDateTime insert_time) {
        this.insert_time = insert_time;
    }
    public void setQuality(int quality) {
        this.quality = quality;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public long getId() {
        return id;
    }
    public LocalDateTime getInsert_time() {
        return insert_time;
    }
    public int getQuality() {
        return quality;
    }
    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "{id:" + id +" temp:" + temperature + " time:" + insert_time +" quality:" + quality +"}\n";
    }
}
