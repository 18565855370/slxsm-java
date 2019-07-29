package com.slxsm.mybatis.interceptor;

import com.alibaba.fastjson.JSON;
import com.slxsm.mybatis.dao.TeamMapper;
import com.slxsm.mybatis.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TeamSqlOperatorTest {

    @Autowired
   /* private TeamMapper mapper;*/

    @Test
    public void queryTestInfos(){
        try {
            String resource = "com/slxsm/mybatis/mybatis/mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();
            try {
                TeamMapper mapper = session.getMapper(TeamMapper.class);
                List<Team> teams = mapper.queryTeamInfos();
                session.commit();
                log.info(JSON.toJSONString(teams));
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
