package time.store.domain;

public class CartItem {
	private Product product;  // 里面包含商品的id 商品的name 商品的价格   提高了代码的复用两
	private int num;           // 用来记录购买商品的数量
	private double totalItem;  // 这类商品的总价
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotalItem() {
		return product.getShop_price() * num;
	}
	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}
	
}
