package opcua.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import opcua.mybatis.pojo.*;
import opcua.mybatis.mapper.*;

import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;;

public class testMybatis {
    public static void test() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession(true);

        // List<DataRecord> res = session.selectList("test.selectLast");
        RecordMapper rm = session.getMapper(RecordMapper.class);
        //DataRecord res = rm.selectById(10000);
        // DataRecord temp = new DataRecord(666.66,LocalDateTime.now(),199);
        // rm.insert(temp);
        // System.out.println(temp.getId());
        SetPoint res = rm.selectIns();
        System.out.println(res);

        session.close();
    }

}

