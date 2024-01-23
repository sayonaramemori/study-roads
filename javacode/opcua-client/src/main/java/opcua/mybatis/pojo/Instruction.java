package opcua.mybatis.pojo;

import java.time.LocalDateTime;

public class Instruction {
    long id;
    double sp;
    LocalDateTime operation_time;
    String status;
    @Override
    public String toString() {
        return "id:" + id + " sp:" + sp + " operation_time:" + operation_time +" status:"+status;
    }
    public Instruction(){}
    public Instruction(long id,double sp,LocalDateTime time,String status){
        this.id = id;
        this.sp = sp;
        this.operation_time = time;
        this.status = status;
    }
    public long getId() {
        return id;
    }
    public LocalDateTime getOperation_time() {
        return operation_time;
    }
    public double getSp() {
        return sp;
    }
    public String getStatus() {
        return status;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setOperation_time(LocalDateTime operation_time) {
        this.operation_time = operation_time;
    }
    public void setSp(double sp) {
        this.sp = sp;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
