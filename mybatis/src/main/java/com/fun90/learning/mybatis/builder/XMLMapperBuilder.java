package com.fun90.learning.mybatis.builder;

import com.fun90.learning.mybatis.mapping.MappedStatement;
import com.fun90.learning.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析XML中的SQL配置---->mappedStatement，并存储到Configuration
     *
     * @param resourceAsSteam
     */
    public void parse(InputStream resourceAsSteam) throws DocumentException {
        Document document = new SAXReader().read(resourceAsSteam);
        Element rootElement = document.getRootElement();

        List<Element> selectList = rootElement.selectNodes("//select");
        String namespace = rootElement.attributeValue("namespace");
        for (Element element : selectList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            // 封装mappedStatement对象
            MappedStatement mappedStatement = new MappedStatement();
            // StatementId:namespace.id
            String statementId = namespace + "." + id;
            mappedStatement.setStatementId(statementId);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            mappedStatement.setSqlCommandType("select");

            // 将封装好的mappedStatement封装到configuration中的map集合中
            configuration.getMappedStatementMap().put(statementId, mappedStatement);
        }
    }
}
