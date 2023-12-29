package mySpring.test.entity;
import java.time.LocalDateTime;


public class Instruction
{
    private Integer id;
    private Double sp;
    private LocalDateTime operationTime;

    public Instruction(){}

    public Instruction(Integer id, Double sp, LocalDateTime time){
        this.id = id;
        this.sp = sp;
        this.operationTime = time;
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

    public LocalDateTime getOperationTime(){
        return this.operationTime;
    }

    public void setOperationTime(LocalDateTime time){
        this.operationTime = time;
    }

}
