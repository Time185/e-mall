package time.store.dao;

import java.sql.SQLException;

import time.store.domain.UserBean;

public interface UserDao {

	void userRegist(UserBean userBean) throws SQLException;

	UserBean findByCode(String code) throws SQLException ;

	void update(UserBean user) throws SQLException;

	UserBean login(String username, String password) throws SQLException;

}
