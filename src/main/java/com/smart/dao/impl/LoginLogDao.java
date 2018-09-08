package com.smart.dao.impl;

import com.smart.dao.ILoginLogDao;
import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao extends CommonDao implements ILoginLogDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_LOGINLOG_SQL = "INSERT INTO t_login_log(user_id, ip, login_datetime)";

    /**
     * 日志写入
     *
     * @param loginLog
     */
    public void insertLoginLog(LoginLog loginLog) {
        jdbcTemplate.update(INSERT_LOGINLOG_SQL, new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
    }
}
