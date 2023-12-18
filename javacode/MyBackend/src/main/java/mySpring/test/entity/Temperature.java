package mySpring.test.entity;

import java.time.LocalDateTime;

public class Temperature
{
    private Long ID;
    private Double pidOutput;
    private LocalDateTime time;
    private Double temperature;
    public void setTemperature(Double val){
        this.temperature = val;
    }
    public Double getTemperature(){
        return this.temperature;
    }
    public LocalDateTime getTime(){
        return this.time;
    }
    public void setTime(LocalDateTime time){
        this.time = time;
    }
    
    public void setPidOutput(Double pid){
        this.pidOutput = pid;
    }

    public Double getPidOutput(){
        return this.pidOutput;
    }

    public Temperature(){

    }

    @Override
    public String toString(){
        String res = "ID " + this.ID + " \n";;
        res += "Pid " + pidOutput+ " \n";;
        res += "Time " + time + " \n";
        res += "Temp " + temperature + " \n";
        return res;
    }

    public Temperature(Long id, Double pidOutput, LocalDateTime time, Double temperature){
        this.ID = id;
        this.pidOutput = pidOutput;
        this.time = time;
        this.temperature = temperature;
    }
    public Long getID(){
        return this.ID;
    }
    public void setID(Long ID){
        this.ID = ID;
    }
}
