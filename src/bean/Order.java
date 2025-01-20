package bean;

import java.sql.Timestamp;

public class Order {

	//注文ID
	private String orderId;

	//商品
	private Product product;

	//ユーザ
	private User user;

	//注文日時
	private Timestamp orderTime;

	//モバイルオーダー判断
	private boolean mobile;

	//選択個数
	private int count;

	//受取済み確認
	private boolean receive;

	//サブスク登録者確認
	private boolean subscription;

	//ゲッターセッター
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}


}
