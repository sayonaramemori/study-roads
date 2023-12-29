package mySpring.test.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mySpring.test.entity.Record;
import mySpring.test.entity.*;
import mySpring.test.service.*;
import mySpring.test.mapper.*;

import java.util.ArrayList;

@Service
public class RecordServiceImp implements RecordService{

    @Autowired
    private RecordMapper rm;

    @Override
    public Record findLast(){
        return rm.findLast();
    }

    @Override
    public Instruction findLastSp(){
        return rm.findLastSp();
    }

    @Override
    public User findUser(String name, String pass){
        return rm.findUser(name,pass);
    }

    @Override
    public ArrayList<Record> findLastRecords(Integer num){
        return rm.findLastRecords(num);
    }
}


