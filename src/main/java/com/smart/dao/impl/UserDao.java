package com.smart.dao.impl;

import com.smart.dao.IUserDao;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao extends CommonDao implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM t_user " +
            " WHERE user_name = ? AND password = ? ";
    private final static String FIND_USER_SQL = "SELECT user_id, credits FROM t_user " +
            " WHERE user_name = ? ";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET " +
            " last_visit = ?,last_ip = ?,credits = ? WHERE user_id = ? ";

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 根据用户名获取用户类
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(FIND_USER_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    /**
     * 更新用户登录信息
     *
     * @param user
     */
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
