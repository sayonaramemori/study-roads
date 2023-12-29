package mySpring.test.entity;

import java.time.LocalDateTime;

public class Record
{
    private Integer id;
    private Double temperature;
    private LocalDateTime insertTime;

    public Record(){}

    public Record(Integer id, Double sp, LocalDateTime time){
        this.id = id;
        this.temperature = sp;
        this.insertTime = time;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer Id){
        this.id = Id;
    }

    public Double getTemperature(){
        return this.temperature;
    }

    public void setTemperature(Double sp){
        this.temperature = sp;
    }

    public LocalDateTime getInsertTime(){
        return this.insertTime;
    }

    public void setInsertTime(LocalDateTime time){
        this.insertTime = time;
    }
}

