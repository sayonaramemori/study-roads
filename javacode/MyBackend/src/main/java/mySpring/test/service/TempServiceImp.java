package mySpring.test.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mySpring.test.entity.Temperature;
import mySpring.test.service.*;
import mySpring.test.mapper.*;

@Service
public class TempServiceImp implements TempService{

    @Autowired
    private TemperatureMapper rm;

    @Override
    public Temperature findLast(){
        return rm.findLast();
    }
}



