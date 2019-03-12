package time.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import time.store.dao.UserDao;
import time.store.domain.UserBean;
import time.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void userRegist(UserBean userBean) throws SQLException {
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {userBean.getUid(),userBean.getUsername(),userBean.getPassword(),userBean.getName(),
					userBean.getEmail(),userBean.getTelephone(),userBean.getBirthday(),userBean.getSex(),
					userBean.getState(),userBean.getCode()};
		qr.update(sql, params);
		
	}

	@Override
	public UserBean findByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from user where code = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<UserBean>(UserBean.class),code);
	}

	@Override
	public void update(UserBean userBean) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update user set username=? , password=? ,name=? ,email=? ,"
				+ "telephone=?, birthday=?, sex=?, state=?, code=? where uid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {userBean.getUsername(),userBean.getPassword(),userBean.getName(),
					userBean.getEmail(),userBean.getTelephone(),userBean.getBirthday(),userBean.getSex(),
					userBean.getState(),userBean.getCode(),userBean.getUid()};
		qr.update(sql, params);
	}

	@Override
	public UserBean login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = ? and password = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());				
		return  qr.query(sql, new BeanHandler<UserBean>(UserBean.class),username,password);
		
	} 

}
