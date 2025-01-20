package bean;

import java.io.Serializable;

public class User extends Auth implements Serializable{

	//ユーザID
	private int userId;

	//パスワード
	private String userPassword;

	//ユーザ氏名
	private String userName;

	//メールアドレス
	private String email;

	//住所
	private String address;

	//電話番号
	private String tel;

	//クレカ
	private Card card;

	//サブスク入会可否
	private boolean subscription;

	//ゲッターセッター
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}




}
