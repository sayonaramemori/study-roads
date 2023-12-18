package mySpring.test.mapper;

import mySpring.test.entity.Temperature;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TemperatureMapper{
    @Select("select * from temp_restriction where ID = (select max(ID) from temp_restriction)")
    public Temperature findLast();
}





