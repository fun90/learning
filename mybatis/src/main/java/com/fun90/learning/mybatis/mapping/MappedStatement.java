package com.fun90.learning.mybatis.mapping;

/**
 * 存放sql配置
 */
public class MappedStatement {
    /**
     * 1.唯一标识 (statementId namespace.id)
     */
    private String statementId;

    /**
     * 2.返回结果类型
     */
    private String resultType;

    /**
     * 3.参数类型
     */
    private String parameterType;

    /**
     * 4.要执行的sql语句
     */
    private String sql;

    /**
     * 5.mapper代理:sqlCommandType
     */
    private String sqlCommandType;

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(String sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }
}
