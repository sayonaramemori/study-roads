package opcua.mybatis.pojo;

public class SetPoint {
    private double sp;
    public SetPoint(){}
    public String toString(){
        return "sp is :" + sp;
    }
    public SetPoint(double sp){
        this.sp = sp;
    }
    public double getSp(){
        return sp;
    }
    public void setSp(double sp){
        this.sp = sp;
    }
}
