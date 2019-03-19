package time.store.service.ServiceImp;

import java.sql.SQLException;

import time.store.dao.ProductDao;
import time.store.dao.daoImp.ProductDaoImp;
import time.store.domain.CartItem;
import time.store.domain.Product;
import time.store.service.CartService;

public class CartServiceImp implements CartService {

	@Override
	public CartItem findCartItemById(String pid, String num1) throws SQLException {
		// 将num 转换为整型
		int num = Integer.parseInt(num1);
		// 通过pid来获取商品信息  product 
		ProductDao dao = new ProductDaoImp();
		Product product = dao.findProductById(pid);
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		System.out.println("num=" + num);
		cartItem.setNum(num);
		cartItem.setTotalItem(product.getShop_price()*num);
		return cartItem;
	}

}
