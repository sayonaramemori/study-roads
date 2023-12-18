package mySpring.test.mapper;

import mySpring.test.entity.Record;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper{
    @Select("select * from sp_log where id = #{id}")
    public Record findById(Integer id);

    @Update("update instruction set temperature = #{sp}")
    public void updateSp(Double sp);
}





