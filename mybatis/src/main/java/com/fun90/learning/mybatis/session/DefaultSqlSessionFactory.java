package com.fun90.learning.mybatis.session;

import com.fun90.learning.mybatis.executor.Executor;
import com.fun90.learning.mybatis.executor.SimpleExecutor;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Executor executor = new SimpleExecutor();
        return new DefaultSqlSession(configuration, executor);
    }

}
