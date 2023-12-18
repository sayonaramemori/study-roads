package mySpring.test.entity;

public class Result<T>
{
    public Result(){

    }
    public Result(Integer code,String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static<T> Result<T> success(T data){
        return new Result<T>(1,"OK",data);
    }
    public static Result success(){
        return new Result(1,"OK",null);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
    public Integer code;
    public String msg;
    public T data;
}
