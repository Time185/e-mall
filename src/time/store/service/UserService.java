package time.store.service;

import java.sql.SQLException;

import time.store.domain.UserBean;

public interface UserService {

	void userRegist(UserBean userBean) throws SQLException;

	boolean activeUser(String code) throws SQLException;

	UserBean login(String username,String password) throws SQLException;

}
