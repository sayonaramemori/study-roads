package mySpring.test.service;
import mySpring.test.entity.Temperature;

public interface TempService{
default public Temperature findLast(){
        return new Temperature();
    }
}

