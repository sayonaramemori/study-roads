package mySpring.test.entity;

import java.time.LocalDateTime;

public class Record
{
    private Integer id;
    private Double sp;
    private LocalDateTime time;

    public Record(){}

    public Record(Integer id, Double sp, LocalDateTime time){
        this.id = id;
        this.sp = sp;
        this.time = time;
    }

    public String toString(){
        String res = "id " + this.id +"\n";
        res += "sp " + this.sp +"\n";
        res += "time " + this.time + "\n";
        return res;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer Id){
        this.id = Id;
    }

    public Double getSp(){
        return this.sp;
    }

    public void setSp(Double sp){
        this.sp = sp;
    }

    public LocalDateTime getTime(){
        return this.time;
    }

    public void setTime(LocalDateTime time){
        this.time = time;
    }
}

