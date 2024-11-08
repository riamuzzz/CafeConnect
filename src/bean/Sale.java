package bean;

public class Sale {

	//商品
	private Product product;

	//注文
	private Order order;

	//ゲッターセッター
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
