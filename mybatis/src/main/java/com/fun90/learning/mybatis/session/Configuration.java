package com.fun90.learning.mybatis.session;

import com.fun90.learning.mybatis.mapping.MappedStatement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放核心配置文件解析的内容
 */
public class Configuration {
    /**
     * 数据源对象
     */
    private DataSource dataSource;

    /**
     * map : key :statementId value : 封装好的MappedStatement
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
