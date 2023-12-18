package mySpring.test.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mySpring.test.entity.Record;
import mySpring.test.service.*;
import mySpring.test.mapper.*;

@Service
public class RecordServiceImp implements RecordService{

    @Autowired
    private RecordMapper rm;

    @Override
    public Record findById(Integer id){
        return rm.findById(id);
    }
}


