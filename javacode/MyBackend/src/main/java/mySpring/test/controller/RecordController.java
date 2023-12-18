package mySpring.test.controller;

import mySpring.test.service.*;
import mySpring.test.entity.Record;
import mySpring.test.entity.Temperature;
import mySpring.test.entity.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin
public class RecordController{
    @Autowired
    private RecordService rs;

    @Autowired
    private UpdateService us;

    @Autowired
    private TempService ts;

    @RequestMapping("/findById")
    public Result<Record> findById(Integer id){
        return Result.success(rs.findById(id));
    }

    @RequestMapping("/findLast")
    public Result<Temperature> findLast(){
        return Result.success(ts.findLast());
    }

    @RequestMapping("/set")
    public Result set(Double sp){
        if(sp>0&&sp<150)us.updateSp(sp);
        else System.out.println("Bad temperature setting range");
        return Result.success();
    }
}

