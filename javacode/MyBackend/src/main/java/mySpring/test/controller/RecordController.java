package mySpring.test.controller;

import mySpring.test.service.*;
import mySpring.test.entity.Record;
import mySpring.test.entity.Instruction;
import mySpring.test.entity.Result;
import mySpring.test.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class RecordController{

    @Autowired
    private RecordService rs;

    @Autowired
    private UpdateService us;

    @PostMapping("/login")
    public Result login(String user, String passwd){
       User res = rs.findUser(user,passwd);
       if(res!=null)
           return Result.success();
       else
           return Result.error("user or passwd error");
    }

    @RequestMapping("/findLastSp")
    public Result<Instruction> findLastSp(){
        return Result.success(rs.findLastSp());
    }

    @RequestMapping("/findLast")
    public Result<Record> findLast(){
        return Result.success(rs.findLast());
    }

    @RequestMapping("/findLastRecords")
    public Result<ArrayList<Record>> findLastRecords(Integer num){
        if(num==0)return Result.success(new ArrayList<Record>());
        else return Result.success(rs.findLastRecords(num));
    }

    @RequestMapping("/set")
    public Result set(Double sp){
        if(sp>0&&sp<=100){
            us.updateSp(sp);
            return Result.success();
        }
        else{
            return Result.error("bad range");
        }
    }
}

