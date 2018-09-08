package com.smart.service.impl;

import com.smart.dao.ILoginLogDao;
import com.smart.dao.IUserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import com.smart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl extends CommonService implements IUserService {
    private ILoginLogDao loginLogDao;
    private IUserDao userDao;

    @Autowired
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(ILoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    /**
     * 验证密码
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 用户是否允许登录
     */
    public boolean hasMatchUser(String userName, String password) {
        return userDao.getMatchCount(userName, password) > 0;
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    /**
     * 用户登录
     *
     * @param user 用户对象
     */
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(user.getCredits() + 5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(new Date());
        loginLog.setIp(user.getLastIp());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
