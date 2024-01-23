package opcua.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {
    private static String resource;
    private static SqlSessionFactory sf;
    private static InputStream inputStream;
    private static Logger logger = LoggerFactory.getLogger(SessionManager.class);

    static {
        resource = "mybatis-config.xml";
        try{
            inputStream = Resources.getResourceAsStream(resource);
        }catch(Exception ex){
            logger.error("Cannot find the config file for mybatis: {}",ex.getMessage());
            System.exit(-1);
        }
        sf = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream = null;
    }

    public static SqlSession getSession(boolean autoCommit){
        return sf.openSession(autoCommit);
    } 

    public static void closeSession(SqlSession se){
        se.close();
    }
}
