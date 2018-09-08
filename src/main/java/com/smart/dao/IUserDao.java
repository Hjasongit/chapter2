package com.smart.dao;

import com.smart.domain.User;

public interface IUserDao extends ICommonDao{
    /**
     * 验证登录
     * @param userName
     * @param password
     * @return
     */
    public int getMatchCount(String userName, String password);

    /**
     * 根据用户名获取用户类
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName);

    /**
     * 更新用户登录信息
     * @param user
     */
    public void updateLoginInfo(User user);
}
