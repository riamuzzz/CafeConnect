package bean;

public class CafeUser extends Auth{

	//カフェ店員ID
	private int cafeUserId;

	//カフェ店員パスワード
	private String cafeUserPassword;

	//カフェ店員氏名
	private String cafeUserName;

	//ゲッターセッター
	public int getCafeUserId() {
		return cafeUserId;
	}

	public void setCafeUserId(int cafeUserId) {
		this.cafeUserId = cafeUserId;
	}

	public String getCafeUserPassword() {
		return cafeUserPassword;
	}

	public void setCafeUserPassword(String cafeUserPassword) {
		this.cafeUserPassword = cafeUserPassword;
	}

	public String getCafeUserName() {
		return cafeUserName;
	}

	public void setCafeUserName(String cafeUserName) {
		this.cafeUserName = cafeUserName;
	}

}
