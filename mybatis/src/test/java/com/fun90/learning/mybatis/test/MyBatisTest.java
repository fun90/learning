package com.fun90.learning.mybatis.test;

import com.fun90.learning.mybatis.io.Resources;
import com.fun90.learning.mybatis.session.SqlSession;
import com.fun90.learning.mybatis.session.SqlSessionFactory;
import com.fun90.learning.mybatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    /**
     * 传统DAO方式调用
     */
    @Test
    public void sessionTest() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User();
        user.setId(1L);
        user.setUsername("tom");
        User tom = sqlSession.selectOne("com.fun90.learning.mybatis.test.UserMapper.selectOne", user);
        System.out.println(tom);

        List<User> users = sqlSession.selectList("com.fun90.learning.mybatis.test.UserMapper.selectList");
        System.out.println(users);
    }

    /**
     * 代理模式调用
     */
    @Test
    public void mapperTest() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User();
        user.setId(1L);
        user.setUsername("tom");
        //代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User tom = userMapper.selectOne(user);
        System.out.println(tom);

        List<User> users = userMapper.selectList();
        System.out.println(users);
    }

}
