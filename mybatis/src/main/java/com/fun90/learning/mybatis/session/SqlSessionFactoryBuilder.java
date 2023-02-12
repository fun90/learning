package com.fun90.learning.mybatis.session;

import com.fun90.learning.mybatis.builder.XMLConfigBuilder;
import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);

        return new DefaultSqlSessionFactory(configuration);
    }

}
