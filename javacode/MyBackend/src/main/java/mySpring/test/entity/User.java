package mySpring.test.entity;
import java.time.LocalDateTime;

public class User{
   private Integer id;
   private String userName;
   private String password;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
   public User(){

  }
  public void setId(Integer id){
      this.id = id;
  }
 public Integer getId(){
      return this.id;
  }
  public String getUserName(){
      return this.userName;
  }
  public void setUserName(String name){
      this.userName = name;
  }
  public String getPassword(){
      return this.password;
  }
 public void setPassword(String pass){
      this.password = pass;
  }
  public LocalDateTime getCreateTime(){
      return this.createTime;
  }
  public LocalDateTime getUpdateTime(LocalDateTime time){
      return this.updateTime;
  }
  public void setCreateTime(LocalDateTime time){
      this.createTime = time;
  }
  public void setUpdateTime(LocalDateTime time){
      this.updateTime = time;
  }

}
