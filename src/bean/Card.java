package bean;

public class Card {

	//クレカ番号
	private String cardNumber;

	//有効期限
	private String cardExpiryDate;

	//セキュリティ番号
	private String cardCvc;

	//クレカ名義
	private String cardName;

	//ゲッターセッター
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getCardCvc() {
		return cardCvc;
	}

	public void setCardCvc(String cardCvc) {
		this.cardCvc = cardCvc;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


}
