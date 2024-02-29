<<<<<<< HEAD
package opcua.mybatis.mapper;
import opcua.mybatis.pojo.*;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;


public interface RecordMapper {
   List<DataRecord> selectLast();
   DataRecord selectById(@Param("ID") long id);
   //2. parameters can be object, and the sign in xml should be the same as the field in the obj. 
   //3. Map is also ok.

   void insert(DataRecord data);

   //Close cache
   @Options(flushCache = Options.FlushCachePolicy.TRUE)
   @Select("select * from instruction limit 1;")
   SetPoint selectIns();
}
=======
package opcua.mybatis.mapper;
import opcua.mybatis.pojo.*;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;


public interface RecordMapper {
   List<DataRecord> selectLast();
   DataRecord selectById(@Param("ID") long id);
   //2. parameters can be object, and the sign in xml should be the same as the field in the obj. 
   //3. Map is also ok.

   void insert(DataRecord data);

   //Close cache
   @Options(flushCache = Options.FlushCachePolicy.TRUE)
   @Select("select * from instruction limit 1;")
   SetPoint selectIns();
}
>>>>>>> 9635c473ca24ad51e6da34a1359f80e1e58d40a7
