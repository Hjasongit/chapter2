package com.smart.service;

import com.smart.domain.User;

public interface IUserService extends ICommonService{
    /**
     * 验证密码
     * @param userName
     * @param password
     * @return
     */
    public boolean hasMatchUser(String userName, String password);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName);

    /**
     * 用户登录
     * @param user
     */
    public void loginSuccess(User user);
}
