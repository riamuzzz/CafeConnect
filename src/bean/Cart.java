package bean;

public class Cart {

	//ユーザ
	private User user;

	//商品
	private Product product;

	//商品名
	private String productName;

	//値段
	private int price;

	//選択個数
	private int count;


	//ゲッターセッター
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
