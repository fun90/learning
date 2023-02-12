package com.fun90.learning.mybatis.executor;

import com.fun90.learning.mybatis.mapping.MappedStatement;
import com.fun90.learning.mybatis.session.Configuration;

import java.util.List;

public interface Executor {

    /**
     * 执行器中封装好jdbc操作
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
