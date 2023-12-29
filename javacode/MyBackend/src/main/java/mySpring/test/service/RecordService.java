package mySpring.test.service;


import java.util.ArrayList;
import mySpring.test.entity.Record;
import mySpring.test.entity.*;

public interface RecordService{
    default public Instruction findLastSp(){
        return new Instruction();
    }
    default public Record findLast(){
        return new Record();
    }
    default User findUser(String name,String pass){
        return new User();
    }
    default ArrayList<Record> findLastRecords(Integer num){
        return new ArrayList<Record>();
    }
}


