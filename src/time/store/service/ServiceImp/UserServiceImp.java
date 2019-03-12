package time.store.service.ServiceImp;

import java.sql.SQLException;

import time.store.utils.UUIDUtils;
import time.store.dao.UserDao;
import time.store.dao.daoImp.UserDaoImp;
import time.store.domain.UserBean;
import time.store.service.UserService;

public class UserServiceImp implements UserService  {

	@Override
	public void userRegist(UserBean userBean) throws SQLException {
		
		// 给uid、state的默认值为0、code赋值
		userBean.setUid(UUIDUtils.getId());	
		userBean.setState(0);
		userBean.setCode(UUIDUtils.getCode());
		// 调用dao层，实现注册功能
		UserDao dao = new UserDaoImp();
		dao.userRegist(userBean);
	}
	
	// 用户用户激活自己的账号 
	@Override
	public boolean activeUser(String code) throws SQLException {
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImp();
		UserBean user = dao.findByCode(code);
		if(user != null) {
			// 说明激活码正确，可以激活
			// 需要将激活码设为空，state设为1
			user.setCode(null);
			user.setState(1);
			// 在数据库进行更新
			dao.update(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public UserBean login(String username,String password) throws SQLException {
		UserDao dao = new UserDaoImp();
		UserBean userBean = dao.login(username,password);
		if( null == userBean) {
			throw new RuntimeException("密码有误！");
		}else if(userBean.getState() == 0){
			throw new RuntimeException("用户未激活！");
		}else {
			return userBean;
		}
	}

}
