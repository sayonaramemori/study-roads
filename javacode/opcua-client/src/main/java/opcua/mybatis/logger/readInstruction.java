package opcua.mybatis.logger;
import opcua.mybatis.SessionManager;
import opcua.mybatis.pojo.*;
import opcua.mybatis.mapper.*;
import org.apache.ibatis.session.SqlSession;

public class readInstruction{
    private static SqlSession session;
    private static RecordMapper rm;
    static{
        session = SessionManager.getSession(true);
        rm = session.getMapper(RecordMapper.class);
    }
    public static double readSp(){
        SetPoint res = rm.selectIns();
        return res.getSp();
    }
}