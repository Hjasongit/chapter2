package com.smart.dao;

import com.smart.domain.LoginLog;

public interface ILoginLogDao extends ICommonDao{
    /**
     * 日志写入
     * @param loginLog
     */
    public void insertLoginLog(LoginLog loginLog);
}
