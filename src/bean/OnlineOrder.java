package bean;

import java.sql.Timestamp;

public class OnlineOrder {

	//注文ID
	private String orderId;

	//商品名
	private String productName;

	//ユーザ名
	private String userName;

	private Product product;

	//住所
	private String address;

	//注文日時
	private Timestamp orderTime;

	//選択個数
	private int count;

	//受取済み確認
	private boolean receive;

	//サブスク登録者確認
	private boolean subscription;

	//モバイルオーダー判断
	private boolean mobile;

	//ゲッターセッター

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
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

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}