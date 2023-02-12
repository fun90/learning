package com.fun90.learning.mybatis.session;

import com.fun90.learning.mybatis.executor.Executor;
import com.fun90.learning.mybatis.mapping.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.query(configuration, mappedStatement, params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<T> list = executor.query(configuration, mappedStatement, params);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("selectOne期望只返回一条结果，实际上返回了多条");
        } else {
            return null;
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {

        // 使用JDK动态代理生成基于接口的代理对象
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            /*
                Object:代理对象的引用，很少用
                Method:被调用的方法的字节码对象
                Object[]:调用的方法的参数
             */
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                // 具体的逻辑 :执行底层的JDBC
                // 通过调用sqlSession里面的方法来完成方法调用
                // 参数的准备: 1.statementId: UserMapper.selectList
                // 问题1:无法获取现有的statementId
                // selectList
                String methodName = method.getName();
                // UserMapper
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                // 方法调用:问题2:要调用sqlSession中增删改查的什么方法呢?
                // 改造当前工程:sqlCommandType
                MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
                // select  update delete insert
                String sqlCommandType = mappedStatement.getSqlCommandType();
                switch (sqlCommandType) {
                    case "select":
                        // 问题3:该调用selectOne 还是 selectList
                        // 判断返回值类型
                        Class<?> returnType = method.getReturnType();
                        boolean assignableFrom = Collection.class.isAssignableFrom(returnType);
                        if (assignableFrom) {
                            return selectList(statementId, objects);
                        }
                        return selectOne(statementId, objects);
                    case "update":
                        // 执行更新方法调用
                        break;
                    case "delete":
                        // 执行delete方法调用
                        break;
                    case "insert":
                        // 执行insert方法调用
                        break;
                }
                return null;
            }
        });
        return (T) proxyInstance;

    }
}
