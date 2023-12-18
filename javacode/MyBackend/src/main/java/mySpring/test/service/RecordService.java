package mySpring.test.service;

import mySpring.test.entity.Record;

public interface RecordService{
    default public Record findById(Integer id){
        return new Record();
    }
}


