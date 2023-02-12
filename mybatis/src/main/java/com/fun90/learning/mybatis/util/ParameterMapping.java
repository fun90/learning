package com.fun90.learning.mybatis.util;

/**
 * 参数映射类（SQL参数映射类，存储#{}、${}中的参数名）
 */
public class ParameterMapping {

    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}