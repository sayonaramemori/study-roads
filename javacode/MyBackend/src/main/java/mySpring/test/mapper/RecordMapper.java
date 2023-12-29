package mySpring.test.mapper;
import java.util.ArrayList;

import mySpring.test.entity.*;
import mySpring.test.entity.Record;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper{
    @Select("select * from sp_log where id = (select max(id) from sp_log)")
    public Instruction findLastSp();

    @Update("update instruction set sp = #{sp}")
    public void updateSp(Double sp);

    @Update("insert into sp_log (sp,operation_time) values(#{sp},now())")
    public void updateSpLog(Double sp);

    @Select("select * from TemperatureRecord where id = (select max(id) from TemperatureRecord)")
    public Record findLast();

    @Select("select * from user where user_name = #{name} and password = #{pass}")
    public User findUser(String name, String pass);

    @Select("(SELECT * FROM TemperatureRecord ORDER BY id DESC LIMIT #{num}) order by id")
    public ArrayList<Record> findLastRecords(Integer num);
}





