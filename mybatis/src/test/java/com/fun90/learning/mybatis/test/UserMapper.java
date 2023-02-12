package com.fun90.learning.mybatis.test;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有用户
     */
    List<User> selectList() throws Exception;

    /**
     * 根据条件进行用户查询
     */
    User selectOne(User user) throws Exception;

}