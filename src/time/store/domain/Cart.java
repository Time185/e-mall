package time.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	// 两个属性  三个方法
	Map<String, CartItem> map = new HashMap<String, CartItem>() ; // 用来存放购物列表中的每一个商品信息
	private double total = 0; // 总价
	
	
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		total = 0;
		// 获取到Map中所有的购物项
		Collection<CartItem> values = map.values();
		for (CartItem cartItem : values) {
			total += cartItem.getTotalItem();
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	// 添加购物车
	public void addCart(CartItem cartItem) {
		String pid = cartItem.getProduct().getPid();
		// 判断该类商品之前是否添加到购物车
		if(map.containsKey(pid)) {
			// 之前存在，那么更改该类商品的数量
			map.get(pid).setNum(map.get(pid).getNum() + cartItem.getNum());
		}else {
			// 之前不存在，直接加入购物车
			map.put(pid, cartItem);
		}
	}
	// 移除购物项
	public void removeCart(String pid) {
		map.remove(pid);
	}
	// 清空购物车
	public void clearCart() {
		map.clear();
	}
	// 返回cartItem数据
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
