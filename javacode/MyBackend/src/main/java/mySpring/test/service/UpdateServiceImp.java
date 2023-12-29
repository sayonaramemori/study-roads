package mySpring.test.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mySpring.test.entity.Record;
import mySpring.test.service.*;
import mySpring.test.mapper.*;

@Service
public class UpdateServiceImp implements UpdateService{

    @Autowired
    private RecordMapper rm;

    @Override
    public void updateSp(Double val){
        rm.updateSp(val);
        rm.updateSpLog(val);
    }

}


