package time.store.domain;

public class OrderItem {
	private String itmid;
	private int quantity;
	private double total;	
	private Product product;
	private Order order;
	public String getItmid() {
		return itmid;
	}
	public void setItmid(String itmid) {
		this.itmid = itmid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
