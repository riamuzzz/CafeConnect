package bean;

public class Cart {

	//ユーザ
	private User user;

	//商品
	private Product product;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
